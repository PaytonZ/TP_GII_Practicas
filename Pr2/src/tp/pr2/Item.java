package tp.pr2;

//ESTA ESTA FINIQUITADA

public abstract class Item {
	protected String id;
	protected String description;
	
	public Item(String i, String d)
	{
		this.id=i;
		this.description=d;
	}
	
	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine r, Place p);
	
	public String getId()
	{
		return this.id;
	}
	
	public String toString()
	{
		return  this.id + ": "+ this.description;
	}
}
