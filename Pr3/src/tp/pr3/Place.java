package tp.pr3;

import tp.pr3.items.*;

public class Place {

	private String Lugar;
	private boolean MotherShip;
	private String Definicion;
	private ItemContainer objetos;
	
	/**
	 * Pace construction
	 * @param l - Place´s name
	 * @param m - this is a Spaceship
	 * @param d - Place´s description
	 */
	
	public Place(String l, boolean m, String d)
	{
		this.Definicion=d;
		this.Lugar=l;
		this.MotherShip=m;
		this.objetos=new ItemContainer();
		
	}
	
	/**
	 * This method checks if this place is a Spaceship
	 * @return - Returns true if this place is a spaceship and false in other case 
	 */
	
	public boolean isSpaceship ()
	{
		return this.MotherShip;
	}
	
	/**
	 * This method shows place´s information by the console
	 */
	
	public String toString()
	{
		String ret = this.Lugar + Interpreter.LINE_SEPARATOR + this.Definicion + Interpreter.LINE_SEPARATOR;
		if (this.objetos.numberOfItems()!=0)
		{
			ret=ret + "The place contains these objects:"+ Interpreter.LINE_SEPARATOR+
				   this.objetos.toString() ;
		}
		return ret;
	}
	
	/**
	 * This method picks a object
	 * @param id - Item´s id to pick
	 * @return - Returns item picked
	 */

	public Item pickItem(String id)
	{
		return this.objetos.pickItem(id);
	}
	
	/**
	 * This method adds a item to the place
	 * @param it - Item to add
	 * @return - Returns place´s itemContainer
	 */
	
	public boolean addItem(Item it)
	{
		return this.objetos.addItem(it);
	}
	
	/**
	 * This method checks if this Item exists in place´s itemContainer
	 * @param id - Item´s id
	 * @return - Returns true if item exits, false in other case
	 */
	
	public boolean existItem (String id)
	{	
		return this.objetos.containsItem(id);
	}
	
	/**
	 * This method checks if this object can be dropped in this place and if is true it drop
	 * @param it - Item to drop
	 * @return - Returns true if the item can be dropped in this place, false in other case
	 */
	
	public boolean dropItem(Item it)
	{
		boolean asd=this.objetos.containsItem(it.getId());
		if (!asd)
		{
			this.addItem(it);
		}
		return !asd;
	}
	
	/**
	 * This method returns place´s itemContainer
	 * @return - Return place´s itemContainer 
	 */
	
	public ItemContainer getObj()
	{
		return this.objetos;
	}
	
	/**
	 * This method returns place´s name 
	 * @return - Returns place´s name
	 */

	public String getLugar()
	{
		return this.Lugar;
	}
	
	
}
