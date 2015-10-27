package sage.smith.Blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sage on 5/4/2014.
 */
public class Deck extends ArrayList<Card>{
    public Deck(){

        for(int i = 1; i <= 10; i++){
            if(i ==1){
                this.add(new Card("Ace","Club",i));
                this.add(new Card("Ace","Jack",i));
                this.add(new Card("Ace","Heart",i));
                this.add(new Card("Ace","Spade",i));
            }

            else {
                this.add(new Card("" + i, "Club", i));
                this.add(new Card("" + i, "Jack", i));
                this.add(new Card("" + i, "Heart", i));
                this.add(new Card("" + i, "Spade", i));

                if(i == 10){
                    this.add(new Card("Jack", "Club", i));
                    this.add(new Card("Jack", "Jack", i));
                    this.add(new Card("Jack", "Heart", i));
                    this.add(new Card("Jack", "Spade", i));
                    this.add(new Card("Queen", "Club", i));
                    this.add(new Card("Queen", "Jack", i));
                    this.add(new Card("Queen", "Heart", i));
                    this.add(new Card("Queen", "Spade", i));
                    this.add(new Card("King", "Club", i));
                    this.add(new Card("King", "Jack", i));
                    this.add(new Card("King", "Heart", i));
                    this.add(new Card("King", "Spade", i));
                }
            }
        }

        Collections.shuffle(this);
    }
}
