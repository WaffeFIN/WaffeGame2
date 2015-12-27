/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.List;
import waffegame2.cardOwner.*;
import waffegame2.player.*;
import waffegame2.ui.*;

/**
 *
 * @author Walter
 */
public class Game {

    private UI ui;
    private List<Player> players;
    private GameLogic rule;

    public Game() {
        this.ui = new TextBasedUI();
        this.players = new ArrayList();
    }

    public void run() {
        //Options
        rule = new GameLogicWaffeGame2(ui);

        //Preperations
        for (int i = 0; i < 2; i++) {
            ui.println("Player " + (i + 1) + ", please enter your name:");
            addPlayer();
        }
        setup();

        //Start game
        for (int i = 0; true;) {
            Player ender = rule.checkEndState();
            if (ender != null) {
                ui.println("\n\n\n" + ender + " is the Winner!\n\nThe game took " + i + " turns to play.");
                break;
            }
            Player playerInTurn = players.get(i % players.size());
            int turnShift = rule.playTurn(playerInTurn);
            i += turnShift;
        }
    }

    public void addPlayer() {
        while (true) {
            String input = ui.getString();
            if (input != null && input.length() > 0) {
                if (hasPlayer(input)) {
                    ui.println("The names should be unique to avoid confusion. Please enter a new name:");
                } else {
                    Player p = new Player(input, rule);
                    players.add(p);
                    break;
                }
            }
        }
    }

    public void setup() {
        rule.addPlayers(players);
        rule.createHands();
        Pile pile = rule.createPile();
        Pack pack = rule.createPack();
        rule.deal();

        ui.setPack(pack);
        ui.setPile(pile);
    }

    public boolean hasPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
