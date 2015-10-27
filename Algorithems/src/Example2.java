import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Example2 {

	Scanner scan;
	PrintWriter outputFile = new PrintWriter(new File("Example1.out"))
	int end;
	Example2(File file){
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = scan.nextInt();
		for(int i = 0; i < end; i++)
		{
			outputFile.print(scan.nextInt() + " ");
			outputFile.print(calculate(scan.nextInt(),scan.nextInt()));
			outputFile.println();
		}
	}
	int calculate(int r, int c)
	{
		return 1+((r-c)*r);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		new Example2(new File(scanner.next()));

	}
}
