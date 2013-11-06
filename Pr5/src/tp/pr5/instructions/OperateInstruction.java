package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;


/**
 * The Instruction for using an item. This Instruction works if the user writes OPERATE id or OPERAR id.
 * @author David Garcia Alvarez and Javier Toledano Regaño.
 *
 */
public class OperateInstruction implements Instruction {
	private String item;
	private Item it;
	private RobotEngine robot;
	private ItemContainer mochila;
	private NavigationModule mapa;
	
	public OperateInstruction() {}
	
	public OperateInstruction(String s)
	{
		item=s;
	}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Drop.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Operate" or "Operar".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		OperateInstruction a=new OperateInstruction();
		if (word.length==2)
			{
				if (word[0].equalsIgnoreCase("OPERATE") || word[0].equalsIgnoreCase("OPERAR"))
				{
					a.item=word[1];
					return a;
				}
				else 
					throw new WrongInstructionFormatException();
			}
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	OPERATE|OPERAR  <id>";
	 */
	@Override
	public String getHelp() {
		return "	OPERATE|OPERAR  <id>";
	}
	/**
	 * 
	 * @param engine The robot engine
	 * @param navigation The information about the game, i.e., the places, current direction and current heading to navigate
	 * @param robotContainer  The inventory of the robot
	 * 
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container) even though the actual implementation of execute() may not require it.
	 */
	@Override
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		this.robot=engine;
		this.mochila=robotContainer;
		this.mapa=navigation;
		
	}
	/**
	 * Execute the instruction operate.
	 * @throws InstructionExecutionException when the item doesn't exist in the robotContainer or if the robot can't be used the item.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		it= this.mochila.pickItem(this.item);
		if (it!=null)
		{
			if(!it.use(this.robot, this.mapa))
				{
					if (it.canBeUsed()==true)
					{
						this.mochila.addItem(it);
					}
					throw new InstructionExecutionException("WALL·E says: I have problems using the object "+ this.item);
				}
			if (it.canBeUsed()==true)
			{
				this.mochila.addItem(it);
				robot.setdiceRobotGrafica("WALL·E says: I used " + it.getId());
				robot.setdiceRobotConsola("WALL·E says: I used " + it.getId());
			}
			else 
				{
					robot.setdiceRobotGrafica("WALL·E says: What a pity! I have no more "+ it.getId() + " in my inventory");
					robot.setdiceRobotConsola("WALL·E says: What a pity! I have no more "+ it.getId() + " in my inventory");
				}
		}
		else throw new InstructionExecutionException("WALL·E says: I have problems using the object "+ this.item);
		
	}

	/**
	 * Unexecute one OperateInstruction.
	 */
	@Override
	public void unExecute() {
		mochila.pickItem(it.getId());// saco el item dela mochila si es que esta.
		it.unUse(robot, mapa);
		mochila.addItem(it);
		robot.setdiceRobotGrafica("WALL·E says: UnOperateInstruction realized");
	}
	/**
	 * this method return if OperateInstrction can do unexecute.
	 * @return always return true.
	 */
	@Override
	public boolean canUnExecute() {
		return true;
	}

	/**
	 * this method return if OperateIntruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
}
