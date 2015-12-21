/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Walter
 */
public abstract class AbstractCardOwner {

    protected List<Card> cards;

    public AbstractCardOwner() {
        this.cards = new ArrayList();
    }

    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public boolean addCards(Collection<Card> collection) {
        return cards.addAll(collection);
    }

    public boolean removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
            return true;
        }
        return false;
    }

    public boolean removeCards(Collection<Card> collection) {
        if (cards.containsAll(collection)) {
            cards.removeAll(collection);
            return true;
        }
        return false;
    }

    public void clear() {
        cards.clear();
    }

    public boolean transferCard(AbstractCardOwner target, Card card) {
        if (!removeCard(card)) {
            return false;
        }
        if (target.addCard(card)) {
            return true;
        } else {
            addCard(card);
            return false;
        }
    }

    public boolean transferCards(AbstractCardOwner target, Collection<Card> collection) {
        if (!removeCards(collection)) {
            return false;
        }
        if (target.addCards(collection)) {
            return true;
        } else {
            addCards(collection);
            return false;
        }
    }

    public Card getCard() {
        return getCard(0);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int cardAmount() {
        return cards.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (Card card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }

}
