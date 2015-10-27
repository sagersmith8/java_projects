//PythogoreanTriples
//Increadible mr. e
//Dylan

import java.util.Arrays;
import java.util.Scanner;


public class PythogoreanTriples {

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		PythogoreanTriples f1 = new PythogoreanTriples();
	}
	Scanner scan = new Scanner(System.in);
	int testCases = scan.nextInt();
	int[] ints;
	int length;
	boolean found;
	public PythogoreanTriples()
	{
		for(int q = 0; q<testCases; q++)
		{
			found = false;
			length = scan.nextInt();
			ints = new int[length];
	
			for(int a = 0; a<length; a++)
			{
				ints[a] = scan.nextInt();
			}
			Arrays.sort(ints);
			for(int i = 0; i<length-2; i++)
			{
				for(int j = i+1; j<length-1; j++)
				{
					for(int k = i+2; k<length; k++)
					{
						int first = ints[i] * ints[i];
						int second = ints[j] * ints[j];
						int third = ints[k] * ints[k];
						if(first+second == third)
						{
							System.out.println("Found Pythaogorean triple: {"+ints[i]+" "+ ints[j]+" "+ints[k]+"}");
							found = true;
						}
					}
				}
			}
			if(!found)
			{
				System.out.println("No Pythogorean triples found in the sequence.");
			}
		}
	}
}
