package tp.pr4.instructions;



import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;




/**
 * Its execution moves the robot from one place to the next one in the current direction. 
 * This instruction works if the user writes MOVE or MOVER.
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class MoveInstruction implements Instruction {
	private NavigationModule navegador;
	private RobotEngine robot;
	/**
	 * Default constructor.
	 */
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
		
	}
	
	/**
	 * Unexecute one MoveInstruction.
	 */
	@Override
	public void unExecute() {
		navegador.unMove();
		robot.unMoving();		
	}
	
	/**
	 * this method return if MoveInstruction can do unexecute.
	 * @return always return true.
	 */
	@Override
	public boolean canUnExecute() {
		return true;
	}
	
	/**
	 * this method return if MoveInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return true;
	}
}
