package tp.pr3;



import java.util.ArrayList;
import java.util.List;

import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class Interpreter {
	private List<Instruction> instruciones;
	private int cualFunciona;
	
	public Interpreter()
	{
		this.instruciones=new ArrayList<Instruction>();
		this.instruciones.add(new MoveInstruction());
		this.instruciones.add(new TurnInstruction());
		this.instruciones.add(new PickInstruction());
		this.instruciones.add(new ScanInstruction());
		this.instruciones.add(new OperateInstruction());
		this.instruciones.add(new DropInstruction());
		this.instruciones.add(new RadarInstruction());
		this.instruciones.add(new HelpInstruction());
		this.instruciones.add(new QuitInstruction());
		this.cualFunciona=-1;
		
	}
	
	/**
	 * This method show line break by the console
	 */
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * This method converts the string to an instruction
	 * @param line - String that contained the instruction
	 * @return - Returns the instruction read by the console
	 * @throws WrongInstructionFormatException - Throw the exception in case of wrong instruction introduced
	 */
	
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException
	{
		Interpreter a = new Interpreter();
		boolean terminado=true;
		Instruction asd=null;
		for(int i=0; i<a.instruciones.size() && terminado;i++)
		{
			try
			{
				asd=a.instruciones.get(i).parse(line);
				a.cualFunciona=i;
				terminado=false;
			}
			catch (WrongInstructionFormatException e)
			{
				
			}
		}
		if (a.cualFunciona==-1)
			throw new WrongInstructionFormatException();
		else
			return asd;
	}
	
	/**
	 * This method shows robot´s help by the console
	 * @return - Returns robot´s help
	 */
   
	public static String interpreterHelp()
	{
		Interpreter a= new Interpreter();
		String s = "The valid instructions for WALL-E are:"+ LINE_SEPARATOR;
		for (int i=0; i<a.instruciones.size(); i++)
		{
			s=s+a.instruciones.get(i).getHelp()+ LINE_SEPARATOR;
		}
		return s;
	}
}
