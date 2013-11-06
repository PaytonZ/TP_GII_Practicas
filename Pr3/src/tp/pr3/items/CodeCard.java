package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Street;



public class CodeCard extends Item {
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


	
}
