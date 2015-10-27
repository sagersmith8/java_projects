package practicePrograms;

import java.util.ArrayList;

/**
 * 
 * @author Nick
 * Project Euler 
 * Problem 26
 *
 */

public class RecipricolCycles 
{
	public static void main(String[] args)
	{
		int denom,longest = 7, longestL = 6;
		
		for(denom = 7; denom<1000; denom++)
		{
			ArrayList<Integer> seq = new ArrayList<Integer>();
			String dec = String.valueOf(1/denom);
			boolean d = false;
			for(int i=0; i<dec.length(); i++)
			{
				if(d)
				{
					int j;
					boolean f=true;
					if(dec.charAt(i) == seq.get(0))
					{
						for(j=1; j<seq.size() && f; j++)
						{
							if(seq.get(j) == dec.charAt(i+j));
							else
								f=false;
						}
						if(f && j>longestL)
						{
							longest = denom;
							longestL = j;
						}
					}
					seq.add((int) dec.charAt(i));
				}
				
				if(dec.charAt(i) == '.')
					d=true;
			}
		}
		System.out.println("Final: " + longest);
	}
}
