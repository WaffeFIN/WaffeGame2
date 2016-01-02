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
public interface GameRules {

    public int getPlayers();

    public int getStartCardAmount();

    public int getMaxCardAmount();

    public void setAllOptions(UI ui);
}
