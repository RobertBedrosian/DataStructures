package practice;

public class Bubble {

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
	public static void sort(int []a)
	{
		int temp, i;
		boolean flag=true;
		while (flag)
		{
			flag=false;
			for (i=0; i<a.length-1;i++)
			{
				if (a[i+1]<a[i])
				{
					temp=a[i+1];
					a[i+1]=a[i];
					a[i]=temp;
					flag=true;
				}
			}
		}
		
	}
}
