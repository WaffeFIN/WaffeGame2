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
 *
 * @author Walter
 */
public abstract class GameLogic implements CardSelector {

    protected UI ui;
    protected List<Player> players;

    public GameLogic(UI ui) {
        this.ui = ui;
        this.players = new ArrayList();
    }
    
    abstract public int getMaxPlayers();

    public void addPlayers(Collection<Player> players) {
        this.players.addAll(players);
    }

    abstract public void setup();

    abstract public List<Player> checkEndState();

    abstract public int playTurn(Player player);

}
