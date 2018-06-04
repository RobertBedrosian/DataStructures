package practice;

public class Insertion 
{

	public static void main(String[] args) 
	{
		System.out.print("Hello, I will sort ");
		int x[]=new int[5];
		x[0]=5;
		x[1]=2;
		x[2]=7;
		x[3]=4;
		x[4]=1;
		
		for(int t=0;t<x.length;t++)
		{
			System.out.print(x[t] + ", ");
		}
		System.out.println("in order!");
		insertsort(x);
		System.out.print("Now we have ");
		for (int t=0;t<x.length;t++)
		{
			System.out.print(x[t] + ", ");
		}
	}
	
	public static void insertsort(int[] a)
	{
		for (int i=1; i<a.length;i++)
		{
			int key=a[i];
			int j;
			for (j=i-1;j>=0 && key < a[j]; j--)
			{
				a[j+1]=a[j];
			}
			a[j+1]=key;
		}
		
	}

}
