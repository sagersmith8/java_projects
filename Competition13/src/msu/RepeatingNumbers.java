package msu;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RepeatingNumbers {
	public static void main(String[] args) {
		DataInputStream input = null;
		int s = 0;
		int[] r = null;
		int[] t;
		String[] words = null;

		try {
			input = new DataInputStream(new FileInputStream("bob.txt"));

		} catch(Exception ex) {
			System.err.println("POOP" + ex.getMessage());
			}

		try {
			s = Integer.parseInt(input.readUTF());
			System.out.println(s);
			r= new int[s];
			t= new int[s];
			words = new String[s];

			for(int i=0; i<s; i++) {
				t[i] = Integer.parseInt(input.readUTF());
				r[i] = Integer.parseInt(input.readUTF());
					words[s] = input.readUTF();
			}
		} catch (IOException ex) {
			System.err.println("FUCK " + ex.getMessage());
		}

			for(int i=0; i<s; i++) { // line
				for(int h=0; h<words[i].length(); h++) {
					for(int j=0; j<r[s]; j++) { // repeat
						System.out.print(words[i].charAt(j));
					}
				}
				System.out.println();
			}
	}
}
