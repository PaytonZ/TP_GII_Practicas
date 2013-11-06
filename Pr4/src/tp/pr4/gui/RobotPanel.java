package tp.pr4.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;

/**
 * RobotPanel displays information about the robot and its inventory.
 * More specifically, it contains labels to show the robot fuel and the weight of recycled material and a table that represents the robot inventory. 
 * Each row displays information about an item contained in the inventory.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class RobotPanel extends JPanel {
	
	private JTable laTabla;
	private JLabel infoDelRobot;
	private JScrollPane panelDeslizable;
	private RobotEngine robot;
	private MiJTable modelo;
	
	/**
	 * RobotPanle construction with a RobotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public RobotPanel(RobotEngine rob)
	{
		robot=rob;
		inicializaRobotPanel();
	}
	
	/**
	 * This method initializes the RobotPanel. 
	 */	

	private void inicializaRobotPanel() {

		infoDelRobot = new JLabel();
		panelDeslizable = new JScrollPane();
		modelo = new MiJTable();
		laTabla = new JTable(modelo); 
		JTableHeader a = laTabla.getTableHeader();
		TableColumnModel tcm = a.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("id");
		tc = tcm.getColumn(1);
		tc.setHeaderValue("description");	
		a.repaint();
		panelDeslizable.setViewportView(laTabla);
		panelDeslizable.setColumnHeaderView(laTabla.getTableHeader());
		
		this.setPreferredSize(new java.awt.Dimension(550, 250));
		
		this.setBorder(new TitledBorder("Robot info"));
		
		infoDelRobot.setText("Fuel: "+ robot.getFuel()+" Recycled: "+ robot.getRecycledMaterial());
		infoDelRobot.setName("infoDelRobot");
		
		LayoutManager elLayaut = new GridLayout(2,1);
		this.setLayout(elLayaut);
		
		this.add(infoDelRobot);
		this.add(panelDeslizable);		
	}
	
	/**
	 * This method updates the robotPanel.
	 */

	public void update() {
		infoDelRobot.setText("Fuel: "+ robot.getFuel() +  " Recycled: "+ robot.getRecycledMaterial());
		robot.InsertarElInventario();
		modelo.rellenar();
		modelo.fireTableDataChanged();
		
	}
	
	/**
	 * This method returns the jTable of robotPanel.
	 * @return - Returns a jtable.
	 */
	
	public JTable getJTable()
	{
		return laTabla;
	}
	
	/**
	 * This method insert the objects of a ItemContainer in MijTable.
	 * @param a - ItemContainer to insert.
	 */
	
	public void InsertarObjetosAlaTabla(ItemContainer a)
	{
		modelo.setObjetos(a);
	}

	
	

}
