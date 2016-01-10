/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.util.ArrayList;
import java.util.List;
import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;
import waffegame2.cardOwner.CardCollection;
import waffegame2.cardOwner.Hand;
import waffegame2.logic.CardSelector;
import waffegame2.player.Player;

/**
 * The User interface - interface
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public abstract class UI implements CardSelector, Runnable {

    /**
     * Waits for the user to input a string
     *
     * @return the string that was inputted
     */
    abstract public String getString();

    /**
     * Prints the text on the screen
     *
     * @param text the text to be printed
     */
    abstract public void print(String text);

    /**
     * Prints the text on the screen on a new line
     *
     * @param text the text to be printed
     */
    abstract public void println(String text);

    /**
     * Hides all cards (for hotseat games)
     */
    abstract public void showTurnSeparator();

    /**
     * Shows a short instructional message to the player
     *
     * @param player The player to refer to
     */
    abstract public void showInstructionsToPlayer(Player player);

    /**
     * Displays the winner
     *
     * @param player the winner to be displayed
     */
    abstract public void showWinner(Player player);

    /**
     * Displays the winners
     *
     * @param players the winners to be displayed
     */
    abstract public void showWinners(List<Player> players);

    /**
     * Shows which cards have been selected
     *
     * @param player the player whose turn it is
     * @param playable the list of hands which contain all visible cards
     * @param selected the CardCollection of all selected cards
     */
    abstract public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected);

    /**
     * This method is called before a turn and setups for it.
     *
     * @param player the player whose turn it is
     * @param str a message to be displayed
     */
    abstract public void beforeTurn(Player player, String str);

    /**
     * This method is called before each attempt of a play, that is, after the
     * beforeTurn method. If the play was illegal this method is called again,
     * where beforeTurn is not called.
     */
    abstract public void inTurn();

    /**
     * This method is called after a legal turn has been played by the player.
     */
    abstract public void afterTurn();

    /**
     * This method is called when the player passes their turn. It is called
     * before afterTurn()
     */
    abstract public void turnPassed();

    abstract public void setPack(Pack pack);

    abstract public void setPile(Pile pile);

    /**
     * Attempts to show the options menu.
     *
     * @return false if unable to
     */
    abstract public boolean showOptionsBox();

    /**
     * The setup necessary before every game.
     */
    abstract public void setup();

    /**
     * Asks for players and returns them as a list of players
     *
     * @param playerAmount the player amount
     * @return a list of players
     */
    public List<Player> getPlayers(int playerAmount) {
        return getPlayers(playerAmount, playerAmount);
    }

    /**
     * Asks for players and returns them as a list of players
     *
     * @param min the minimum amount of player names to ask for
     * @param max the maximum amount of player names to ask for
     * @return a list of players
     */
    public List<Player> getPlayers(int min, int max) { //<---------------------- FIX
        List<Player> players = new ArrayList();
        for (int i = 0; i < min; i++) {
            println("Player " + (i + 1) + ", please enter your name:");
            players.add(getPlayer(players, false));
        }
        for (int i = min; i < max; i++) {
            println("Player " + (i + 1) + ", please enter your name (enter empty string to start):");
            Player p = getPlayer(players, false);
            if (p == null) {
                break;
            }
            players.add(p);
        }
        return players;
    }

    /**
     * Asks for a player name, checks that the name is unique and returns the
     * player
     *
     * @param players the list of players to be added to
     * @param skipable whether the player can be skipped
     * @return a new player
     */
    private Player getPlayer(List<Player> players, boolean skipable) {
        while (true) {
            String input = getString().trim();
            if (input != null) {
                if (input.length() > 0) {
                    if (hasPlayer(input, players)) {
                        println("The names should be unique to avoid confusion. Please enter a new name:");
                    } else {
                        Player p = new Player(input, this);
                        return p;
                    }
                } else if (skipable) {
                    return null;
                } else {
                    println("The names should be non-empty. Please enter a new name:");
                }
            }
        }
    }

    /**
     * Checks whether the player list already has a player with the specified
     * name
     *
     * @param name the name to check
     * @param players the list to check
     * @return true if the list contains a player with the same name as 'name'
     */
    public boolean hasPlayer(String name, List<Player> players) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
