/**
 * 
 */
package tp.pr5;


/**
 * This class is parent class GUIcontroller, 
 * and consists of a RobotEngine and gives a reference to observers of the robot.
 * @author DAvid Garcia y Javier Toledano
 *
 */
public abstract class Controller {
	protected RobotEngine robot;
	protected RobotEngineObserver observadorRobot;
	protected NavigationObserver observadorNavegador;
	protected ObservaSalidaRobot observaSalida;
	
	/**
	 * Default constructor.
	 * @param game
	 */
	public Controller(RobotEngine game)
	{
		robot=game;
	}
	public abstract void startController();
	
	/**
	 * This method set a reference to the observer of RobotEngine
	 * @param gameObserver
	 */
	public void registerEngineObserver(RobotEngineObserver gameObserver)
	{
		observadorRobot=gameObserver;	
	}
	
	/**
	 * This method set a reference to the observer of NavigationModule.
	 * @param playerObserver
	 */
	public void registerRobotObserver(NavigationObserver playerObserver)
	{
		observadorNavegador=playerObserver;
	}
	
	/**
	 * This method set a reference to the observer of ExitObserver.
	 * @param observer
	 */
	public void registrerObserverOut(ObservaSalidaRobot observer)
	{
		observaSalida=observer;
	}
}
