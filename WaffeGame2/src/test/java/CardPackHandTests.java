/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import waffegame2.domain.*;

/**
 *
 * @author Walter
 */
public class CardPackHandTests {

    private Pack pack;
    private Hand hand;

    public CardPackHandTests() {
    }

    @Before
    public void setUp() {
        pack = new Pack(3, 1);
        hand = new Hand(10);
    }

    @Test
    public void testPackSize() {
        assertTrue(pack.cardAmount() == 3 + 1 * 52);
    }

    @Test
    public void testHandSize() {
        assertTrue(hand.cardAmount() == 0);
    }

    @Test
    public void testCardToString() {
        Card card = new Card(Value.TEN, Suit.CLUBS);
        assertTrue(card.toString().equals("10 of Clubs"));

        card = new Card(Value.KING, Suit.HEARTS);
        assertTrue(card.toString().equals("King of HEarts"));

        card = new Card(Value.ACE, Suit.SPADES);
        assertTrue(card.toString().equals("Ace of Spades"));

        card = new Card(Value.QUEEN, Suit.DIAMONDS);
        assertTrue(card.toString().equals("Queen of Diamonds"));

        card = new Card(Value.JOKER, Suit.JOKER);
        assertTrue(card.toString().equals("Joker"));
    }
}
