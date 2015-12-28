/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.List;
import waffegame2.cardOwner.Play;
import waffegame2.player.Player;
import waffegame2.ui.UI;

/**
 *
 * @author Walter
 */
public class DummyGameLogic extends GameLogic {

    public DummyGameLogic(UI ui) {
        super(ui);
    }

    @Override
    public void setup() {
    }

    @Override
    public int getMaxPlayers() {
        return 2; //:D
    }

    @Override
    public List<Player> checkEndState() {
        return players; //:D
    }

    @Override
    public int playTurn(Player player) {
        return 1; //:D
    }

    @Override
    public Play selectCards(Player player) {
        return new Play(); //:D
    }

}
