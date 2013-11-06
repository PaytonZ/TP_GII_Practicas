package tp.pr5.gui;

import javax.swing.JOptionPane;

/**
 * This class close the panel option.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class ConfirmarCerrar extends JOptionPane {

	/**
	 * This method close the panel option.
	 */
	
	public ConfirmarCerrar()
	{
		int ax = JOptionPane.showConfirmDialog(null, "Quit?");
		setVisible(true);
        if(ax == JOptionPane.YES_OPTION)
        	System.exit(0);
        
	}
}
