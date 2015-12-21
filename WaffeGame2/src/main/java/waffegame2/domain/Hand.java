/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Walter
 */
public class Hand extends AbstractCardOwner {

    private int maxCardAmount;
    private boolean visible;

    public Hand(int maxCardAmount) {
        this(maxCardAmount, false);
    }

    public Hand(int maxCardAmount, boolean visible) {
        super();
        this.maxCardAmount = maxCardAmount;
        this.visible = visible;
    }

    @Override
    public boolean addCard(Card card) {
        if (maxCardAmount <= 0 || cardAmount() < maxCardAmount) {
            return super.addCard(card);
        }
        return false;
    }

    @Override
    public boolean addCards(Collection<Card> collection) {
        if (maxCardAmount <= 0 || cardAmount() + collection.size() - 1 < maxCardAmount) {
            return super.addCards(collection);
        }
        return false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public void order() {
        Collections.sort(cards, new CardComparator());
    }
}
