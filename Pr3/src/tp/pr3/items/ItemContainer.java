package tp.pr3.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp.pr3.Interpreter;


public class ItemContainer implements Iterable<Item> {

private List<Item> contenedor;
	/**
	 * ItemContainer Constructor
	 */
	public ItemContainer ()
	{	
		this.contenedor = new ArrayList<Item>();
	}
	/**
	 * 
	 * @param id the id of the item.
	 * @return the position where the item is, if it doesn't exist in this container return -1.
	 */
	public int buscador( String id)
	{	
		int i=-1;
		int c=0;
		boolean encontrado = false;
		Iterator<Item> it=this.contenedor.iterator();
		while (it.hasNext() && !encontrado)
		{
			
			if (((Item) it.next()).getId().equalsIgnoreCase(id)) 
			{
				i=c;
				encontrado=true;
			}
			c++;
		}
		return i;
	}
	/**
	 * 
	 * @param id the id of the item.
	 * @return true if the item exist, return false if the item doesn't exist.
	 */
	public boolean containsItem( String id)
	{	
		return buscador(id)!=-1;
	}
	/**
	 * This method sorts the array alphabetically.
	 */
	private void ordenar() 
	{
		int in=0;
		Item asd=null;
		for (int i=1; i<this.contenedor.size(); i++)
		{
			asd=this.contenedor.get(i);
			in=i;
			while (in>0 && this.compareString(this.contenedor.get(in-1).getId(), asd.getId()))
			{
				this.contenedor.set(in, this.contenedor.get(in-1));
				in--;
			}
			this.contenedor.set(in, asd);
		}
	}
	/**
	 * 
	 * @param a
	 * @param b
	 * @return This method calculates that string is greater
	 */
	private boolean compareString(String a, String b)
	{
		int i=0;
		boolean r= false;
		boolean sol= false;
		while((!sol)&&(i<a.length()))
			{	
				if (((int)a.toUpperCase().charAt(i))==((int)b.toUpperCase().charAt(i)))
					i++;
					else if  (((int)a.toUpperCase().charAt(i))<((int)b.toUpperCase().charAt(i)))
							{
								r= false;
								sol=true;
							}							
							else
								{
									r=true;
									sol=true;
								}
			}
		return r;
	}
	
	/**
	 * 
	 * @param item to add
	 * @return if the item was be added return true, else return false.
	 */
	public boolean addItem (Item item)
	{
		boolean ret=false;
		if (this.buscador(item.getId())==-1)
		{
			this.contenedor.add(item);
			ret= true;
		}
		return ret;
	}
	/**
	 * 
	 * @param id of the item that the robot is searching.
	 * @return the item, if the item doesn't exist return null.
	 */
	public Item getItem (String id)
	{
		Item i=null;
		int a = this.buscador(id);
		if (a!=-1)
		{
			i=this.contenedor.get(a);
		}
		return i;
	}
	
	/**
	 * 
	 * @param id of the item that the robot is searching.
	 * @return the item, if the item doesn't exist return null. If item exist in 
	 * the array this items it's remove.
	 */
	public Item pickItem( String id)
	{
		Item i=null;
		int a = this.buscador(id);
		if (a!=-1)
		{
			i=this.contenedor.get(a);
			this.contenedor.remove(a);
		}
		return i;
	}
	/**
	 * @return a string with the container items.
	 */
	public String toString()
	{
		String s="";
		this.ordenar();
		for(int i=0; i<this.numberOfItems(); i++)
		{
			if (i!=this.numberOfItems()-1)
				s=s+"   "+this.contenedor.get(i).getId()+Interpreter.LINE_SEPARATOR;
			else
				s=s+"   "+this.contenedor.get(i).getId();
		}	
		return s;
	}
	/**
	 * 
	 * @return the number of items.
	 */
	public int numberOfItems()
	{
		return this.contenedor.size();
	}
	@Override
	public Iterator<Item> iterator() {
		return new MiIterador();
	}
		
	
	
	protected class MiIterador  implements Iterator<Item> {
		protected int posicion;
		public MiIterador()
		{
			this.posicion=0;
		}
		@Override
		public boolean hasNext() {
			boolean result=false;
			if (this.posicion<contenedor.size())
			{
				result=true;
			}
			return result;
		}

		@Override
		public Item next() {
			this.posicion++;
			return contenedor.get(this.posicion-1);
		}

		@Override
		public void remove() {
			contenedor.remove(this.posicion-1);
			
		}


}
}
