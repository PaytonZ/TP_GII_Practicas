package tp.pr5;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the city where the robot is wandering. 
 * It contains information about the streets and the places in the city.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public class City {
	private List<Street> mapa;
	
	/**
	 * Default construction.
	 */
	
	public City()
	{
		this.mapa= new ArrayList<Street>();
	}
	
	/**
	 * City construction using an array of streets.
	 * @param cityMap - Array with City´s map.
	 */
	
	public City(Street[] cityMap)
	{
		this.mapa= new ArrayList<Street>();
		int i=0;
		while (i<cityMap.length)
		{
			if (cityMap[i]!=null)
				this.mapa.add(cityMap[i]);
			i++;
		}
			
	}
	
	/**
	 * This Method add street.
	 * @param street - Street to add.
	 */
	
	public void addStreet(Street street)
	{
		this.mapa.add(street);
	}
	
	/**
	 * this method checks if there is street. 
	 * @param p - Place where the robot is.
	 * @param d - Direction where the robot is looking.
	 * @return - Returns street if this place and in this direction exists a street.
	 */
	
	public Street lookForStreet(Place p, Direction d)
	{
		int i=0;
		Street esta=null;
		boolean ya=false;
		while(!ya && i<this.mapa.size())
		{
			if (this.mapa.get(i).comeOutFrom(p, d))
			{
				esta=this.mapa.get(i);
				ya=true;
			}
			i++;
		}
		return esta;
	}
	
	/**
	* This method return city´s map.
	* @return - Returns City´s map.
	*/
	
	
	public List<Street> getCalles()
	{
		return  mapa;
	}

}
