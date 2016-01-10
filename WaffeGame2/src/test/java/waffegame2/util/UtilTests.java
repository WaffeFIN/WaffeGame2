/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.util;

import org.junit.Test;
import static org.junit.Assert.*;
import waffegame2.card.Card;
import waffegame2.card.Suit;
import waffegame2.card.Value;

/**
 *
 * @author Walter
 */
public class UtilTests {

    public UtilTests() {
    }

    @Test
    public void testSquare() {
        assertEquals(1, Util.sqr(1), 0.01);
        assertEquals(7, Util.sqr(2.646), 0.01);
    }

    @Test
    public void testDistanceSquared() {
        assertEquals(25, Util.distanceSquared(1, -2, 4, 2), 0.01);
        assertEquals(0, Util.distanceSquared(4, -2, 4, -2), 0.01);
    }

    @Test
    public void testIsInteger() {
        assertEquals(true, Util.isInteger("7"));
        assertEquals(true, Util.isInteger("-17"));
        assertEquals(false, Util.isInteger("-17asd"));
        assertEquals(false, Util.isInteger("0.3"));
    }

    @Test
    public void testGetCardSpriteFileName() {
        Card c1 = new Card(Value.ACE, Suit.SPADES);
        Card c2 = new Card(Value.KING, Suit.CLUBS);
        Card c3 = new Card(Value.FOUR, Suit.HEARTS);
        Card c4 = new Card(Value.TEN, Suit.DIAMONDS);
        Card c5 = new Card(Value.JOKER, Suit.JOKER);

        assertEquals("images/cAS.png", Util.getCardSpriteFileName(c1));
        assertEquals("images/cKC.png", Util.getCardSpriteFileName(c2));
        assertEquals("images/c4H.png", Util.getCardSpriteFileName(c3));
        assertEquals("images/c10D.png", Util.getCardSpriteFileName(c4));
        assertEquals("images/cJJ.png", Util.getCardSpriteFileName(c5));
    }
}
