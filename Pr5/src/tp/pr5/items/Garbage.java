package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;


/**
 * The garbage is a type of item that generates recycled material after using it. 
 * The garbage can be used only once. After using it, it must be removed from the robot inventory
 *  @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class Garbage extends Item implements Comparable <Item> {
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
			r.addRecycledMaterial(recicla);	
			times--;
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
	
	/**
	 * This method unused a Garbage.
	 * @param r the robotEngine
	 * @param nav the NavigationModule
	 * 
	 */
	@Override
	public void unUse(RobotEngine r, NavigationModule nav) {
		r.subRecycledMaterial(recicla);
		times++;
	}
	

	
}
