import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ConsoleArithmetic {
	
	Scanner readLine;
	StringTokenizer readWords;
	
	public ConsoleArithmetic(){
		readLine = new Scanner(System.in);
		while(readLine.hasNextLine()){
			readWords = new StringTokenizer(readLine.nextLine(),"+");
			while(readWords.hasMoreTokens()){
				System.out.print(readWords.nextToken());
			}
		}
	}
	
	public ConsoleArithmetic(File file){
		
		try {
			readLine = new Scanner(file);
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(readLine.hasNext()){
			readWords = new StringTokenizer(readLine.nextLine(),"+",true);
			while(readWords.hasMoreTokens()){
				System.out.println(readWords.nextToken());
			}
		}
	}
	
	public static void main(String[] args) {
		new ConsoleArithmetic();
	}
}
