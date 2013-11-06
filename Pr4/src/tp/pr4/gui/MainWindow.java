package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr4.City;
import tp.pr4.Direction;
import tp.pr4.RobotEngine;
import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;

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
	
	
	private NavigationPanel inferior;
	private PanelSuperior superior;
	private Container panelPrincipal;
	private JPanel panel;
	private RobotEngine robot;
	private VentanaError fail;
	private ConfirmarCerrar close;
	private VentanaGameOver gameOver;
	
	/**
	 * MainWindow construction with a robotEngine 
	 * @param rob - RobotEngine.
	 */
	
	public MainWindow(RobotEngine rob)
	{
		super("WALL·E: The garbage collector");
		robot=rob;
		inicializaMainWindow();
		robot.setMainWindows(this);
	}
	
	/**
	 * This method initializes the MainWindow.
	 */

	private void inicializaMainWindow() {
		this.setSize(600,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = this.getContentPane();
		
		inferior = new NavigationPanel(robot);
		superior = new PanelSuperior(robot);
		panel = new JPanel();
		
		LayoutManager Layout = new BorderLayout();
        this.panel.setLayout(Layout);
		
		panel.add(superior,BorderLayout.NORTH);
		panel.add(inferior,BorderLayout.SOUTH);
		
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
	 * This method is the main of interface.
	 * @param args - 
	 */
	
	public static void main(String args){
		try{
			
			FileInputStream fstream = new FileInputStream(args);
			CityLoaderFromTxtFile leer= new CityLoaderFromTxtFile();
			City c = leer.loadCity(fstream);
			RobotEngine e= new RobotEngine(c, leer.getIni(), Direction.NORTH);
			final MainWindow v = new MainWindow(e);
			v.update();
	        v.arranca();
	        
		}
		catch (IOException e)
		{
			System.err.println("Error reading the map file: "+ args + " (No existe el fichero o el directorio)");
			System.exit(2);
		}
		catch (WrongCityFormatException e)
		{
			System.err.println("Incorrect format.");
			System.exit(2);
		}
		finally
		{
		}
    	
    }

	
	/**
	 * this method updates the window.
	 */
	
	public void update() {
		this.inferior.update();
		this.superior.update();		
	}
	
	/**
	 * This method generate a new window Error.
	 */
		
	public void sacarVentanaError(String s)
	{
		fail=new VentanaError(s);
	}

	/**
	 * This method generate a new window close.
	 */
	
	public void sacarVentanaCerrar()
	{
		close =new ConfirmarCerrar();
	}
	
	/**
	 * This method generate a new window game over.
	 */
	
	public void sacarVentanaGameOver()
	{
		gameOver = new VentanaGameOver();
	}
	
	/**
	 * This method return the window error.
	 * @return - Returns window error.
	 */

	public VentanaError getFail() {
		return fail;
	}
	
	/**
	 * This method changes window error.
	 * @param fail - Window error to change.
	 */

	public void setFail(VentanaError fail) {
		this.fail = fail;
	}
	
	/**
	 * This method return window close.
	 * @return - Window close.
	 */

	public ConfirmarCerrar getClose() {
		return close;
	}
	
	/**
	 * This method changes window close
	 * @param close - Window close to change.
	 */

	public void setClose(ConfirmarCerrar close) {
		this.close = close;
	}
	
	/**
	 * This method returns window game over
	 * @return - Window game over
	 */

	public VentanaGameOver getGameOver() {
		return gameOver;
	}
	
	/**
	 * This method changes the window game over
	 * @param gameOver - Window game over to change.
	 */

	public void setGameOver(VentanaGameOver gameOver) {
		this.gameOver = gameOver;
	}
	
	
	
}
