package tp.pr4.instructions;


import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;


/**
 * This instruction drops an Item from the current place and puts it the robot inventory. 
 * This instruction works if the user writes DROP or SOLTAR
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class DropInstruction implements Instruction {
	private NavigationModule navegador;
	private ItemContainer robotmochila;
	private RobotEngine robot;
	private String item;

	/**
	 * Default constructor
	 */
	public DropInstruction(){} 
	/**
	 * Constructor with one param.
	 * @param it the id of the item to drop
	 */
	public DropInstruction(String it)
	{
		item=it;
	}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Drop.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Drop" or "Soltar".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		DropInstruction a = new DropInstruction();
		if (word.length==2)
			{
				if (word[0].equalsIgnoreCase("DROP") || word[0].equalsIgnoreCase("SOLTAR"))
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
	 * @return String with "	DROP|SOLTAR  <id>";
	 */
	@Override
	public String getHelp() {
		return "	DROP|SOLTAR  <id>";
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
		this.navegador=navigation;
		this.robotmochila=robotContainer;
		robot=engine;
		
	}

	/**
	 * Execute the instruction drop.
	 * @throws InstructionExecutionException when the item doesn't exist in the robotContainer or the item exist in the place.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		Item it= this.robotmochila.pickItem(this.item);
		if (it== null)
		{
			throw new InstructionExecutionException("You do not have any " + this.item+".");
		}
		else 
		{
			if ( this.navegador.getCurrentPlace().getObj().buscador(this.item)==-1)
			{
				this.navegador.dropItemAtCurrentPlace(it);
				
			}
			else throw new InstructionExecutionException("The item " + this.item + " already exist in this place");
		}
		
	}
	
	/**
	 * This method give you the id of the item to drop.
	 * @return the id of the item to drop.
	 */
	public String getItem()
	{
		return this.item;
	}
	
	/**
	 * Unexecute one DropInstruction.
	 */
	@Override
	public void unExecute() {
		Item it= this.navegador.getCurrentPlace().pickItem(this.item);
		robot.anadirItemALaMochila(it,robotmochila);
		
		
	}

	/**
	 * this method return if DropInstrction can do unexecute.
	 * @return always return true.
	 */
	@Override
	public boolean canUnExecute() {
		return true;
	}

	/**
	 * this method return if DropIntruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}

}
