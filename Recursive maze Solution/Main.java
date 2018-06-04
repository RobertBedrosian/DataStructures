package maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	   final static int TRIED = 2;
	   final static int PATH = 8;

	public static void main(String[] args) throws IOException 
	{
		String csvFile1 = "D:/Downloads/mazeinput.csv";
		BufferedReader br=null;
		int rowlength = 41;
		String[][] maze = new String[41][];
		int [][] grid = new int[41][41];
		String delimiter = ",";
		String line = "";

		try 
		{
			br = new BufferedReader(new FileReader(csvFile1));
			for (int i=0;i<rowlength;i++)
			{
				while ((line = br.readLine()) != null) 
				{
					 maze[i++] = line.split(delimiter);
				}
			}

			br.close();
		}
		catch ( ArrayIndexOutOfBoundsException e ) 
		{
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i=0;i<rowlength;i++)
		{
			for (int l=0;l<rowlength;l++)
			{
				grid[i][l] = Integer.parseInt(maze[i][l]);
			}
			System.out.println();
		}
		for (int i=0;i<rowlength;i++)
		{
			for (int l=0;l<rowlength;l++)
			{
				System.out.print(grid[i][l]);
			}
			System.out.println();
		}
		System.out.println("==============================================================================================================");
		//40,17 = exit
		//0, 27 = start
		//start position
		int x=0;
		int y=27;
		traverse (grid, x, y);
		for (int i=0;i<rowlength;i++)
		{
			for (int l=0;l<rowlength;l++)
			{
				System.out.print(grid[i][l]);
			}
			System.out.println();
		}
		File file = new File("D:/Downloads/maze instructions.csv");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter output=new BufferedWriter(fw);
		try
		{
			for (int i=0;i<rowlength;i++)
			{
				for (int l=0;l<rowlength;l++)
				{
					output.write(String.valueOf(grid[i][l]));
					output.write(",");
				}
				output.newLine();
			}
			output.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
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
		File file1 = new File("D:/Downloads/maze instructions.txt");
		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter output1=new BufferedWriter(fw1);
		try
		{
			output1.write("Tried Path = 2, Actual Path = 8");
			output1.newLine();
			output1.write("=====================================================================================================================");
			output1.newLine();
			for (int i=0;i<rowlength;i++)
			{
				for (int l=0;l<rowlength;l++)
				{
					output1.write(String.valueOf(grid[i][l]));
					output1.write(",");
				}
				output1.newLine();
			}
			output1.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
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
	//pass row x and column y into method

	   public static boolean traverse (int [][] grid, int row, int column)
	   {
	      boolean done = false;
	      
	      if (valid (grid, row, column))
	      {
	         grid[row][column] = TRIED;  // this cell has been tried

	         if (row == 40 && column == 17)
	            done = true;  // the maze is solved
	         else
	         {
	        	if (!done)
	            done = traverse (grid,row+1, column);     // down
	            if (!done)
	               done = traverse (grid,row, column+1);  // right
	            if (!done)
	               done = traverse (grid,row-1, column);  // up
	            if (!done)
	               done = traverse (grid,row, column-1);  // left
	         }

	         if (done)  // this location is part of the final path
	            grid[row][column] = PATH;
	      }
	      
	      return done;
	   }
	   
	   private static boolean valid (int [][] grid,int row, int column)
	   {
	      boolean result = false;
	 
	      // check if cell is in the bounds of the matrix
	      if (row >= 0 && row < grid.length &&
	          column >= 0 && column < grid[row].length)

	         //  check if cell is not blocked and not previously tried
	         if (grid[row][column] == 1)
	            result = true;

	      return result;
	   }

	   public static String toString (int [][] grid)
	   {
	      String result = "\n";

	      for (int row=0; row < grid.length; row++)
	      {
	         for (int column=0; column < grid[row].length; column++)
	            result += grid[row][column] + "";
	         result += "\n";
	      }

	      return result;
	   }
	

	}

