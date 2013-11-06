package tp.pr3.cityLoader.cityLoaderExceptions;


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
