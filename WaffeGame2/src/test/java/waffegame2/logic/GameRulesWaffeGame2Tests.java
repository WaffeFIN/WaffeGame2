/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Walter
 */
public class GameRulesWaffeGame2Tests {

    private GameRulesWaffeGame2 rules;

    public GameRulesWaffeGame2Tests() {
    }

    @Before
    public void setUp() {
        rules = new GameRulesWaffeGame2();
    }

    @Test
    public void testImplementedMethodsDefault() {
        assertEquals(2, rules.getMinPlayers());
        assertEquals(2, rules.getMaxPlayers());
        assertEquals(10, rules.getStartCardAmount());
        assertEquals(10, rules.getMaxCardAmount());
    }

    @Test
    public void testOtherMethodsDefault() {
        assertEquals(false, rules.areSharedHandsEnabled());
        assertEquals(false, rules.mustHitIfAbleTo());
    }
}
