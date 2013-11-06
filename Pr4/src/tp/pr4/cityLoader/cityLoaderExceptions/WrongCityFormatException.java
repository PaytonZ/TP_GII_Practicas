package tp.pr4.cityLoader.cityLoaderExceptions;

/**
 * Exception thrown by the map loader when the file does not adhere to the file format.
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */
@SuppressWarnings("serial")
public class WrongCityFormatException extends Exception {

	/**
	 * WrongCityFormatException construction default
	 */
	
	public WrongCityFormatException()
	{
		super();
	}
	
	/**
	 * WrongCityFormatException construction
	 * @param arg - Message to show in this exception
	 */
	
	public WrongCityFormatException(String arg)
	{
		super(arg);
	}
}
