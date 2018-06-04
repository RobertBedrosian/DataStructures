package project2;

public class Graph 
{
	int v;
	int [][]adj;
	int [][]adj2;
	String [] verticies;
	public Graph(int v)
	{
		this.v=v;
		verticies= new String[v];
		adj= new int [v][v];
		adj2=new int [v][v];
		for (int i=0;i<v;i++)
		{
			for (int j=0;j<v;j++)
			{
				adj[i][j]=0;
				adj2[i][j]=0;
			}
		}
		
	}
	public void moddfs()
	{
		MyStack s=new MyStack(v);
		int visited[]=new int[v];
		int element=0;
		int i=0;
		visited[i]= 1;
		s.push(0);
		while (!s.isEmpty())
		{
			element=s.peek();
			i=element;
			while (i < v)
			{
				if (adj[element][i]==1 && visited[i]==0)
				{
					s.push(i);
					visited[i]=1;
					element=i;
					i=1;
					adj2[element][i]=0;
					continue;
				}
				i++;
			}
			s.pop();
		}
	findDupe();
	}
	/*
	 * In finding dupelicates, we run a modified dfs method. As we traverse the
	 * graph, we remove the edges we traverse. Whatever is left is not a "tree"
	 * edge in which indicates a dupelicate. We keep two copies of an ajacency
	 * matrix in order to delete values on one matrix and preserve the values
	 * with the other.
	 */
	public void findDupe()
	{
		boolean done=false;
		int index=0;
		while (!done)
		{
			for (int i=0;i<v;i++)
			{
				if (adj2[index][i]==1)
				{
					System.out.println(verticies[i] + " is included multiple times in " 
				+ verticies[index] + ". Remove " + verticies[i]
						+ " from one of the files below");
					for (int q=0;q<v;q++)
					{
						if (adj[i][q]==1)
						{
							System.out.println(verticies[q] + " includes "+ verticies[i]);
							System.out.println(verticies[index]+ " includes "+ verticies[i]);
						}
					}
				}
			}
			if (index==v-1)
			{
				done=true;
			}
			else
			{
				index++;
			}
		}
	}

}
