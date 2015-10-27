import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class ACM {

	Scanner scan;
	PrintWriter pw;
	String [] insignificant;
	String word, newWord;
	int end;
	boolean run = true, rrun = true;
	public ACM()
	{
		try {
			scan = new Scanner(new File("C:/Users/Sage/workspace/AMC_ProgrammingCompetition/c.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pw = new PrintWriter(new File("C:/Users/Sage/workspace/AMC_ProgrammingCompetition/c.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		end = Integer.parseInt(scan.nextLine());
		insignificant = new String[end];
		for(int i = 0; i < end; i++)
		{
			insignificant[i] = scan.nextLine();
		}
		while(scan.hasNext())
		{
			String s =scan.nextLine();
			if(s.equals("LAST CASE"))
			{
				end = Integer.parseInt(scan.nextLine());
				insignificant = new String[end];
				for(int i = 0; i < end; i++)
				{
					insignificant[i] = scan.nextLine();
				}
			}
			else
			{
				run(s);
			}
		}
		scan.close();
		pw.close();
	}
	void run(String t)
	{		
		ArrayList al = new ArrayList();
		ArrayList<String> al2 = new ArrayList<String>();
		int size = 0;
		Scanner scanner = new Scanner(t);
		word = scanner.next().toLowerCase();
		while(scanner.hasNext())
		{
			String b= scanner.next();
			al2.add(b);
			System.out.println(b);
		}
		while(run == true)
		{
			int counter = 0;
			for(int j = 0; j < al2.size(); j++)
			{
				int c = 0;
				for(int i = 0; i < insignificant.length; i++)
				{
					if(al2.get(j).equals(insignificant[i]))
					{
						c = 1;
					}
				}
				if(c == 1)
				{
				}
				else
				{
					size = al.size();
					for(int i = 0; i < al2.get(j).length(); i++)
					{
						if(word.charAt(counter) == al2.get(j).charAt(i))
						{
							if(al.contains(al2.get(j)+ String.valueOf(i)))
							{
							}
							else
							{
								al.add(al2.get(j)+ String.valueOf(i));
							}

							i = al2.get(j).length();
						}
					}
					if(size == al.size())
					{
						run = false;
					}
					counter++;
				}
			}
		}
		if(al.size() <= 1)
		{
			System.out.println(word.toUpperCase() + " is not a valid abbreviation");
		}
		else
		{
			System.out.println(word.toUpperCase() + " can be formed in " + (al.size()-1) + " ways");
		}
	}
	public static void main(String[] args) {
		new ACM();
	}

}
