package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;



/**
 * Its execution request the robot to finish the simulation 
 * This Instruction works if the user writes QUIT or SALIR
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */

public class QuitInstruction implements Instruction {
	private RobotEngine robot;

	/**
	 * Default constructor.
	 */
	public QuitInstruction(){}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Quit.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Quit" or "Salir".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("QUIT")||cad.equalsIgnoreCase("SALIR"))
			return new QuitInstruction();
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	QUIT|SALIR";
	 */
	@Override
	public String getHelp() {
		return "	QUIT|SALIR";
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
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot=engine;
		
	}
	/**
	 * Execute the instruction quit.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		
		this.robot.requestQuit();
	}
	
	/**
	 * Unexecute one QuitInstruction. You can't undo one QuitInstruction, this method does nothing
	 */
	@Override
	public void unExecute() {
		
	}
	
	/**
	 * this method return if QuitInstruction can do unexecute.
	 * @return always return false.
	 */
	
	@Override
	public boolean canUnExecute() {
		return false;
	}
	
	/**
	 * this method return if QuitInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
}
