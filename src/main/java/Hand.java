/*
    Hand Rankings:
        1 - Royal Flush (A♦ K♦ Q♦ J♦ T♦)
        2 - Straight Flush (T♥ 9♥ 8♥ 7♥ 6♥)
        3 - Four-of-a-Kind (J♦ J♣ J♠ J♥ K♦)
        4 - Full House (A♥ A♣ A♦ 9♠ 9♣)
        5 - Flush (A♠ J♠ 8♠ 4♠ 3♠)
        6 - Straight (9♥ 8♠ 7♣ 6♦ 5♣)
        7 - Three-of-a-Kind (7♠ 7♦ 7♣ K♦ Q♣)
        8 - Two-Pair (9♣ 9♦ 6♣ 6♠ Q♥)
        9 - One-Pair (A♦ A♥ K♠ 9♦ 4♥)
        10 - High Card (A♠ J♦ 8♣ 6♠ 2♥)
 */
public class Hand {
    private Card firstCard;
    private Card secondCard;
    int handRanking;

    public Hand(){
        firstCard = null;
        secondCard = null;
    }
    public Hand(Card firstCard,  Card secondCard){
        this.firstCard = firstCard;
        this.secondCard = secondCard;
        handRanking = 10;
    }

    public Card getFirstCard() {
        return firstCard;
    }
    public Card getSecondCard() {
        return secondCard;
    }
    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }
    public void setSecondCard(Card secondCard) {
        this.secondCard = secondCard;
    }

    public String toString(){
        return this.firstCard + "," + this.secondCard;
    }
}
