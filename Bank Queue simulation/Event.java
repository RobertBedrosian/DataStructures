package queue;

public class Event 
{
	private String stuff;
	private int locatime;
	
	public Event(String stuff, int locatime)
	{
		this.stuff=stuff;
		this.locatime=locatime;
		
	}
	public Event(String stuff, String locatime)
	{
		this.stuff=stuff;
		this.locatime=Integer.valueOf(locatime);
		
	}
	public int gettime()
	{
		return locatime;
	}
	public String getstuff()
	{
		return stuff;
		
	}
	public void setstuff(String stuff)
	{
		this.stuff=stuff;
		
	}
	public void concatenate(String stuff)
	{
		this.stuff= this.stuff + ", " +stuff;
	}

	
	
	
}
