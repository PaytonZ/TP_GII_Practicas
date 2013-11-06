/**
 * 
 */
package tp.pr5;

import java.util.Observer;

/**
 * This interface is used to communicate the RobotEngine observer and  RobotEngine.
 * @author David Garcia y Javier Toledano
 *
 */
public interface RobotEngineObserver extends Observer {
	
	/**
	 * Get the rotation to turn.
	 * @return -Returns the rotation.
	 */
	Rotation cogerRotation();
	
	/**
	 * Get the item selected in the JTable.
	 * @return - Returns The item.
	 */
	public Object selecionadoTabla();
}
