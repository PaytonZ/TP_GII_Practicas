package tp.pr5;


import java.util.Observable;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * This class is in charge of the robot navigation features.
 * It contains the city where the robot looks for garbage, the current place where the robot is, and the current direction of the robot.
 * It contains methods to handle the different robot movements and to pick and drop items at the current place.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 *IMPORTANTE: NO HEMOS QUITADO LOS GETTERS PUESTO QUE EN LA DOCUMENTACIÓN VIENEN,
 *			PODRIAMOS HABERLOS QUITADO, PERO LOS TEST LOS USAN. HEMOS CREADO LA INTERFAZ
 *			OBSERVANAVIGATION PARA ASEGURARNOS QUE LOS QUE RECIBAN EL MODELO AL LLAMAR
 *			A notifyObservers() CASETEAMOS CON LA INTERFAZ DE ESTE MODO SOLO TIENE ACCESO A LOS
 *			GETTERS Y NO PUEDE MODIFICAR EL MODELO DE NINGUNA FORMA.
 */
public class NavigationModule extends Observable implements ObservaNavigation{
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
		ciudad=ciu;
		lugar=ini;
		dondemiro=Direction.NORTH;
	}
	
	/**
	 * This method inform the observers of the NavigationModule.
	 */
	public void informarObservadores(){
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(); // notifica a los observadores.
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
	 * This method return the robot to the place when he was before to move. 
	 */
	public void unMove()
	{
		Street s= this.ciudad.lookForStreet(this.lugar, dondemiro.opposite(dondemiro));
		lugar=s.nextPlace(this.lugar);
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
	 * This method pick a item of the place where the NavigationModule is.
	 * @param s - Name of the item.
	 * @return	- Returns the item.
	 */
	public Item cogerItemDeLaPlaza(String s)
	{
		Item a=lugar.pickItem(s);
		return a;
	}
	
	
}
