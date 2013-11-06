package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;


public abstract class Item {

	protected String id;
	protected String description;
	
	/**
	 * Item constructor
	 */
	public Item(String i, String d)
	{
		this.id=i;
		this.description=d;
	}
	
	/**
	 * return if the item can be used.
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * 
	 * @return the id of the Item.
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * @return the id and the description of the item
	 */
	public String toString()
	{
		return  this.id + ": "+ this.description;
	}
	
	/**
	 * 
	 * @param r the RobotEngine where the item go be used.
	 * @param nav the NavigationModule where the item go be used.
	 * @return true if the item can be use, if the item can't be used return false.
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav) ;
	
}
