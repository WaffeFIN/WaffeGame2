/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import waffegame2.cardOwner.*;
import waffegame2.player.CardSelector;
import waffegame2.player.Player;
import waffegame2.ui.UI;

/**
 *
 * @author Walter
 */
public abstract class GameLogic implements CardSelector {

    protected UI ui;
    protected List<Player> players;
    protected Pack pack;
    protected Pile pile;

    public GameLogic(UI ui) {
        this.ui = ui;
        this.players = new ArrayList();
    }

    public void addPlayers(Collection<Player> players) {
        this.players.addAll(players);
    }

    abstract public Pile createPile();

    abstract public Pack createPack();

    abstract public void createHands();

    abstract public void deal();

    abstract public Player checkEndState();

    abstract public int playTurn(Player player);

}
