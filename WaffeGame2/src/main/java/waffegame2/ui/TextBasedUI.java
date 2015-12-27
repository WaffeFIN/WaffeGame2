/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.util.Scanner;
import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;

/**
 *
 * @author Walter
 */
public class TextBasedUI implements UI {

    private Scanner sc;
    private Pack pack;
    private Pile pile;

    public TextBasedUI() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    @Override
    public void setPile(Pile pile) {
        this.pile = pile;
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }
    
    @Override
    public void printSeparator() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------------------------------------------\n");
    }

    @Override
    public String getString() {
        return (sc.nextLine());
    }
}
