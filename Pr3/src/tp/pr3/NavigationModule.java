package tp.pr3;

import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.items.Item;

public class NavigationModule {
	private City ciudad;
	private Place lugar;
	private Direction dondemiro; 
	
	/**
	 * NavigationModule construction
	 * @param ciu - City´s map 
	 * @param ini - Initial place of robot
	 */
	
	public NavigationModule(City ciu, Place ini)
	{
		this.ciudad=ciu;
		this.lugar=ini;
		this.dondemiro=Direction.NORTH;
	}
	
	/**
	 * This method checks if robot´s place is a spaceship 
	 * @return - Returns if this place is a Spaceship
	 */
	
	public boolean atSpaceship()
	{
		return this.lugar.isSpaceship();
	}
	
	/**
	 * This method turn the robot 
	 * @param rotation - Rotation is direction of turn
	 */	
	
	public void rotate(Rotation rotation)
	{
		if (rotation==Rotation.LEFT)
		{
			this.dondemiro=this.dondemiro.turnLeft(this.dondemiro);
		}
		else if (rotation== Rotation.RIGHT)
		{
			this.dondemiro=this.dondemiro.turnRight(this.dondemiro);
		}
	}
	
	/**
	 * This method move the robot in direction where he´s looking
	 * @throws InstructionExecutionException - Throw the exception of execution´s error
	 */
	
	public void move() throws InstructionExecutionException
	{
		Street s= this.ciudad.lookForStreet(this.lugar, this.dondemiro);
		if ((s==null) || !(s.isOpen()))
		{
			if (s==null)
			throw new InstructionExecutionException("WALL·E says: There is no street in direction " + this.dondemiro.toString());
			else
				throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");
		}
		else 
		{
			this.lugar=s.nextPlace(this.lugar);
		}		
	}
	
	/**
	 * This method pick the object of the place
	 * @param id - Object to pick
	 * @return - Returns the object picked
	 */
	
	public Item pickItemFromCurrentPlace(String id)
	{
		return this.lugar.pickItem(id);
	}
	
	/**
	 * This method drops the object selected
	 * @param it - Object to drop
	 */
	
	public void dropItemAtCurrentPlace(Item it)
	{
		this.lugar.addItem(it);
	}
	
	/**
	 * This method looks if this object is in this place
	 * @param id - Object to look
	 * @return - Returns if the object is in this place
	 */
	
	public boolean findItemAtCurrentPlace(String id)
	{
		int i= this.lugar.getObj().buscador(id);
		return i!=-1;
	}
	
	/**
	 * This method changes robot´s direction
	 * @param heading - New robot`s direction 
	 */
	
	public void initHeading(Direction heading)
	{
		this.dondemiro=heading;
	}
	
	/**
	 * this method shows by the console the place where the robot is
	 */
	
	public void scanCurrentPlace()
	{
		System.out.println(this.lugar.toString());
	}
	
	/**
	 * This method returns the street
	 * @return - Returns the street
	 */
	
	public Street getHeadingStreet()
	{
		return this.ciudad.lookForStreet(this.lugar, this.dondemiro);
	}
	
	/**
	 * This method returns the direction where the robot is looking
	 * @return - Returns robot´s direction 
	 */
	
	public Direction getCurrentHeading()
	{
		return this.dondemiro;
	}
	
	/**
	 * This method returns the place where the robot is
	 * @return - Returns the place
	 */
	
	public Place getCurrentPlace()
	{
		return this.lugar;
	}
	
	/**
	 * this method returns city`s map
	 * @return - Returns city`s map
	 */
	
	public City getCity()
	{
		return this.ciudad;
	}
	
}
