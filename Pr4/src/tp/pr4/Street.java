package tp.pr4;

import tp.pr4.items.CodeCard;

/**
 * A street links two places A and B in one direction. If it is defined as Street(A,NORTH,B) it means that Place B is at NORTH of Place A. 
 * Streets are two-way streets, i.e. if B is at NORTH of A then A is at SOUTH of B.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public class Street {
	private Place Plaza;
	private Direction Dir;
	private Place Plaza2;
	private boolean open;
	private String code;
	
	/**
	 * Street construction
	 * @param p - Initial place
	 * @param d - Street´s direction
	 * @param p2 - Destination place
	 */
	
	public Street (Place p, Direction d, Place p2)
	{
		this.Plaza=p;
		this.Dir=d;
		this.Plaza2=p2;
		this.open=true;
		this.code = new String();
	}
	
	/**
	 * Street construction
	 * @param p - Initial place
	 * @param d - Street´s direction
	 * @param p2 - Destination place
	 * @param i - Open street
	 * @param c - Street´s code
	 */
	
	public Street (Place p, Direction d, Place p2, boolean i, String c)
	{
		this.Plaza=p;
		this.Dir=d;
		this.Plaza2=p2;
		this.open=i;
		this.code=c;
	}
	/**
	 * This method checks if exist street in this place and in this direction 
	 * @param place - Robot´s place
	 * @param whichDirection - Direction where the robot is looking
	 * @return - Returns true if the street exists and false in other case
	 */
	public boolean comeOutFrom (Place place, Direction whichDirection)
	{
		boolean ret = false;
		if (place == this.Plaza)
			ret = (whichDirection==this.Dir);
		else if (place == this.Plaza2)
				ret = whichDirection.opposite(whichDirection)==this.Dir;
		return ret;
	}
	
	/**
	 * This method returns street´s destination
	 * @param whereAmI - Initial place of street
	 * @return - Returns street´s destination
	 */
	
	public Place nextPlace (Place whereAmI)
	{
		Place ret = null;
		if(this.Plaza==whereAmI)
			ret = this.Plaza2;
		else if (this.Plaza2==whereAmI)
				ret = this.Plaza;
		return ret;
	}
	
	/**
	 * This method returns initial place of street
	 * @return - Returns initial place of street
	 */
	
	public Place getPlace1()
	{
		return this.Plaza;
	}
	
	/**
	 * This method returns street´s destination
	 * @return - Returns street´s destination
	 */
	
	public Place getPlace2()
	{
		return this.Plaza2;
	}
	
	/**
	 * This method returns street´s direction
	 * @return - Returns street´s direction
	 */
	
	public Direction getDirection()
	{
		return this.Dir;
	}
	
	/**
	 * This method checks if the street is open 
	 * @return - Returns true if the street is open and false in other case
	 */
	
	public boolean isOpen()
	{
		return this.open;
	}
	
	/**
	 * This method opens the street
	 * @param card - Cad for open the street
	 * @return - Returns true if the street was opened and false in other case
	 */
	
	public boolean open (CodeCard card)
	{
		if (card.getCode().equalsIgnoreCase(this.code))
		{
			this.open=true;
		}
		return card.getCode().equalsIgnoreCase(this.code);
	}
	
	/**
	 * This method closes the street
	 * @param card - Card for close the street
	 * @return - Returns true if the street was closed and false in other case
	 */
	
	public boolean close (CodeCard card)
	{
		if (card.getCode().equalsIgnoreCase(this.code))
		{
			this.open=false;
		}
		return card.getCode().equalsIgnoreCase(this.code);
	}
	
	/**
	 * This method returns street´s code
	 * @return - Returns street´s code
	 */
	
	public String getCode()
	{
		return this.code;
	}
}
