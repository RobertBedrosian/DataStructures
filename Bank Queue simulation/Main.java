package queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main {

	public static void main(String[] args) throws IOException 
	{
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Queue queueA = new LinkedList();
		String csvFile1 = "D:/Downloads/Bank Queue v2.csv";
		BufferedReader br=null;
		try 
		{
			String delimiter = ",";
			String line = "";
			br = new BufferedReader(new FileReader(csvFile1));
			while ((line = br.readLine()) != null) 
			{
				
				
				String[] Info = line.split(delimiter);
				int transaction;
				if(Info[3].equals("Deposit"))
				{
					transaction = 4;
				}
				else if(Info[3].equals("Withdrawal"))
				{
					transaction = 2;
				}
				else if(Info[3].equals("Transfer"))
				{
					transaction = 3;
				}
				else
				{
					transaction = 1;
				}
				System.out.println(Info[0] + "," + Info[3]);
				Customer f= new Customer(Info[1], Info[2], transaction);
				if (customers.size()==0)
				{
					customers.add(f);
				}
				else
				{
					if (customers.get(customers.size()-1).id.equals(f.id))
					{
						customers.get(customers.size()-1).trans(transaction);
					}
					else
					{
						customers.add(f);
					}
				}
				
				
			}
			br.close();
			System.out.println(customers.size());
		}
		catch ( ArrayIndexOutOfBoundsException e ) 
		{
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		LinkedList <Customer> queue = new LinkedList<Customer>();
		LinkedList <Event> events = new LinkedList<Event>();
		int loctime = 1;
		for (int i = 0; i <=44;i++)
		{
			queue.add(customers.get(i));
			Event t=new Event("Customer " + customers.get(i).id + " enters the bank", customers.get(i).arrival);
			events.add(t);
			if (i==0)
			{
				queue.get(i).setwait(0);
			}
			else
			{
				queue.get(i).setwait(queue.get(i-1).getdeparted() - Integer.valueOf(queue.get(i).arrival));
			}
			queue.get(i).setatT(loctime);
			Event Z = new Event ("Customer " + customers.get(i).id + " begins transaction", loctime);
			events.add(Z);
			loctime = loctime + queue.get(i).transaction;
			Event s = new Event("Customer " + customers.get(i).id + " departs", queue.get(i).getdeparted());
			events.add(s);
			
		}
		double avg=0.00;
		for (int i=0; i <= 44;i++)
		{
			avg = avg + queue.get(i).wait;
			System.out.println("Customer " + queue.get(i).id + " arrived " + queue.get(i).arrival +  " waited " + queue.get(i).wait + " Was at Teller at " + queue.get(i).atT + " Transaction time was " + queue.get(i).transaction + " Departed at " + queue.get(i).getdeparted());
		}
		/**
		 * Sort list of events
		 */
		Collections.sort(events, new Comparator<Event>(){
			   @Override
			   public int compare(Event o1, Event o2){
			        if(o1.gettime() < o2.gettime()){
			           return -1; 
			        }
			        if(o1.gettime() > o2.gettime()){
			           return 1; 
			        }
			        return 0;
			   }
			});
		for (int i=0; i < events.size();i++)
		{
			if (i< events.size()-1)
			{
				if(events.get(i+1).gettime() == events.get(i).gettime())
				{
					events.get(i).concatenate(events.get(i+1).getstuff());
					events.remove(i+1);
					
				}
				
			}
		}		
		for (int i=0; i < events.size();i++)
		{
			if (i< events.size()-1)
			{
				if(events.get(i+1).gettime() == events.get(i).gettime())
				{
					events.get(i).concatenate(events.get(i+1).getstuff());
					events.remove(i+1);
					
				}
				
			}
		}
		for (int i =0;i< events.size();i++)
		{
			System.out.println(events.get(i).gettime()+ "\t" + events.get(i).getstuff());
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		avg=avg/customers.size();
		String Aavg;
		Aavg= formatter.format(avg);
		System.out.println(avg);
		File file1 = new File("D:/Downloads/bankqueueOutput.csv");
		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter output1=new BufferedWriter(fw1);
		try
		{
			output1.write("Customer, Arrived, Waiting Time, At Teller, Transaction Time, Departed");
			output1.newLine();
			for (int i=0;i<=44;i++)
			{
				output1.write(queue.get(i).id + "," + queue.get(i).arrival + "," + queue.get(i).wait + "," +queue.get(i).atT  + "," + queue.get(i).transaction +","+ queue.get(i).getdeparted() );
				output1.newLine();
			}
			output1.write("==================================================");
			output1.newLine();
			output1.write("Average wait time was: " + Aavg + " / Total number of Arrivals was: " + customers.size());
			output1.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}finally 
		{
			if (br != null) 
			{
				try 
				{
					br.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		File file = new File("D:/Downloads/bankqueueTrace.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter output=new BufferedWriter(fw);
		try
		{
			output.write("Time\tEvent");
			output.newLine();
			for (int i =0;i< events.size();i++)
			{
				output.write(events.get(i).gettime()+ "\t" + events.get(i).getstuff());
				output.newLine();
			}
			output.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}finally 
		{
			if (br != null) 
			{
				try 
				{
					br.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
