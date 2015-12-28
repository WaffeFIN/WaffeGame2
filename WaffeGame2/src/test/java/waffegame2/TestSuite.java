package waffegame2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Walter
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    waffegame2.card.CardTests.class,
    waffegame2.cardOwner.CardOwnerTests.class,
    waffegame2.cardOwner.HandTests.class,
    waffegame2.cardOwner.PackTests.class,
    waffegame2.cardOwner.PileTypeTests.class,
    waffegame2.cardOwner.PlayTests.class,
    waffegame2.logic.GameLogicWaffeGame2Tests.class,
    waffegame2.logic.GameTests.class,
    waffegame2.player.PlayerTests.class
})
public class TestSuite {

}
