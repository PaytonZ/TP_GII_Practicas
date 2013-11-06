package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;



public class Garbage extends Item {
	private int recicla;
	private int times;
	/**
	 * Garbage Constructor
	 * @param id the id of the item.
	 * @param des the description of the item.
	 * @param r the recycled that the robot gets when it operate this item.
	 */
	public Garbage (String id, String des, int r)
	{
		super (id,des);
		this.recicla=r;
		this.times=1;
	}
	/**
	 * 
	 * @param r the RobotEngine where the item go be used.
	 * @param nav the NavigationModule where the item go be used.
	 * @return true if the fuel was be use, if the fuel was not be used return false.
	 */
	public boolean canBeUsed()
	{
		return this.times>0;
	}
	/**
	 * 
	 * @param r the RobotEngine where the item go be used.
	 * @param nav the NavigationModule where the item go be used.
	 * @return true if the garbage can be use, if the garbage can't be used return false.
	 */
	public boolean use(RobotEngine r, NavigationModule p) 
	{
		boolean usar = this.canBeUsed();
		if (usar)
		{
			r.addRecycledMaterial(this.recicla);	
			this.times--;
		}
		return usar;
				
	}
	
	
	/**
	 * @return the id and the description of the item
	 */
	public String toString()
	{
		return this.id + ": "+ this.description + "// recycled material = " + this.recicla;
	}
}

