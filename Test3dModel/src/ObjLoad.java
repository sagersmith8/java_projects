

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ObjLoad
{
	ArrayList<Float> al = new ArrayList<Float>();
	public ObjLoad(String dir)
	{
		try
		{
			Scanner scan = new Scanner(new File(dir));			  
			while(scan.hasNext())   
			{
				if(scan.next().equals("f"))
				{
					for(int i = 0; i <3; i++)
					{
						String scanned = scan.next();
						scanned.replace('/', ' ');
						System.out.println(scanned);
						Scanner scan2 = new Scanner(scanned);
						while(scan2.hasNext())
						{
							al.add(scan.nextFloat());
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	public ArrayList<Float> x()
	{
		ArrayList<Float> x = new ArrayList<Float>();
		for(int i = 0; i < al.size(); i+=3)
		{
			x.add(al.get(i));
		}
		return x;
	}
	public ArrayList<Float> y()
	{
		ArrayList<Float> y = new ArrayList<Float>();
		for(int i = 1; i < al.size(); i+=3)
		{
			y.add(al.get(i));
		}
		return y;
	}
	public ArrayList<Float> z()
	{
		ArrayList<Float> z = new ArrayList<Float>();
		for(int i = 2; i < al.size(); i+=3)
		{
			z.add(al.get(i));
		}
		return z;
	}
}