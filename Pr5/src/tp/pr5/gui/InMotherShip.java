package tp.pr5.gui;

import javax.swing.JOptionPane;

/**
 * This class is a JOptionPane whit the message "WALL·E says: I am at my spaceship. Do you want close the game?"
 * @author David Garcia y Javier Toledano
 *
 */
@SuppressWarnings("serial")
public class InMotherShip extends JOptionPane {

	/**
	 * Default builder.
	 */
	public InMotherShip()
	{
		int ax = JOptionPane.showConfirmDialog(null, "WALL·E says: I am at my spaceship. Do you want close the game?");
		setVisible(true);
        if(ax == JOptionPane.YES_OPTION)
        	System.exit(0);
	}

}
