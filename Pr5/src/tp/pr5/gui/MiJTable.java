package tp.pr5.gui;


import javax.swing.table.AbstractTableModel;

import tp.pr5.items.ItemContainer;

/**
 * This class generate a Table with a columns and rows defined, and complete with the item of the ItemContainer.  
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */

@SuppressWarnings("serial")
public class MiJTable extends AbstractTableModel {
	private int numColum;
	private int numFilas;
	private ItemContainer objetos;
	
	/**
	 * MiJTable construction with column = 2 and row = 0.
	 */
	
	public MiJTable()
	{
		numColum=2;
		numFilas=0;
	}
	
	/**
	 * MiJTable construction with a ItemContainer
	 * @param obj
	 */
		
	public MiJTable(ItemContainer obj)
	{
		numColum=2;
		objetos=obj;
		numFilas=objetos.numberOfItems();
	}
	
	@Override
	
	/**
	 * This method returns number of columns
	 */
	
	public int getColumnCount() {
		
		return numColum;
	}

	@Override
	
	/**
	 * This method returns number of rows
	 */
	
	public int getRowCount() {
		return numFilas;
	}

	@Override
	
	/**
	 * This method returns object´ID or the Object´Description or null.
	 */
	
	public Object getValueAt(int arg0, int arg1) {
		if(arg1==0)
			return objetos.getItem(arg0).getId();
		else if (arg1==1)
			return objetos.getItem(arg0).getDescription();
		else return null;
	}
	
	/**
	 * This method changes the ItemContainer and changes the number of row by the number of items.
	 * @param a - The ItemContainer to change.
	 */
	
	public void setObjetos(ItemContainer a)
	{
		objetos=a;
		numFilas=objetos.numberOfItems();
	}
	
	/**
	 * This method Complete the JTble with the items of ItemContainer.
	 */
	
	public void rellenar()
	{
		for (int i=0; i<numFilas; i++)
		{
			String a = objetos.getItem(i).getId();
			setValueAt(a, i, 0);
			String s = objetos.getItem(i).getDescription();
			setValueAt(s, i, 1);
		}
	}

}
