package tp.pr4.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tp.pr4.Interpreter;


/**
 * A container of items. It can be employed by any class that stores items. 
 * A container cannot store two items with the same identifier

	It provides methods to add new items, access them and remove them from the container.
 *  @author David Garcia Alvarez and Javier Toledano Regaño
 *
 */
public class ItemContainer implements Iterable<Item>, Collection<Item> {

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
		ItemGeneric it = new ItemGeneric(id);
		int i=Collections.binarySearch(contenedor, it);
		if (i<0)
		{
			i=-1;
		}
		return i;
		
		
		
	}
	
	/**
	 * 
	 * @param it the item you are searching for.
	 * @return the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
	 */
	public int buscador(Item it)
	{
		return Collections.binarySearch(contenedor, it);
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
	 * 
	 * @param item to add
	 * @return if the item was be added return true, else return false.
	 */
	public boolean addItem (Item item)
	{
		return add(item);
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
		Iterator<Item> it = this.contenedor.iterator();
		while (it.hasNext())
		{
			s=s+"	"+it.next().getId();
			if (it.hasNext())
			{
				s=s+Interpreter.LINE_SEPARATOR;
			}
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
	/**
	 * @return a Iterator for iterate the collection
	 */
	@Override
	public Iterator<Item> iterator() {
		return new MiIterador();
	}
		
	
	/**
	 * This class implement the iterator of ItemContainer
	 * @author David Garcia Alvarez and Javier Toledano Regaño
	 *
	 */
	protected class MiIterador  implements Iterator<Item> {
		protected int posicion;
		/**
		 * Default constructor
		 */
		public MiIterador()
		{
			this.posicion=0;
		}
		
		/**
		 * @return true if the collection has other element, false ifit doesn't have.
		 */
		@Override
		public boolean hasNext() {
			return this.posicion<contenedor.size();
		}

		/**
		 * @return the next item in the container.
		 */
		@Override
		public Item next() {
			this.posicion++;
			return contenedor.get(this.posicion-1);
		}
		
		/**
		 * Remove the item of the container.
		 */

		@Override
		public void remove() {
			contenedor.remove(this.posicion-1);
			
		}


}
	
	/**
	 * 
	 * @param i the posicion where the item is in the collection.
	 * @return the item.
	 */
	public Item getItem(int i)
	{
		return this.contenedor.get(i);
	}
	
	/**
	 *  Add 1 item to the collection in orther. If the item be in the collection u can add.
	 *  @return true if the item was added.
	 */
	@Override
	public boolean add(Item it) {
		boolean anadido=false;
		if (!contains(it))
		{
			int a = Collections.binarySearch(contenedor, it);
			a=(a+1)*-1;
			if (a==contenedor.size())
			{
				contenedor.add(it);
			}
			else
			{
				contenedor.add(a, it);
			}
			anadido=true;
		}
		return anadido;
	
	}
	
	/**
	 * Unsoported Operation.
	 */
	@Override
	public boolean addAll(Collection<? extends Item> arg0) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Removes all of the elements from this collection 
	 */
	@Override
	public void clear() {
		contenedor = new ArrayList<Item>();
		
	}
	
	/**
	 * Returns true if this collection contains the specified element.
	 */
	@Override
	public boolean contains(Object arg0) {
		return buscador(((Item) arg0))>-1;
	}
	
	/**
	 * Returns true if this collection contains all of the elements in the specified collection
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return contenedor.containsAll(arg0);
	}
	
	/**
	 * Returns true if this collection contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return contenedor.size()==0;
	}
	
	/**
	 * Removes a single instance of the specified element from this collection, if it is present
	 */
	@Override
	public boolean remove(Object arg0) {
		return contenedor.remove(arg0);
	}
	
	/**
	 * Removes all of this collection's elements that are also contained in the specified collection
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return contenedor.removeAll(arg0);
	}
	
	/**
	 * Retains only the elements in this collection that are contained in the specified collection 
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return contenedor.retainAll(arg0);
	}
	
	/**
	 * Returns the number of elements in this collection.
	 */
	@Override
	public int size() {
		return contenedor.size();
	}
	
	/**
	 * Returns an array containing all of the elements in this collection.
	 */
	@Override
	public Object[] toArray() {
		
		return contenedor.toArray();
	}
	
	/**
	 * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return contenedor.toArray(arg0);
	}
}
