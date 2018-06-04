package queue;

import java.util.ArrayList;

public class Customer {
	String id;
	String arrival;
	int transaction;
	int atT;
	int wait=0;
	
	public Customer(String id, String arrival, int transaction)
	{
		this.id=id;
		this.arrival=arrival;
		this.transaction= transaction;
	}
	public void trans(int transaction)
	{
		this.transaction= this.transaction + transaction;
	}
	public String getid()
	{
		return this.id;
	}
	public void setatT(int atT)
	{
		this.atT=atT;
	}
	public void setwait(int wait)
	{
		this.wait=this.wait + wait;
	}
	public int getdeparted()
	{
		return (this.atT + this.transaction);
	}
	

}
