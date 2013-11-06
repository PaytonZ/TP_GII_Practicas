package tp.pr2;

//ESTA ESTA FINIQUITADA

import java.util.ArrayList;

public class ItemContainer {
	private ArrayList<Item> contenedor;
	
	public ItemContainer ()
	{	
		this.contenedor = new ArrayList<Item>();
	}
	
	public int Buscador( String id)
	{	
		int i=-1;
		int c=0;
		boolean encontrado = false;
		while (c<this.contenedor.size() && !encontrado)
		{
			
			if (this.contenedor.get(c).getId().equalsIgnoreCase(id)) 
			{
				i=c;
				encontrado=true;
			}
			c++;
		}
		return i;
	}
	
	private void Ordenar()
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
	
	
	public boolean addItem (Item item)
	{
		int a= this.Buscador(item.getId());
		if (a==-1)
		{
			this.contenedor.add(item);
			return true;
		}
		else return false;
	}
	
	public Item getItem (String id)
	{
		Item i=null;
		int a = this.Buscador(id);
		if (a!=-1)
		{
			i=this.contenedor.get(a);
		}
		return i;
	}
	
	public Item pickItem( String id)
	{
		Item i=null;
		int a = this.Buscador(id);
		if (a!=-1)
		{
			i=this.contenedor.get(a);
			this.contenedor.remove(a);
		}
		return i;
	}
	
	public String toString()
	{
		String s="";
		this.Ordenar();
		for(int i=0; i<this.numberOfItems(); i++)
		{
			s=s+"   "+this.contenedor.get(i).getId()+Interpreter.LINE_SEPARATOR;
		}	
		return s;
	}
	
	public int numberOfItems()
	{
		return this.contenedor.size();
	}

}
