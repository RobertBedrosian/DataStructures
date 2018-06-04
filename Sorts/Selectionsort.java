package practice;

public class Selectionsort 
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
		sort(x);
		System.out.print("Now we have ");
		for (int t=0;t<x.length;t++)
		{
			System.out.print(x[t] + ", ");
		}
	}
	public static void sort(int[] a)
	{
		for (int i=0; i< a.length-1; i++)
		{
			int index= i;
			for (int j=i+1;j<a.length;j++)
			{
				if (a[j]<a[index])
				{
					index=j;
				}
				
			}
			int smallernumber = a[index];
			a[index]=a[i];
			a[i]=smallernumber;
		}
		
	}
}
