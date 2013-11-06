package tp.pr5;

/**
 * This interface is used to cast in the GUI and you are sure, they can't edit the model, only do getters.
 * @author David Garcia y Javier Toledano
 *
 */
public interface ObservaNavigation {

	/**
	 * This method returns the direction where the robot is looking
	 * @return - Returns robot´s direction 
	 */
	
	public Direction getCurrentHeading();
	
	/**
	 * This method returns the place where the robot is
	 * @return - Returns the place
	 */
	
	public Place getCurrentPlace();
	
	/**
	 * This method returns the street
	 * @return - Returns the street
	 */
	
	public Street getHeadingStreet();
	
}
