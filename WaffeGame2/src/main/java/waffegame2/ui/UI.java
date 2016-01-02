/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.util.List;
import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;
import waffegame2.cardOwner.CardCollection;
import waffegame2.cardOwner.Hand;
import waffegame2.logic.CardSelector;
import waffegame2.player.Player;

/**
 *
 * @author Walter
 */
public interface UI extends CardSelector {

    public void print(String text);

    public void println(String text);

    public String getString();

    public void printSeparator(); //TextUI 

    public void waitForPlayerToContinue();

    public void showInstructions(String name);

    public void showWinner(String name);

    public void showWinners(List<String> names);

    public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected);

    public void showPreTurn(String str);

    //GUI stuff below
    public void setPack(Pack pack);

    public void setPile(Pile pile);

}
