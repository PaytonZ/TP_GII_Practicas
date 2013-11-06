package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import tp.pr4.RobotEngine;
import tp.pr4.instructions.QuitInstruction;

/**
 * This method generate the panel higher and methods to modify it.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class PanelSuperior extends JPanel implements ActionListener {
	private JMenuBar menuBar;
	private JMenu file;
	private PanelBotonesAccion panelIz;
	private RobotPanel panelDr;
	private RobotEngine robot;
	private JMenuItem quitar;
	
	/**
	 * PAnelSuperior construction with a robotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public PanelSuperior(RobotEngine rob)
	{
		robot=rob;
		inicializaPanelSuperiror();
		robot.setmPanelSuperior(this);
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
		panelIz = new PanelBotonesAccion(robot);
		panelDr = new RobotPanel(robot);
		file = new JMenu("File");
		quitar= new JMenuItem("Quit");
		quitar.addActionListener(this);
		file.add(quitar);
		
		this.setPreferredSize(new java.awt.Dimension(700, 150));
		
		menuBar.add(file);
		menuBar.setName("menuBar");
		
		LayoutManager elLayaout = new BorderLayout();
		this.setLayout(elLayaout);
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(panelIz, BorderLayout.WEST);
		this.add(panelDr, BorderLayout.EAST);
	
		
		
		
	}

	/**
	 * This method updates the robotPanel.
	 */
	
	public void update() {
		panelDr.update();
	}
	
	/**
	 * This method returns the table of RobotPanel.
	 * @return - Returns table of robotPanel.
	 */
	
	public JTable getJTable()
	{
		return panelDr.getJTable();
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
	 * This method generate new quit instruction if read quitar.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==quitar)
		{
			robot.communicateRobot(new QuitInstruction());
		}
		
	}

	
}
