package sage.smith;

import java.util.ArrayList;

/**
 * Created by Sage on 4/18/2014.
 */
public class CountCharacters extends Thread{
    private ArrayList<String> chars;
    long [] characterTotal;
    int cases;

    public CountCharacters(ArrayList<String> chars, long[] characterTotal, int cases){
        this.chars = chars;
        this.characterTotal = characterTotal;
        this.cases = cases;
    }

    public void run(){
        if(cases == 0) {
            for (int j = 0; j < chars.size() / 2; j++) {
                String word = chars.get(j).toUpperCase();
                int length = word.length();
                for (int i = 0; i < length; i++) {
                   characterTotal[(int) word.charAt(i)]++;
                }
            }
        }

        else if(cases == 1) {
            for (int j = chars.size() / 2; j < chars.size(); j++) {
                String word = chars.get(j).toUpperCase();
                int length = word.length();
                for (int i = 0; i < length; i++) {
                    characterTotal[(int) word.charAt(i)]++;
                }
            }
        }
    }
}
