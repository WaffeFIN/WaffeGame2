/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import waffegame2.player.*;
import waffegame2.ui.*;

/**
 *
 * @author Walter
 */
public class Game {

    private UI ui;
    private List<Player> players;
    private GameRules rules;
    private GameLogic logic;

    public Game() {
        this.ui = new TextBasedUI();
        this.players = new ArrayList();
        setGamelogic(new GameLogicWaffeGame2(ui));
    }

    public void setGamelogic(GameLogic logic) {
        this.logic = logic;
        this.rules = logic.getRules();
    }

    public void run() {
        rules.setAllOptions(ui);

        addPlayers();
        logic.init();

        while (true) {
            logic.playGame();
        }
    }

    private void addPlayers() {
        for (int i = 0; i < rules.getPlayers(); i++) {
            ui.println("Player " + (i + 1) + ", please enter your name:");
            addPlayer();
        }

        logic.addPlayers(players);
    }

    private void addPlayer() {
        while (true) {
            String input = ui.getString();
            if (input != null && input.length() > 0) {
                if (hasPlayer(input)) {
                    ui.println("The names should be unique to avoid confusion. Please enter a new name:");
                } else {
                    Player p = new Player(input, ui);
                    players.add(p);
                    break;
                }
            }
        }
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
