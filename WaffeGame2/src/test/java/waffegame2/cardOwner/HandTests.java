/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import waffegame2.card.Card;
import waffegame2.card.Suit;
import waffegame2.card.Value;

/**
 *
 * @author Walter
 */
public class HandTests {

    private Hand hand;

    public HandTests() {
    }

    @Before
    public void setUp() {
        hand = new Hand(10);
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
    public void testHandMaxSize3() {
        List<Card> cards = new ArrayList();
        for (int i = 0; i < 11; i++) {
            cards.add(new Card(Value.ACE, Suit.SPADES));
        }
        CardOwner owner = new Pack();
        assertTrue(owner.addCards(cards));
        int amount = owner.cardAmount();
        assertEquals(false, owner.transferCards(hand));
        assertEquals(true, owner.cardAmount() == amount);
        owner.removeCard(owner.getCard());
        assertEquals(true, owner.transferCards(hand));
        assertEquals(false, owner.cardAmount() == amount);
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
    public void testHandSorting() {
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
