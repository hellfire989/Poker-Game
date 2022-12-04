public class Player {
    int playerNo;
    int handRanking;
    String handRankString;
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

    public String getHandRankString(){
        switch(handRanking){
            case 1:
                handRankString = "Royal Flush";
                break;
            case 2:
                handRankString = "Straight Flush";
                break;
            case 3:
                handRankString = "Four of a Kind";
                break;
            case 4:
                handRankString = "Full House";
                break;
            case 5:
                handRankString = "Flush";
                break;
            case 6:
                handRankString = "Straight";
                break;
            case 7:
                handRankString = "Three of a kind";
                break;
            case 8:
                handRankString = "Two Pair";
                break;
            case 9:
                handRankString = "One pair";
                break;
            default:
                handRankString = "High Card";
                break;
        }
        return handRankString;
    }
}
