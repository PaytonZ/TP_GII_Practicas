package tp.pr5.gui;

import javax.swing.JOptionPane;

/**
 * This class does visible the window error.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class VentanaError extends JOptionPane{

	
	/**
	 * This method does visible the window error.
	 * @param s - 
	 */
	
	public VentanaError(String s) {
		showMessageDialog(null, s);
		setVisible(true);
	}
}
