package tp.pr5.instructions;


import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;


/**
 * Its execution rotates the robot This Instruction works if the robot writes TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class TurnInstruction implements Instruction {
	private RobotEngine robot;
	private NavigationModule mapa;
	private Rotation direccion;
	
	/**
	 * Default constructor.
	 */
	public TurnInstruction(){}
	
	/**
	 * Constructor with a Rotation param
	 * @param a the direction the robot rotate
	 */
	public TurnInstruction(Rotation a)
	{
		direccion=a;
	}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Turn.
	 * @throws WrongInstructionFormatException when the String cad doen't be "TURN" or "GIRAR".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		TurnInstruction a = new TurnInstruction();
		if (word.length==2)
			{
				if ((word[0].equalsIgnoreCase("TURN")||word[0].equalsIgnoreCase("GIRAR"))&&(word[1].equalsIgnoreCase("right") ))
				{
					a.direccion=Rotation.RIGHT;
					return a;
				}
				else if ((word[0].equalsIgnoreCase("TURN")||word[0].equalsIgnoreCase("GIRAR"))&&(word[1].equalsIgnoreCase("left")))
				{
					a.direccion=Rotation.LEFT;
					return a;
				}		
				else 
					throw new WrongInstructionFormatException();
			}
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	TURN | GIRAR < LEFT|RIGHT >";
	 */
	@Override
	public String getHelp() {
		return "	TURN|GIRAR < LEFT|RIGHT >";
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
	public void configureContext(RobotEngine engine,NavigationModule navigation, ItemContainer robotContainer) {
		this.robot=engine;
		this.mapa=navigation;
		
	}
	/**
	 * Execute the instruction Turn.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
			mapa.rotate(this.direccion);			
			robot.turning();
			robot.seMovioCrearMensaje();
		
	}

	/**
	 * Unexecute one TurnInstruction.
	 */
	@Override
	public void unExecute() {
		mapa.rotate(direccion.oposite(direccion)); 
		robot.unTurning();
		robot.setdiceRobotGrafica("WALL·E says: UnTurnInstruction realized");
		
	}
	
	/**
	 * This method return if TurnInstrction can do unexecute.
	 * @return always return true.
	 */
	@Override
	public boolean canUnExecute() {
		return true;
	}

	/**
	 * this method return if TurnIntruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return true;
	}
}
