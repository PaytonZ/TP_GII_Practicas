package tp.pr2;

// ESTA ESTA FINIQUITADA.

public class Place {
	private String Lugar;
	private boolean MotherShip;
	private String Definicion;
	private ItemContainer objetos;
	
	/**
	 * Contructora que crea un Place.
	 * @param l - Nombre del lugar.
	 * @param m - Si esta la Mothership o no.
	 * @param d - Descripci�n del lugar.
	 */
	
	public Place(String l, boolean m, String d)
	{
		this.Definicion=d;
		this.Lugar=l;
		this.MotherShip=m;
		this.objetos=new ItemContainer();
		
	}
	
	/**
	 * Comprueba si el lugar es la Mothership
	 * @return Devuelve true si el lugar es la Mothership y false en caso contrario.
	 */
	
	public boolean isSpaceship ()
	{
		return this.MotherShip;
	}
	
	/**
	 * Convierte el Place en un String para devolverlo por pantalla.
	 */
	
	public String toString()
	{
		if (this.objetos.numberOfItems()!=0)
		{
			return this.Lugar+ Interpreter.LINE_SEPARATOR + this.Definicion +Interpreter.LINE_SEPARATOR+"The place contains these objects: "+ Interpreter.LINE_SEPARATOR+ this.objetos.toString() ;
		}
		else return this.Lugar+ Interpreter.LINE_SEPARATOR + this.Definicion +Interpreter.LINE_SEPARATOR ;
	}

	public Item pickItem(String id)
	{
		return this.objetos.pickItem(id);
	}
	
	public boolean addItem(Item it)
	{
		return this.objetos.addItem(it);
	}
	
}
