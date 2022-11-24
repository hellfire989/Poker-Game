public class Board {
    private Card flopCard1;
    private Card flopCard2;
    private Card flopCard3;
    private Card turn;
    private Card river;

    public Card getFlopCard1() {
        return flopCard1;
    }
    public Card getFlopCard2() {
        return flopCard2;
    }
    public Card getFlopCard3() {
        return flopCard3;
    }
    public Card getTurn() {
        return turn;
    }
    public Card getRiver() {
        return river;
    }

    public void setFlopCard1(Card flopCard1) {
        this.flopCard1 = flopCard1;
    }
    public void setFlopCard2(Card flopCard2) {
        this.flopCard2 = flopCard2;
    }
    public void setFlopCard3(Card flopCard3) {
        this.flopCard3 = flopCard3;
    }
    public void setTurn(Card turn) {
        this.turn = turn;
    }
    public void setRiver(Card river) {
        this.river = river;
    }

    public Board(Card flopCard1, Card flopCard2, Card flopCard3, Card turn, Card river) {
        this.flopCard1 = flopCard1;
        this.flopCard2 = flopCard2;
        this.flopCard3 = flopCard3;
        this.turn = turn;
        this.river = river;
    }

    public String toString(){
        return this.flopCard1 + "," + this.flopCard2 + "," + this.flopCard3 + "," + this.turn + "," + this.river;
    }
}
