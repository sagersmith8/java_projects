package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class WordFrequency {
	Scanner scan;
	StringTokenizer stringTokenizer;
	long startTime, endTime;
	int size;

	public WordFrequency(TreeMap<String,Integer> col, ArrayList<String> words){
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
			stringTokenizer = new StringTokenizer(scan.nextLine().toUpperCase(),"' _&,.!?-;:\")*03`([]");
			while(stringTokenizer.hasMoreTokens()){
				String st = stringTokenizer.nextToken().trim();
				if(!words.contains(st)){
					col.put(st, 1);
					words.add(st);
				}
				else{
					int num = col.get(st)+1;
					col.put(st, num);
				}
			}
		}

		endTime = System.currentTimeMillis();
		sort(words, col);
		for(int i = 0; i < col.size(); i++){
			System.out.println(words.get(i)+ " " +col.get(words.get(i)));
		}
	}

	public void sort(ArrayList<String> words,TreeMap<String,Integer> vals){
		size = words.size()-1;
		heapify(words, vals);
		
		for(int i = size; i >= 0; i--){
			swap(words,0, i);
			size=size-1;
			maxheap(words, vals, 0);
		}
	}
	
	public void heapify(ArrayList<String> words,TreeMap<String,Integer> vals){
		for(int i = size/2; i >= 0; i--){
			maxheap(words,vals ,i);
		}
	}
	
	public void maxheap(ArrayList<String> words,TreeMap<String,Integer> vals, int i){
		int left = 2*i;
		int right=2*i+1;
		int largest;
		
		if(left<=size&&vals.get(words.get(left))<vals.get(words.get(i))){
			largest = left;
		}
		
		else{
			largest = i;
		}
		
		if(right <= size &&vals.get(words.get(right))<vals.get(words.get(largest))){
			largest = right;
		}
		if(largest!=i){
			swap(words, i, largest);
			maxheap(words, vals, largest);
		}
	}
	
	public void swap(ArrayList<String> words, int i, int j){
		String a = words.get(i);
		words.set(i, words.get(j));
		words.set(j, a);
	}


	public static void main(String[] args) {
		new WordFrequency(new TreeMap<String,Integer>(), new ArrayList<String>());
	}

}
