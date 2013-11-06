package tp.pr4;

import java.util.ArrayList;
import java.util.Scanner;

import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.PanelSuperior;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/**
 * This class represents the robot engine.
 * It controls robot movements by processing the instructions introduced with the keyboard.
 * The engine stops when the robot arrives at the space ship, runs out of fuel or receives a quit instruction. 
 * The robot engine is also responsible for updating the fuel level and the recycled material according to the actions that the robot performs in the city. 
 * The robot engine also contains an inventory where the robot stores the items that it collects from the city. 
 * The robot engine can also have a reference to a window (in swing) and a robot panel where the robot will display some information if the swing interface is in use.
 *
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

public class RobotEngine {
	private NavigationModule map;
	private int sopa;
	private int recicla; 
	private ItemContainer mochila;
	private Instruction ins;
	private Scanner sc;
	private boolean quitar;
	private NavigationPanel mPanelInferior;
	private PanelSuperior mPanelSuperior;
	private MainWindow ventanaVista;
	private ArrayList<Instruction> unDo;
	
	/**
	 * RobotEngine construction
	 * @param cm - City´s map
	 * @param ini - Initial place
	 * @param dir - Initial direction where the robot is looking
	 */
	
	public RobotEngine(City cm, Place ini, Direction dir)
	{
		this.map=new NavigationModule(cm,ini);
		this.sopa=100;
		this.mochila= new ItemContainer();
		this.recicla=0;
		this.quitar=false;
		this.sc = new Scanner(System.in);
		unDo= new ArrayList<Instruction>();
	}
	
	/**
	 * This method starts robotEngine
	 */
	
	public void startEngine()
	{
		String r=this.map.getCurrentPlace().toString();
		mostrarPantalla(r); 
		mostrarPantalla(this.dondeMira());
		this.printRobotState();
		while (!this.map.getCurrentPlace().isSpaceship() && !this.quitar && this.sopa>0)
		{
			mostrarPrompt();
			String l =leerString(this.sc);
			try {
				this.ins=Interpreter.generateInstruction(l);
				this.communicateRobot(this.ins);
			} catch (WrongInstructionFormatException e) {
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
		}
		if(this.map.getCurrentPlace().isSpaceship())
		{
			mostrarPantalla("WALL·E says: I am at my spaceship. Bye bye"); 
		}
		if (this.sopa<0)
		{
			mostrarPantalla("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
	}
	
	/**
	 * This method execute a instruction
	 * @param c - Robot´s instruction
	 */
	
	public void communicateRobot(Instruction c)
	{
		try
		{	
			if(sopa>=5 || !c.gastaFuel())
			{
				c.configureContext(this, this.map, this.mochila);
				c.execute();
				if (c.canUnExecute())
					unDo.add(c);
			}			
			if (mPanelInferior!=null && mPanelSuperior!=null)
			{
				update();
			}
			if (mPanelInferior!=null && mPanelSuperior!=null && sopa<=0)
			{
				ventanaVista.sacarVentanaGameOver();
			}
		} 
		catch (InstructionExecutionException e) 
		{			
			if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
				System.out.println(e.getMessage());
			else 
				ventanaVista.sacarVentanaError(e.getMessage());
		}
	}
	
	/**
	 * This method adds fuel
	 * @param fuel - Fuel´s amount to add
	 */
	
	public void addFuel (int fuel)
	{
		this.sopa=this.sopa+fuel;
	}
	
	/**
	 * This method adds RecycledMaterial
	 * @param basura - RecycledMaterial´s amount to add
	 */
	
	public void addRecycledMaterial (int basura)
	{
		this.recicla=this.recicla+basura;
	}
	
	/**
	 * This method converts the direction where the robot is looking to string
	 * @return - Returns a string with la direction where the robot is looking
	 */
	
	public String dondeMira() 
	{
		return "WALL·E is looking at direction " + this.map.getCurrentHeading().toString();
	}
	
	/**
	 * This method shows this string by the console
	 * @param a - Strieng to show by the console
	 */
	
	private static  void mostrarPantalla(String a)
	{
		System.out.println(a);
	}
	
	/**
	 * This method  shows robot´s state
	 */
	
	public void printRobotState()
	{
		if (this.sopa>0)
		{
			System.out.println("      "+"* My power is "+ this.sopa);
		}
		else System.out.println("      "+"* My power is 0");
		System.out.println("      "+"* My reclycled material is "+ this.recicla);
	}
	
	/**
	 * This method shows WALLE·E>
	 */
	
	private static void mostrarPrompt() 
	{
		System.out.print("WALL·E> ");
	} 
	
	/**
	 * This method reads the console and converts this read in a string
	 * @param sc - Variable scanner for read by the console
	 * @return
	 */
	
	private static String leerString(Scanner sc) 
	{
		String a= sc.nextLine();
		return a;
	}
	
	/**
	 * This method consumes fuel in the movement
	 */
	
	public void moving()
	{
		this.sopa=this.sopa-5;
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
		{
			System.out.println("WALL·E says: Moving in direction "+ this.map.getCurrentHeading().toString());
			System.out.println(this.map.getCurrentPlace().toString());
			if (this.map.getCurrentPlace().isSpaceship())
			{
				System.out.println("The place is empty. There are no objects to pick");
				System.out.println("");
			}
			System.out.println("");
			printRobotState();
		}
	}
	
	/**
	 * This method does a unMove and return the fuel consumed to the robot. 
	 */
	
	public void unMoving()
	{
		sopa=sopa+5;
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
		{
			System.out.println(this.map.getCurrentPlace().toString());
			if (this.map.getCurrentPlace().isSpaceship())
			{
				System.out.println("The place is empty. There are no objects to pick");
				System.out.println("");
			}
			System.out.println("");
			printRobotState();
		}
	}
	
	/**
	 * This method returns the street where the robot is looking
	 * @return - Returns the street where the robot is looking
	 */
	
	public Street getHeadingStreet()
	{
		return this.map.getCity().lookForStreet(this.map.getCurrentPlace(), this.map.getCurrentHeading());
	}
	
	/**
	 * This method does that finish the program by quit
	 */
	
	public void requestQuit()
	{
		this.quitar=true;
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
			System.out.println("WALL·E says: I have communication problems. Bye bye.");
		else ventanaVista.sacarVentanaCerrar();
		
	}
	
	/**
	 * This method shows robot´s help by the console
	 */

	public void requestHelp() {
		mostrarPantalla ( Interpreter.interpreterHelp());
	}
	
	/**
	 * This method returns robot´s fuel
	 * @return - Returns robot´s fuel
	 */
	
	public int getFuel()
	{
		return this.sopa;
	}
	
	/**
	 * This method returns recycled material
	 * @return - Returns the recycled material
	 */

	public int getRecycledMaterial() {
		
		return this.recicla;
	}

	/**
	 * This method returns robot´s direction.
	 * @return - Returns robot´s direction
	 */
	
	public Direction obtenerDireccion() {
		
		return this.map.getCurrentHeading();
	}

	/**
	 * This method return robot´s navigationModule.
	 * @return - return navigationModule.
	 */
	
	public NavigationModule getNavigationModule()
	{
		return map;
	}
	
	/**
	 * This method returns the lower panel of the interface.
	 * @return - Returns lower panel.
	 */

	public NavigationPanel getNavigationPanel() {
		return mPanelInferior;
	}
	
	/**
	 * This method changes the lower panel.
	 * @param mPanelInferior - Lower panel to introduce in the interface.
	 */

	public void setNavigationPanel(NavigationPanel mPanelInferior) {
		this.mPanelInferior = mPanelInferior;
	}
	
	/**
	 * This method returns the higher panel.
	 * @return- Returns the higher panel.
	 */

	public PanelSuperior getmPanelSuperior() {
		return mPanelSuperior;
	}

	/**
	 * This method changes the higher panel.
	 * @param mPanelSuperior - Higher panel to introduce to the interface.
	 */
	
	public void setmPanelSuperior(PanelSuperior mPanelSuperior) {
		this.mPanelSuperior = mPanelSuperior;
	}
	
	/**
	 * This method updates the lower panel and the higher panel.
	 */
	
	public void update()
	{
		mPanelInferior.update();
		mPanelSuperior.update();
	}
	
	/** Informs the player that he has exhausted the item that has been used.
	 * 
	 * @param it the id of the item;
	 */
	public void elItemNoSeAnadio(String it) {
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
		{
			System.out.println("WALL·E says: What a pity! I have no more "+ it + " in my inventory");
		}
		
	}
	
	/**
	 * This method turns tthe robot in the panel
	 */

	public void turning() {
		sopa=sopa-5;
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
		{
			System.out.println(dondeMira());
			printRobotState();
		}
		
		
	}
	
	/**
	 * This method returns the object selected in the table, in case of exist.
	 * @return - Retruns the object in case of exists or null.
	 */
	
	public Object selecionadoTabla()
	{
		int a = mPanelSuperior.getJTable().getSelectedRow();
		Object s;
		if (a!=-1)
		{
			s = mPanelSuperior.getJTable().getValueAt(a, 0);
		}
		else s = null;
		return s;
	}
	
	/**
	 * This method insert the ItemContainer in the table.
	 */
	
	public void InsertarElInventario()
	{
		mPanelSuperior.getRobotPanel().InsertarObjetosAlaTabla(mochila);
	}
	
	/**
	 * This method adds to the itemContainer a item.
	 * @param it - Item t add.
	 * @param a  - ItemContainer when add.
	 */
	
	public void anadirItemALaMochila(Item it, ItemContainer a)
	{
		a.addItem(it);
		if (mPanelInferior==null && mPanelSuperior==null && ventanaVista== null)
		{
			System.out.println("WALL·E says: I am happy! Now I have "+ it.getId());
		}
	}
	
	/**
	 * This method changes the main window.
	 * @param a - Main window to change.
	 */
	
	public void setMainWindows(MainWindow a)
	{
		ventanaVista=a;
	}
	
	/**
	 * This method reduces to the fuel into amount given.
	 * @param power - Fuel to reduce.
	 */

	public void subFuel(int power) {
		sopa = sopa -power;
		
	}
	
	/**
	 * This method remove recycled material to the robot after undo. 
	 * @param recicla2 - Recycled material to undoing.
	 */

	public void subRecycledMaterial(int recicla2) {
		recicla=recicla-recicla2;
		
	}

	/**
	 * this method return the fuel to the robot after undo a turn.
	 */
	
	public void unTurning() {
		sopa=sopa+5;
		
	}
	
	/**
	 * This method returns the instruction to undo.
	 * @return - Returns instruction.
	 */

	public Instruction getInstruction() {
		Instruction a = null;
		if (unDo.size()!=0)
		{
			a =unDo.get(unDo.size()-1);
		}
		return a;
	}
	
	/**
	 * This method deletes the instruction after undoing. 
	 */

	public void deleteLastInstruction() {
		unDo.remove(unDo.size()-1);
		
	}

}
