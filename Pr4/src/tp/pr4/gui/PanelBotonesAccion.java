package tp.pr4.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.QuitInstruction;
import tp.pr4.instructions.TurnInstruction;
import tp.pr4.instructions.UnDoInstruction;

/**
 * This class generate the panel of buttons of the Actions.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class PanelBotonesAccion extends JPanel implements	ActionListener	{

	private JButton move;
	private JButton quit;
	private JButton turn;
	private JComboBox<String> direcion;
	private JButton pick;
	private JTextField elItem;
	private JButton drop;
	private JButton operate;
	private RobotEngine robot;
	private JButton unDo;
	
	/**
	 * PanelBotonesAccion construction with a robotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public PanelBotonesAccion(RobotEngine rob)
	{
		robot=rob;
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
		move.addActionListener(this);
		quit = new JButton("QUIT");
		quit.addActionListener(this);
		turn = new JButton("TURN");
		turn.addActionListener(this);
		direcion = new JComboBox<String>();
		pick = new JButton("PICK");
		pick.addActionListener(this);
		elItem = new JTextField();
		drop = new JButton("DROP");
		drop.addActionListener(this);
		operate = new JButton("OPERATE");
		operate.addActionListener(this);
		unDo= new JButton("UNDO");
		unDo.addActionListener(this);
		
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
	 * This method receives a ActionEvent and execute the action related.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==move)
		{
			realizarMovimiento();
		}
		else if (arg0.getSource()==turn)
		{
			realizarTurn();
		}
		else if (arg0.getSource()==quit)
		{
			realizarQuit();
		}
		else if (arg0.getSource()==pick)
		{
			realizaPick();
		}
		else if (arg0.getSource()==drop)
		{
			realizarDrop();
		}
		else if (arg0.getSource()==operate)
		{
			realizarOperate();
		}
		else if (arg0.getSource()==unDo)
		{
			realizarUnDo();
		}
		
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
		if (robot.selecionadoTabla()!=null)
			robot.communicateRobot(new OperateInstruction((String) robot.selecionadoTabla()));
		
	}
	
	/**
	 * This method generate a new Drop instruction.
	 */

	private void realizarDrop() {
		if (robot.selecionadoTabla()!=null)
			robot.communicateRobot(new DropInstruction((String) robot.selecionadoTabla()));
		
	}
	
	/**
	 * This method generate a new Pick instruction.
	 */

	private void realizaPick() {
				
		robot.communicateRobot(new PickInstruction(elItem.getText()));
	}
	
	/**
	 * This method generate a new Turn instruction.
	 */

	private void realizarTurn() {
		robot.communicateRobot(new TurnInstruction(cogerRotacion()));
		
	}
	
	/**
	 * This method generate a new move instruction.
	 */

	private void realizarMovimiento()
	{
		robot.communicateRobot(new MoveInstruction());
	}
	
	/**
	 * This method returns a rotation, left or right,
	 * in case of what the select item was left or right in other case return null.
	 * @return
	 */
	
	private Rotation cogerRotacion()
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
}
