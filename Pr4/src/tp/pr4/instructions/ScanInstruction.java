package tp.pr4.instructions;


import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;


/**
 * The execution of this instruction shows the information of the inventory of the robot or the complete description about the item with identifier id contained in the inventory 
 * This Instruction works if the player writes SCAN or ESCANEAR (id is not mandatory)
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class ScanInstruction implements Instruction {
	private ItemContainer mochila;
	private String item;
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Scan.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Scan" or "Escanear".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		ScanInstruction a = new ScanInstruction();
		if (word.length<=2)
			{
				if (word[0].equalsIgnoreCase("SCAN") || word[0].equalsIgnoreCase("ESCANEAR"))
					{
						if (word.length==2)
						{
							a.item=word[1];
						}
						return a;
					}
				else 
					throw new WrongInstructionFormatException();
			}
		else 
			throw new WrongInstructionFormatException();
		
	}
	/**
	 * @return String with "	SCAN|ESCANEAR  <id>";
	 */
	@Override
	public String getHelp() {
		return "	SCAN|ESCANEAR";
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
	}
	/**
	 * Execute the instruction Scan.
	 * @throws InstructionExecutionException when the robotContainer it's empty or when the RobotContainer doesn't have the item.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		if (this.item==null)
		{
			if (this.mochila.numberOfItems()!=0)
			{
				System.out.println("WALL·E says: I am carrying the following items"+Interpreter.LINE_SEPARATOR+ this.mochila.toString());
			}
			else 
				throw new InstructionExecutionException("WALL·E says: My inventory is empty");
			
		}
		else
		{
			Item it= this.mochila.getItem(this.item);
			if (it!=null)
			{
				System.out.println("WALL·E says: " + it.toString());
			}
			else throw new InstructionExecutionException("WALL·E says: I have not such object");
				
		}
		
	}
	/**
	 * Unexecute one ScanInstruction. You can't undo one ScanInstruction, this method does nothing
	 */
	@Override
	public void unExecute() {
		
		
	}
	
	/**
	 * This method return if ScanInstruction can do unexecute.
	 * @return always return false.
	 */
	@Override
	public boolean canUnExecute() {
		return false;
	}
	
	/**
	 * this method return if ScanInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
}
