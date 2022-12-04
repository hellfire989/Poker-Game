import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    String[] values = {"A","10","J","Q","K"};
    String[] suits = {"♥", "♦", "♣", "♠"};

    public ArrayList<Card> getDeck(){
        return cards;
    }

    public Deck(){
        for(String suit : suits){
            for(String value : values){
                this.cards.add(new Card(value,suit));
            }
        }
    }
    public Card removeTopCard(){
        if(cards.size() > 0)
            return cards.remove(0);
        else
            return null;
    }
}
