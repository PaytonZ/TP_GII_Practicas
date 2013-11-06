package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class HelpInstruction implements Instruction {
	private RobotEngine theEngine;
	
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
		this.theEngine =engine;
		
	}
	/**
	 * Execute the instruction Help.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		this.theEngine.requestHelp();
		
	}

}
