/*		Charecter Art Gem
		The Baus
		Nick, Jeremy,Roster,joe		*/

import java.util.*;

public class ArtGem
{
	public static void main(String[] args)
	{
		int height, width, input, m = 0;

		Scanner in = new Scanner(System.in);

		input = in.nextInt();

		height = (input * 2 - 1);
		width = (input * 3 - 2);

		//first line
		for(int y=0; y<(input - 1); y++)
		{
			System.out.print(".");
		}
		for(int x=0; x<input; x++)
		{
			System.out.print("*");
		}
		System.out.println("");

		// rows
		for (int i=input-2; i>0; i--)
		{
			String out = "";

			// spaces
			for (int o=0; o<i; o++)
			{
				out = (out + ".");
			}
			// asteriks
			out = out + "*";
			//middle
			for (int p=0; p<input + m; p++)
			{
				out = (out + ".");
			}
			out = out + "*";
			System.out.println(out);
			m=m+2;
		}
		// middle line
		System.out.print("*");
		for (int r=0; r<width -2; r++)
		{
			System.out.print(".");
		}
		System.out.print("*");
		System.out.println("");
		m = m-2;

		// bottom rows
		for (int z=1; z<=input-2; z++)
		{
			String out = "";
			// spaces
			for (int as=z; as>0; as--)
			{
				out = (out + ".");
			}
			// asteriks
			out = out + "*";
			//middle
			for (int fg=0; fg<input + m; fg++)
			{
				out = (out + ".");
			}
			out = out + "*";
			System.out.println(out);
			m=m-2;
		}

		//last line
		for(int y=0; y<(input - 1); y++)
		{
			System.out.print(".");
		}
		for(int x=0; x<input; x++)
		{
			System.out.print("*");
		}
		System.out.println("");
	}
}