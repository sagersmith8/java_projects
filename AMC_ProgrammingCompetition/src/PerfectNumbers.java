import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class PerfectNumbers {

	Scanner scan;
	PrintWriter pw;
	int end;
	public PerfectNumbers()
	{
		try {
			scan = new Scanner(new File("e.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Could not read e.in");
		}
		try {
			pw = new PrintWriter(new File("e.out"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Could not find e.out");
		}
		
		while(scan.hasNext()){
			long i = scan.nextLong();
			long k = 3;
			if(i == 0){}
			else if(i<6)
			{
				pw.println(i+ " IS NOT perfect.");
			}	
			else{
				for(long j=2; j<i/2; j++){
					if(i%j==0)
					{
						k += i/j;
					}
				}
				if(k == i)
				{
					pw.println(i+" IS perfect.");
//					System.out.println(i+" IS perfect");
				}else{
					pw.println(i+" IS NOT perfect.");
//					System.out.println(i+" IS NOT perfect");
				}
			}
		}
		pw.close();
		scan.close();
	}
		
	public static void main(String[] args) {
		new PerfectNumbers();
	}
}
