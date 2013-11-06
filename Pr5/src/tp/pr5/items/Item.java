package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;


/**
 * The superclass of every type of item. 
 * It contains the common information for all the items and it defines the interface that the items must match
 *  @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public abstract class Item implements Comparable<Item> {

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
	 * 
	 * @return the description of the Item.
	 */
	public String getDescription()
	{
		return description;
	}
	
	public abstract void unUse(RobotEngine r, NavigationModule nav);
	
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
	
	
	/**
	 * This method compare two items
	 * @param it the item to compare
	 * @return	true if the items are equals and false if aren't equals.
	 */
	public boolean equals(Item it)
	{
		return /*it.description.equalsIgnoreCase(description) &&*/ it.id.equalsIgnoreCase(id);
	}

	
	
	/**
	 * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
		The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. (This implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws an exception.)

		The implementor must also ensure that the relation is transitive: (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0.

		Finally, the implementor must ensure that x.compareTo(y)==0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.

		It is strongly recommended, but not strictly required that (x.compareTo(y)==0) == (x.equals(y)). Generally speaking, any class that implements the Comparable interface and violates this condition should clearly indicate this fact. 
		The recommended language is "Note: this class has a natural ordering that is inconsistent with equals."

		In the foregoing description, the notation sgn(expression) designates the mathematical signum function, which is defined to return one of -1, 0, or 1 according to whether the value of expression is negative, zero or positive.
		
		@param arg0 - the object to be compared.
		
		@return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
		
	 */
	public int compareTo(Item arg0) {
		return id.compareToIgnoreCase(((Item)arg0).id);
	}
	
}
