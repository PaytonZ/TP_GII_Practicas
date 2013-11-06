package tp.pr5;

import java.util.Observer;

/**
 * This interface is used to communicate the RobotEngine exits observer and  RobotEngine.
 * @author David Garcia y Javier Toledano.
 *
 */
public interface ObservaSalidaRobot extends Observer {

	/**
	 * This method insert one text where you need.
	 * @param g -The string with the message.
	 */
	public void insertaUnTexto(String g);
}
