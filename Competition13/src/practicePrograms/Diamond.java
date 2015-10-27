package practicePrograms;

import java.util.Scanner;

public class Diamond {
	
	static Scanner input = new Scanner(System.in);
	static int in = input.nextInt();
	static int spacer = in-2;
	static int spaces = 1;
	
	public static void main(String[] args) {
		if(in == 1) {
			System.out.println("*");
		}else if(in == 2){
			System.out.println(" *\n* *\n *");
		}else {
			topBottom();
			for(int i=0; i<in-1; i++) {
				for(int j=0; j<spacer; j++) {
					System.out.print(" ");
				}
				System.out.print("*");
				for(int j=0; j<spaces; j++) {
					System.out.print(" ");
				}
				System.out.println("*");
				spacer--;
				spaces+=2;
			}
//			middle();
			spacer+=2;
			spaces-=4;
			for(int i=0; i<in-2; i++) {
				for(int j=0; j<spacer; j++) {
					System.out.print(" ");
				}
				System.out.print("*");
				for(int j=0; j<spaces; j++) {
					System.out.print(" ");
				}
				System.out.println("*");
				spacer++;
				spaces-=2;
			}
			topBottom();
		}
	}
	
	public static void topBottom() {
		for(int i=0; i<in-1; i++) {
			System.out.print(" ");
		}
		System.out.println("*");
	}
	
	public static void middle() {
		System.out.print("*");
		for(int i=0; i<in+2; i++) {
			System.out.print(" ");
		}
		System.out.println("*");
	}
}
