import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Example1 
{
	Scanner scan;
	int end;
	Example1(File file)
	{
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = scan.nextInt();
		
		for(int i = 0; i<end; i++)
		{
			System.out.print(scan.nextInt());
			System.out.print(characterPrint(scan.nextInt(),scan.next()));
			System.out.println();
		}	
	}
	StringBuffer characterPrint(int repetition,String word)
	{
		StringBuffer words = new StringBuffer();
		for(int i = 0; i < word.length(); i++)
		{
			for(int j = 0; j< repetition; j++)
			{
				words.append(word.charAt(i));
			}
		}
		return words;    
	}
	public static void main(String[] args) 
	{
		Scanner scaner = new Scanner(System.in);
		new Example1(new File(scaner.next()));
	}
}
