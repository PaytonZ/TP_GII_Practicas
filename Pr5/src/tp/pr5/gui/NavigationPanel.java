package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.NavigationObserver;
import tp.pr5.ObservaNavigation;
import tp.pr5.RobotEngine;
import tp.pr5.gui.images.LaImagenRobot;

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
public class NavigationPanel extends JPanel implements NavigationObserver {
	
	private LaImagenRobot imagen;
	private MatrizCeldas navegador;
	private JTextArea infoLugar;
	private JPanel panelDeLaInfo;
	
	/**
	 * NavigationPanel construction with a RobotEngine.
	 * @param rob - the RobotEngine to use.
	 */
	
	public NavigationPanel()
	{
		inicializaNavigationPanel();
	}
	
	/**
	 * NavigationPanel construction with a RobotEngine and EventListener
	 * @param controlador - EventListener to use.
	 * @param rob - RobotEngine to use.
	 */
	
	public NavigationPanel(EventListener controlador, RobotEngine rob)
	{
		inicializaNavigationPanel();
	}
	
	/**
	 * This method initialize the NavigationPanel.
	 */

	private void inicializaNavigationPanel() {
		imagen = new LaImagenRobot();
		navegador = new MatrizCeldas();
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

	/**
	 * This method update the image, the matriz and the text with the information about the place.
	 */
	@Override
	public void update(Observable o, Object arg) {
		imagen.update(o, arg);
		navegador.update(o,arg);
		infoLugar.setText(((ObservaNavigation)o).getCurrentPlace().toString());
		
	}

	
	/**
	 * This method sets the controller in each panel
	 * @param e -The controller.
	 */
	public void fijarControlador(EventListener e) {
		navegador.fijarControlador(e);
		
	}

}
