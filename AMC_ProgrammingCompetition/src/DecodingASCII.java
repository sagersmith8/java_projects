import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class DecodingASCII {

	Scanner scan;
	PrintWriter pw;
	int end;
	public DecodingASCII()
	{
		try {
			scan = new Scanner(new File("a.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pw = new PrintWriter(new File("a.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		end = Integer.parseInt(scan.nextLine());
		for(int i = 0; i < end; i++)
		{
			pw.println(decrypt(scan.nextLine(),scan.nextLine()).toString());
		}
		scan.close();
		pw.close();
	}
	
	StringBuffer decrypt(String first, String second)
	{
		StringBuffer st = new StringBuffer();
		for(int i = 0; i < first.length(); i++)
		{
			if((int)(first.charAt(i)) == 32)
			{
				st.append('~');
			}
			else
			{
				st.append((char)((int)(first.charAt(i) - 1)));
			}
			try{
			if((int)second.charAt(i) == 126)
			{
				st.append(" ");
			}
			else
			{
				st.append((char)((int)(second.charAt(i) + 1)));
			}
			}
			catch(Exception ex){}
		}
		return st;
	}
	public static void main(String[] args) {
		new DecodingASCII();
	}

}
