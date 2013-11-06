/**
 * 
 */
package tp.pr5.console;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import tp.pr5.Controller;
import tp.pr5.InformacionObservadores;
import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * This class is used for controller the robot by console.
 * @author David Garcia y Javier Toledano.
 *
 */
public class ConsoleController extends Controller implements Observer{
	
	protected  Scanner sc;
	protected Instruction ins;
	
	/**
	 * Default Builder
	 * @param game The RobotEngine
	 */
	public ConsoleController(RobotEngine game) {
		super(game);
	}

	/**
	 * The method to star the control.
	 */
	@Override
	public void startController() {
		inicio();
		this.sc = new Scanner(System.in);
		while (!robot.isInMotherShip() && !robot.isTheEnd() && robot.haveFuel())
		{
			mostrarPrompt();
			String l =leerString(sc);
			try {
				ins=Interpreter.generateInstruction(l);
				robot.communicateRobot(ins);
			} catch (WrongInstructionFormatException e) {
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
		}
		if(robot.isInMotherShip())
		{
			mostrarPantalla("WALL·E says: I am at my spaceship. Bye bye"); 
		}
		if (!robot.haveFuel())
		{
			mostrarPantalla("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
		
	}
	
	/**
	 * The method for write by console.
	 * @param a
	 */
	private static  void mostrarPantalla(String a)
	{
		System.out.println(a);
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
	 * This method shows WALLE·E>
	 */
	
	private static void mostrarPrompt() 
	{
		System.out.print("WALL·E> ");
	}

	/**
	 * This method update with the new inform about the RobotEngine. 
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg!=null)
		{
			if (arg.getClass()==InformacionObservadores.class)
			{
				Object a = ((InformacionObservadores)arg).buscarMiInfo("MensajeConsola");
				if (a!=null)
				{
					mostrarPantalla(((String)a));
				}
			}
		}
		
	} 
	
	/**
	 * This method start the game.
	 */
	public void inicio()
	{
		String r=robot.InformacionPlaza();
		mostrarPantalla(r); 
		mostrarPantalla(robot.dondeMira());
		System.out.println(robot.printRobotState());
	}

}
