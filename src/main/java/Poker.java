import java.sql.SQLOutput;
import java.util.*;

public class Poker {
    private static HashMap<String, Integer> cardTotals = new HashMap<>();
    private static HashMap<String, Integer> suitTotals = new HashMap<>();
    public static void main(String args[]){
        Deck deck = new Deck();

        System.out.println("Deck before shuffling: ");
        System.out.println(deck.getDeck());

        //Collections.shuffle(deck.getDeck());

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
        System.out.println("Calculating Winner");

        ArrayList<Card> allCards = convertCardsToArrayList(player.getHand(),board);

        checkForPairTwoPairTripsQuadsFullHouse(player, allCards);
        checkForStraight(player, allCards);
        checkForFlush(player, allCards);
        checkForStraightFlush(player, allCards);
        checkForRoyalFlush(player, allCards);
        System.out.println(player.getHandRankString());
        System.out.println();
    }

    public static void checkForPairTwoPairTripsQuadsFullHouse(Player player, ArrayList<Card> allCards){
        int lastElement;
        int secondToLastElement;
        for(Card card : allCards)
            if (cardTotals.containsKey(card.getValue()))
                cardTotals.put(card.getValue(), cardTotals.get(card.getValue()) + 1);
            else
                cardTotals.put(card.getValue(), 1);

        Object[] cardTotalArray = cardTotals.values().toArray();
        Arrays.sort(cardTotalArray);

        lastElement = (int) cardTotalArray[cardTotalArray.length-1];
        secondToLastElement = (int) cardTotalArray[cardTotalArray.length-2];

        if(lastElement == 3 && secondToLastElement == 2) // Full House
            player.setHandRanking(4);
        else if(lastElement == 2 && secondToLastElement == 2) // Two Pair
            player.setHandRanking(8);
        else if(lastElement == 2) // Pair
            player.setHandRanking(9);
        else if(lastElement == 3) // Trips
            player.setHandRanking(7);
        else if(lastElement > 3) // Quads
            player.setHandRanking(3);
    }
    public static void checkForStraight(Player player, ArrayList<Card> allCards){
        int count = 0;
        List<Integer> cards = new LinkedList<>();

        for(Card currentCard : allCards) {
            if ("J".equals(currentCard.getValue())) {
                cards.add(11);
            } else if ("Q".equals(currentCard.getValue())) {
                cards.add(12);
            } else if ("K".equals(currentCard.getValue())) {
                cards.add(13);
            } else if ("A".equals(currentCard.getValue())) {
                cards.add(1);
                cards.add(14);
            } else {
                cards.add(Integer.valueOf(currentCard.getValue()));
            }
        }

        Collections.sort(cards);

        for(int i = 0; i < cards.size()-1; i++)
            if(cards.get(i) == cards.get(i+1)-1)
                count++;

        if(count >= 5)
            player.setHandRanking(6);
    }

    public static void checkForFlush(Player player, ArrayList<Card> allCards){
        for(Card card : allCards)
            if (suitTotals.containsKey(card.getSuit()))
                suitTotals.put(card.getSuit(), suitTotals.get(card.getSuit()) + 1);
            else
                suitTotals.put(card.getSuit(), 1);

        Object[] suitTotalArray = suitTotals.values().toArray();
        Arrays.sort(suitTotalArray);
        int maxSuits = (int) suitTotalArray[suitTotalArray.length-1];

        if(maxSuits >= 5)
            player.setHandRanking(5);
    }

    public static void checkForStraightFlush(Player player, ArrayList<Card> allCards){
        checkForStraight(player,allCards);
        if(player.getHandRanking() == 6) {
            checkForFlush(player, allCards);
            if(player.getHandRanking() == 5)
                player.setHandRanking(2);
        }
    }

    public static void checkForRoyalFlush(Player player, ArrayList<Card> allCards){
        int count = 0;
        if(player.getHandRanking() == 2)
            for(Card card : allCards)
                if(card.getValue().equals("A") || card.getValue().equals("K"))
                    count++;
        if(count >= 2)
            player.setHandRanking(1);
    }

    public static ArrayList<Card> convertCardsToArrayList(Hand hand,Board board){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.add(board.getFlopCard1());
        allCards.add(board.getFlopCard2());
        allCards.add(board.getFlopCard3());
        allCards.add(board.getTurn());
        allCards.add(board.getRiver());
        allCards.add(hand.getFirstCard());
        allCards.add(hand.getSecondCard());
        return allCards;
    }
}
