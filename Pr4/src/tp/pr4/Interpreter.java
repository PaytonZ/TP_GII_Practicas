package tp.pr4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.HelpInstruction;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.QuitInstruction;
import tp.pr4.instructions.RadarInstruction;
import tp.pr4.instructions.ScanInstruction;
import tp.pr4.instructions.TurnInstruction;
import tp.pr4.instructions.UnDoInstruction;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;

/**
 * The interpreter is in charge of converting the user input in an instruction for the robot. Up to now, the valid instructions are:
 *	•MOVE | MOVER 
 *	•TURN | GIRAR { LEFT | RIGHT }
 *	•PICK | COGER <ITEM>
 *	•DROP | SOLTAR <ITEM>
 *	•SCAN | ESCANEAR [ <ITEM> ] 
 *	•RADAR
 *	•OPERATE | OPERAR <ITEM>
 *	•HELP | AYUDA
 *	•QUIT | SALIR
 *
 * @author David Garcia Alvarez y Javier Toledano Regaño
 *
 */



public class Interpreter implements Iterable<Instruction>{
	private List<Instruction> instruciones;
	
	/**
	 * Default construction.
	 */
	
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
		this.instruciones.add(new UnDoInstruction());
		
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
		int cualFunciona =-1;
		Interpreter a = new Interpreter();
		boolean terminado=true;
		Instruction asd=null;
		Iterator<Instruction> it = a.instruciones.iterator();
		while (it.hasNext() && terminado)
		{
			try
			{
				asd=it.next().parse(line);
				cualFunciona++;
				terminado=false;
			}
			catch (WrongInstructionFormatException e)
			{
				
			}
		}
		if (cualFunciona==-1)
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
		Iterator<Instruction> it = a.instruciones.iterator();
		while (it.hasNext())
			s=s+it.next().getHelp()+ LINE_SEPARATOR;
		return s;
	}
	
	
	protected class MiIterador  implements Iterator<Instruction> {
		protected int posicion;
		/**
		 * Default constructor
		 */
		public MiIterador()
		{
			this.posicion=0;
		}
		
		/**
		 * @return true if the collection has other element, false ifit doesn't have.
		 */
		@Override
		public boolean hasNext() {
			return this.posicion<instruciones.size();
		}

		/**
		 * @return the next item in the container.
		 */
		@Override
		public Instruction next() {
			this.posicion++;
			return instruciones.get(posicion-1);
		}
		
		/**
		 * Remove the item of the container.
		 */

		@Override
		public void remove() {
			instruciones.remove(this.posicion-1);
			
		}


}


	@Override
	public Iterator<Instruction> iterator() {
		return new MiIterador();
	}
	
}
