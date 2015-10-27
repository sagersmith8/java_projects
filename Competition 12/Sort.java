import java.util.*;

public class Sort
{
	public static void main(String[] args)
	{
		ArrayList<Integer> input = new ArrayList<Integer>();
		ArrayList odd = new ArrayList();
		ArrayList even = new ArrayList();
		int g, in1;

		Scanner in = new Scanner(System.in);

		while (in1 != -1)
		{
			in.nextInt() = in1;
			input.add(in1);
		}

		// sort odd and even
		for (g=0; g<input.size()-1; g++);
		{
			int num = input.get(g);

			if ((num % 2) == 0)
			{
				even.add(input.get(g));
			}
			else if (num % 2 == 1)
			{
				odd.add(input.get(g));
			}
		}

		// print out arrays
		for (int h=0; h<odd.size(); h++)
		{
			System.out.print(odd.get(h) + " ");
		}
		for (int d=0; d<even.size(); d++)
		{
			System.out.print(even.get(d) + " ");
		}
		System.out.println("");
		System.out.println(even.size() + " , " + odd.size());
		for (int z=0; z<input.size(); z++)
		{
			System.out.print(input.get(z) + " ");
		}
	}
}