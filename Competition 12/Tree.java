import java.util.*;

public class Tree
{
	public static void main(String[] args)
	{
		int[] x1 = new int[100];
		int[] y2 = new int[100];
		int number, out = 0;
		boolean flag = false;

		Scanner in = new Scanner(System.in);

		// takes total number of inputs
		number = in.nextInt();

		// imports pairs into arrays
		for (int i=0; i<number; i++)
		{
			x1[i] = in.nextInt();
			y2[i] = in.nextInt();
		}

		// check if it is a root
		for(int x=0; x<x1.length; x++)
		{
			for (int y=0; y<y2.length; y++)
			{
				if (x == y || x > number || y > number)
				{
					// makes sure it is not checking aginst itself or a blank spot
				}
				else if (y2[x] == y2[y])
				{
					// checks to see if there are duplicates
					// outputs the conflicting pairs
//					System.out.println(x + "," + y);
					flag = true;
				}
			}
		}
		if (flag == true)
		{
			System.out.println("Not a root");
		}

		// check for root
		if (flag == false)
		{
			for (int x=0; x<x1.length; x++)
			{
				boolean root = true;

				for (int y=0; y<y2.length; y++)
				{
					if (x1[x] == y2[y])
					{
						// checks to see if the number in row 1 is in row 2
						root = false;
					}
				}


				if (root == true)
				{
					out = x1[x];
				}
			}

			System.out.println(out + " is the root");
		}

/*		// check to see if the program gets correct inputs
		for (int g=0; g<x1.length; g++)
		{
			System.out.print(x1[g] + " ");
			System.out.println(y2[g]);
		}
*/	}
}