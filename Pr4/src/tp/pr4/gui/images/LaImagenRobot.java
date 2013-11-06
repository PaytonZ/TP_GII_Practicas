package tp.pr4.gui.images;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import tp.pr4.Direction;
import tp.pr4.NavigationModule;
/**
 * This class represents the image of the robot, which changes when rotating the robot.
 * @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
@SuppressWarnings("serial")
public class LaImagenRobot extends JLabel {
	private NavigationModule robot;
	/**
	 * The construction using NavigationModule for get the direction;
	 * @param rob
	 */
	public LaImagenRobot(NavigationModule rob)
	{
		robot=rob;
		cambiarImagen(rob.getCurrentHeading());
	}
	
	/**
	 * The method to update the image
	 */
	public void update() {
		cambiarImagen(robot.getCurrentHeading());
		
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
