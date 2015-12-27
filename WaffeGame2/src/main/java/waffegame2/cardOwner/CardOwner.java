/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import waffegame2.card.Card;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Walter
 */
public abstract class CardOwner {

    protected List<Card> cards;

    public CardOwner() {
        this.cards = new ArrayList();
    }

    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public boolean addCards(Collection<Card> collection) {
        return cards.addAll(collection);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
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

    public boolean transferCard(CardOwner target) {
        return transferCard(target, getCard());
    }

    public boolean transferCards(CardOwner target) {
        return transferCards(target, getCards());
    }

    public boolean transferCard(CardOwner target, Card card) {
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

    public boolean transferCards(CardOwner target, Collection<Card> collection) {
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

    public List<Card> getCards(int amount) {
        List<Card> list = new ArrayList(cards.subList(0, amount));
        return list;
    }

    public List<Card> getCards() {
        List<Card> list = new ArrayList(cards);
        return list;
    }

    public int cardAmount() {
        return cards.size();
    }

    abstract public String getName();

    public String listCards() {
        String str = "";
        if (cardAmount() == 0) {
            return "<none>";
        }
        for (Card card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }

    @Override
    public String toString() {
        return getName() + ":\n" + listCards();
    }
}
