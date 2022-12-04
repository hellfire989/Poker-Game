import java.sql.SQLOutput;
import java.util.*;

public class Poker {
    private static HashMap<String, Integer> cardTotals = new HashMap<>();
    private static HashMap<String, Integer> suitTotals = new HashMap<>();
    private static List<Player> players = new LinkedList<>();
    private static List<Player> winners = new LinkedList<>();
    private static ArrayList<Card> currentPlayersCards;
    private static Deck deck;
    private static Board board;
    public static void main(String args[]){
        long totalRunTime = 0;
        for(int i = 0; i < 10000; i++) {
            players.clear();
            winners.clear();
            long startTime = System.nanoTime();

            deck = new Deck();
            Collections.shuffle(deck.getDeck());

            addPlayers(7);
            createBoard();
            calculateWinners();
            printResults();

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("");
            System.out.println("Total run time of program: " + totalTime / 1000 + " Microseconds");
            totalRunTime += totalTime;
        }
        System.out.println("Total run time of 1000 runs: " + totalRunTime / 1000 + " Microseconds");
    }

    public static void findHandRankForPlayer(Player currentPlayer){
        currentPlayersCards = convertCardsToArrayList(currentPlayer.getHand(),board);

        //Return booleans and add if checks
        checkForPairTwoPairTripsQuadsFullHouse(currentPlayer);
        checkForStraight(currentPlayer);
        checkForFlush(currentPlayer);
        checkForStraightFlush(currentPlayer);
        checkForRoyalFlush(currentPlayer);
    }

    public static void checkForPairTwoPairTripsQuadsFullHouse(Player player){
        cardTotals.clear();
        int lastElement;
        int secondToLastElement;
        for(Card card : currentPlayersCards)
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

    public static void checkForStraight(Player player){
        int count = 0;
        boolean hasAce = false;
        List<Integer> cards = new LinkedList<>();

        for(Card currentCard : currentPlayersCards) {
            if ("J".equals(currentCard.getValue())) {
                cards.add(11);
            } else if ("Q".equals(currentCard.getValue())) {
                cards.add(12);
            } else if ("K".equals(currentCard.getValue())) {
                cards.add(13);
            } else if ("A".equals(currentCard.getValue())) {
                cards.add(1);
                cards.add(14);
                hasAce = true;
            } else {
                cards.add(Integer.valueOf(currentCard.getValue()));
            }
        }

        Set<Integer> set = new HashSet<>(cards); //Removes duplicates
        cards.clear();
        cards.addAll(set);
        Collections.sort(cards);

        for(int i = 0; i < cards.size()-1; i++) {
            if (cards.get(i) == cards.get(i + 1) - 1)
                count++;
        }

        if(count > 5 && hasAce)
            player.setHandRanking(6);
        else if(count > 4 && !hasAce)
            player.setHandRanking(6);
    }

    public static void checkForFlush(Player player){
        suitTotals.clear();
        for(Card card : currentPlayersCards)
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

    public static void checkForStraightFlush(Player player){
        checkForStraight(player);
        if(player.getHandRanking() == 6) {
            checkForFlush(player);
            if(player.getHandRanking() == 5)
                player.setHandRanking(2);
        }
    }

    public static boolean checkForRoyalFlush(Player player){
        checkForStraightFlush(player);
        if(player.getHandRanking() == 2) {
            int count = 0;
            List<String> cards = new LinkedList<>();
            for (Card card : currentPlayersCards)
                cards.add(card.getValue());

            Set<String> set = new HashSet<>(cards);
            cards.clear();
            cards.addAll(set);
            Collections.sort(cards);

            for (String str : cards)
                if (str.equals("A") || str.equals("K") || str.equals("Q") || str.equals("J") || str.equals("10"))
                    count++;

            if (count == 5) {
                player.setHandRanking(1);
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Card> convertCardsToArrayList(Hand hand,Board board){
        ArrayList<Card> currentPlayersCards = new ArrayList<>();
        currentPlayersCards.add(board.getFlopCard1());
        currentPlayersCards.add(board.getFlopCard2());
        currentPlayersCards.add(board.getFlopCard3());
        currentPlayersCards.add(board.getTurn());
        currentPlayersCards.add(board.getRiver());
        currentPlayersCards.add(hand.getFirstCard());
        currentPlayersCards.add(hand.getSecondCard());
        return currentPlayersCards;
    }

    public static void addPlayers(int totalPlayers){
        Player newPlayer;

        for(int i = 0; i < totalPlayers; i++) {
            newPlayer = new Player(0, 10, new Hand(deck.removeTopCard(), deck.removeTopCard()));
            newPlayer.setPlayerNo(i);
            players.add(newPlayer);
        }
    }
    public static void createBoard(){
        board = new Board(deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard(),deck.removeTopCard());
    }

    public static void calculateWinners(){
        for(Player player : players)
            findHandRankForPlayer(player);

        winners.add(players.get(0));
        for(int i = 1; i < players.size(); i++){
            if(players.get(i).getHandRanking() < winners.get(0).getHandRanking()){
                winners.clear();
                winners.add(players.get(i));
            }
            else if(players.get(i).getHandRanking() == winners.get(0).getHandRanking()){
                winners.add(players.get(i));
            }
        }

    }

    public static void printResults(){
        for(Player player : players) {
            System.out.println("Player: " + player.getPlayerNo() + " Hand Dealt: ");
            System.out.println(player.getHand());
        }

        System.out.println("");
        System.out.println("Board Dealt: ");
        System.out.println(board);
        System.out.println("");

        System.out.println("Winner(s) are: ");
        for(Player player : winners)
            System.out.println("Player: " + player.getPlayerNo() + " With Hand: " + player.getHand() + " - " + player.getHandRankString());
    }
}
