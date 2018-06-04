package poker;

public class Card 
{
	private int value;
	private int type;

	public Card(int value, int type)
	{
		this.value=value;
		this.type=type;
	}
	public int getType()
	{
		return this.type;
	}
	public int getValue()
	{
		return this.value;
	}
}
