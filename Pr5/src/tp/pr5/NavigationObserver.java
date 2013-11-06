/**
 * 
 */
package tp.pr5;

import java.util.Observer;

/**	
 * This interface is used to communicate the NavigationModule observer and  NavigationModule.
 * @author David Garcia y Javier Toledano
 *
 */
public interface NavigationObserver extends Observer {

	/**
	 * This method insert one text where you need.
	 * @param infoDeLaPlazaBoton -The string with the message.
	 */
	void insertarTextAlLog(String infoDeLaPlazaBoton);
}
