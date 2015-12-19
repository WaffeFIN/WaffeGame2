/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.ArrayList;
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

    public boolean addCard(Card c) {
        cards.add(c);
        return true;
    }

    public boolean addCard(List<Card> c) {
        List<Card> backup = new ArrayList(cards);
        for (Card card : c) {
            if (!addCard(card)) {
                cards = backup;
                return false;
            }
        }
        return true;
    }

    public Card getCard() {
        return getCard(0);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public Card takeCard() {
        return takeCard(0);
    }

    public Card takeCard(int index) {
        if (index >= cardAmount()) {
            return null;
        }
        Card c = cards.get(index);
        cards.remove(index);
        return c;
    }

    public boolean transferCard(CardOwner target, int index) {
        Card c = takeCard(index);
        if (c == null) {
            return false;
        }
        if (target.addCard(c)) {
            return true;
        } else {
            addCard(c);
            return false;
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int cardAmount() {
        return cards.size();
    }
}
