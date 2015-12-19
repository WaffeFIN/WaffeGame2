/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import waffegame2.domain.*;

/**
 *
 * @author Walter
 */
public class PileTests {

    private Pack pack;
    private Pile pile;
    private Hand hand;
    private List<Card> cards;

    public PileTests() {
    }

    @Before
    public void setUp() {
        pack = new Pack(3, 1);
        pile = new Pile();
        hand = new Hand(10);
        cards = new ArrayList();
        cards.add(new Card(Value.TWO, Suit.DIAMONDS));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
    }

    @Test
    public void simpleTransferToPile() {
        assertTrue(pile.addCard(new Card(Value.FIVE, Suit.DIAMONDS)));
    }

    @Test
    public void transferToPileWorks() {
        pile.addCard(new Card(Value.FIVE, Suit.DIAMONDS));
        assertTrue(pile.cardAmount() == 1);
    }

    @Test
    public void emptyPileIsInvalid() {
        cards.add(new Card(Value.TEN, Suit.CLUBS));
        pile.addCard(cards);
        assertTrue(pile.getType() == PileType.NONE);
    }

    @Test
    public void emptyPileIsInvalidWithJokers() {
        cards.add(new Card(Value.NINE, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        pile.addCard(cards);
        assertTrue(pile.getType() == PileType.NONE);
    }

    @Test
    public void emptyPileSuitIsValid() {
        cards.add(new Card(Value.TEN, Suit.DIAMONDS));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.SUIT);
    }

    @Test
    public void emptyPileSuitIsValidWithJokers() {
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.SUIT);
    }

    @Test
    public void emptyPileStraightIsValid() {
        cards.add(new Card(Value.THREE, Suit.HEARTS));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWrapAround() {
        cards.add(new Card(Value.KING, Suit.CLUBS));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithJokersOnEnd() {
        cards.add(new Card(Value.THREE, Suit.SPADES));
        cards.add(new Card(Value.FOUR, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithJokersInMiddle() {
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.QUEEN, Suit.CLUBS));
        cards.add(new Card(Value.NINE, Suit.SPADES));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithThreeJokersInMiddle() {
        cards.add(new Card(Value.FOUR, Suit.HEARTS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JACK, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        assertTrue(pile.addCard(cards));
        assertTrue(pile.getType() == PileType.STRAIGHT);
    }
}
