import java.util.*;

public class Parallel
{
	public static void main(String[] args)
	{
		int height, width;

		Scanner in = new Scanner(System.in);

		height = in.nextInt();
		width = in.nextInt();

		// rows
		for (int i=0; i<height; i++)
		{
			String out = "";

			// spaces
			for (int o=0; o<i; o++)
			{
				out = (out + " ");
			}
			// asteriks
			for (int p=0; p<width; p++)
			{
				out = (out + "*");
			}
			System.out.println(out);
		}
	}
}