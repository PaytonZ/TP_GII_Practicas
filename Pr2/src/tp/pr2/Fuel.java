package tp.pr2;

public class Fuel extends Item{
	private int power;
	private int times;
	
	public Fuel(String id, String des, int p, int t)
	{
		super(id,des);
		this.power=p;
		this.times=t;
	}
	
	public boolean canBeUsed ()
	{
		if (this.times>0)
		{
			return true;
		}
		else
			return false;
	}
	private void disminuirTimes()
	{
		this.times--;
	}
	
	public boolean use (RobotEngine r, Place p) 
	{
		if (this.canBeUsed())
		{
			r.addFuel(this.power);
			this.disminuirTimes();
			return true;	
		}
		else return false;
	}
	
	public int getTimes()
	{
		return this.times;
	}
	
	public String toString()
	{
		return  this.id + ": "+ this.description + "// power = " + this.power + ", times = " + this.times;
	}
}
