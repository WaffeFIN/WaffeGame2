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
public class PileTests {

    private Pack pack;
    private Pile pile;
    private Hand hand;
    private List<Card> cards;

    public PileTests() {
    }

    private void checkAdd(PileType type) {
        boolean r = pile.addCard(cards);
        assertEquals(type, pile.getType());
        assertTrue(r);
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
    public void addCardToPile() {
        assertTrue(pile.addCard(new Card(Value.FIVE, Suit.DIAMONDS)));
    }

    @Test
    public void addCardToPileWorks() {
        pile.addCard(new Card(Value.FIVE, Suit.DIAMONDS));
        assertEquals(pile.cardAmount(), 1);
        assertEquals(pile.getType(), PileType.SUIT);
    }

    @Test
    public void emptyPileIsInvalid() {
        cards.add(new Card(Value.TEN, Suit.CLUBS));
        assertTrue(!pile.addCard(cards));
        assertEquals(pile.getType(), PileType.NONE);
    }

    @Test
    public void emptyPileIsInvalidWithJokers() {
        cards.add(new Card(Value.NINE, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        assertTrue(!pile.addCard(cards));
        assertEquals(pile.getType(), PileType.NONE);
    }

    @Test
    public void emptyPileSuitIsValid() {
        cards.add(new Card(Value.TEN, Suit.DIAMONDS));
        checkAdd(PileType.SUIT);
    }

    @Test
    public void emptyPileSuitIsValidWithJokers() {
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.SUIT);
    }

    @Test
    public void emptyPileStraightIsValid() {
        cards.add(new Card(Value.THREE, Suit.HEARTS));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWrapAround() {
        cards.add(new Card(Value.KING, Suit.CLUBS));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithJokersOnEnd() {
        cards.add(new Card(Value.THREE, Suit.SPADES));
        cards.add(new Card(Value.FOUR, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithJokersInMiddle() {
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.FOUR, Suit.CLUBS));
        cards.add(new Card(Value.FIVE, Suit.SPADES));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithJokersInMiddleWrapAround() {
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.QUEEN, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.TEN, Suit.SPADES));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPileStraightIsValidWithThreeJokersInMiddle() {
        cards.add(new Card(Value.FOUR, Suit.HEARTS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JACK, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.STRAIGHT);
    }

    @Test
    public void emptyPilePairsAreValid() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        checkAdd(PileType.PAIRS);
    }

    @Test
    public void emptyPilePairsAreValidWithJoker() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.FOUR, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.PAIRS);
    }

    @Test
    public void emptyPilePairsAreValidWithJokers() {
        cards.clear();
        cards.add(new Card(Value.SEVEN, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.PAIRS);
    }

    @Test
    public void emptyPileTriplesAreValid() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        checkAdd(PileType.TRIPLES);
    }

    @Test
    public void emptyPileTriplesAreValidWithJoker() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.TRIPLES);
    }

    @Test
    public void emptyPileTriplesAreValidWithJokers() {
        cards.add(new Card(Value.ACE, Suit.HEARTS));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.TRIPLES);
    }

    @Test
    public void emptyPileQuadruplesAreValid() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.ACE, Suit.HEARTS));
        cards.add(new Card(Value.TWO, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        checkAdd(PileType.QUADRUPLES);
    }

    @Test
    public void emptyPileQuadruplesAreValidWithJokers() {
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.HEARTS));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.QUADRUPLES);
    }

    @Test
    public void evolutionTest1() {
        checkAdd(PileType.SUIT);
        cards.clear();
        cards.add(new Card(Value.KING, Suit.CLUBS));
        checkAdd(PileType.STRAIGHT);
        cards.clear();
        cards.add(new Card(Value.TWO, Suit.CLUBS));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.PAIRS);
        cards.clear();
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        checkAdd(PileType.TRIPLES);
        cards.clear();
        cards.add(new Card(Value.KING, Suit.HEARTS));
        checkAdd(PileType.PAIRS);
        cards.clear();
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.TWO, Suit.SPADES));
        checkAdd(PileType.QUADRUPLES);
    }

    @Test
    public void evolutionTest2() {
        checkAdd(PileType.SUIT);
        cards.clear();
        cards.add(new Card(Value.SIX, Suit.DIAMONDS));
        checkAdd(PileType.SUIT);
        cards.clear();
        cards.add(new Card(Value.KING, Suit.DIAMONDS));
        checkAdd(PileType.SUIT);
        cards.clear();
        cards.add(new Card(Value.THREE, Suit.SPADES));
        cards.add(new Card(Value.JOKER, Suit.JOKER));
        cards.add(new Card(Value.FIVE, Suit.SPADES));
        checkAdd(PileType.STRAIGHT);
        cards.clear();
        cards.add(new Card(Value.QUEEN, Suit.HEARTS));
        checkAdd(PileType.STRAIGHT);
    }
}

