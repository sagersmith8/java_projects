package sage.smith.Blackjack;

/**
 * Created by Sage on 5/4/2014.
 */
public class Game {
    Player player = new Player();
    Player dealer = new Player();
    Deck deck = new Deck();
    int index = 0;

    public Game(int type) {
        dealCard(player);
        dealCard(dealer);
        dealCard(player);
        dealCard(dealer);
       /* while(playerAction

        }
        /*}

        else{
            while(dealer.getTotal()<=17){
                dealCard(dealer);
            }
        }*/
    }

    public void dealCard(Player player) {
        player.addCard(deck.remove(0));
    }
}
