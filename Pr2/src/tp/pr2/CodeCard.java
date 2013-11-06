package tp.pr2;

public class CodeCard extends Item {
	private String code;
	
	public CodeCard(String id, String des, String c)
	{
		super(id,des);
		this.code=c;
	}
	
	public boolean canBeUsed()
	{
		return true;
	}
	
	public boolean use(RobotEngine r, Place p) 
	{	
		
			Street s =  r.getHeadingStreet(); 
			if (s==null) return false;	
			else if (s.isOpen()) 
				return s.close(this);		
			else 
				return s.open(this);		
		
	}
	
	public String getCode()
	{
		return this.code;
	}
	
}
