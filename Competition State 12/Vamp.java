import java.util.*;

public class Vamp 
{
	static Scanner scan = new Scanner(System.in);
	static int numberToKill = 0;
	static int forloop = scan.nextInt();
	static int counter = 0;
	static Double[] array = new Double [forloop];
	public static void main(String[] args)
	{
		for(int i=0; i < forloop; i++)
		{
			array[i] = (scan.nextDouble())/(scan.nextDouble());
		}
		kill();
	}
	public static void kill()
	{
		for(int j = 0;j < forloop-1; j++)
		{
			for(int k = 1;k < forloop; k++)
			{
				if(array[j] == array[k])
				{
					
					counter++;
				}
				if(counter > numberToKill)
				{
					numberToKill = counter;	
				}
			}
			
		}
		numberToKill = counter/2;
		System.out.println("It took " + numberToKill + " blasts");
	}
}
