package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public interface Instruction {

	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction.
	 * @throws WrongInstructionFormatException when the String cad doen't be valid.
	 */
	public abstract Instruction parse(java.lang.String cad) throws WrongInstructionFormatException;
	/**
	 * 
	 * @return a string with an instruction that the robot understands
	 */
	public abstract String getHelp();
	
	/**
	 * 
	 * @param engine The robot engine
	 * @param navigation The information about the game, i.e., the places, current direction and current heading to navigate
	 * @param robotContainer  The inventory of the robot
	 * 
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container) even though the actual implementation of execute() may not require it.
	 */
	public abstract void configureContext (RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException  if there exist any execution error.
	 */
	public abstract void execute() throws InstructionExecutionException;
}
