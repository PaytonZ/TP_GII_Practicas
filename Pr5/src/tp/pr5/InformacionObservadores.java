package tp.pr5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class InformacionObservadores {

	private Collection<ObjetoInformativo> informacion;
	
	public InformacionObservadores()
	{
		informacion=new ArrayList<ObjetoInformativo>();
	}
	
	public void setInformacion(ObjetoInformativo a)
	{
		informacion.add(a);
	}
	
	public Object buscarMiInfo(String s)
	{
		Iterator<ObjetoInformativo> it = informacion.iterator();
		Object a= null;
		boolean encontrado =false;
		while ( it.hasNext() && !encontrado)
		{
			ObjetoInformativo asd=it.next();
			if (asd.getDefinicion().equalsIgnoreCase(s))
			{
				a= asd.getObjeto();
				encontrado=true;
			}
		}
		return a;
	}
}
