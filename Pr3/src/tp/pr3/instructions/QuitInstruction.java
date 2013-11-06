package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class QuitInstruction implements Instruction {

	private RobotEngine robot;

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
		System.out.println("WALL·E says: I have communication problems. Bye bye.");
		
	}

}
