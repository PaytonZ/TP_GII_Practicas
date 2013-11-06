package tp.pr3.instructions.exceptions;


@SuppressWarnings("serial")
public class InstructionExecutionException extends Exception {
	
	/**
	 * InstructionExecutionException construction default
	 */
	
	public InstructionExecutionException()
	{
		super();
	}
	
	/**
	 * InstructionExecutionException construction
	 * @param arg - Message to show in this exception
	 */
	
	public InstructionExecutionException(String arg)
	{
		super(arg);
	}
	
	/**
	 * InstructionExecutionException construction default
	 * @param arg - Message to show in this exception
	 * @param arg1 - If this exception is thowable
	 */
	
	public InstructionExecutionException(String arg, Throwable arg1)
	{
		super(arg, arg1);
	}

	/**
	 * InstructionExecutionException construction
	 * @param arg1 - If this exception is throwable
	 */
	
	public InstructionExecutionException(Throwable arg1)
	{
		super(arg1);
	}
}
