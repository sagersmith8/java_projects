package sage.smith.Blackjack;

/**
 * Created by Sage on 5/4/2014.
 */
public class Card{
    private String name;
    private String suit;
    private int value;

    public Card(String name, String suit, int value){
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public String getCard(){
        return name + " of " + suit + "'s";
    }

    public String getName(){
        return name;
    }

    public String getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }
}
