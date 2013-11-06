package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;




/**
 * Shows the game help with all the instructions that the robot can execute. 
 * This instruction works if the user writes HELP or AYUDA
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class HelpInstruction implements Instruction {
private RobotEngine theEngine;
	/**
	 * Default constructor.
	 */
	public HelpInstruction() {}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Help.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Help" or "Ayuda".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException 
	{
		if (cad.equalsIgnoreCase("HELP") || cad.equalsIgnoreCase("AYUDA"))
			return new HelpInstruction();
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	HELP|AYUDA";
	 */
	@Override
	public String getHelp() {
		return "	HELP|AYUDA";
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
		theEngine =engine;
		
	}
	/**
	 * Execute the instruction Help.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		
		theEngine.requestHelp();
		
	}
	/**
	 * Unexecute one HelpInstruction. You can't undo one HelpInstruction, this method does nothing
	 */
	@Override
	public void unExecute() {
		// Seria correcto poner esto?  throw new UnsupportedOperationException();
		
	}
	/**
	 * this method return if HelpInstruction can do unexecute.
	 * @return always return false.
	 */
	@Override
	public boolean canUnExecute() {
		return false;
	}
	/**
	 * this method return if HelpInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
}
