package tp.pr5.gui.images;

import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import tp.pr5.Direction;
import tp.pr5.ObservaNavigation;
/**
 * This class represents the image of the robot, which changes when rotating the robot.
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
@SuppressWarnings("serial")
public class LaImagenRobot extends JLabel {
	/**
	 * The construction using NavigationModule for get the direction;
	 * @param rob
	 */
	public LaImagenRobot()
	{
	}
	
	/**
	 * The method to update the image
	 */
	public void update(Observable o, Object arg) {
		
		  cambiarImagen(((ObservaNavigation)o).getCurrentHeading());
		 
		
	}
	
	/**
	 * If the robot has changed direction this method changes the image.
	 * @param a The direction  to which the robot looks
	 */
	private void cambiarImagen(Direction a)
	{
		switch (a) {
		case EAST: this.setIcon(new ImageIcon(LaImagenRobot.class.getResource("walleEast.png")));		
			break;
		case WEST: this.setIcon(new ImageIcon(LaImagenRobot.class.getResource("walleWest.png")));		
			break;
		case NORTH: this.setIcon(new ImageIcon(LaImagenRobot.class.getResource("walleNorth.png")));		
			break;
		case SOUTH: this.setIcon(new ImageIcon(LaImagenRobot.class.getResource("walleSouth.png")));		
			break;
		case UNKNOWN:
		default:
			break;
		}
	}
}
