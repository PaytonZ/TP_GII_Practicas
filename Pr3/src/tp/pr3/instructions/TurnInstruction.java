package tp.pr3.instructions;


import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class TurnInstruction implements Instruction {

	private RobotEngine robot;
	private NavigationModule mapa;
	private Rotation direccion;
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
		
			this.mapa.rotate(this.direccion);
			this.robot.moving();
			System.out.println(this.robot.dondeMira());
			this.robot.printRobotState();
		
	}

}
