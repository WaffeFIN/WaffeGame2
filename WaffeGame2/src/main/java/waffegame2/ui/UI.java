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
 * The User interface - interface
 *
 * @author      Walter Grönholm
 * @version     1.0
 * @since       2016-01-02
 */
public interface UI extends CardSelector {

    //Essential
    public String getString();

    public void waitForPlayerToContinue();
    
    //Extra
    public void print(String text);

    public void println(String text);
    
    public void showSeparator(); //TextUIOnly 

    public void showInstructions(String name);

    public void showWinner(String name);

    public void showWinners(List<String> names);

    public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected);

    public void showPreTurn(String str);

    //GUI stuff below
    public void setPack(Pack pack);

    public void setPile(Pile pile);

}
