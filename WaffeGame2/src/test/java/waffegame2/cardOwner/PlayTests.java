/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import waffegame2.card.Card;

/**
 *
 * @author Walter
 */
public class PlayTests {

    private Play play;
    private CardOwner owner1;
    private CardOwner owner2;

    public PlayTests() {
    }

    @Before
    public void setUp() {
        play = new Play();
        owner1 = new Pack(1, 1);
        owner2 = new Pack();

        owner1.transferCards(owner2, owner1.getCards(14));
    }

    @Test
    public void testAddCards() {
        play.addCardsFrom(owner1, owner1.getCards(3));
        assertEquals(3, play.cardAmount());

        play.addCardFrom(owner2, owner2.getCard());
        assertEquals(4, play.cardAmount());
    }

    @Test
    public void testRemoveCards() {
        List<Card> cards = owner1.getCards(3);
        play.addCardsFrom(owner1, cards);
        Card card = owner2.getCard();
        play.addCardFrom(owner2, card);
        cards.add(card);
        assertEquals(false, play.removeCards(owner1, cards));
        cards.remove(card);
        assertEquals(true, play.removeCards(owner1, cards));
        assertEquals(1, play.cardAmount());
        assertEquals(true, play.removeCard(owner2, card));
        assertEquals(0, play.cardAmount());
    }

    @Test
    public void testTransferCards() {
        CardOwner target = new Hand(8);
        play.addCardsFrom(owner1, owner1.getCards(6));
        play.addCardFrom(owner2, owner2.getCard());
        int owner1Amount = owner1.cardAmount();
        int owner2Amount = owner2.cardAmount();
        assertEquals(true, play.transferCards(target));
        assertEquals(owner1Amount - 6, owner1.cardAmount());
        assertEquals(owner2Amount - 1, owner2.cardAmount());
        assertEquals(0, play.cardAmount());

        play.addCardFrom(owner1, owner1.getCard());
        play.addCardFrom(owner2, owner2.getCard());
        owner1Amount = owner1.cardAmount();
        owner2Amount = owner2.cardAmount();
        assertEquals(false, play.transferCards(target));
        assertEquals(owner1Amount, owner1.cardAmount());
        assertEquals(owner2Amount, owner2.cardAmount());
        assertEquals(2, play.cardAmount());
    }

    @Test
    public void testGetCards() {
        play.addCardsFrom(owner1, owner1.getCards(6));
        Card card = owner2.getCard();
        play.addCardFrom(owner2, card);
        assertEquals(card, play.getCards(owner2).get(0));
    }
}
