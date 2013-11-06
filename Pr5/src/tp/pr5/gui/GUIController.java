/**
 * 
 */
package tp.pr5.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;

import javax.swing.JTextField;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.UnDoInstruction;

/**
 * This class control the GUI of the program.
 * @author David Garcia y Javier Toledano
 *
 */
public class GUIController extends Controller implements ActionListener,FocusListener {

	private String nombreItem;
	/**
	 * Defualt builder.	
	 * 
	 * @param game the RobotEngine
	 */
	public GUIController(RobotEngine game) {
		super(game);
	}

	/**
	 * This method is the event according to the component that produced it. 
	 * @param event - The event object
	 */
	private void trataEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que generó el evento
        cambiarModelo(fuente);
        if (robot.isTheEnd())
        {
			ConfirmarCerrar a = new ConfirmarCerrar(); // NO SABEMOS HASTA QEU PUNTO ES BUENO QUE EL CONTROLADOR LANCE VENTANAS, A PURI NO LE PARECIO
			robot.noQuitar();                          // MAL, SE PODRIAN QUITAR LOS WARNING CON UN SUPRESSWARNING, PERO NO NOS PARECE ELEGANTE.
        }
        else if (!robot.haveFuel())
        {
        	VentanaGameOver a= new VentanaGameOver();
        }
        else if (robot.isInMotherShip())
        {
        	InMotherShip a = new InMotherShip();
        }
        else if (robot.huboExcepcion())
        {
        	VentanaError asd = new VentanaError(robot.getME());
        }
	}
	
	/**
	 * This method changes the model according to the event to occur.
	 * @param fuente
	 */
	private void cambiarModelo(Component fuente) {
		if (fuente.getName().equalsIgnoreCase("Bmover"))
		{
			realizarMovimiento();
		}
		else if (fuente.getName().equalsIgnoreCase("Bturn"))
		{
			realizarTurn();
		}
		else if (fuente.getName().equalsIgnoreCase("Bquit"))
		{
			realizarQuit();
		}
		else if (fuente.getName().equalsIgnoreCase("Bpick"))
		{
			if (nombreItem!=null&&!nombreItem.equalsIgnoreCase(""))
				realizaPick();
		}
		else if (fuente.getName().equalsIgnoreCase("Bdrop"))
		{
			realizarDrop();
		}
		else if (fuente.getName().equalsIgnoreCase("Boperate"))
		{
			realizarOperate();
		}
		else if (fuente.getName().equalsIgnoreCase("Bundo"))
		{
			realizarUnDo();
		}
		else if (fuente.getName().equalsIgnoreCase("JTElItem"))
		{
			JTextField campo=(JTextField)fuente;
            nombreItem = campo.getText();
		}
		else if (fuente.getName().equalsIgnoreCase("botonMatriz"))
		{
			observadorNavegador.insertarTextAlLog(((PlaceCell) fuente).infoDeLaPlazaBoton());
		}
	}


	/**
	 * This function captures the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		trataEventoGenerico(arg0);
		
	}


	/**
	 * This method generate a new UnDo instruction.
	 */
	
	private void realizarUnDo() {
		
		 robot.communicateRobot(new UnDoInstruction());
		 
		
	}
	
	/**
	 * This method generate a new Quit instruction.
	 */

	private void realizarQuit() {		
		  robot.communicateRobot(new QuitInstruction());
	}
	
	/**
	 * This method generate a new operate instruction.
	 */

	private void realizarOperate() {
		if (observadorRobot.selecionadoTabla()!=null)
			robot.communicateRobot(new OperateInstruction((String) observadorRobot.selecionadoTabla()));
		
	}
	
	/**
	 * This method generate a new Drop instruction.
	 */

	private void realizarDrop() {
		if (observadorRobot.selecionadoTabla()!=null)
			robot.communicateRobot(new DropInstruction((String) observadorRobot.selecionadoTabla()));
		
	}
	
	/**
	 * This method generate a new Pick instruction.
	 */

	private void realizaPick() {
		  robot.communicateRobot(new PickInstruction(nombreItem));
		 
	}
	
	/**
	 * This method generate a new Turn instruction.
	 */

	private void realizarTurn() {
		
		 robot.communicateRobot(new TurnInstruction(observadorRobot.cogerRotation()));
		 
		
	}
	
	/**
	 * This method generate a new move instruction.
	 */

	private void realizarMovimiento()
	{
		  robot.communicateRobot(new MoveInstruction());		 
	}

	@Override
	public void startController() {
		
	}

	/**
	 * This function captures the FocusEvent Gained
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	/**
	 * This function captures the FocusEvent lost
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		trataEventoGenerico(arg0);
	}
	

}
