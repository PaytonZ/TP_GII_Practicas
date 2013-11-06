package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction{
	private NavigationModule navegador;
	private RobotEngine robot;
	
	public MoveInstruction() {}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Move.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Move" or "MOVER".
	 */
	public Instruction parse(java.lang.String cad) throws WrongInstructionFormatException
	{
		if (cad.equalsIgnoreCase("MOVE")|| cad.equalsIgnoreCase("MOVER"))
			return new MoveInstruction();
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	MOVE|MOVER";
	 */
	@Override
	public String getHelp() {
		return "	MOVE|MOVER";
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
		this.robot=engine;
		
	}
	/**
	 * Execute the instruction move.
	 * @throws InstructionExecutionException when the robot can't move.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		
		this.navegador.move();
		this.robot.moving();
		System.out.println("WALL·E says: Moving in direction "+ this.navegador.getCurrentHeading().toString());
		System.out.println(this.navegador.getCurrentPlace().toString());
		if (this.navegador.getCurrentPlace().isSpaceship())
		{
			System.out.println("The place is empty. There are no objects to pick");
			System.out.println("");
		}
		System.out.println("");
		this.robot.printRobotState();
	
		
	}
}
