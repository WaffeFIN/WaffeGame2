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

    private int startCardsAmount;
    private int maxCardAmount;
    private int packs;
    private boolean sharedHandsAreEnabled;
    private boolean mustHitIfAbleTo;
    
    private static final int minPlayers = 2;
    private static final int maxPlayers = 2;

    public GameRulesWaffeGame2() { //DO NOT CHANGE CONSTRUCTOR VALUES
        this.startCardsAmount = 10;
        this.maxCardAmount = 10;
        this.packs = 1;
        this.sharedHandsAreEnabled = false;
        this.mustHitIfAbleTo = false;
    }

    @Override
    public void setAllOptions(UI ui) {
        return; //:D
    }

    public int getStartCardAmount() {
        return startCardsAmount; //:D
    }

    public int getMaxCardAmount() {
        return maxCardAmount; //:D
    }

    public boolean areSharedHandsEnabled() {
        return sharedHandsAreEnabled;
    }

    public boolean mustHitIfAbleTo() {
        return mustHitIfAbleTo;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

}
