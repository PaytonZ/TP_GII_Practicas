package tp.pr5;

public class ObjetoInformativo {

	private String definicion;
	private Object objeto;
	
	public ObjetoInformativo( String s, Object ob)
	{
		definicion=s;
		objeto=ob;
	}
	
	public String getDefinicion() {
		return definicion;
	}


	public Object getObjeto() {
		return objeto;
	}
	
	
}
