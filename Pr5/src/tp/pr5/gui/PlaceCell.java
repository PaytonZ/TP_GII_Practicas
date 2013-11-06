package tp.pr5.gui;

import java.awt.Color;

import javax.swing.JButton;

import tp.pr5.Place;

/**
 * Represents a Place in the city on the Swing interface. 
 * It is a button, which name is the place name.
 * A PlaceCell needs to store a reference to the place that it represents. 
 * However, this place should not be modified by the PlaceCell
 * When the user clicks on a PlaceCell the CityPanel will show the place description if the Place was previously visited.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class PlaceCell extends JButton {

	private Place plazaDeLaCelda;
	
	/**
	 * Default PlaceCell construction. 
	 */
	
	public PlaceCell()
	{
		plazaDeLaCelda= new Place();
		inicializaPlaceCell();
	}
	
	/**
	 * This method initialize PlaceCell.
	 */

	private void inicializaPlaceCell() {
		
		
	}
	
	
	/**
	 * This method Chage the color of cell in green.
	 * @param s
	 */
	
	public void insertarText(String s)
	{
		this.setText(s);
		this.setBackground(Color.GREEN);
	}
	
	/**
	 * This method change the color of cell in gray.
	 */
	
	public void ponerColorGris()
	{
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * This method return the place of the cell.
	 * @return - Return cell´s place.
	 */

	public Place getPlazaDeLaCelda() {
		return plazaDeLaCelda;
	}
	
	/**
	 * This method changes the cell´s place.
	 * @param plazaDeLaCelda - Place to change.
	 */

	public void setPlazaDeLaCelda(Place plazaDeLaCelda) {
		this.plazaDeLaCelda = plazaDeLaCelda;
	}
	
	/**
	 * This method returns name of cell´s place in a string
	 * @return - Return a string with the name of place.
	 */
	
	public String infoDeLaPlazaBoton()
	{
		return plazaDeLaCelda.toString();
	}
	
}
