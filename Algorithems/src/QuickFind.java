import java.util.Scanner;


public class QuickFind
{
	static int[] ints;
	static Scanner scan = new Scanner(System.in);
	public QuickFind()
	{
		ints = new int[9];
		for(int i = 0; i < ints.length; i++)
		{
			ints[i]=i;
		}
	}
	public static void main(String[] args)
	{
		while(scan.hasNext())
		{
			if(scan.next().equals("union"))
			{
				union(scan.nextInt(),scan.nextInt());
			}
			else if(scan.next().equals("find"))
			{
				if(find(scan.nextInt(),scan.nextInt()))
				{
					System.out.println("yes");
				}
				else
				{
					System.out.println("no");
				}
			}
		}
	}
	
	public static void union(int x1,int x2)
	{
		for(int i = 0; i < ints.length;i++)
		{
			if(ints[i]==ints[x1])
			{
				ints[i]=ints[x2];
			}
		}
	}
	public static boolean find(int x1, int x2)
	{
		if(ints[x1]==ints[x2])
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
