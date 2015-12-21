package waffegame2.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import waffegame2.domain.*;

/**
 *
 * @author Walter
 */
public class CardTests {

    public CardTests() {
    }

    @Test
    public void isJokerWorks() {
        Card card = new Card(Value.JOKER, Suit.JOKER);
        assertEquals(true, card.isJoker());
    }

    @Test
    public void tryingToCreateInvalidCard() {
        try {
            Card card = new Card(Value.ACE, Suit.JOKER);
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
        try {
            Card card = new Card(Value.JOKER, Suit.SPADES);
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testCardToString() {
        Card card = new Card(Value.TEN, Suit.CLUBS);
        assertEquals("10 of Clubs", card.toString());

        card = new Card(Value.KING, Suit.HEARTS);
        assertEquals("King of Hearts", card.toString());

        card = new Card(Value.ACE, Suit.SPADES);
        assertEquals("Ace of Spades", card.toString());

        card = new Card(Value.QUEEN, Suit.DIAMONDS);
        assertEquals("Queen of Diamonds", card.toString());

        card = new Card(Value.JOKER, Suit.JOKER);
        assertEquals("Joker", card.toString());
    }

}
