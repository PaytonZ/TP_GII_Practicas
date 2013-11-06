package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import tp.pr5.InformacionObservadores;
import tp.pr5.items.ItemContainer;

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
	private MiJTable modelo;
	
	/**
	 * RobotPanle construction with a RobotEngine.
	 * @param rob - RobotEngine to use.
	 */
	
	public RobotPanel()
	{
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
		
		infoDelRobot.setText("Fuel: "+ /*robot.getFuel()*/" Recycled: " /*robot.getRecycledMaterial()*/);
		infoDelRobot.setName("infoDelRobot");
		
		LayoutManager elLayaut = new GridLayout(2,1);
		this.setLayout(elLayaut);
		
		this.add(infoDelRobot);
		this.add(panelDeslizable);		
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

/**
 * This method update the Robot info, and the robot backpack
 * @param arg0 - The RobotEngine.
 * @param arg1
 */
	public void update(Observable arg0, Object arg1) {
		if (arg1!=null)
		{
			if (arg1.getClass()==InformacionObservadores.class)
			{
				Object a= ((InformacionObservadores)arg1).buscarMiInfo("Fuel");
				Object b= ((InformacionObservadores)arg1).buscarMiInfo("Reciclado");
				Object c= ((InformacionObservadores)arg1).buscarMiInfo("Mochila");
				if (a!=null && b!=null && c!=null)
				{
					infoDelRobot.setText("Fuel: "+ ((Integer)a) +  " Recycled: " +((Integer)b));
					modelo.setObjetos(((ItemContainer)c));		
					modelo.rellenar();
					modelo.fireTableDataChanged();
				}
			}
		}
				
	}
	

}
