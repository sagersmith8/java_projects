package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class WordCount {
	Scanner scan;
	StringTokenizer stringTokenizer;
	long startTime, endTime;
	
	public WordCount(Collection<String> col){
		startTime = System.currentTimeMillis();
		try {
			/*
			 * I don't Know how you grade these, but the Alice.txt needs to
			 * within project or you will throw an error
			*/
			scan = new Scanner(new File("Alice.txt"));
		} 

		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		while(scan.hasNextLine()){
			stringTokenizer = new StringTokenizer(scan.nextLine().toUpperCase()," _&,.!?-;:\")*03`([]");
			while(stringTokenizer.hasMoreTokens()){
				String st = stringTokenizer.nextToken().trim();
				if(!col.contains(st)){
					col.add(st);
				}
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println(col.getClass()+":  WordCount:" +col.size()+"  Time:" +(endTime-startTime));
	}

	public static void main(String[] args) {
		new WordCount(new ArrayList<String>());
		new WordCount(new LinkedList<String>());
		new WordCount(new HashSet<String>());
		new WordCount(new TreeSet<String>());
	}

}
