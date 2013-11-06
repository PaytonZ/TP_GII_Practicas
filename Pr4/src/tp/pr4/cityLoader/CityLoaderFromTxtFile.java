package tp.pr4.cityLoader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import tp.pr4.*;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr4.items.*;

/**
 * City loader from a txt file The mandatory format must be:
 * BeginCity
 * BeginPlaces
 * place 0 Entrada Estamos_en_la_entrada._Comienza_la_aventura noSpaceShip
 * place 1 Callao In_this_square... spaceShip
 * ...
 * EndPlaces
 * BeginStreets
 * street 0 place 0 south place 1 open
 * street 1 place 1 east place 2 open
 * street 2 place 2 north place 3 closed onetwothreefourfive
 * ...
 * EndStreets
 * BeginItems
 * fuel 0 Petrol from_olds_heatings 10 3 place 0
 * fuel 1 Battery to_get_cracking -50 1 place 0
 * codecard 2 Card The_key_is_too_easy onetwothreefourfive place 1
 * garbage 3 Newspapers News_on_sport 30 place 2
 * ...
 * EndItems
 * EndCity
 *
 *
 * If you want to enhance your loader, it should also parse the descriptions using the following format:
 *
 * place 0 Entrada "Estamos en la entrada. Comienza la aventura" noSpaceShip
 * garbage Agua "Dicen que si los humanos no bebÃ­an de esto, terminaban muriendo." 10 3 place 0
 *
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public class CityLoaderFromTxtFile {
	private List<Street> calles;
	private List<Place> plazas;
	
	/**
	 * CityLoaderFromTextFile construction
	 */
	
	public CityLoaderFromTxtFile()
	{
		this.calles= new ArrayList<Street>();
		this.plazas= new ArrayList<Place>();
	}
	
	/**
	 * This method reads from the file a map´s city
	 * @param file - File where the city is
	 * @return - Returns a city´s map
	 * @throws WrongCityFormatException - Throw exception when wrong format of city´s map
	 * @throws IOException - Throws exception when 
	 */
	
	public City loadCity(InputStream file) throws WrongCityFormatException, IOException
	{
		DataInputStream entrada = new DataInputStream(file);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
		ArrayList<String> map = new ArrayList<String>();
		map.add(buffer.readLine());
		int cont=0;
		while(map.get(cont)!=null )
		{
			map.add(buffer.readLine());
			cont++;
		}
		map.remove(cont);
		if(cont==0)
			throw new WrongCityFormatException();
		cont=0;
		if (!map.get(cont).equalsIgnoreCase("BeginCity"))
			throw new WrongCityFormatException();
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		if (!map.get(cont).equalsIgnoreCase("BeginPlaces"))
			throw new WrongCityFormatException();
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		cont=leerPlazas(cont, map);
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		if (!map.get(cont).equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException();
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		cont=leerCalles(cont, map);
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		if (!map.get(cont).equalsIgnoreCase("BeginItems"))
			throw new WrongCityFormatException();
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		cont=leerItems(cont, map);
		cont++;
		if (cont>=map.size())
			throw new WrongCityFormatException();
		if (!map.get(cont).equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException();
		entrada.close();
		City ciu= new City(this.arrayTrans());
		return ciu;
	}
	
	/**
	 * This method returns true if this string is equals to spaceship
	 * @param a - String name
	 * @return - Return true if is the spaceship and false in other case
	 */
	
	private boolean navenodriza(String a)
	{
		return a.equalsIgnoreCase("spaceShip");
	}
	
	/**
	 * This method converts a string´s arrays in a string
	 * @param s - Strings to convert
	 * @return - Returns string
	 */
	
	private String Trans(String[] s)
	{
		String qwe="";
		int cont=0;
		while(s.length>cont)
		{
			qwe=qwe+s[cont]+" ";
			cont++;
		}
		return qwe;
	}
		
	/**
	 * This method returns if this string is equals to open
	 * @param a - String name
	 * @return - Returns true if this string is equals to open and false in other case
	 */
	
	private boolean isOpen(String a)
	{
		return (a.equalsIgnoreCase("open"));
	}
	
	/**
	 * This method generates a array of streets
	 * @return - Returns a array of streets
	 */
	
	private Street[] arrayTrans()
	{
		Street[] s= new Street[20];
		int c=0;
		while (c<this.calles.size()&& c<20)
		{
			s[c]=this.calles.get(c);
			c++;
		}
		return s;
	}
	
	/**
	 * This method returns initial place of city´s map
	 * @return - Returns initial place of city´s map
	 */
	
	public Place getInitialPlace()
	{
		return this.plazas.get(0);
	}
	
	/**
	 * This method reads place into the map.
	 * @param cont - Counter of places.
	 * @param map - City map.
	 * @return - Returns the number of places.
	 * @throws WrongCityFormatException - Throw exception when wrong format of city´s map.
	 */
	
	private int leerPlazas(int cont,ArrayList<String> map ) throws WrongCityFormatException
	{
		int incremento=0;
		while (!map.get(cont).equalsIgnoreCase("EndPlaces"))
		{
			String[] plaza = map.get(cont).split(" ");
			if (plaza.length!=5)
				throw new WrongCityFormatException();
			if (!plaza[0].equalsIgnoreCase("place"))
				throw new WrongCityFormatException();
			else if (incremento!=Integer.parseInt(plaza[1]))
				throw new WrongCityFormatException();
			else
				{		
					String[] descripcion = plaza[3].split("_");
					String descripcion1=this.Trans(descripcion);
					Place p= new Place(plaza[2], navenodriza(plaza[4]),descripcion1);
					this.plazas.add(p);
					cont++;
					if (cont>=map.size())
						throw new WrongCityFormatException();
					incremento++;
				}
		}
		return cont;
	}
	
	/**
	 * This method reads streets into the map.
	 * @param cont - Counter of streets.
	 * @param map - City map.
	 * @return - Returns the number of streets.
	 * @throws WrongCityFormatException - Throw exception when wrong format of city´s map.
	 */
	
	private int leerCalles(int cont,ArrayList<String> map) throws WrongCityFormatException
	{
		int incremento=0;
		while (!map.get(cont).equalsIgnoreCase("EndStreets"))
		{
			String[] str = map.get(cont).split(" ");
			Street callejon;
			if (!str[0].equalsIgnoreCase("street"))
				throw new WrongCityFormatException();
			if (str.length!=8 && str.length!=9)
				throw new WrongCityFormatException();
			else if (incremento!=Integer.parseInt(str[1]))
				throw new WrongCityFormatException();
			else if (str.length==8)
				{
					if (Integer.parseInt(str[3])>this.plazas.size() || Integer.parseInt(str[6])> this.plazas.size())
						throw new WrongCityFormatException();
					callejon= new Street(this.plazas.get(Integer.parseInt(str[3])), Direction.stringDirection(str[4]),this.plazas.get(Integer.parseInt(str[6])),this.isOpen(str[7]), "0000" );
					this.calles.add(callejon);
				}
			else if (str.length==9)
				{
					if (Integer.parseInt(str[3])>this.plazas.size() || Integer.parseInt(str[6])> this.plazas.size())
						throw new WrongCityFormatException();
					callejon= new Street(this.plazas.get(Integer.parseInt(str[3])), Direction.stringDirection(str[4]),this.plazas.get(Integer.parseInt(str[6])),this.isOpen(str[7]), str[8] );
					this.calles.add(callejon);
				}
			else 
				throw new WrongCityFormatException();
			cont++;
			if (cont>=map.size())
				throw new WrongCityFormatException();
			incremento++;
		}
		return cont;
	}
	
	/**
	 * This method reads items into the place.
	 * @param cont - Counter of items.
	 * @param map - City map.
	 * @return - Returns the number of items.
	 * @throws WrongCityFormatException - Throw exception when wrong format of city´s map.
	 */
	
	private int leerItems(int cont, ArrayList<String> map) throws WrongCityFormatException
	{
		int incremento =0;
		while (!map.get(cont).equalsIgnoreCase("EndItems"))
		{
			String[] obj= map.get(cont).split(" ");
			if (obj.length==7)
				{
				if (!obj[0].equalsIgnoreCase("codecard")&& !obj[0].equalsIgnoreCase("garbage"))
					throw new WrongCityFormatException();
				}
			else if (obj.length==8)
				{
					if (!obj[0].equalsIgnoreCase("fuel"))
						throw new WrongCityFormatException();
				}
			else
				throw new WrongCityFormatException();
			String[] descripcion= obj[3].split("_");
			String des=this.Trans(descripcion);
			if (incremento!=Integer.parseInt(obj[1]))
				throw new WrongCityFormatException();
			if(obj[0].equalsIgnoreCase("fuel"))
			{
				Item it= new Fuel(obj[2], des, Integer.parseInt(obj[4]),Integer.parseInt(obj[5]));
				if (Integer.parseInt(obj[7])<this.plazas.size())
					this.plazas.get(Integer.parseInt(obj[7])).addItem(it);
				else throw new WrongCityFormatException();
			}
			else if(obj[0].equalsIgnoreCase("codecard"))
			{
				
				Item it= new CodeCard(obj[2], des, obj[4]);
				if (Integer.parseInt(obj[6])<this.plazas.size())
					this.plazas.get(Integer.parseInt(obj[6])).addItem(it);
				else throw new WrongCityFormatException();
			}
			else if(obj[0].equalsIgnoreCase("garbage"))
			{				
				Item it= new Garbage(obj[2], des, Integer.parseInt(obj[4]));
				if (Integer.parseInt(obj[6])<this.plazas.size())
					this.plazas.get(Integer.parseInt(obj[6])).addItem(it);
				else throw new WrongCityFormatException();
			}
			else throw new WrongCityFormatException(); 
			cont++;
			if (cont>=map.size())
				throw new WrongCityFormatException();
			incremento++;
		}
		return cont;
	}
	
	/**
	 * This method returns initial place
	 * @return - Initial place
	 */
	
	public Place getIni()
	{
		return this.plazas.get(0);
	}
	
	
}
