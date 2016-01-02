/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import waffegame2.ui.TextBasedUI;

/**
 *
 * @author Walter
 */
public class GameLogicWaffeGame2Tests {

    private GameLogic logic;

    public GameLogicWaffeGame2Tests() {
    }

    @Before
    public void setUp() {
        logic = new GameLogicWaffeGame2(new TextBasedUI());
    }

    @Test
    public void getRulesWorks() {
        assertEquals(GameRulesWaffeGame2.class, logic.getRules().getClass());
    }
}
