package tp.pr5.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.Rotation;

/**
 * This class generate the panel of buttons of the Actions.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class PanelBotonesAccion extends JPanel	{

	private JButton move;
	private JButton quit;
	private JButton turn;
	private JComboBox<String> direcion;
	private JButton pick;
	private JTextField elItem;
	private JButton drop;
	private JButton operate;
	private JButton unDo;
	
	/**
	 * PanelBotonesAccion construction with a robotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public PanelBotonesAccion()
	{
		iniciarElPanel();
	}
	
	/**
	 * PanelBotonesAccion with a EventListener.
	 * @param controlador - EventListener to use.
	 */
	
	public PanelBotonesAccion(EventListener controlador)
	{
		iniciarElPanel();
	}
	
	/**
	 * This method initialize the PanelBotonesAccion.
	 */

	private void iniciarElPanel() {
		move = new JButton("MOVE");
		quit = new JButton("QUIT");
		turn = new JButton("TURN");
		direcion = new JComboBox<String>();
		pick = new JButton("PICK");
		elItem = new JTextField();
		drop = new JButton("DROP");
		operate = new JButton("OPERATE");
		unDo= new JButton("UNDO");
		
		this.setPreferredSize(new java.awt.Dimension(250, 250));
		
		this.setBorder(new TitledBorder("Instructions"));
		this.setName("Panel");
		// hay que añadir el tamaño deseado
		
		move.setName("BMover");
		quit.setName("BQuit");
		turn.setName("BTurn");
		direcion.setName("CBDirecion");
		pick.setName("BPick");
		elItem.setName("JTElItem");
		drop.setName("BDrop");
		operate.setName("BOperate");
		direcion.addItem("LEFT");
		direcion.addItem("RIGHT");
		unDo.setName("BUnDo");
		
		elItem.setBackground(Color.WHITE);
		
		LayoutManager elLayout = new GridLayout(5,2);
		this.setLayout(elLayout);
		
		this.add(move);
		this.add(quit);
		this.add(turn);
		this.add(direcion);
		this.add(pick);
		this.add(elItem);
		this.add(drop);
		this.add(operate);
		this.add(unDo);
	}


	/**
	 * This method returns a rotation, left or right,
	 * in case of what the select item was left or right in other case return null.
	 * @return
	 */
	public Rotation cogerRotacion()
	{
		Rotation ret = Rotation.UNKNOWN;
		if (direcion.getSelectedItem().toString().equalsIgnoreCase("RIGHT"))
		{
			ret= Rotation.RIGHT;
		}
		else if (direcion.getSelectedItem().toString().equalsIgnoreCase("LEFT"))
		{
			ret= Rotation.LEFT;
		}
		 return ret;
	}
	
	
	/**
	 * This method sets the controller in each JButton.
	 * @param e -The controller.
	 */
	public void fijarControlador(EventListener controlador)
	{
		move.addActionListener((ActionListener)controlador);
		quit.addActionListener((ActionListener)controlador);
		turn.addActionListener((ActionListener)controlador);
		pick.addActionListener((ActionListener)controlador);
		elItem.addFocusListener((FocusListener)controlador);
		drop.addActionListener((ActionListener)controlador);
		operate.addActionListener((ActionListener)controlador);
		unDo.addActionListener((ActionListener)controlador);
		direcion.addFocusListener((FocusListener)controlador);
	}
	
	
}
