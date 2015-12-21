/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

/**
 *
 * @author Walter
 */
public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void playTurn() {
        /*
            Select/unselect card
            Hit cards / pass turn (if none selected... should it be its own button?)
        */
        //
    }
}
