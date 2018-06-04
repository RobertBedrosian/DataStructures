package tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) throws IOException
	{
		//Yes on left, No on right
		//Question loop
		//Placeholder
		TreeNode sleft=new TreeNode("Is it a cat?  ", "cat");
		TreeNode sright=new TreeNode("Is it a snake?","snake");
		TreeNode start=new TreeNode("Does it have legs?",sleft, sright, "cat");
		TreeNode curr=start;
		boolean restart = true;
		BufferedReader userinput = new BufferedReader(new InputStreamReader(System.in));
		while(restart)
		{
			System.out.println("Think of an animal and I will guess it.");
			curr = start;
			System.out.print(curr.item);
			boolean quit = false;
			while(!quit)
			{
				String response=userinput.readLine();
				while(!response.equalsIgnoreCase("no") && !response.equalsIgnoreCase("yes"))
				{
					System.out.println("Please enter Yes or no.");
					response =userinput.readLine();
				}
				if (response.equalsIgnoreCase("yes"))
				{
					if (curr.leftChild==null)
					{
						System.out.print("I win!  Continue? ");
						response = userinput.readLine();
						if (response.equalsIgnoreCase("no"))
						{
							restart=false;
							quit=true;
							System.out.print("Goodbye.");
						}
						else
						{
							quit=true;
						}
					}
					else
					{
						curr=curr.leftChild;
						System.out.print(curr.item);
					}
				}
				else
				{
					if(curr.rightChild == null)
					{
						System.out.print("I give up.  What is it? ");
						String tempA;
						tempA=userinput.readLine();
						System.out.print("Please type a question whose answer is yes for a(n) " + tempA + " and no for a(n) " + curr.animal +  ".  ");
						String tf=userinput.readLine();
						TreeNode T= new TreeNode("Is it a " + tempA + "?", tempA);
						TreeNode p= new TreeNode(tf, T, T.animal);
						curr.setRight(curr, p);
						System.out.print("Continue? ");
						response= userinput.readLine();
						if (response.equalsIgnoreCase("no"))
						{
							restart = false;
							quit=true;
							System.out.print("Goodbye.");
						}
						else
						{
							quit=true;
						}
					}
					else
					{
						curr=curr.rightChild;
						System.out.print(curr.item);
					}

				}
				
			}
		}
		userinput.close();
		
		
		

	}
	
}
