//Robert Bedrosian, ID:108610565
//CS282 Advanced Data Structures

package poker;

import java.util.ArrayList;

public class Poker 
{
	//This program will simulate a "Crazy Omaha" game
  	//by using an array of 52 indices  to simulate a full deck
	//and the first 13 indices will be hearts, then diamonds, 
	//then clubs, and finally spades. Each player will have
	//their own array of 52 with 1's indicating a card from the	
	//table and 2's indicating a card from the players hand
	//If a minimum of 2 cards and a maximum of 3 cards can be
	//used from the players hand, then the hand must be
	//constructed using a minimum value of 8 and max of 9
	//for most cases.
	//Also, our indices are as follows:	
	//index 0+(13*suit)=Ace, 1+(13*suit)=2, 2+(13*suit)=3,...,
	//10+(13*suit)=Jack, 11+(13*suit)=Queen, 12+(13*suit)=King
	public static void main(String[] args)
	{																													
		final int tablesize=5; 
		int numplayers=0;
		Card[] table=new Card[tablesize];
		int[] tablevalues=new int[13];
		int[] tabletype=new int[4];
		ArrayList<Player> players= new ArrayList<>();
		//hearts, diamonds, clubs, spades
		for (int i=0; i<args.length; i++)
		{
			int value;
			int type;
			String[] parts=args[i].split("");
			if (parts[0].equalsIgnoreCase("2"))
			{
				parts[0]="1";
			}
			if (parts[0].equalsIgnoreCase("3"))
			{
				parts[0]="2";
			}
			if (parts[0].equalsIgnoreCase("4"))
			{
				parts[0]="3";
			}
			if (parts[0].equalsIgnoreCase("5"))
			{
				parts[0]="4";
			}
			if (parts[0].equalsIgnoreCase("6"))
			{
				parts[0]="5";
			}
			if (parts[0].equalsIgnoreCase("7"))
			{
				parts[0]="6";
			}
			if (parts[0].equalsIgnoreCase("8"))
			{
				parts[0]="7";
			}
			if (parts[0].equalsIgnoreCase("9"))
			{
				parts[0]="8";
			}
			if (parts[0].equalsIgnoreCase("j"))
			{
				parts[0]="10";
			}
			if (parts[0].equalsIgnoreCase("q"))
			{
				parts[0]="11";
			}
			if (parts[0].equalsIgnoreCase("k"))
			{
				parts[0]="12";
			}
			if (parts[0].equalsIgnoreCase("a"))
			{
				parts[0]="0";
			}
			if (parts[0].equalsIgnoreCase("t"))
			{
				parts[0]="9";
			}
			if (parts[1].equalsIgnoreCase("h"))
			{
				parts[1]="0";
			}
			if (parts[1].equalsIgnoreCase("d"))
			{
				parts[1]="1";
			}
			if (parts[1].equalsIgnoreCase("c"))
			{
				parts[1]="2";
			}
			if (parts[1].equalsIgnoreCase("s"))
			{
				parts[1]="3";
			}
			
			value=Integer.parseInt(parts[0]);
			type= Integer.parseInt(parts[1]);
			Card c=new Card(value, type);
			//We store the first 5 "arguments" as the cards from the table
			if (i < 5)
			{
				table[i]=c;
				tablevalues[c.getValue()]++;
				tabletype[c.getType()]++;
			}
			else
			{
			//We then store the next 5 as the next player
				if (i%5==0)
				{
					Player p=new Player(numplayers+1);
					//This stores the table's cards in the player's array of 52
					for (int k=0;k<5;k++)
					{
						p.cards[table[k].getValue()+(13*table[k].getType())]=1;
					}
					players.add(p);
					numplayers++;
					
					players.get(numplayers-1).playercards[(i%5)]=c;
					players.get(numplayers-1).values[c.getValue()]++;
					players.get(numplayers-1).type[c.getType()]++;
					players.get(numplayers-1).cards[c.getValue()
					                                +(13*c.getType())]=2;
					
				}
				else
				//As long as we havn't hit a total of 5 cards, 
				//we continue to add cards
				//to the current player's hand.
				{
					players.get(numplayers-1).playercards[(i%5)]=c;
					players.get(numplayers-1).values[c.getValue()]++;
					players.get(numplayers-1).type[c.getType()]++;
					players.get(numplayers-1).cards[c.getValue()
					                                +(13*c.getType())]=2;
				}
			}
		}
		//Testcase
		//for (int i=0;i<tablesize;i++)
		//{
		//	System.out.println(table[i].getType() + " and the value is " +
		//table[i].getValue());
		//}
		//for (int i=0;i<numplayers;i++)
		//{
		//	System.out.println("Player " +
		//players.get(i).getnumber() + " has");
		//	for (int j=0; j<tablesize;j++)
		//	{
		//		System.out.println(players.get(i).playercards[j].getValue());
		//	}
		//}
		
		//for (int i=0;i<numplayers;i++)
		//{
		//	System.out.println("Player " 
		//+ players.get(i).getnumber() + " has ");
		//	for (int j=0; j<52;j++)
		//	{
		//		System.out.print(players.get(i).cards[j] + " ");
		//	}
		//}
		//End Testcase
		rank(numplayers, players);
		int num=1;
		//We finally check from highest rank to lowest and then output the
		//players
		for (int i=16;i>=1;i--)
		{
			for (int j=0;j<numplayers;j++)
			{
				if (players.get(j).getrank()==i)
				{
					players.get(j).getrankS();
					System.out.println(num + ") Player " 
					+ players.get(j).getnumber() +
					" has " + players.get(j).getRank());
					num++;
				}
			}
		}

	}
	public static void rank(int numplayers, ArrayList<Player> players)
	{
		for (int i=0;i<numplayers;i++)
		{
			int [] a=players.get(i).cards;					
			//Dinnerparty check
			if ((a[11] + a[12] + a[24] + a[25] + a[37] + a[38] + a[50] + a[51])
					>=8)
			{
				int sum=0;
				int issuited=0;
				for (int x=0;x<4;x++)
				{
					if (a[11+(13*x)]!=0)
					{
						if (a[12+(13*x)]!=0)
						{
							issuited++;
							sum+=a[11+(13*x)];
							sum+=a[12+(13*x)];
						}
					}
				}
				if (issuited==3 && (sum==8||sum==9))
				{
					players.get(i).setrank(16);
				}

				
			}
			//Politics check
			if (players.get(i).getrank()<16)
			{
				int sum=0;
				int suited = 0;
				int helper = 0;
				while (helper <4)
				{
					if (a[10+13*helper]!=0)
					{
						if (a[11+13*helper]!=0)
						{
							if (a[12+13*helper]!=0)
							{
								sum+=a[10+13*helper];
								sum+=a[11+13*helper];
								sum+=a[12+13*helper];
								suited++;
							}
							
						}
						
					}
						helper++;
				}
				if (suited==2 && (sum==8 || sum==9))
				{
					players.get(i).setrank(15);
				}
			}
			//Orgy check
			if ( players.get(i).getrank()<15)
			{
				int sum1=0;
				int sum2=0;
				for (int z=0;z<4;z++)
				{
					for (int x=10;x<12;x++)
					{
						if (a[x+(13*z)]==1)
						{
							sum1++;
						}
						if (a[x+(13*z)]==2)
						{
							sum2+=2;
						}
					}
				}
				if (sum2>=4 && sum1 >=3)
				{
					players.get(i).setrank(14);
					
				}
			}
			//Kingdom and flush check
			if (players.get(i).getrank()<14)
			{
				for (int x=0;x<4;x++)
				{
					int sum1=0;
					int sum2=0;
					for (int y=0;y<13;y++)
					{
						if (a[y+(13*x)]==1)
						{
							sum1++;
						}
						if (a[y+(13*x)]==2)
						{
							sum2+=2;
						}
					}
					if (sum2>=4 && sum1>=3)
					{
						if (a[10+(13*x)] !=0 && a[11+(13*x)]!=0 &&
								a[12+(13*x)]!=0)
						{
							players.get(i).setrank(13);
						}
						else
						{
							players.get(i).setrank(9);
						}
					}
				}
			}

			//homosapiens check
			if (players.get(i).getrank()<13)
			{
				int sum1=0;
				int sum2=0;
				for (int x=0; x<4;x++)
				{
					for(int y=10;y<13;y++)
					{
						if (a[y+(13*x)]==1)
						{
							sum1++;
						}
						if (a[y+(13*x)]==2)
						{
							sum2+=2;
						}
					}
				}
				if (sum2>=4 && sum1 >=3)
				{
					players.get(i).setrank(12);
				}
			}
			//overfull house check
			if (players.get(i).getrank()<12)
			{
				int sum=0;
				//check for 4 of a kind
				for (int z=0; z<13; z++)
				{
					if (a[z]!=0)
					{
						if (a[z+13]!=0)
						{
							if (a[z+26]!=0)
							{
								if (a[z+39]!=0)
								{
									sum+=a[z];
									sum+=a[z+13];
									sum+=a[z+26];
									sum+=a[z+39];
								}
								
							}
						}
					}
				}
				//check for a pair
				int []ispair=new int[13];
				if(sum<=7)
				{
					for (int z=0; z<52; z++)
					{
						if (a[z]!=0)
						{
							ispair[z%13]+=a[z];
						}
					}
					//check array now
					for (int z=0; z<13; z++)
					{
						if (ispair[z]>=2 && ispair[z] <=4)
						{
							if (sum+ispair[z]==8 || sum+ispair[z]==9)
							{
								players.get(i).setrank(11);
							}
						}
					}
				}
			}
			//triplets check or triple check
			if (players.get(i).getrank()<11)
			{
				int []istrip1=new int[13];
				int []istrip2=new int[13];
				int tripcount=0;
				int trip=0;
				
				for (int z=0; z<52; z++)
				{
					if (a[z]==1)
					{
						istrip1[z%13]++;
					}
					if (a[z]==2)
					{
						istrip2[z%13]+=2;
					}
				}
				for (int z=0;z<13;z++)
				{
					if (istrip1[z]+(istrip2[z]/2)>=3)
					{
						trip+=istrip1[z];
						trip+=istrip2[z];
						tripcount++;
					}
				}
				if (tripcount==2 && (trip==8 || trip==9))
				{
					players.get(i).setrank(10);
				}
				if (tripcount==1 && (trip==8 || trip==9))
				{
					players.get(i).setrank(5);
				}
			}
			//check Odd or even
			if (players.get(i).getrank()<9)
			{
				//Because of how our indices are set, index 1=2 and index 2 =3
				//so really, if we are checking for odds then if take
				//index%2 and if it is equal to 0 then it is an odd number.
				int isodd=0;
				int howodd=0;
				int iseven=0;
				int howeven=0;
				for (int z=0;z<4;z++)
				{
					for (int x=1;x<10;x++)
					{
						if (a[x+(13*z)]!=0)
						{
							if (x%2==1)
							{
								iseven++;
								howeven+=a[x+(13*z)];
							}
							if (x%2==0)
							{
								isodd++;
								howodd+=a[x+(13*z)];
							}
						}
					}
				}
				if (isodd>=6 && (howodd==8 || howodd==9))
				{
					players.get(i).setrank(8);
				}
				if (iseven>=6 && (howeven==8 || howeven==9))
				{
					players.get(i).setrank(7);
				}
			}
			//Monarchy check
			if (players.get(i).getrank()<7)
			{
				int []b=new int[52];
				for (int x=0;x<52;x++)
				{
					b[x]=a[x];
				}
				int monarchy=0;
				int howm=0;
				for (int x=0;x<4;x++)
				{
					if (b[10+(13*x)]!=0)
					{
						if (b[11+(13*x)]!=0)
						{
							if (b[12+(13*x)]!=0)
							{
								monarchy++;
								howm+=b[10+(13*x)];
								howm+=b[11+(13*x)];
								howm+=b[12+(13*x)];
								b[10+(13*x)]=0;
								b[11+(13*x)]=0;
						  	b[12+(13*x)]=0;
							}
						}
					}
				}
				if (monarchy==1)
				{
					for (int x=0;x<4;x++)
					{
						for (int y=10;y<13;y++)
						{
							if (b[y+(13*x)]!=0)
							{
								b[y+(13*x)]=0;
							}
						}
					}
				}
				for (int x=0;x<52;x++)
				{
					howm+=b[x];
				}
				if (howm >=8 && monarchy==1)
				{
					players.get(i).setrank(6);
				}
				
			}
			//check for monochromatic
			if (players.get(i).getrank()<5)
			{
				for (int x=0;x<2;x++)
				{
					int sum1=0;
					int sum2=0;
					for (int y=0;y<26;y++)
					{
						if (a[y+(26*x)]==1)
						{
							sum1++;
						}
						if (a[y+(26*x)]==2)
						{
							sum2+=2;
						}
					}
					if (sum2>=4 && sum1>=4)
					{
						players.get(i).setrank(4);
					}
				}
			}
			
			//check for swingers (2 suited kings and queens)
			if (players.get(i).getrank()<4)
			{
				int sum=0;
				int suited=0;
				for (int x=0;x<4;x++)
				{
					if (a[11+(13*x)]!=0)
					{
						if (a[12+(13*x)]!=0)
						{
							sum+=a[11+(13*x)];
							sum+=a[12+(13*x)];
							suited++;
						}
					}
				}
				if (suited==2 && (sum<=6))
				{
					players.get(i).setrank(3);
				}
			}
			//check for rainbow
			if (players.get(i).getrank()<3)
			{
				int suits=0;
				int howsuits=0;
				for (int x=0;x<4;x++)
				{
					for (int y=0;y<13;y++)
					{
						if (suits !=1+x)
						{
							if (a[y+(13*x)]!=0)
							{
								suits++;
								howsuits+=a[y+(13*x)];
							}
						}
					}
				}
				if (suits==4 && howsuits <=7)
				{
					players.get(i).setrank(2);
				}
			}
			}
			//end of for	
			
		}

}
