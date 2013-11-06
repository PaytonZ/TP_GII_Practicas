package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class PickInstruction implements Instruction {
	private String item;
	private NavigationModule mapa;
	private ItemContainer mochila;
	
	public PickInstruction(){}
	/**
	 * 
	 * @param cad String to be converted into a instruction
	 * @return the instruction Pick.
	 * @throws WrongInstructionFormatException when the String cad doen't be "Pick" or "Coger".
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] word = cad.split(" ");
		PickInstruction a= new PickInstruction();
		if (word.length==2)
			{
				if (word[0].equalsIgnoreCase("PICK") || word[0].equalsIgnoreCase("COGER"))
					{
						a.item=word[1];
						return a;
					}
				else 
					throw new WrongInstructionFormatException();
			}
		else 
			throw new WrongInstructionFormatException();
	}
	/**
	 * @return String with "	PICK|COGER  <id>";
	 */
	@Override
	public String getHelp() {
		return "	PICK|COGER  <id>";
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
		this.mochila=robotContainer;
		this.mapa=navigation;
		
	}
	/**
	 * Execute the instruction drop.
	 * @throws InstructionExecutionException when the item doesn't exist in the place or if the robot have this item.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		Item it= this.mapa.getCurrentPlace().pickItem(this.item);
		if (it==null)
		{
			throw new InstructionExecutionException("WALL·E says: Ooops, this place has not the object "+ this.item);
		}
		else
		{
			if (this.mochila.buscador(it.getId())==-1)
			{
				this.mochila.addItem(it);
				System.out.println("WALL·E says: I am happy! Now I have "+ this.item);
			}
			else
				throw new InstructionExecutionException("WALL·E says: I am stupid! I had already the object "+ this.item);
		}
		
	}

}
