package sage.smith;

/**
 * Created by Sage on 4/18/2014.
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

public class ReadChars2 {
    static HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();

    public ReadChars2(){
        long callTime = 0;// = System.currentTimeMillis();
        long count = 0;
        String allWords = "";
        CountCharacters[] countCharacters;
        long[] charTotals = new long[128];
        Runnable[] runnables;
        ArrayList<String> strings = new ArrayList<String>();
        BufferedReader bufferedReader;
        char replace = (char)65533;

        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Sage\\Downloads\\Java_II_Lab_8_Filies\\english-words.txt");
            allWords = IOUtils.toString(fileInputStream);
            callTime = System.currentTimeMillis();
            allWords = allWords.replaceAll("[^A-Za-z']", "").toUpperCase();
            char[] characters = allWords.toCharArray();
            bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\Sage\\Downloads\\Java_II_Lab_8_Filies\\english-words.txt")));
            String string;
            while ((string = bufferedReader.readLine()) != null){
                strings.add(string.replaceAll(Character.toString(replace),""));
            }

            countCharacters = new CountCharacters[2];

            for (int i = 0; i < countCharacters.length; i++) {
                countCharacters[i] = new CountCharacters(strings, charTotals, i);
                countCharacters[i].start();
            }


            System.out.println("Executing....");
            callTime = System.currentTimeMillis();
            for(int i = 0; i < countCharacters.length; i++){
                countCharacters[i].join();
            }



            for(int i = 65; i <=90; i++ ){
                System.out.println((char)i + " :" + charTotals[i]);
            }

            System.out.println(characters.length + " Total characters");
        }

        catch (Exception ex){
            ex.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Process took " + (endTime - callTime) + "ms" );
    }

    public static void main(String[] args) {
        new ReadChars2();
    }
}

