package tp.pr4;


import java.awt.event.ActionEvent;

import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.PlaceCell;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

/**
 * This class is in charge of the robot navigation features.
 * It contains the city where the robot looks for garbage, the current place where the robot is, and the current direction of the robot.
 * It contains methods to handle the different robot movements and to pick and drop items at the current place.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public class NavigationModule {
	private City ciudad;
	private Place lugar;
	private Direction dondemiro; 
	private NavigationPanel panelNavegacion;
	
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
		panelNavegacion=null;
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
		if (panelNavegacion==null)
		{
			System.out.println("Great! I have dropped " + it.getId());
		}
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
	

	/**
	 * This method returns the NavigationPanel.
	 * @return - Returns the navigationPanel.
	 */

	public NavigationPanel geNavigationPanel() {
		return panelNavegacion;
	}
	
	/**
	 * This method changes the navigationPanel.
	 * @param panelNavegacion - NavigationPanel to change in the robot.
	 */

	public void setNavigationPanel(NavigationPanel panelNavegacion) {
		this.panelNavegacion = panelNavegacion;
	}

	/**
	 * This method update the panel after making a move.
	 */
	
	public void actulizaPorMove() {
		if (!panelNavegacion.getMatrizCeldas().getCell().getText().equalsIgnoreCase(getCurrentPlace().getLugar()))
		{
			panelNavegacion.getMatrizCeldas().getCell().ponerColorGris();
			switch(getCurrentHeading()){
			case NORTH: if(panelNavegacion.getMatrizCeldas().getCellAbajo().getPlazaDeLaCelda()!=getCurrentPlace())
							panelNavegacion.getMatrizCeldas().moveNorth();
						else panelNavegacion.getMatrizCeldas().moveSouth();
						break;
			case SOUTH: if(panelNavegacion.getMatrizCeldas().getCellArriba().getPlazaDeLaCelda()!=getCurrentPlace())
							panelNavegacion.getMatrizCeldas().moveSouth(); 
						else
							panelNavegacion.getMatrizCeldas().moveNorth();
						break;
			case EAST: if(panelNavegacion.getMatrizCeldas().getCellIzquierda().getPlazaDeLaCelda()!=getCurrentPlace())
							panelNavegacion.getMatrizCeldas().moveEast(); 
						else panelNavegacion.getMatrizCeldas().moveWeast();
						break;
			case WEST: if(panelNavegacion.getMatrizCeldas().getCellDerecha().getPlazaDeLaCelda()!=getCurrentPlace())
							panelNavegacion.getMatrizCeldas().moveWeast(); 
						else panelNavegacion.getMatrizCeldas().moveEast(); 
						break;			
			default:
				break;
			}
			this.panelNavegacion.getMatrizCeldas().getCell().insertarText(getCurrentPlace().getLugar());
			this.panelNavegacion.getMatrizCeldas().getCell().setPlazaDeLaCelda(getCurrentPlace());
		}
		
	}
	
	/**
	 * This method inicialice the NavigationPanel
	 */
	
	public void inicilizaElPanel()
	{
		this.panelNavegacion.getMatrizCeldas().getCell().insertarText(getCurrentPlace().getLugar());
		this.panelNavegacion.getMatrizCeldas().getCell().setPlazaDeLaCelda(getCurrentPlace());
	}

	/**
	 * This method shows place´s information into the log after press the button. 
	 * @param arg0 - Press event.
	 */
	
	public void botonPulsado(ActionEvent arg0) {
		panelNavegacion.insertarTextAlLog(((PlaceCell) arg0.getSource()).infoDeLaPlazaBoton());
		
	}
	
	
}
