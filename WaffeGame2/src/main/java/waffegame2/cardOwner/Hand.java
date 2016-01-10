/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import waffegame2.card.CardComparator;
import waffegame2.card.Card;
import java.util.Collection;
import java.util.Collections;
import waffegame2.player.Player;

/**
 * A CardOwner that is owned by a player, therefore has an accessibility level.
 * Can be limited to be a certain size.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class Hand extends CardOwner {

    private Player player;
    private String name;
    private int maxCardAmount;
    private HandAccessibility acc;

    public Hand(int maxCardAmount) {
        this(null, "", maxCardAmount, HandAccessibility.PRIVATE);
    }

    public Hand(Player player, String name, int maxCardAmount) {
        this(player, name, maxCardAmount, HandAccessibility.PRIVATE);
    }

    public Hand(Player player, String name, int maxCardAmount, HandAccessibility acc) {
        super();
        this.player = player;
        this.name = name;
        this.maxCardAmount = maxCardAmount;
        this.acc = acc;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return name;
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

    /**
     * @return the accessibility level of the hand-object.
     */
    public HandAccessibility getAccessibility() {
        return acc;
    }

    /**
     * Sets the accessibility level of the hand-object.
     */
    public void setAccessibility(HandAccessibility acc) {
        this.acc = acc;
    }

    /**
     * Sorts the cards from lowest value to highest
     */
    public void sort() {
        Collections.sort(cards, new CardComparator());
    }
}
