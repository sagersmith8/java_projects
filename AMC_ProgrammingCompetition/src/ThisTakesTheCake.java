import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ThisTakesTheCake {
	Scanner scan;
	PrintWriter pw;
	double side1, side2, side3, side4;
	Point point[] = new Point[8];
	
	public ThisTakesTheCake(){
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
		
		while(scan.hasNext()){
			for(int i=0;i<4; i++){
			point[0] = new Point(scan.nextInt(), scan.nextInt());
			}
			for(int i = 4; i < 8; i++)
			{
				
			}
		}
	}
	
	public static void main(String[] args) {
		new ThisTakesTheCake();
	}

}
