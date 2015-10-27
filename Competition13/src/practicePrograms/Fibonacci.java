package practicePrograms;

import java.util.ArrayList;

public class Fibonacci
{
	public static void main(String[] args)
	{
		ArrayList<Integer> seq = new ArrayList<Integer>();
		int sum=2, newNum = 3;
		int index = 1;
		
		seq.add(1);
		seq.add(2);
			
		while(newNum<4000000)
		{
			newNum = seq.get(index)+seq.get(index-1);
			seq.add(newNum);
			index++;
			if(newNum%2 == 0)
			{
				sum+=newNum;
			}
		}
		System.out.println("Final: " + sum);
	}
}
