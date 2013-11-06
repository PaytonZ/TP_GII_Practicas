package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/**
 * This class implement the instuction UNDO.
 * This Instruction works if the robot writes UNDO or DESHACER.
 * * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class UnDoInstruction implements Instruction{
	private Instruction instrucion;
	private RobotEngine robot;
	
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction UnDoInstruction.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Undo" or "Deshacer".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("UNDO") || cad.equalsIgnoreCase("DESHACER"))
			return new UnDoInstruction();
		else 
			throw new WrongInstructionFormatException();
	}

	/**
	 * @return String with "	UNDO|DESHACER";
	 */
	@Override
	public String getHelp() {
		return "	UNDO|DESHACER";
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
		robot=engine;
		
	}

	/**
	 * Execute the instruction Undo.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		instrucion=robot.getInstruction();
		if(instrucion!=null)
		{
			instrucion.unExecute();
			robot.deleteLastInstruction();
		}
		else
			throw new InstructionExecutionException("WALL·E says: I am stupid! I doesn't have instructions for 'UN DO'");
		}
	
	
	/**
	 * Unexecute one UnDoInstruction. You can't undo one UnDoInstruction, this method does nothing
	 */
	@Override
	public void unExecute() {
		
	}

	/**
	 * this method return if UnDoInstruction can do unexecute.
	 * @return always return false.
	 */
	@Override
	public boolean canUnExecute() {
		return false;
	}

	/**
	 * this method return if UnDoInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}

}
