package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;



/**
 * A CodeCard can open or close the door placed in the streets. 
 * The card contains a code that must match the street code in order to perform the action
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class CodeCard extends Item implements Comparable <Item>{
private String code;
	
	/**
	 * CodeCard constructor
	 */
	public CodeCard(String id, String des, String c)
	{
		super(id,des);
		this.code=c;
	}
	
	/**
	 * @return true always because a CodeCard always can be used.
	 */
	public boolean canBeUsed()
	{
		return true;
	}
	
	/**
	 * 
	 * @param r the RobotEngine where the item go be used.
	 * @param nav the NavigationModule where the item go be used.
	 * @return true if the CodeCard was be use, if the CodeCard was not be used return false.
	 */
	public boolean use(RobotEngine r, NavigationModule map) 
	{	
		Street s =  map.getHeadingStreet(); 
		boolean usar;
		if (s==null) usar = false;	
		else if (s.isOpen()) 
			usar = s.close(this);		
		else 
			usar = s.open(this);			
		return usar;
	}
	
	/**
	 * 
	 * @return the code of the CodeCard.
	 */
	public String getCode()
	{
		return this.code;
	}
	
	/**
	 * This method unused a CodeCard.
	 * @param r the robotEngine
	 * @param nav the NavigationModule
	 * 
	 */
	@Override
	public void unUse(RobotEngine r, NavigationModule nav) {
		Street s =  nav.getHeadingStreet(); 
		if (s.isOpen()) 
			s.close(this);		
		else 
			s.open(this);
	}
	

}
