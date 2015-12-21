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
public abstract class AbstractPlayer {

    protected String name;
    protected Hand hand;
    protected Hand sharedHand;

    public AbstractPlayer(String name) {
        this.name = name;
        this.hand = new Hand(10);
        this.sharedHand = new Hand(10);
    }

    public Hand getSharedHand() {
        return sharedHand;
    }

    abstract public void playTurn();
}
