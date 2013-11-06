package tp.pr3;

import java.util.Scanner;



import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;


public class RobotEngine {
	private NavigationModule map;
	private int sopa;
	private int recicla; 
	private ItemContainer mochila;
	private Instruction ins;
	private Scanner sc;
	private boolean quitar;
	
	/**
	 * RobotEngine construction
	 * @param cm - City´s map
	 * @param ini - Initial place
	 * @param dir - Initial direction where the robot is looking
	 */
	
	public RobotEngine(City cm, Place ini, Direction dir)
	{
		this.map=new NavigationModule(cm,ini);
		this.sopa=100;
		this.mochila= new ItemContainer();
		this.recicla=0;
		this.quitar=false;
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * This method starts robotEngine
	 */
	
	public void startEngine()
	{
		String r=this.map.getCurrentPlace().toString();
		mostrarPantalla(r); 
		mostrarPantalla(this.dondeMira());
		this.printRobotState();
		while (!this.map.getCurrentPlace().isSpaceship() && !this.quitar && this.sopa>0)
		{
			mostrarPrompt();
			String l =leerString(this.sc);
			try {
				this.ins=Interpreter.generateInstruction(l);
				this.communicateRobot(this.ins);
			} catch (WrongInstructionFormatException e) {
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
		}
		if(this.map.getCurrentPlace().isSpaceship())
		{
			mostrarPantalla("WALL·E says: I am at my spaceship. Bye bye"); 
		}
		if (this.sopa<0)
		{
			mostrarPantalla("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
	}
	
	/**
	 * This method execute a instruction
	 * @param c - Robot´s instruction
	 */
	
	public void communicateRobot(Instruction c)
	{
		try
		{
			this.ins.configureContext(this, this.map, this.mochila);
			this.ins.execute();
		} 
		catch (InstructionExecutionException e) 
		{			
				
				System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method adds fuel
	 * @param fuel - Fuel´s amount to add
	 */
	
	public void addFuel (int fuel)
	{
		this.sopa=this.sopa+fuel;
	}
	
	/**
	 * This method adds RecycledMaterial
	 * @param basura - RecycledMaterial´s amount to add
	 */
	
	public void addRecycledMaterial (int basura)
	{
		this.recicla=this.recicla+basura;
	}
	
	/**
	 * This method converts the direction where the robot is looking to string
	 * @return - Returns a string with la direction where the robot is looking
	 */
	
	public String dondeMira() 
	{
		return "WALL·E is looking at direction " + this.map.getCurrentHeading().toString();
	}
	
	/**
	 * This method shows this string by the console
	 * @param a - Strieng to show by the console
	 */
	
	private static  void mostrarPantalla(String a)
	{
		System.out.println(a);
	}
	
	/**
	 * This method  shows robot´s state
	 */
	
	public void printRobotState()
	{
		if (this.sopa>0)
		{
			System.out.println("      "+"* My power is "+ this.sopa);
		}
		else System.out.println("      "+"* My power is 0");
		System.out.println("      "+"* My reclycled material is "+ this.recicla);
	}
	
	/**
	 * This method shows WALLE·E>
	 */
	
	private static void mostrarPrompt() 
	{
		System.out.print("WALL·E> ");
	} 
	
	/**
	 * This method reads the console and converts this read in a string
	 * @param sc - Variable scanner for read by the console
	 * @return
	 */
	
	private static String leerString(Scanner sc) 
	{
		String a= sc.nextLine();
		return a;
	}
	
	/**
	 * This method consumes fuel in the movement
	 */
	
	public void moving()
	{
		this.sopa=this.sopa-5;
	}
	
	/**
	 * This method returns the street where the robot is looking
	 * @return - Returns the street where the robot is looking
	 */
	
	public Street getHeadingStreet()
	{
		return this.map.getCity().lookForStreet(this.map.getCurrentPlace(), this.map.getCurrentHeading());
	}
	
	/**
	 * This method does that finish the program by quit
	 */
	
	public void requestQuit()
	{
		this.quitar=true;
	}
	
	/**
	 * This method shows robot´s help by the console
	 */

	public void requestHelp() {
		mostrarPantalla ( Interpreter.interpreterHelp());
	}
	
	/**
	 * This method returns robot´s fuel
	 * @return - Returns robot´s fuel
	 */
	
	public int getFuel()
	{
		return this.sopa;
	}
	
	/**
	 * This method returns recycled material
	 * @return - Returns the recycled material
	 */

	public int getRecycledMaterial() {
		
		return this.recicla;
	}

}
