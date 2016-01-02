/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import waffegame2.ui.UI;

/**
 * The class which holds the data for all variable rules for WaffeGame2
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class GameRulesWaffeGame2 implements GameRules {

    private int players;
    private int startCardsAmount;
    private int maxCardAmount;
    private boolean sharedHandsAreEnabled;
    private boolean mustHitIfAbleTo;

    public GameRulesWaffeGame2() {
        this.players = 2;
        this.startCardsAmount = 10;
        this.maxCardAmount = 10;
        this.sharedHandsAreEnabled = false;
        this.mustHitIfAbleTo = false;
    }

    @Override
    public void setAllOptions(UI ui) {
        return; //:D
    }

    @Override
    public int getPlayers() {
        return players; //:D
    }

    @Override
    public int getStartCardAmount() {
        return startCardsAmount; //:D
    }

    @Override
    public int getMaxCardAmount() {
        return maxCardAmount; //:D
    }

    public boolean areSharedHandsEnabled() {
        return sharedHandsAreEnabled;
    }

    public boolean mustHitIfAbleTo() {
        return mustHitIfAbleTo;
    }

}
