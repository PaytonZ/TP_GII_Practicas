package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class RadarInstruction implements Instruction {

	private NavigationModule mapa;

	public RadarInstruction() {}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Radar.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Radar".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("RADAR"))
			return new RadarInstruction();
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	RADAR";
	 */
	@Override
	public String getHelp() {
		return "	RADAR";
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
		this.mapa=navigation;
		
	}
	/**
	 * Execute the instruction Radar.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		System.out.println(this.mapa.getCurrentPlace());
	}

}
