package practicePrograms;

import java.util.ArrayList;

public class PrimeFactors
{
	public static void main(String[] args)
	{
		int num ;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> factors = new ArrayList<Integer>();
		
		num = 600851475143/1000;
		
		// find primes
		
		// search for prime factors
		while(!primes.contains(num))
		{
			for(int i=0; i<primes.size(); i++)
			{
				if(num%primes.get(i) == 0)
				{
					num = num/primes.get(i);
					factors.add(primes.get(i));
				}
			}
		}
		factors.add(num);
		
		//find greatest and output it
		int out = 0;
		for(int i=0; i<factors.size(); i++)
		{
			if(factors.get(i) > out)
			{
				out = factors.get(i);
			}			
		}
		System.out.println("Final: " + out);
	}
}
