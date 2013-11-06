package tp.pr5.instructions.exceptions;

@SuppressWarnings("serial")
public class WrongInstructionFormatException extends Exception {
	
	/**
	 * WrongInstructionFormatException construction default
	 */
	
	public WrongInstructionFormatException() {
		super();
	}
	
	/**
	 * WrongInstructionFormatException construction
	 * @param arg - Message to show in this exception
	 */
	
	public WrongInstructionFormatException(String arg)
	{
		super (arg);
	}
	
	/**
	 * WrongInstructionFormatException construction
	 * @param arg - Message to show in this exception
	 * @param arg1 - If this exception is throwable
	 */
	
	public WrongInstructionFormatException(String arg, Throwable arg1)
	{
		super(arg,arg1);
	}
	
	/**
	 * WrongInstructionFormatException construction
	 * @param arg1 - If this exception is throwable
	 */
	
	public WrongInstructionFormatException(Throwable arg1)
	{
		super (arg1);
	}

}
