package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;


/**
 * An item that represents fuel. 
 * This item can be used at least once and it provides power energy to the robot. 
 * When the item is used the configured number of times, then it must be removed from the robot inventory
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class Fuel extends Item implements Comparable <Item>{
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
	 * Añade 1 al numero de veces que se puede usar un Item
	 */
	private void subirTimes()
	{
		times++;		
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
	
	/**
	 * This method unused a Fuel.
	 * @param r the robotEngine
	 * @param nav the NavigationModule
	 * 
	 */
	@Override
	public void unUse(RobotEngine r, NavigationModule nav) {
		r.subFuel(power);
		subirTimes();
	}
	

}
