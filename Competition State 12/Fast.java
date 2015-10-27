//CrossWord puzzle solver
//Incredible Mr. E
//Tyzer, Dylan, Nick, Sage
import java.util.*;

public class Fast
{
	int counter = 0, counter2 = 0;
	Scanner scan = new Scanner(System.in);
	int counter3 = 0;
	int numWords = scan.nextInt();
	int[] row = new int[numWords];
	int[] col= new int[numWords];
	int[] wordLength= new int[numWords];
	int[] direction= new int[numWords];
	String[] stringArray = new String[numWords];
	char[][] charArray = new char[50][50];
	
	public Fast()
	{
		for(int q = 0; q<charArray.length; q++)
		{
			for(int r = 0; r<charArray[0].length; r++)
			{
				charArray[q][r] = '*';
			}
		}
		stars();
		kool();
		getWords();

		for(int q = 0; q<charArray.length; q++)
		{
			for(int r = 0; r<charArray[0].length; r++)
			{
				System.out.print(charArray[q][r]);
			}
			System.out.println();
		}
	}
	public void getWords()
	{
		for(int a = 0; a<numWords; a++)
		{
			String bob = stringArray[a];
			String joe = stringArray[a];
			if(direction[a] == 0)
			{
				counter = 0;
				for(int i = col[a]; i<col[a]+wordLength[a]; i++)
				{
					charArray[row[a]][i] = bob.charAt(counter);
					counter++;				
				}
			}
			if(direction[a] == 1)
			{
				counter2 = 0;
				for(int i = row[a]; i<row[a]+wordLength[a]; i++)
				{
					charArray[i][col[a]] = joe.charAt(counter2);
					counter2++;
				}
			}
		}
		
	}
	public void kool()
	{
		for(int a = 0; a<numWords; a++)
		{
			stringArray[a] = scan.next();
		}
		for(int a = 0; a<numWords; a++)
		{
			for(int c = 0; c<numWords; c++)
			{
				if(stringArray[a].length()== wordLength[c])
				{
					if(stringArray[a].charAt(counter3)== stringArray[c].charAt(counter3))
					{
						System.out.println("Stain dillion i spelled your name incorrectly" +
								";LKZXchvj;zljkxchfg");
					}
				}
				counter3++;
			}
		}		
	}
	public void stars()
	{
		for(int a  = 0; a<numWords; a++)
		{
			row[a] = scan.nextInt();
			col[a] = scan.nextInt();
			wordLength[a] = scan.nextInt();
			direction[a] = scan.nextInt();
		}
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Fast f1 = new Fast();
	}
	
}
