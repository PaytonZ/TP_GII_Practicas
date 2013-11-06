package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;


/**
 * This interface represents an instruction supported by the application. Every instruction that the robot may perform implements this interface. 
 * It forces instructions to provide with the implementation of four different methods:
Parse method tries to parse a string with the information of the instruction the class represents
Help method returns a string with an explanation of the kind of expression that the parse method supports
ConfigureContext method establishes the context needed to execute the instruction
Execute method performs the actual work of the instruction, executing it.
The execute method does not have any parameter. Therefore the context of execution must be given to the instruction object prior to its invocation using the configureContext method.
 Note that the actual context depends on the subclass because the information needed varies between instructions.
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
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
	
	/**
	 * Unexecute one Instruction.
	 */
	public abstract void unExecute();
	/**
	 * 
	 * @return if the Intruction can be unexecute.
	 */
	public abstract boolean canUnExecute();
	/**
	 * 
	 * @return if the Instruction use the Robot's fuel.
	 */
	public abstract boolean gastaFuel();
}
