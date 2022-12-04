import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    String[] values = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
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
