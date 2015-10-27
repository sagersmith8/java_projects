import java.util.*;

public class RainbowSort
{
	public static void main(String[] args)
	{
		int[] input = new int[100];
		int[] odd = new int[100];
		int[] even = new int[100];
		int count = 0, g, oddC = 0, evenC = 0, preO = 0, preE = 0;

		Scanner in = new Scanner(System.in);

		while (in.nextInt() != -1)
		{
			input[count] = in.nextInt();
			count++;
		}

		// sort odd and even
		for (g=0; g<count; g++);
		{
			if (input[g] % 2 == 0)
			{
				even[evenC] = input[g];
				evenC++;
			}
			else if (input[g] % 2 == 1)
			{
				odd[oddC] = input[g];
				oddC++;
			}
		}

/*		// print out odd and even
		for(int y=0; y<odd.length; y++)
		{
			int lowest = 100000;

			for(int h=0; h<odd.length; h++)
			{
				if (odd[h] < lowest && odd[h] > preO)
				{
					lowest = odd[h];
					preO = odd[h];
					odd[h] = 100000;
				}
			}
			System.out.print(lowest);
		}
*/		for (int x=0; odd[x]!=0; x++)
		{
			System.out.println(odd[x]);
		}
	}
}