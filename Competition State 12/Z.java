//distracting z
// the incredible mr. e
//nick sage

import java.util.*;

public class Z 
{
	static Scanner scan = new Scanner(System.in);	
	static int in = scan.nextInt();
	static int spacer = in-2;
	
	public Z()
	{
		bottomTop();
		for (int y=0; y<in-2; y++)
		{
			for (int x=0; x<spacer; x++)
			{
				System.out.print(" ");
			}
			System.out.println("*");
			spacer--;
		}
		bottomTop();
	}
	
	public static void bottomTop()
	{
		for (int i=0; i<in; i++)
		{
			System.out.print("*");
		}
		System.out.println();
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Z xyz = new Z();
	}
}
