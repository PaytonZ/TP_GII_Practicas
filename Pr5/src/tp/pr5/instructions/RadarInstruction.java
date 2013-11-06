package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;


/**
 * This Instruction shows the description of the current place and the items in it.
 * This Instruction works if the user writes RADAR
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */


public class RadarInstruction implements Instruction {
	private NavigationModule mapa;
	private RobotEngine robot;
	
	/**
	 * Default constructor
	 */
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
		robot=engine;
		
	}
	/**
	 * Execute the instruction Radar.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		robot.setdiceRobotConsola(this.mapa.getCurrentPlace().toString());
	}
	/**
	 * Unexecute one RadarInstruction. You can't undo one RadarInstruction, this method does nothing
	 */
	@Override
	public void unExecute() {
		
	}
	
	/**
	 * This method return if RadarInstruction can do unexecute.
	 * @return always return false.
	 */
	@Override
	public boolean canUnExecute() {
		return false;
	}
	
	/**
	 * This method return if RadarInstruction use fuel when the instruction it's executing.
	 * @return always return false.
	 */
	@Override
	public boolean gastaFuel() {
		return false;
	}
	
}
