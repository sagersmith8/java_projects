package com.example.batman;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjLoad extends ArrayList<Float>
{
	public ObjLoad(String dir)
	{
		try
		{
			Scanner scan = new Scanner(new File(dir));			  
			while(scan.hasNext())   
			{
				if(scan.next().equals("v"))
				{
					for(int i = 0; i <3; i++)
					{
						this.add(scan.nextFloat());
					}
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}