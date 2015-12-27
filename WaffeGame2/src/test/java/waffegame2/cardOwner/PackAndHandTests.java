/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import waffegame2.card.Value;
import waffegame2.card.Suit;
import waffegame2.card.Card;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Walter
 */
public class PackAndHandTests {

    private Pack pack;
    private Hand hand;

    public PackAndHandTests() {
    }

    @Before
    public void setUp() {
        pack = new Pack(1, 3);
        hand = new Hand(10);
    }

    @Test
    public void testPacktoString() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.HEARTS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.KING, Suit.DIAMONDS));
        pack.addCards(cards);
        assertEquals(true, pack.toString().contains("Ace of Spades"));
        assertEquals(true, pack.toString().contains("Ace of Hearts"));
        assertEquals(true, pack.toString().contains("Joker"));
        assertEquals(true, pack.toString().contains("2 of Clubs"));
        assertEquals(true, pack.toString().contains("King of Diamonds"));
    }

    @Test
    public void testPackSize1() {
        assertEquals(pack.cardAmount(), 1 * (52 + 3));
        Pack otherPack = new Pack(3, 4);
        assertEquals(otherPack.cardAmount(), 3 * (52 + 4));
    }

    @Test
    public void testPackSize2() {
        pack = new Pack();
        assertEquals(pack.cardAmount(), 0);
    }

    @Test
    public void testPackJokers() {
        int jokers = 0;
        for (Card card : pack.getCards()) {
            if (card.isJoker()) {
                jokers++;
            }
        }
        assertEquals(1 * 3, jokers);
        jokers = 0;
        Pack otherPack = new Pack(3, 4);
        for (Card card : otherPack.getCards()) {
            if (card.isJoker()) {
                jokers++;
            }
        }
        assertEquals(3 * 4, jokers);
    }

    @Test
    public void testDefaultHandSize() {
        assertEquals(0, hand.cardAmount(), 0);
    }

    @Test
    public void testDefaultHandVisibility() {
        assertEquals(false, hand.isVisible());
    }

    @Test
    public void testHandVisibility() {
        hand.setVisibility(true);
        assertEquals(true, hand.isVisible());
        hand.setVisibility(false);
        assertEquals(false, hand.isVisible());
    }

    @Test
    public void testHandMaxSize1() {
        List<Card> cards = new ArrayList();
        for (int i = 0; i < 9; i++) {
            cards.add(new Card(Value.ACE, Suit.SPADES));
        }
        hand.addCards(cards);
        assertEquals(true, hand.addCard(new Card(Value.TWO, Suit.DIAMONDS)));
        assertEquals(false, hand.addCard(new Card(Value.THREE, Suit.DIAMONDS)));
    }

    @Test
    public void testHandMaxSize2() {
        List<Card> cards = new ArrayList();
        for (int i = 0; i < 11; i++) {
            cards.add(new Card(Value.ACE, Suit.SPADES));
        }
        assertEquals(false, hand.addCards(cards));
        assertEquals(true, hand.addCard(new Card(Value.TWO, Suit.DIAMONDS)));
    }

    @Test
    public void testHandAddCards() {
        Hand otherHand = new Hand(0);
        List<Card> cards = new ArrayList();
        for (int i = 0; i < 50; i++) {
            cards.add(new Card(Value.ACE, Suit.SPADES));
        }
        assertEquals(true, otherHand.addCards(cards));
        assertEquals(true, otherHand.addCard(new Card(Value.TWO, Suit.DIAMONDS)));
    }

    @Test
    public void testHandOrdering() {
        List<Card> cards = new ArrayList();
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.QUEEN, Suit.HEARTS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.KING, Suit.DIAMONDS));
        hand.addCards(cards);
        hand.sort();
        assertEquals(true, hand.toString().contains("Joker\n"
                + "Ace of Spades\n"
                + "2 of Clubs\n"
                + "Queen of Hearts\n"
                + "King of Diamonds"));
    }

}
