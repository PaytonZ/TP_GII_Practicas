package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;


/**
 * This instruction tries to pick an Item from the current place and puts it the robot inventory.
 *  This instruction works if the user writes PICK id or COGER id
 *  @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class PickInstruction implements Instruction {
	private String item;
	private NavigationModule mapa;
	private ItemContainer mochila;
	private RobotEngine robot;
	
	public PickInstruction(){}
	
	public PickInstruction(String it)
	{
		item=it;
	}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Pick.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Pick" or "Coger".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		PickInstruction a= new PickInstruction();
		if (word.length==2)
			{
				if (word[0].equalsIgnoreCase("PICK") || word[0].equalsIgnoreCase("COGER"))
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
	 * @return String with "	PICK|COGER  <id>";
	 */
	@Override
	public String getHelp() {
		return "	PICK|COGER  <id>";
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
		this.mochila=robotContainer;
		this.mapa=navigation;
		this.robot=engine;
		
	}
	/**
	 * Execute the instruction drop.
	 * @throws InstructionExecutionException when the item doesn't exist in the place or if the robot have this item.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		Item it= mapa.cogerItemDeLaPlaza(item);
		if (it==null)
		{
			throw new InstructionExecutionException("WALL·E says: Ooops, this place has not the object "+ this.item);
		}
		else
		{
			if (this.mochila.buscador(it.getId())==-1)
			{
				this.robot.anadirItemALaMochila(it,mochila);
				robot.setdiceRobotGrafica("WALL·E says: I am happy! Now I have "+ it.getId());
				robot.setdiceRobotConsola("WALL·E says: I am happy! Now I have "+ it.getId());
			}
			else
				throw new InstructionExecutionException("WALL·E says: I am stupid! I had already the object "+ this.item);
		}
		
	}
	/**
	 * Unexecute one PickInstruction.
	 */
	@Override
	public void unExecute() {
		Item it= this.mochila.pickItem(this.item);
		mapa.dropItemAtCurrentPlace(it);
		robot.setdiceRobotGrafica("WALL·E says: UnPickInstruction realized");
	}

	/**
	 * this method return if PickInstruction can do unexecute.
	 * @return always return true.
	 */
	@Override
	public boolean canUnExecute() {
		return true;
	}

	/**
	 * this method return if PickIntruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
}
