/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.player;

import waffegame2.logic.CardSelector;
import java.util.ArrayList;
import java.util.List;
import waffegame2.cardOwner.Hand;
import waffegame2.cardOwner.Play;

/**
 *
 * @author Walter
 */
public class Player {

    private String name;
    protected List<Hand> hands;
    private CardSelector selector;

    public Player(String name, CardSelector selector) {
        this.name = name;
        this.hands = new ArrayList();
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hands.get(0);
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public Hand getHand(int index) {
        return hands.get(index);
    }

    public void setHand(int index, Hand hand) {
        hands.set(index, hand);
    }

    public List<Hand> getHands() {
        return hands;
    }

    public int cardAmount() {
        int cardAmount = 0;
        for (Hand hand : hands) {
            cardAmount += hand.cardAmount();
        }
        return cardAmount;
    }

    public Play selectCards() {
        return selector.selectCards(this);
    }

    @Override
    public String toString() {
        return getName();
    }

}
