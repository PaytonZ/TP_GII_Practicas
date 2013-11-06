package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;


public class Fuel extends Item {
	private int power;
	private int times;
	/**
	 * 
	 * @param id id of the item.
	 * @param des the description of the item.
	 * @param p energy that the robot gets .
	 * @param t the numbers of times that the robot can be used the item.
	 */
	public Fuel(String id, String des, int p, int t)
	{
		super(id,des);
		this.power=p;
		this.times=t;
	}
	/**
	 * @return if the item can be used one time more return true.
	 */
	public boolean canBeUsed ()
	{
		return this.times>0;
	}
	
	/**
	 * @return one reduces the time the robot can use the item.
	 */
	private void disminuirTimes()
	{
		this.times--;
	}
	/**
	 * 
	 * @param r the RobotEngine where the item go be used.
	 * @param nav the NavigationModule where the item go be used.
	 * @return true if the fuel was be use, if the fuel was not be used return false.
	 */
	public boolean use (RobotEngine r, NavigationModule p) 
	{
		boolean usar=this.canBeUsed();
		if (usar)
		{
			r.addFuel(this.power);
			this.disminuirTimes();	
		}
		return usar;
	}
	/**
	 * 
	 * @return the times that the robot can use this items.
	 */
	public int getTimes()
	{
		return this.times;
	}
	/**
	 * @return the id and description of the robot in a String.
	 */
	public String toString()
	{
		return  this.id + ": "+ this.description + "// power = " + this.power + ", times = " + this.times;
	}
}
