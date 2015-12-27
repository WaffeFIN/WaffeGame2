/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import waffegame2.card.Card;

/**
 *
 * @author Walter
 */
public class Play {

    private Map<CardOwner, List<Card>> cardMap;

    public Play() {
        this.cardMap = new HashMap();
    }

    public Map<CardOwner, List<Card>> getCardMap() {
        return cardMap;
    }

    public void addCard(CardOwner owner, Card card) {
        if (cardMap.containsKey(owner)) {
            List<Card> cards = cardMap.get(owner);
            cards.add(card);
        } else {
            List<Card> cards = new ArrayList();
            cards.add(card);
            cardMap.put(owner, cards);
        }
    }

    public void addCards(CardOwner owner, Collection<Card> collection) {
        if (cardMap.containsKey(owner)) {
            List<Card> cards = cardMap.get(owner);
            cards.addAll(collection);
        } else {
            List<Card> cards = new ArrayList();
            cards.addAll(collection);
            cardMap.put(owner, cards);
        }
    }

    public boolean removeCard(CardOwner owner, Card card) {
        if (cardMap.containsKey(owner)) {
            List<Card> cards = cardMap.get(owner);
            return cards.remove(card);
        }
        return false;
    }

    public boolean removeCards(CardOwner owner, Collection<Card> collection) {
        if (cardMap.containsKey(owner)) {
            List<Card> cards = cardMap.get(owner);
            if (cards.containsAll(collection)) {
                return cards.removeAll(collection);
            }
        }
        return false;
    }

    public List<Card> getCards() {
        List<Card> cards = new ArrayList();
        for (List<Card> list : cardMap.values()) {
            cards.addAll(list);
        }
        return cards;
    }

    public boolean transferCards(CardOwner target) {
        if (target.addCards(getCards())) {
            for (CardOwner owner : cardMap.keySet()) {
                if (!owner.removeCards(cardMap.get(owner))) {
                    throw new IllegalStateException("Temporary Play object containing non-existant cards");
                }
            }
            return true;
        }
        return false;
    }

    public int cardAmount() {
        return getCards().size();
    }
}
