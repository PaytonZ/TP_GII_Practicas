/**
 * 
 */
package tp.pr5;

/**	
 * This interface is used for have access to the Description, Lugar and isSpaceship of one place.
 * @author David Garcia y Javier Toledano
 *
 */
public interface PlaceInfo {

	/**
	 * Getter of the description
	 * @return -Returns description.
	 */
	String getDescription();
	
	/**
	 * Getter of the Lugar.
	 * @return -Returns lugar.
	 */
	String getLugar();
	
	/**
	 * Getter of is the Spaceship.
	 * @return -Return is the spaceship.
	 */
	boolean isSpaceship();
}
