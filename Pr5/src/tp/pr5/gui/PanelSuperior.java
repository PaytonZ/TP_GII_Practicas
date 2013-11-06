package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tp.pr5.RobotEngineObserver;
import tp.pr5.Rotation;

/**
 * This method generate the panel higher and methods to modify it.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class PanelSuperior extends JPanel implements RobotEngineObserver{
	private JMenuBar menuBar;
	private JMenu file;
	private PanelBotonesAccion panelIz;
	private RobotPanel panelDr;
	private JMenuItem quitar;
	
	/**
	 * PAnelSuperior construction with a robotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public PanelSuperior()
	{
		inicializaPanelSuperiror();
	}
	
	/**
	 * PanelSuperior construction with a EventListener.
	 * @param controlador - EventListener to use.
	 */
	
	public PanelSuperior(EventListener controlador)
	{
		inicializaPanelSuperiror();
	}
	
	/**
	 * This method initialize the PanelSuperior.
	 */

	private void inicializaPanelSuperiror() {
		menuBar = new JMenuBar();
		panelIz = new PanelBotonesAccion();
		panelDr = new RobotPanel();
		file = new JMenu("File");
		quitar= new JMenuItem("Quit");
		file.add(quitar);
		
		this.setPreferredSize(new java.awt.Dimension(700, 150));
		
		menuBar.add(file);
		menuBar.setName("menuBar");
		
		quitar.setName("Bquitar");
		
		LayoutManager elLayaout = new BorderLayout();
		this.setLayout(elLayaout);
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(panelIz, BorderLayout.WEST);
		this.add(panelDr, BorderLayout.EAST);	
		
	}

	/**
	 * This method sets the controller in each panel
	 * @param e -The controller.
	 */
	public void fijarControlador(EventListener controlador)
	{
		quitar.addActionListener((ActionListener)controlador);
		panelIz.fijarControlador(controlador);
	}
	
	/**
	 * This method returns the robotPanel.
	 * @return - Returns the robotPanel.
	 */
	
	public RobotPanel getRobotPanel()
	{
		return panelDr;
	}


	/**
	 * Return the rotation selected in the "Instructions" panel. 
	 */
	@Override
	public Rotation cogerRotation() {
		
		return panelIz.cogerRotacion();
	}

	/**
	 * Get the item selected in the JTable.
	 * @return - Returns The item.
	 */
	@Override
	public Object selecionadoTabla() {
		int a = panelDr.getJTable().getSelectedRow();
		Object s;
		if (a!=-1)
		{
			s = panelDr.getJTable().getValueAt(a, 0);
		}
		else s = null;
		return s;
	}

	/**
	 * this method update the RobotPanel.
	 */
	@Override
	public void update(Observable o, Object arg) {
		panelDr.update(o, arg);
		
	}

	
}
