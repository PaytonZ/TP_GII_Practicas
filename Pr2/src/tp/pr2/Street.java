package tp.pr2;

// ESTA ESTA FINIQUITADA.

public class Street {
	private Place Plaza;
	private Direction Dir;
	private Place Plaza2;
	private boolean open;
	private String code;
	
	public Street (Place p, Direction d, Place p2)
	{
		this.Plaza=p;
		this.Dir=d;
		this.Plaza2=p2;
		this.open=true;
		this.code = new String();
	}
	
	public Street (Place p, Direction d, Place p2, boolean i, String c)
	{
		this.Plaza=p;
		this.Dir=d;
		this.Plaza2=p2;
		this.open=i;
		this.code=c;
	}
	/**
	 * Comprueba si hay Street desde place donde esta el robot y la direccition a la que mira el robot, devolviendo true en caso de que exista la calle y false en caso contrario 
	 * @param place - Place donde esta el robot
	 * @param whichDirection - Direction a la que mira el robot
	 * @return - True en caso que haya street y false en caso contrario
	 */
	public boolean comeOutFrom (Place place, Direction whichDirection)
	{
		if (place == this.Plaza)
		return (whichDirection==this.Dir);
		else if (place == this.Plaza2)
		return whichDirection.opposite(whichDirection)==this.Dir;
		else
		return false;
	}
	
	/**
	 * Recibiendo un Place devuelve el destino de la street
	 * @param whereAmI - Place del origen de la calle
	 * @return - Place destino de la Street
	 */
	
	public Place nextPlace (Place whereAmI)
	{
		if(this.Plaza==whereAmI)
			return this.Plaza2;
		else if (this.Plaza2==whereAmI)
			return this.Plaza;
		else return null;
	}
	
	/**
	 * Devuelve el origen de la street
	 * @return - Origen ce la street
	 */
	
	public Place getPlace1()
	{
		return this.Plaza;
	}
	
	/**
	 * Devuelve el destino de la street
	 * @return - Destino de la street
	 */
	
	public Place getPlace2()
	{
		return this.Plaza2;
	}
	
	/**
	 * Devuelve la direction de la street
	 * @return - Direction de la street
	 */
	
	public Direction getDirection()
	{
		return this.Dir;
	}
	
	public boolean isOpen()
	{
		return this.open;
	}
	
	public boolean open (CodeCard card)
	{
		if (card.getCode().equalsIgnoreCase(this.code))
		{
			this.open=true;
			return true;
		}
		else return false;
	}
	
	public boolean close (CodeCard card)
	{
		if (card.getCode().equalsIgnoreCase(this.code))
		{
			this.open=false;
			return true;
		}
		else return false;
	}
	
}
