/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import waffegame2.card.Card;
import waffegame2.card.Suit;
import waffegame2.card.Value;
import waffegame2.cardOwner.Hand;
import waffegame2.cardOwner.HandAccessibility;
import waffegame2.player.Player;
import waffegame2.ui.TextBasedUI;

/**
 *
 * @author Walter
 */
public class GameLogicWaffeGame2Tests {

    private GameLogicWaffeGame2 logic;
    private ByteArrayOutputStream outputStream;

    public GameLogicWaffeGame2Tests() {
    }

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public void createLogic() {
        logic = new GameLogicWaffeGame2(new TextBasedUI());
    }

    public void createLogic(String input) {
        logic = new GameLogicWaffeGame2(new TextBasedUI(new Scanner(input)));
    }

    private void checkContains(String output, boolean expected) {
        assertEquals(expected, outputStream.toString().contains(output));
        System.out.println("\n" + outputStream.toString());
    }

    @Test
    public void getRulesWorks() {
        createLogic();
        assertEquals(GameRulesWaffeGame2.class, logic.getRules().getClass());
    }

    @Test
    public void getGameNameWorks() {
        createLogic();
        assertEquals("WaffeGame2", logic.getGameName());
    }

    @Test
    public void testAddPlayers() {
        String input = "";
        createLogic(input);

        addPlayers();
        assertEquals(2, logic.getPlayers().size());
        addPlayers();
        assertEquals(4, logic.getPlayers().size());
    }

    private List<Player> addPlayers() {
        List<Player> players = new ArrayList();
        players.add(new Player("Name", new DummySelector()));
        players.add(new Player("Other", new DummySelector()));
        logic.addPlayers(players);
        return players;
    }

    @Test
    public void testInit() {
        String input = "";
        createLogic(input);

        List<Player> players = addPlayers();
        logic.init();
        assertTrue(logic.getPack() != null);
        assertTrue(logic.getPile() != null);
        for (Player player : players) {
            assertEquals(3, player.getHands().size());
            assertEquals(HandAccessibility.PRIVATE, player.getHand(0).getAccessibility());
            assertTrue(player.getHand(0).getName().contains("Private Hand"));
            assertEquals(HandAccessibility.VISIBLE, player.getHand(1).getAccessibility());
            assertTrue(player.getHand(1).getName().contains("Shared Hand"));
            assertEquals(HandAccessibility.VISIBLE, player.getHand(2).getAccessibility());
            assertTrue(player.getHand(2).getName().contains("Shared Hand"));
        }
    }

    @Test
    public void testSetup() {
        String input = "";
        createLogic(input);

        List<Player> players = addPlayers();
        logic.init();
        assertEquals(1 * 52 + 3, logic.getPack().cardAmount());
        logic.getPack().clear();
        assertEquals(0, logic.getPack().cardAmount());
        for (Hand hand : players.get(0).getHands()) {
            hand.addCard(new Card(Value.ACE, Suit.SPADES));
        }
        assertTrue(players.get(0).cardAmount() < 10);
        logic.setup();
        assertTrue(players.get(0).cardAmount() == 10);
        assertEquals(1 * 52 + 3 - 2 * 10, logic.getPack().cardAmount());
    }

    @Test
    public void testEndState1() {
        String input = "";
        createLogic(input);

        List<Player> players = addPlayers();
        logic.init();
        logic.setup();
        for (Hand hand : players.get(0).getHands()) {
            hand.clear();
        }
        logic.playGame();
        checkContains(players.get(0).getName() + ",", false);
    }

    @Test
    public void testEndState2() {
        String input = "\n\n";
        createLogic(input);

        List<Player> players = addPlayers();
        logic.init();
        logic.setup();
        for (Hand hand : players.get(1).getHands()) {
            hand.clear();
        }
        logic.playGame();
        checkContains(players.get(0).getName() + ",", false);
        checkContains(players.get(1).getName() + ",", false);
    }

//    @Test
//    public void testCardSelection1() {
//        String input = "\n"
//                + "0 1 2 3 4 5 6 7 8 9\n";
//        createLogic(input);
//
//        List<Player> players = addPlayers();
//        logic.init();
//        logic.setup();
//        logic.getPack().clear();
//        logic.getPack().createCards();
//        logic.playGame();
//        checkContains(players.get(0).getName() + ",", true);
//        checkContains(players.get(1).getName() + ",", false);
//    }
//
//    @Test
//    public void testCardSelection2() {
//        String input = "\n"
//                + "0\n"
//                + "\n"
//                + "0 1 2 3 4 5 6 7 8 9\n";
//        createLogic(input);
//
//        List<Player> players = addPlayers();
//        logic.init();
//        logic.setup();
//        logic.getPack().clear();
//        logic.getPack().createCards();
//        logic.playGame();
//        checkContains(players.get(0).getName() + ",", true);
//        checkContains(players.get(1).getName() + ",", true);
//    }

    @Test
    public void testP() {

        //logic.playGame();
        //logic.setup();
    }

}
