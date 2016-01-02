/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import waffegame2.player.Player;
import waffegame2.ui.UI;

/**
 * Abstract class extended by all game logic
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public abstract class GameLogic {

    protected UI ui;
    protected List<Player> players;

    public GameLogic(UI ui) {
        this.ui = ui;
        this.players = new ArrayList();
    }

    public void addPlayers(Collection<Player> players) {
        this.players.addAll(players);
    }

    abstract public String getGameName();

    abstract public GameRules getRules();

    abstract public void init();

    abstract public void setup();

    abstract public void playGame();

}
