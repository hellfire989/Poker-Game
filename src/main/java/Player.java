public class Player {
    int playerNo;
    int handRanking;
    Hand hand;

    public int getPlayerNo() {
        return playerNo;
    }
    public int getHandRanking() {
        return handRanking;
    }
    public Hand getHand() {
        return hand;
    }
    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }
    public void setHandRanking(int handRanking) {
        this.handRanking = handRanking;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Player(int playerNo, int handRanking, Hand hand) {
        this.playerNo = playerNo;
        this.handRanking = handRanking;
        this.hand = hand;
    }
}
