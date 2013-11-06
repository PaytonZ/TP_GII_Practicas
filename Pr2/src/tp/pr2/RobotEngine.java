package tp.pr2;

import java.util.Scanner;

public class RobotEngine {
	private City map;
	private Place plazaini;
	private Direction dmiro;
	private int sopa;
	private int recicla; 
	private ItemContainer mochila;
	private Instruction ins;
	
	public RobotEngine(City cm, Place ini, Direction dir)
	{
		this.map=cm;
		this.plazaini=ini;
		this.dmiro=dir;
		this.sopa=50;
		this.mochila= new ItemContainer();
		this.recicla=0;
	}
	
	
	
	public void startEngine()
	{
		String r=this.plazaini.toString();
		mostrarPantalla(r); //a�adido
		this.MostrarInfo();
		mostrarPantalla(this.dondeMira());
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		while (!this.plazaini.isSpaceship() && !quit && this.sopa>0)
		{
			mostrarPrompt();
			String l =leerString(sc);
			this.ins=Interpreter.generateInstruction(l);
			if (!this.ins.isValid())
			{
				System.out.println("WALL·E says: I do not understand. Please repeat");
			}
			else
			{
				quit=this.processInstruction(this.ins);
				if (quit)
				{
					mostrarPantalla("WALL·E says: I have communication problems. Bye bye.");
				}
			}
		}
		if(this.plazaini.isSpaceship())
		{
			mostrarPantalla(this.dondeMira());
			mostrarPantalla("WALL·E says: I am at my space ship. Bye Bye");
		}
		if (this.sopa<0)
		{
			mostrarPantalla("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
	}
	

	private boolean processInstruction(Instruction i)
	{
		if(i.getAction()==Action.QUIT) return true;
		else if (i.getAction()==Action.HELP)
		{
			processInstructionHelp();
			return false;
		}
		else if (i.getAction()==Action.TURN)
		{
			if (i.getRotation()==Rotation.LEFT)
			{
				this.dmiro= this.dmiro.turnLeft(this.dmiro);
				this.sopa-=1;
			}
			
			if (i.getRotation()==Rotation.RIGHT)
			{
				this.dmiro=this.dmiro.turnRight(this.dmiro);
				this.sopa-=1;
			}
			
			this.MostrarInfo();
			mostrarPantalla(this.dondeMira());
			return false;
		}
		else if (i.getAction()==Action.MOVE)
		{
			
			Street s = this.map.lookForStreet(this.plazaini, this.dmiro);
			if (s!=null)
			{
				if (s.isOpen())
				{
					this.plazaini=s.nextPlace(this.plazaini);
					System.out.println("WALL·E says: Moving in direction " + this.dmiro.toString());
					System.out.println(this.plazaini.toString());
					this.sopa-=5;
					this.MostrarInfo();
					mostrarPantalla(this.dondeMira());
				}
				else
				{
					System.out.println("WALL·E says: Arrggg, there is a street but it is closed!");
				}
				
			}
			else
			{
				mostrarPantalla("WALL·E says: There is no street in direction " + this.dmiro.toString());
			}
			return false;
		}
		else if (i.getAction()==Action.OPERATE)
		{
			int posicion = this.mochila.Buscador(i.getId());
			if ( posicion !=-1)
			{
				Item objeto= this.mochila.pickItem(i.getId());
				if (objeto.getClass()==CodeCard.class)
					this.mochila.addItem(objeto);
				if (objeto.use(this, this.plazaini))
				{
					if (objeto.getClass()==Fuel.class)
						if (objeto.canBeUsed())
						{
							this.mochila.addItem(objeto);
							this.MostrarInfo();
						}
						else	{
							this.MostrarInfo();
							System.out.println("WALL·E says: What a pity! I have no more "+objeto.getId()+ " in my inventory");
						}
					
					else if (objeto.getClass()==Garbage.class)
							{
								this.MostrarInfo(); //a�adido
								System.out.println("WALL·E says: What a pity! I have no more "+objeto.getId()+ " in my inventory");
							}
				}
				else System.out.println("WALL·E says: I have problems using the object "+ objeto.getId());
			}
			else System.out.println("WALL·E says: I have problems using the object " + i.getId());
			return false;
		}
		else if (i.getAction()==Action.PICK)
		{
			Item o=this.plazaini.pickItem(i.getId());
			if (o==null)
			{
				System.out.println("WALL·E says: Ooops, this place has not the object "+ i.getId());
			}
			else
			{
				if (this.mochila.addItem(o))
				{
					System.out.println("WALL·E says: I am happy! Now I have  "+ o.getId());
				}
				else System.out.println("WALL·E says: I am stupid! I had already the object "+ o.getId());
			}
			return false;
		}
		else if (i.getAction()==Action.SCAN)
		{
			if( i.getId().length()==0)//Aqui
			{
				if (this.mochila.numberOfItems()==0)
				{
					System.out.println("WALL·E says: My inventory is empty");
				}
				else
				{
					System.out.println("WALL·E says: I am carrying the following items"+Interpreter.LINE_SEPARATOR+ this.mochila.toString());
				}
			}
			else
			{
				Item o=this.mochila.getItem(i.getId());
				if (o==null)
				{
					System.out.println("WALL·E says: I have not such object");
				}
				else
				{
					System.out.println("WALL·E says: " + o.toString());
				}
			}
			return false;
		}
		else return false;
	}
	
	private static void processInstructionHelp()
	{
		mostrarPantalla( "The valid instructions for WALL-E are:");
		mostrarPantalla ( Interpreter.interpreterHelp());
	}
	
	private static String leerString(Scanner sc) 
	{
		String a= sc.nextLine();
		return a;
	}
	
	private static void mostrarPrompt() 
	{
		System.out.print("WALL·E > ");
	} 
	
	private static  void mostrarPantalla(String a)
	{
		System.out.println(a);
	}
	
	private String dondeMira() 
	{
		return "WALL·E is looking at direction " + this.dmiro.toString();
	}
	
	public void addFuel (int fuel)
	{
		this.sopa=this.sopa+fuel;
	}
	
	public void addRecycledMaterial (int basura)
	{
		this.recicla=this.recicla+basura;
	}
	
	public int getFuel()
	{
		return this.sopa;
	}
	
	public int getRecycledMaterial()
	{
		return this.recicla;
	}
	
	public City getMap()
	{
		return this.map;
	}
	
	public Direction getDirection()
	{
		return this.dmiro;
	}
	
	public ItemContainer getMochila()
	{
		return this.mochila;
	}
	
	public Street getHeadingStreet()
	{
		return this.map.lookForStreet(this.plazaini, this.dmiro);
	}
	
	private void MostrarInfo()
	{
		if (this.sopa>0)
		{
			System.out.println("   "+"* My power is "+ this.sopa);
		}
		else System.out.println("   "+"* My power is 0");
		System.out.println("   "+"* My recycled material is: "+ this.recicla);
	}
	
}
