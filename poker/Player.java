package poker;

public class Player 
{
	private final int handsize=5;
	protected Card[]playercards=new Card[handsize];
	private int number;
	private int rank=1;
	private String ranks;
	int[] cards=new int[52];
	int[] values=new int[13];
	int[] type=new int[4];
	boolean check=true;
	String hand;
	public Player(int number)
	{
		this.number=number;
		
	}
	public int getnumber()
	{
		return number;
	}
	public void setrank(int rank)
	{
		this.rank=rank;
	}
	public int getrank()
	{
		return rank;
	}
	public String getRank()
	{
		return ranks;
	}
	public void getrankS()
	{
		if (this.rank==16)
		{
			this.ranks=("Dinner Party");
		}
		else if (this.rank==15)
		{
			this.ranks=("Politics!");
		}
		else if (this.rank==14)
		{
			this.ranks=("Orgy!");
		}
		else if (this.rank==13)
		{
			this.ranks=("Kingdom!");
		}
		else if (this.rank==12)
		{
			this.ranks=("Homosapiens!");
		}
		else if (this.rank==11)
		{
			this.ranks=("Overfull House!");
		}
		else if (this.rank==10)
		{
			this.ranks=("Triplets!");
		}
		else if (this.rank==9)
		{
			this.ranks=("Flush!");
		}
		else if (this.rank==8)
		{
			this.ranks=("Odd!");
		}
		else if (this.rank==7)
		{
			this.ranks=("Even!");
		}
		else if (this.rank==6)
		{
			this.ranks=("Monarchy!");
		}
		else if (this.rank==5)
		{
			this.ranks=("3-Pair!");
		}
		else if (this.rank==4)
		{
			this.ranks=("Monochromatic!");
		}
		else if (this.rank==3)
		{
			this.ranks=("Swingers!");
		}
		else if (this.rank==2)
		{
			this.ranks=("Rainbow!");
		}
		else
		{
			this.ranks=("Non-Rainbow :(");
		}
	}

}
