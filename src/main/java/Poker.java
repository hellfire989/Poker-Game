import java.util.ArrayList;
import java.util.Collections;

public class Poker {
    public static void main(String args[]){
        Deck deck = new Deck();

        System.out.println("Deck before shuffling: ");
        System.out.println(deck.getDeck());

        Collections.shuffle(deck.getDeck());

        System.out.println("Deck after shuffling: ");
        System.out.println(deck.getDeck());

        Player player = new Player(0,10,new Hand(deck.removeTopCard(),deck.removeTopCard()));

        System.out.println("Hand Dealt: ");
        System.out.println(player.getHand());

        Board board = new Board(deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard());

        System.out.println("Board Dealt: ");
        System.out.println(board);
        System.out.println("");

        calculateWinner(player, board);
    }

    public static void calculateWinner(Player player, Board board){
        boolean hasPair = false;
        System.out.println("Calculating Winner");

        ArrayList<Card> handCards = convertHandToArrayList(player.getHand());
        ArrayList<Card> boardCards = convertBoardToArrayList(board);

        checkForPairTwoPairTripsQuads(player,handCards,boardCards);
        System.out.println(player.getHandRanking());
    }

    public static void checkForPairTwoPairTripsQuads(Player player, ArrayList<Card> handCards, ArrayList<Card> boardCards){
        int count = 0;
        for(Card boardCard : boardCards){
            for(Card handCard : handCards){
                if(handCard.getValue().equals(boardCard.getValue()))
                    count++;
            }
        }
        if(count == 1)
            player.setHandRanking(9);
        if(count == 2)
            player.setHandRanking(8);
        if(count == 3)
            player.setHandRanking(7);
        if(count > 3)
            player.setHandRanking(3);
    }
    
    public static ArrayList<Card> convertHandToArrayList(Hand hand){
        ArrayList<Card> handCards = new ArrayList<Card>();
        handCards.add(hand.getFirstCard());
        handCards.add(hand.getSecondCard());
        return handCards;
    }
    public static ArrayList<Card> convertBoardToArrayList(Board board){
        ArrayList<Card> boardCards = new ArrayList<Card>();
        boardCards.add(board.getFlopCard1());
        boardCards.add(board.getFlopCard2());
        boardCards.add(board.getFlopCard3());
        boardCards.add(board.getTurn());
        boardCards.add(board.getRiver());
        return boardCards;
    }
}
