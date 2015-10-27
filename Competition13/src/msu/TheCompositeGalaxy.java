package msu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TheCompositeGalaxy {
	Scanner scan;
	PrintWriter pw;
	boolean d = true;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TheCompositeGalaxy() {
		try {
			pw = new PrintWriter(new File("h.out"));
		} catch (FileNotFoundException ex) {
			System.err.println("Could not find h.out");
		}
		try {
			scan = new Scanner(new File("h.in"));
		} catch (FileNotFoundException ex) {
			System.err.println("Could not find h.in");
		}
		
		while(d) {
			int low = scan.nextInt();
			int high = scan.nextInt();
			if(low == 0 && high == 0) {
				d=false;
			} else {
				ArrayList longest = new ArrayList();
				ArrayList current = new ArrayList();
				
				for(int i=high; i>=low; i--) {
					if(isComposite(i)) {
						current.add(i);
					} else {
						current = new ArrayList();
					}
					if(current.size() > longest.size()) {
						longest = current;
					}
				}
				pw.println(longest.get(longest.size()-1) + " " + longest.get(0));
			}
		}
		pw.close();
	}
	
	public boolean isComposite(int num) {
		boolean yes = false;
		
		for(int i=2; i<=(int)Math.sqrt(num); i++) {
			if(num%i == 0) {
				i=num;
				yes = true;
			}
		}
		return yes;
	}
	
	public static void main(String[] args) {
		new TheCompositeGalaxy();
	}
}