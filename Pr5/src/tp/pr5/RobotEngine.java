package tp.pr5;

import java.util.ArrayList;
import java.util.Observable;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

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
 *
 *
 *
 *
 *
 *	IMPORTANTE: HEMOS ELIMINADO LOS GETTER, ES DECIR NUESTRA APLICACION FUNCIONA SI LOS QUITAS DE ESTA CLASE,
 *				PERO HEMOS MANTENIDO LOS GETTER DEL FUEL Y DEL MATERIA RECICLADO POR QUE LOS TEST LOS USAN, Y SIN ELLOS 
 *				NO PASARIAMOS LOS TEST. NO HEMOS ELIMINADO LOS GETTER DE LOS STRING CON LO QUE EL ROBOT DEBERIA DE DECIR POR
 *				CONSOLA, PUESTO QUE HEMOS CONSIDERADO QUE NO SON IMPORTANTES PARA LA ENCAPSULACIÓN. PODRIAMOS HABERLOS METIDO
 *				EN EL arg1 DEL MÉTODO update(arg0,arg1) DE LOS OBSERVADORES, COMO HICIMOS CON EL FUEL Y CON EL MATERIAL RECICLADO
 *				PERO NO LO VIMOS NECESARIO.
 *				
 */

public class RobotEngine extends Observable{
	private NavigationModule map;
	private int sopa;
	private int recicla; 
	private ItemContainer mochila;
	private boolean quitar;
	private ArrayList<Instruction> unDo;
	private String diceRobotGrafica;
	private String diceRobotConsola;
	private boolean huboE;
	private String mensageEscepcion;
	private InformacionObservadores infoObs;
	
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
		unDo= new ArrayList<Instruction>();
		diceRobotGrafica= "Robot attributes has been updated: (" + sopa +"," + recicla + ")";
		huboE=false;
	}
	
	/**
	 * This method give you the description of the place where the robot is.
	 * @return -Returns the description of the place.
	 */
	public String InformacionPlaza()
	{
		return map.getCurrentPlace().toString();
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
		} 
		catch (InstructionExecutionException e) 
		{			
			diceRobotGrafica=e.getMessage();
			diceRobotConsola=e.getMessage();
			mensageEscepcion=e.getMessage();
			huboE=true;
		}
		rellenarInfoObs();
		informarObservadores();
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
	 * This method  shows robot´s state
	 */
	
	public String printRobotState()
	{
		String ret;
		if (this.sopa>0)
		{
			ret ="      "+"* My power is "+ sopa +Interpreter.LINE_SEPARATOR;
		}
		else ret="      "+"* My power is 0"+Interpreter.LINE_SEPARATOR;
		ret=ret+"      "+"* My recycled material is "+ recicla;
		return ret;
	}
	

	
	
	
	/**
	 * This method consumes fuel in the movement
	 */
	
	public void moving()
	{
		this.sopa=this.sopa-5;
		diceRobotConsola= "WALL·E says: Moving in direction "+ map.getCurrentHeading().toString() + Interpreter.LINE_SEPARATOR+
						map.getCurrentPlace().toString()+Interpreter.LINE_SEPARATOR;
		if (map.getCurrentPlace().isSpaceship())
		{
			diceRobotConsola=diceRobotConsola +"The place is empty. There are no objects to pick"+ Interpreter.LINE_SEPARATOR;
		}
		diceRobotConsola=diceRobotConsola+printRobotState();
	}
	
	/**
	 * This method does a unMove and return the fuel consumed to the robot. 
	 */
	
	public void unMoving()
	{
		sopa=sopa+5;
		diceRobotConsola= map.getCurrentPlace().toString()+Interpreter.LINE_SEPARATOR;
		if (map.getCurrentPlace().isSpaceship())
		{
			diceRobotConsola=diceRobotConsola +"The place is empty. There are no objects to pick"+ Interpreter.LINE_SEPARATOR;
		}
		diceRobotConsola=diceRobotConsola+printRobotState();
	}
	

	
	/**
	 * This method does that finish the program by quit
	 */
	
	public void requestQuit()
	{
		this.quitar=true;
		diceRobotConsola="WALL·E says: I have communication problems. Bye bye.";	
	}
	
	/**
	 * This method shows robot´s help by the console
	 */

	public void requestHelp() {
		diceRobotConsola=Interpreter.interpreterHelp();
	}

	

	
	/**
	 * This method turns the robot in the panel
	 */

	public void turning() {
		sopa=sopa-5;

		diceRobotConsola= dondeMira()+Interpreter.LINE_SEPARATOR+
						printRobotState();
		
	}
	
	

	
	/**
	 * This method adds to the itemContainer a item.
	 * @param it - Item t add.
	 * @param a  - ItemContainer when add.
	 */
	
	public void anadirItemALaMochila(Item it, ItemContainer a)
	{
		a.addItem(it);
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

	/**
	 * This method inform the observers.
	 */
	public void informarObservadores(){
        setChanged(); // establece que ha habido un cambio.
        notifyObservers(infoObs); // notifica a los observadores.
        map.informarObservadores();
    }
	

	/**
	 * This method inform if the game is end.
	 * @return -If the game is end.
	 */
	public boolean isTheEnd() {
		return quitar;
	}

	/**
	 * This method changes to false quitar if the game is ended.
	 */
	public void noQuitar() {
		quitar=false;
		
	}
	
	/**
	 * this method inform if the robot is in the SpaceShip.
	 * @return -Return if the robot is in the Spaceship
	 */
	public boolean isInMotherShip() {
		return map.getCurrentPlace().isSpaceship();
	}
	
	/**
	 * Getter of the string where be the information for the GUI.
	 * @return -Returns The message for the GUI.
	 */
	public String getdiceRobotGrafica() {
		return diceRobotGrafica;
	}

	/**
	 * Setter of the string where be the information for the GUI.
	 *  @param  diceRobot - The message to set.
	 */
	public void setdiceRobotGrafica(String diceRobot) {
		diceRobotGrafica = diceRobot;
	}

	/**
	 * Getter of the string where be the information for the console.
	 * @param  String - The message to set.
	 */
	public void setdiceRobotConsola(String string) {
		diceRobotConsola=string;
		
	}

	/**
	 * Getter of the string where be the information for the console.
	 * @return -Returns The message for the console.
	 */
	public String getdiceRobotConsola() {
		return diceRobotConsola;
		
	}

	/**
	 * this method inicialiced the observers with the robot information
	 */
	public void requestStart() {
		rellenarInfoObs();
		informarObservadores();
	}

	/**
	 * Inform if in one execution the robot have a one InstructionException.
	 * @return -Returns if the robot have a exception.
	 */
	public boolean huboExcepcion() {
		boolean a = huboE;
		huboE=false;
		return a;
	}

	/**
	 * Getter of the string where be the information of the exception.
	 * @return -Returns The message for the exception.
	 */
	public String getME() {
		return mensageEscepcion;
	}

	public void addNavigationObserver(NavigationObserver observadorDelNavegador) {
		map.addObserver(observadorDelNavegador);
		
	}

	/**
	 * this method return if the robot have fuel or not.
	 * @return
	 */
	public boolean haveFuel() {
		return sopa>0;
	}

	public void seMovioCrearMensaje() {
		diceRobotGrafica="Robot attributes has been updated: (" + sopa +"," + recicla + ")";
		
	}

	/**
	 * This method return the robot fuel.
	 * @return
	 */
	public int getFuel() {
		return sopa;
	}

	/**
	 * This method return the robot fuel.
	 * @return
	 */
	public int getRecycledMaterial() {
		return recicla;
	}

	public void informarObservadoresNavegador() {
		map.informarObservadores();
		
	}
	
	private void rellenarInfoObs()
	{

		infoObs = new InformacionObservadores();
		infoObs.setInformacion(new ObjetoInformativo("Fuel", sopa));
		infoObs.setInformacion(new ObjetoInformativo("Reciclado", recicla));
		infoObs.setInformacion(new ObjetoInformativo("MensajeConsola", diceRobotConsola));
		infoObs.setInformacion(new ObjetoInformativo("MensajeGUI", diceRobotGrafica));
		infoObs.setInformacion(new ObjetoInformativo("MensajeException", mensageEscepcion));
		infoObs.setInformacion(new ObjetoInformativo("Mochila", mochila));
	}

}
