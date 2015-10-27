package sage.smith.Blackjack;

import java.util.ArrayList;

/**
 * Created by Sage on 5/4/2014.
 */
public class Player {
   private ArrayList<Card> cards = new ArrayList<Card>();
   private int total = 0;
   public ArrayList<Card> getCards(){
       return cards;
   }

   public void addCard(Card card){
       cards.add(card);
       total+=card.getValue();
   }

   public int getTotal(){
       return total;
   }
}
