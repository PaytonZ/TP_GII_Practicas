package tp.pr2;

public class Garbage extends Item{
	private int recicla;
	private int times;
	
	public Garbage (String id, String des, int r)
	{
		super (id,des);
		this.recicla=r;
		this.times=1;
	}
	
	public boolean canBeUsed()
	{
		if (this.times>0)
		return true;
		else return false;
	}
	
	public boolean use(RobotEngine r, Place p) 
	{
		if (this.canBeUsed())
		{
			r.addRecycledMaterial(this.recicla);	
			this.times--;
			return true;
		}
		else return false;
				
	}
	
	public String toString()
	{
		return this.id + ": "+ this.description + "// recycled material = " + this.recicla;
	}
}
