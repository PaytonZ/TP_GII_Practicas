package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr5.NavigationObserver;
import tp.pr5.ObservaSalidaRobot;
import tp.pr5.RobotEngineObserver;

/**
 * This class creates the window for the Swing interface. The MainWindow contains the following components:
 *
 *  A menu with the QUIT action.
 *  An Action panel with several buttons to perform MOVE, TURN, OPERATE, PICK, and DROP actions. 
 *  Additionally it has a combo box of turn rotations and a text field to write the name of the item that the robot wants to pick from the current place.
 *  A RobotPanel that displays the robot information (fuel and recycled material) and the robot inventory, which shows a table with item names and descriptions that the robot carries. 
 *  The user can select the items contained in the inventory in order to DROP or OPERATE an item
 *  A CityPanel that represents the city. It shows the places that the robot has visited and an icon that represents the robot heading. 
 *  The robot heading is updated when the user performs a TURN action. The visible places are updated when the robot performs a MOVE action. 
 *  Once a place is visited, the user can click on it in order to display the place description (similar to the RADAR command).
 *
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame  {
	
	
	private static NavigationPanel inferior;
	private static PanelSuperior superior;
	private Container panelPrincipal;
	private JPanel panel;
	private static InfoPanel panelInfo;
	
	/**
	 * MainWindow construction with a robotEngine 
	 * @param rob - RobotEngine.
	 */
	
	public MainWindow()
	{
		super("WALL·E: The garbage collector");
		inicializaMainWindow();
	}
	
	/**
	 * This method initializes the MainWindow.
	 */

	private void inicializaMainWindow() {
		this.setSize(600,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = this.getContentPane();
		
		inferior = new NavigationPanel();
		superior = new PanelSuperior();
		panel = new JPanel();
		panelInfo=new InfoPanel();
		
		LayoutManager Layout = new BorderLayout();
        this.panel.setLayout(Layout);
		
		panel.add(superior,BorderLayout.NORTH);
		panel.add(inferior,BorderLayout.CENTER);
		panel.add(panelInfo,BorderLayout.SOUTH);
		
		
		panelPrincipal.add(panel);
		this.pack();
	}
	
	/**
	 * This method does visible the window.
	 */
	
	public void arranca(){
        EventQueue.invokeLater(new Runnable(){
        	public void run() {
        		setVisible(true);
        	}
        });		
	}
	
	/**
	 * This method sets the controller in each panel
	 * @param e -The controller.
	 */
	public void fijarControlador(EventListener e) {
		superior.fijarControlador(e);
		inferior.fijarControlador(e);
		
	}

	/**
	 * This method return the RobotEngine Observer.
	 * @return -The RobotEngine observer.
	 */
	public RobotEngineObserver observadorDelRobot() {
		return superior;
	}

	/**
	 * This method return the NavigationModule Observer.
	 * @return -The observer
	 */
	public NavigationObserver observadorDelNavegador() {
		return inferior;
	}

	/**
	 * This method return the observer of the Robot messages.
	 * @return -the observer.
	 */
	public ObservaSalidaRobot observadorDeLosMensajesRobot() {
		return panelInfo;
	}

	

	
	


	
	
}
