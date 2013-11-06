package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.RobotEngine;
import tp.pr4.gui.images.LaImagenRobot;

/**
 * This class is in charge of the panel that displays the information about the robot heading and the city that is traversing.
 * It contains the grid that represents the city in the Swing interface,
 * a text area to show the place descriptions, and a label with an icon which represents the robot heading.
 * The 11x11 grid contains PlaceCell objects and the first place starts at (5,5).
 * This panel will update the visited places when the robot moves from one place to another.
 * Additionally it will show the place description on a text area if the user clicks on a visited place. 
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	
	private LaImagenRobot imagen;
	private MatrizCeldas navegador;
	private JTextArea infoLugar;
	private JPanel panelDeLaInfo;
	private RobotEngine robot;
	
	/**
	 * NavigationPanel construction with a RobotEngine.
	 * @param rob - the RobotEngine to use.
	 */
	
	public NavigationPanel(RobotEngine rob)
	{
		robot=rob;
		inicializaNavigationPanel();		
		robot.setNavigationPanel(this);
		robot.getNavigationModule().setNavigationPanel(this);
		robot.getNavigationModule().inicilizaElPanel();
	}
	
	/**
	 * NavigationPanel construction with a RobotEngine and EventListener
	 * @param controlador - EventListener to use.
	 * @param rob - RobotEngine to use.
	 */
	
	public NavigationPanel(EventListener controlador, RobotEngine rob)
	{
		robot=rob;
		inicializaNavigationPanel();
	}
	
	/**
	 * This method initialize the NavigationPanel.
	 */

	private void inicializaNavigationPanel() {
		imagen = new LaImagenRobot(robot.getNavigationModule());
		navegador = new MatrizCeldas(robot.getNavigationModule());
		panelDeLaInfo = new JPanel();
		infoLugar = new JTextArea();
		
		panelDeLaInfo.setBorder(new TitledBorder("Log"));		
		infoLugar.setEditable(false);
		infoLugar.setPreferredSize(new java.awt.Dimension(800, 100));
		infoLugar.setBackground(Color.WHITE);
		
		panelDeLaInfo.add(infoLugar);
		
		LayoutManager elLayout = new BorderLayout();
		this.setLayout(elLayout);
		
		this.add(imagen, BorderLayout.WEST);
		this.add(navegador, BorderLayout.EAST);
		this.add(panelDeLaInfo, BorderLayout.SOUTH);
		
		
		
		
	}

	/**
	 * This method updates the NavigationPanel.
	 */

	public void update() {
		imagen.update();
		navegador.update();
		infoLugar.setText(robot.getNavigationModule().getCurrentPlace().toString());
		
	}
	
	/**
	 * This method insert a information of the place.
	 * @param s - Information of the place.
	 */
	
	public void insertarTextAlLog(String s)
	{
		infoLugar.setText(s);
	}
	
	/**
	 * This method returns the matrix.
	 * @return - Returns the matrix
	 */
	
	public MatrizCeldas getMatrizCeldas()
	{
		return navegador;
	}

}
