package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.ObservaNavigation;

/**
 * This class generate a matrix of cells and the methods to chage the matrix.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class MatrizCeldas extends JPanel  {

	private PlaceCell[][] matrizBotones;
	private int col;
	private int fil;
	private boolean firstTime;
	
	/**
	 * MatrizCeldas construction with NavigationModule.
	 * @param rob
	 */
	
	public MatrizCeldas()
	{
		col=5;
		fil=5;
		inicializaMatrizCeldas();
		firstTime=true;
		
	}
	
	

	/**
	 * This method generate the matrix of cells with eleven columns and eleven rows. 
	 */
	
	private void inicializaMatrizCeldas() {
		matrizBotones = new PlaceCell [11][11];
		setPreferredSize(new java.awt.Dimension(700, 300));
		
		LayoutManager elLayout = new GridLayout(11,11);
		setLayout(elLayout);
		
		setBorder(new TitledBorder("City Map"));
		
		for (int i=0; i<11; i++)
		{
			for (int j=0; j<11; j++)
			{
				matrizBotones[i][j] = new PlaceCell();
				matrizBotones[i][j].setName("botonMatriz");
				add(matrizBotones[i][j]);
			}
		}
		
	}
	

	/**
	 * This method updates the position in the matrix
	 */

	
	public void update(Observable o, Object arg) {
		if (!firstTime)
		{
			if (!getCell().getText().equalsIgnoreCase(((ObservaNavigation)o).getCurrentPlace().getLugar()))
			{
				getCell().ponerColorGris();
				switch(((ObservaNavigation)o).getCurrentHeading()){
				case NORTH: if(getCellAbajo().getPlazaDeLaCelda()!=((ObservaNavigation)o).getCurrentPlace())
								moveNorth();
							else moveSouth();
							break;
				case SOUTH: if(getCellArriba().getPlazaDeLaCelda()!=((ObservaNavigation)o).getCurrentPlace())
								moveSouth(); 
							else
								moveNorth();
							break;
				case EAST: if(getCellIzquierda().getPlazaDeLaCelda()!=((ObservaNavigation)o).getCurrentPlace())
								moveEast(); 
							else moveWeast();
							break;
				case WEST: if(getCellDerecha().getPlazaDeLaCelda()!=((ObservaNavigation)o).getCurrentPlace())
								moveWeast(); 
							else moveEast(); 
							break;			
				default:
					break;
				}
				getCell().insertarText(((ObservaNavigation)o).getCurrentPlace().getLugar());
				getCell().setPlazaDeLaCelda(((ObservaNavigation)o).getCurrentPlace());
			}
		}
		else 
		{
			getCell().insertarText(((ObservaNavigation)o).getCurrentPlace().getLugar());
			getCell().setPlazaDeLaCelda(((ObservaNavigation)o).getCurrentPlace());
			firstTime=false;
		}
	}

	/**
	 * This method sets the controller in each panel
	 * @param e -The controller.
	 */
	public void fijarControlador(EventListener controlador)
	{
		for (int i=0; i<11; i++)
		{
			for (int j=0; j<11; j++)
			{
				matrizBotones[i][j].addActionListener((ActionListener)controlador);
			}
		}
	}
	/**
	 * This method returns the cell when the robot is.
	 * @return - The cell in when the robot is.
	 */
	
	public PlaceCell getCell()
	{
		return matrizBotones[fil][col];
	}
	
	/**
	 * This method returns the column
	 * @return - Column when it is.
	 */
	
	public int getColum()
	{
		return col;
	}
	
	/**
	 * this method returns the row when the robot is.
	 * @return - Row when it is.
	 */
	
	public int getFila()
	{
		return fil;
	}
	
	/**
	 * This method changes the row.
	 * @param fila - Row to change.
	 */
	
	public void setFila(int fila)
	{
		fil=fila;
	}
	
	/**
	 * This method changes the column.
	 * @param columna - Column to change.
	 */
	
	public void setColumna(int columna)
	{
		col=columna;
	}
	
	/**
	 * This method changes the row after move to north.
	 */
	
	public void moveNorth()
	{
		fil--;
		if (fil<0)
			fil=0;
	}
	
	/**
	 * This method changes the row after move to south.
	 */
	
	public void moveSouth()
	{
		fil++;
		if (fil>11)
			fil=11;
	}
	
	/**
	 * This method changes the column after move to east.
	 */
	
	public void moveEast()
	{
		col++;
		if (col>11)
			col=11;
	}
	
	/**
	 * This method changes the column after move to west.
	 */
	
	public void moveWeast()
	{
		col--;
		if(col<0)
			col=0;
	}

	
	
	/**
	 * This method returns the cell higher to the actual.
	 * @return - Return the cell higher.
	 */

	public PlaceCell getCellArriba() {
		if (fil!=0)
			return matrizBotones[fil-1][col];
		else return matrizBotones[fil][col];
	}
	
	/**
	 * This method returns the cell lower to the actual.
	 * @return - Return the cell lower.
	 */
	
	public PlaceCell getCellAbajo()
	{
		if(fil!=11)
			return matrizBotones[fil+1][col];
		else return matrizBotones[fil][col];
	}
	
	/**
	 * This method returns the cell to the right of the actual. 
	 * @return - Return right cell.
	 */
	
	public PlaceCell getCellDerecha()
	{
		if(col!=11)
			return matrizBotones[fil][col+1];
		else return matrizBotones[fil][col];
	}
	
	/**
	 * This method returns the cell to the left of the actual.
	 * @return - Return left cell.
	 */
	
	public PlaceCell getCellIzquierda()
	{
		if(col!=0)
			return matrizBotones[fil][col-1];
		else  return matrizBotones[fil][col];
	}

}
