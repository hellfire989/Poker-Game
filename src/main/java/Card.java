public class Card {
    private String value;
    private String suit;

    public String getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public String getCard(){
        return this.value + this.suit;
    }

    public Card(String value,  String suit){
        this.value = value;
        this.suit = suit;
    }

    public String toString(){
        return this.value + this.suit;
    }
}
