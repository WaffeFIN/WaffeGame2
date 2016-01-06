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

    //Essential
    abstract public String getString();

    abstract public void waitForPlayerToContinue();

    //Extra
    abstract public void print(String text);

    abstract public void println(String text);

    abstract public void showTurnSeparator(); //TextUIOnly 

    abstract public void showInstructionsToPlayer(String name);

    abstract public void showWinner(String name);

    abstract public void showWinners(List<String> names);

    abstract public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected);

    abstract public void showPreTurn(String str);

    //GUI stuff below
    abstract public void setPack(Pack pack);

    abstract public void setPile(Pile pile);
    
    abstract public boolean showOptionsBox();

    public List<Player> addPlayers(int playerAmount) {
        return getPlayers(playerAmount, playerAmount);
    }

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

    private Player getPlayer(List<Player> players, boolean skipable) {
        while (true) {
            String input = getString();
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
                }
            }
        }
    }

    public boolean hasPlayer(String name, List<Player> players) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
