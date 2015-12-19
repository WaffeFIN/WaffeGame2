package waffegame2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import waffegame2.domain.*;

/**
 *
 * @author Walter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pack pack;
        Pile pile;
        Hand hand;
        List<Card> cards;
        pack = new Pack(3, 1);
        pile = new Pile();
        hand = new Hand(10);
        cards = new ArrayList();
        cards.add(new Card(Value.TWO, Suit.DIAMONDS));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
        cards.add(new Card(Value.KING, Suit.CLUBS));
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println(pile.addCard(cards));
        System.out.println(pile.getType());
    }

}
