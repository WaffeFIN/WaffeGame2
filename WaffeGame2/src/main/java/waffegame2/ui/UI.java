/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;

/**
 *
 * @author Walter
 */
public interface UI {

    public void print(String text);

    public void println(String text);
    
    public void printSeparator();

    public String getString();

    public void setPack(Pack pack);

    public void setPile(Pile pile);

}
