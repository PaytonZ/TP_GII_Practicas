package tp.pr4.gui;

import javax.swing.JOptionPane;

/**
 * This class does visible the window Game over.
 * @author David Garcia Alvarez y Javier Toledano Regaño 
 * 
 */

@SuppressWarnings("serial")
public class VentanaGameOver extends JOptionPane {
	
	/**
	 * This method does visible window game over.
	 */
	
	public VentanaGameOver()
	{
		int ax = JOptionPane.showConfirmDialog(null, "Game Over, Wall·E doesn't have fuel. Do you want close the game?");
		setVisible(true);
        if(ax == JOptionPane.YES_OPTION)
        	System.exit(0);
	}

}
