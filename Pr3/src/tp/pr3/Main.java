package tp.pr3;

import java.io.FileInputStream;
import java.io.IOException;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;

public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length==0 && args.length<2)
		{
			System.err.println("Bad params.");
			System.err.println("Usage: java tp.pr3.Main <mapfile>");
			System.err.println("");
			System.err.println("<mapfile> : file with the description of the city.");
			System.exit(1);
		}
		try{
			
			FileInputStream fstream = new FileInputStream(args[0]);
			CityLoaderFromTxtFile leer= new CityLoaderFromTxtFile();
			City c = leer.loadCity(fstream);
			RobotEngine e= new RobotEngine(c, leer.getIni(), Direction.NORTH);
			e.startEngine();
		}
		catch (IOException e)
		{
			System.err.println("Error reading the map file: "+ args[0] + " (No existe el fichero o el directorio)");
			System.exit(2);
		}
		catch (WrongCityFormatException e)
		{
			System.err.println("Incorrect format.");
			System.exit(2);
		}
		finally
		{
		}



	}

}
