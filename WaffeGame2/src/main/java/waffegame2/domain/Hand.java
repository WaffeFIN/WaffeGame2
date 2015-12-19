/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.Collections;

/**
 *
 * @author Walter
 */
public class Hand extends CardOwner {

    private int maxCardAmount;
    private boolean cardsAreVisible;

    public Hand(int maxCardAmount) {
        this(maxCardAmount, false);
    }

    public Hand(int maxCardAmount, boolean cardsAreVisible) {
        super();
        this.maxCardAmount = maxCardAmount;
        this.cardsAreVisible = cardsAreVisible;
    }

    @Override
    public boolean addCard(Card c) {
        if (cardAmount() >= maxCardAmount) {
            return false;
        }
        return super.addCard(c);
    }

    public void order() {
        Collections.sort(cards, new CardComparator());
    }
}
