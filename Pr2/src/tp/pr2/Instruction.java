package tp.pr2;



public class Instruction {
	private Action accion;
	private Rotation rotacion;
	private String id;
	
	/**
	 * Constructora por defecto. Devuelve acci�n y rotaci�n a UNKNOWN
	 */
	public Instruction()
	{
		this.accion = Action.UNKNOWN;
		this.rotacion = Rotation.UNKNOWN;
		this.id=new String();
	}   
	  
	/**
	 * Constructora que recibe una Action y genera la rotaci�n a UNKNOWN.
	 * @param a - Acci�n de la instrucci�n
	 */
	
	public Instruction (Action a)
	{
		this.accion = a;
		this.rotacion = Rotation.UNKNOWN;
		this.id=new String(); 
	}
	
	/**
	 * Contructora que recibe una Action y una Rotation.
	 * @param a - Acci�n de la instrucci�n.
	 * @param r - Rotaci�n de la instrucci�n.
	 */
	
	public Instruction (Action a, Rotation r)
	{
		this.accion=a;
		this.rotacion=r;
		this.id=new String();
	}
	
	public Instruction (Action a, String s)
	{
		this.accion=a;
		this.rotacion=Rotation.UNKNOWN;
		this.id=s;
	}
	
	public Action getAction()
	{
		return this.accion;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public Rotation getRotation()
	{
		return this.rotacion;
	}
	
	/**
	 * Comprueba que la instruccion sea correcta y no sea Action y Rotation UNKNOWN.
	 * @return devuelve true si la instrucci�n es valida y devuelve false si la instrucci�n no sea valida: Action = UNKNOWN o Action = TURN y Rotation = UNKNOWN.
	 */
	// cuidado con el scan...
	public boolean isValid ()
	{
		if(this.accion==Action.UNKNOWN)
			return false;
		else if (this.accion==Action.TURN)
		{
			if (this.rotacion==Rotation.UNKNOWN)
				return false;
			else return true;
		} 
		else if ((this.accion==Action.PICK)||(this.accion==Action.OPERATE))
					{
						if(this.id.length()!=0)
						{
							return true;
						}
						else return false;
					}
		else return true; 
	}

}
