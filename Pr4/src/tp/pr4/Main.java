package tp.pr4;

import java.io.FileInputStream;
import java.io.IOException;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr4.gui.MainWindow;
/**
 * Application entry-point. The application admits a parameter -m | --map with the name of the map file to be used and a parameter -i | --interface with the type of interface (console or swing) 
 * If no arg is specified (or more than one file is given), it prints an error message (in System.err) and the application finishes with an error code (-1). 
 * If the map file cannot be read (or it does not exist), the application ends with a different error code (-2). 
 * If the interface arg is not correct (console or swing) the application prints a message and the application finishes with an error code (-3).
 * If the interface arg is not included it starts the application in console mode. Otherwise, the simulation starts and eventually the application will end normally (return code 0).
 * @author David Garcia Alvarez y Javier Toledano Rega�o
 *
 */


public class Main {

	/**
	 * Principal Main
	 * @param args Array of string with the information for iniciate the game.
	 */
	public static void main(String[] args) {
		if (args.length==0)
		{
			System.err.println("Map file not specified");
			System.exit(1);
		}
		else if (args.length==1 && args[0].equalsIgnoreCase("-h"))
			mostrarAyuda();
		else  if (args.length<4)
				{
					if (!args[1].equalsIgnoreCase("console") || args[1].equalsIgnoreCase("swing"))
						System.err.println("Interface not specified");
					
					else	
						System.err.println("Map file not specified");
						
					System.exit(1);
				}
		else if (args.length ==4 )
			{
				if ((args[2].equalsIgnoreCase("-i")||args[2].equalsIgnoreCase("--interface"))&&!(args[3].equalsIgnoreCase("console") || args[3].equalsIgnoreCase("swing")))
				{
					System.err.println("Wrong type of interface");
					System.exit(1);
				}
				else if ((args[0].equalsIgnoreCase("-i")||args[0].equalsIgnoreCase("--interface"))&&!(args[1].equalsIgnoreCase("console") || args[1].equalsIgnoreCase("swing"))) 	
				{
					System.err.println("Wrong type of interface");
					System.exit(1);
				}
				else if ((args[2].equalsIgnoreCase("-i")||args[2].equalsIgnoreCase("--interface")) && ((args[0].equalsIgnoreCase("-m")||args[0].equalsIgnoreCase("--map")) && args[3].equalsIgnoreCase("console")))
				{
					Main.ejecuta(args[1]);
				}
				else if((args[0].equalsIgnoreCase("-i")||args[0].equalsIgnoreCase("--interface")) &&((args[2].equalsIgnoreCase("-m")||args[2].equalsIgnoreCase("--map")) && args[1].equalsIgnoreCase("console")))
				{
					Main.ejecuta(args[3]);
				}
				else if ((args[2].equalsIgnoreCase("-i")||args[2].equalsIgnoreCase("--interface")) && ((args[0].equalsIgnoreCase("-m")||args[0].equalsIgnoreCase("--map")) && args[3].equalsIgnoreCase("swing")))
				{
					MainWindow.main(args[1]);
				}
				else if ((args[0].equalsIgnoreCase("-i")||args[0].equalsIgnoreCase("--interface")) && ((args[2].equalsIgnoreCase("-m")||args[2].equalsIgnoreCase("--map")) && args[1].equalsIgnoreCase("swing")))
				{
					MainWindow.main(args[3]);
				}
			}
		else Main.errorParametros();
			
		
	}
	
	/**
	 * This method inicite the game in the console.
	 * @param string the file.txt to open the map
	 */
	private static void ejecuta(String string) {
		try{
			
			FileInputStream fstream = new FileInputStream(string);
			CityLoaderFromTxtFile leer= new CityLoaderFromTxtFile();
			City c = leer.loadCity(fstream);
			RobotEngine e= new RobotEngine(c, leer.getIni(), Direction.NORTH);
			e.startEngine();
		}
		catch (IOException e)
		{
			System.err.println("Error reading the map file: "+ string + " (No existe el fichero o el directorio)");
			System.exit(2);
		}
		catch (WrongCityFormatException e)
		{
			System.err.println("Incorrect format.");
			System.exit(2);
		}
		
	}
	
	/**
	 * This method shows robot�s help when use -h.
	 */

	private static void mostrarAyuda() {
		System.out.println("Execute this assignment with these parameters:");
		System.out.println("usage: tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]");
		System.out.println(" -h,--help               Shows this help message");
		System.out.println(" -i,--interface <type>   The type of interface: console or swing");
		System.out.println(" -m,--map <mapfile>      File with the description of the city");
		
	}

	/**
	 * This method shows message when introduces error parameter
	 */
	
	private static void errorParametros()
	{
		System.err.println("Bad params.");
		System.err.println("Usage: java tp.pr3.Main <mapfile>");
		System.err.println("");
		System.err.println("<mapfile> : file with the description of the city.");
		System.exit(1);
	}
}
