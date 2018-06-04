package project2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.io.File;

/**
 * 
 * @author Robert Bedrosian
 *Run with arguments
 */
public class Main 
{

	public static void main(String[] args) 
	{

		File dir=new File(args[0]);
		displayDirectoryContents(dir);
		
		

	}
	//This iterates through the entire directory recursively
	public static void displayDirectoryContents(File dir) 
	{
		Pattern p=Pattern.compile("(#include)(\\s\\W)(\\w+\\W\\w+)(\\W)");
		File[] files = dir.listFiles();
		/*
		 * The graph is comprised of files (verticies) and include statements
		 * (edges).
		 */
		Graph a=new Graph(files.length);
		for (int i=0;i<files.length;i++)
		{
			a.verticies[i]=files[i].getName();
			//System.out.println(a.verticies[i]);
		}
		int index=0;
		int sindex;
		for (File file : files) 
		{
			if (file.isDirectory()) 
			{
				//System.out.println("directory:" + file.getCanonicalPath());
				displayDirectoryContents(file);
			} 
			else 
			{
				//System.out.println("     file:" + file.getCanonicalPath());
				//This checks the file for includes
				BufferedReader br = null;
				try 
				{
					String line = "";
					br = new BufferedReader(new FileReader(file));
					while ((line = br.readLine()) != null) 
					{
						sindex=0;
						try
						{
							Matcher m=p.matcher(br.readLine());
							while (m.find())
							{
								//System.out.println("found " + m.group(3) + " from "
							//+ file.getCanonicalPath());
								//Need to link filename to edge here
								//this while loop will identify which number corresponds to
								//which include file.
								boolean isequal=false;
								while (!isequal)
								{
									if (a.verticies[sindex].equalsIgnoreCase(m.group(3)))
									{
										a.adj[index][sindex]=1;
										a.adj2[index][sindex]=1;
										isequal=true;
									}
									else
									{
										sindex++;
									}
								}
							}
							
						} catch ( ArrayIndexOutOfBoundsException e ) {
						    e.printStackTrace();
						}
							 
					}
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
				//end check for includes
				index++;
			}
		}
		a.moddfs();
	}
	//End Directory check

}
