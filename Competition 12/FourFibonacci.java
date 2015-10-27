/*		FourFibonacci
		The Baus
		Joe		*/

public class FourFibonacci
{
	public static void main(String[] args)
	{
		int f1 = 1, f2 = 0, f3 = 1, f4 = 0, output;



		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);

		output = f1+f2+f3+f4;

		while(output<1000000)
		{
			output = f1+f2+f3+f4;

			if(output<1000000)
			{
				System.out.println(output);

				f1 = f2;
				f2 = f3;
				f3 = f4;
				f4 = output;
			}
		}
	}
}