package practicePrograms;

import java.util.ArrayList;

public class Multiples 
{
	public static void main(String[] args)
	{
		ArrayList<Integer> m5 = new ArrayList<Integer>();
		int sum=0;
		
		for(int m=0; m<1000; m+=5)
		{
			sum+=m;
			m5.add(m);
		}
		
		for(int m=0; m<1000; m+=3)
		{
			if(!m5.contains(m))
			{
				sum+=m;
			}
		}
		System.out.println("Final: " + sum);
	}
}
