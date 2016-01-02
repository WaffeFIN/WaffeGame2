/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import waffegame2.ui.UI;

/**
 *
 * @author Walter
 */
public class DummyRules implements GameRules {

    @Override
    public int getPlayers() {
        return 2;
    }

    @Override
    public int getStartCardAmount() {
        return 5;
    }

    @Override
    public void setAllOptions(UI ui) {
    }

    @Override
    public int getMaxCardAmount() {
        return 5;
    }

}
