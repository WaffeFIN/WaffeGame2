/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import waffegame2.card.Card;
import waffegame2.card.Suit;
import waffegame2.card.Value;
import waffegame2.cardOwner.CardCollection;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;

/**
 *
 * @author Walter
 */
public class TextBasedUITests {

    private UI ui;
    private ByteArrayOutputStream outputStream;

    public TextBasedUITests() {
    }

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void createUIWithInput(String input) {
        ui = new TextBasedUI(new Scanner(input));
    }

    private void checkContains(String output, boolean expected) {
        assertEquals(expected, outputStream.toString().contains(output));
    }

    @Test
    public void printTests() {
        createUIWithInput("");
        ui.print("this text is here");
        ui.println(", but this text is here too");
        ui.print("this text is on the next row?");
        ui.printSeparator();
        checkContains("this text is here, but this text is here too", true);
        checkContains("\nthis text is on the next row?", true);
        checkContains("?\n\n", true);
    }

    @Test
    public void testForScanner() {
        createUIWithInput("test\ntwo\n");
        assertEquals("test", ui.getString());
        assertEquals("two", ui.getString());
    }

    @Test
    public void testSkip() {
        createUIWithInput("\n");
        ui.waitForPlayerToContinue();
        assertTrue(true);
    }

    @Test
    public void showWinnerTest() {
        createUIWithInput("");
        ui.showWinner("Wafafel");
        checkContains("Wafafel ", true);
    }

    @Test
    public void showWinnersTest() {
        createUIWithInput("");
        List<String> names = new ArrayList();
        names.add("xD");
        names.add("zÖll");
        names.add("asdxadas");
        ui.showWinners(names);
        checkContains("xD", true);
        checkContains("zÖll", true);
        checkContains("asdxadas", true);
    }

    @Test
    public void showInstructionsTest() {
        createUIWithInput("");
        ui.showInstructions("sxckmJBSa");
        checkContains("sxckmJBSa", true);
        checkContains("select ", true);
    }

    @Test
    public void showPreTurnTest() {
        createUIWithInput("");
        ui.showPreTurn("testOutputxashjdvkasd");
        checkContains("testOutputxashjdvkasd", true);
    }

    @Test
    public void selectCardTest() {
        createUIWithInput("1 2 4\n");

        Player player = new Player("Name", ui);
        setupPlayer(player);

        List<Card> cards = ui.selectCards(player, player.getHands());
        assertEquals(3, cards.size());
        assertEquals(Suit.CLUBS,cards.get(0).getSuit());
    }

    @Test
    public void selectCardTestNull() {
        createUIWithInput("\n");

        Player player = new Player("Name", ui);
        setupPlayer(player);

        assertEquals(null, ui.selectCards(player, player.getHands()));
    }

    @Test
    public void showSelectedCardsTests() {
        createUIWithInput("");

        Player player = new Player("Name", ui);
        setupPlayer(player);
        
        CardCollection selected = new CardCollection();
        selected.addCardsFrom(player.getHand(), player.getHand().getCards());

        ui.showSelectedCards(player, player.getHands(), selected);
        
        checkContains(">[0]<",true);
        checkContains(">[1]<",true);
        checkContains(">[2]<",true);
    }

    private void setupPlayer(Player player) {
        Hand hand1 = new Hand(player, player.getName() + "'s Hand", 23);
        Hand hand2 = new Hand(player, player.getName() + "'s Hand numero dos", 123);
        hand1.addCard(new Card(Value.ACE, Suit.HEARTS));
        hand1.addCard(new Card(Value.KING, Suit.CLUBS));
        hand1.addCard(new Card(Value.QUEEN, Suit.SPADES));
        hand2.addCard(new Card(Value.JACK, Suit.HEARTS));
        hand2.addCard(new Card(Value.TEN, Suit.DIAMONDS));
        player.addHand(hand1);
        player.addHand(hand2);
    }
}
