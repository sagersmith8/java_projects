// Zombies
//The incredible Mr. E
// Dylion, Tyzer

import java.util.Scanner;

public class Zombies {
	Scanner scan = new Scanner(System.in);
	int ones = 0, theNumber, secondInput, testCases;
	public Zombies()
	{
		testCases = scan.nextInt();

		for(int j = 0; j<testCases; j++)
		{
			ones = 0;
			theNumber = scan.nextInt();
			secondInput = scan.nextInt();
			
			Binary(theNumber);
			if(ones%2 == 0 && secondInput == 0 ||
					ones%2 == 1 && secondInput == 1)
			{
				System.out.println("valid");
			}
			else
			{
				System.out.println("Corrupt");
			}
		}
		
	}
	
	public void Binary(int theNumber)
	{
		for(int i = 15; i>=0; i--)
		{
			if(Math.pow(2, i) <=theNumber)
			{
				theNumber -= Math.pow(2,i);
				ones++;
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Zombies xyz = new Zombies();
	}
}
