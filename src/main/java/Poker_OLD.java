import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class Poker_OLD {
    static List<Card> board = new ArrayList<Card>();
    static HashMap<Integer, Card> hands = new HashMap<Integer, Card>();
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Generate hands for 7 players: ");
        for(int i = 0; i < 3; i++)
            generateHand();

        generateBoard();
    }

    public static void generateBoard(){
        for(int i = 0; i < 5; i++) {
            Card card = new Card(generateValue(),generateSuit());
            board.add(i,card);
        }

        System.out.println("\nBoard\n");

        for(Card currentCard : board){
            System.out.print(currentCard.getCard());
        }
    }

    public static void generateBoardBasic(){
        String response = "";

        String board = "";
        for(int i = 0; i < 2; i++){
            board += generateCard() + ",";
        }

        board += generateCard();
        System.out.println();
        System.out.println("Flop comes: ");
        System.out.println(board);

        if(keyboard.nextLine().equals(""));

        board += "," + generateCard();
        System.out.println("Turn comes: ");
        System.out.println(board);

        if(keyboard.nextLine().equals(""));

        board += "," + generateCard();
        System.out.println("River comes: ");
        System.out.println(board);

    }

    public static void generateHand(){
        String hand = "";
        hand += generateCard() + "," + generateCard();
        System.out.println(hand);
    }

    public static String generateCard(){
        return generateValue() + generateSuit();
    }

    public static String generateValue(){
        int num = (int) (Math.random()*13);
        String value = "";
        switch (num) {
            case 0:  value = "A";
                break;
            case 11:  value = "J";
                break;
            case 12:  value = "Q";
                break;
            case 13:  value = "K";
                break;
            default: value = String.valueOf(num);
                break;
        }
        return value;
    }

    public static String generateSuit(){
        int num = (int) (Math.random()*4);
        String suit = "";
        switch (num) {
            case 0:  suit = "♥";
                break;
            case 1:  suit = "♦";
                break;
            case 2:  suit = "♣";
                break;
            case 3:  suit = "♠";
                break;
            default: suit = "No Suit";
                break;
        }
        return suit;
    }
}
