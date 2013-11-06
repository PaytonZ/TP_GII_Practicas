package tp.pr2;

//ESTA ESTA FINIQUITADA.

import java.util.ArrayList;
import java.util.Arrays;




public class City {
	private ArrayList<Street> mapa;
	
	public City(Street[] s)
	{
		this.mapa = new ArrayList<Street>(Arrays.asList(s));
	}
	
	public City()
	{
		this.mapa = new ArrayList<Street>();
	}
	
	public Street lookForStreet(Place p, Direction d)
	{
		int i=0;
		Street esta=null;
		boolean ya=false;
		Direction op = d.opposite(d);
		while(!ya && i<this.mapa.size())
		{
			if (this.mapa.get(i).getPlace1().equals(p) && this.mapa.get(i).getDirection()==d)
			{
				esta=this.mapa.get(i);
				ya=true;
			}
			if (this.mapa.get(i).getPlace2().equals(p) && this.mapa.get(i).getDirection()==op)
			{
				esta=this.mapa.get(i);
				ya=true;
			}
			i++;
		}
		return esta;
	}
}
