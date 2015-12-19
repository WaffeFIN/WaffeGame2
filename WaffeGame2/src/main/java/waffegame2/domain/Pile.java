/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Walter
 */
public class Pile extends CardOwner {

    private PileType type;

    public Pile() {
        this.type = PileType.NONE;
    }

    public void discardCards() {
        cards.clear();
    }

    public PileType getType() {
        return type;
    }

    @Override
    public boolean addCard(Card c) {
        ArrayList<Card> list = new ArrayList();
        list.add(c);
        return (addCard(list));
    }

    @Override
    public boolean addCard(List<Card> c) {
        if (c.isEmpty()) {
            return false;
        }
        List<Card> backup = new ArrayList(cards);
        for (Card card : c) {
            cards.add(card);
        }
        if (isValid()) {
            return true;
        }
        cards = backup;
        return false;
    }

    private boolean isValid() {
        PileType minType = PileType.NONE;
        switch (type) {
            case NONE:
            case SUIT:
                if (testSuit()) {
                    minType = PileType.SUIT;
                    break;
                }
            case STRAIGHT:
                if (testStraight()) {
                    minType = PileType.STRAIGHT;
                    break;
                }
            case PAIRS:
                if (testGroup(2)) {
                    minType = PileType.PAIRS;
                    break;
                }
            case TRIPLES:
                if (testGroup(3)) {
                    minType = PileType.TRIPLES;
                    break;
                }
            case QUADRUPLES:
                if (testGroup(4)) {
                    minType = PileType.QUADRUPLES;
                    break;
                }
        }
        if (minType != PileType.NONE) {
            type = minType;
            return true;
        }
        return false;
    }

    private boolean testSuit() {
        if (cardAmount() == 0) {
            return false;
        }
        Suit suit = Suit.JOKER;
        for (Card card : cards) {
            if (suit == Suit.JOKER) {
                if (card.getSuit() != Suit.JOKER) {
                    suit = card.getSuit();
                }
            } else if (!card.getSuit().equals(Suit.JOKER) && !card.getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    private boolean testStraight() {
        if (cardAmount() == 0 || cardAmount() >= 14) {
            return false;
        }
        List<Card> list = new ArrayList(cards);
        Collections.sort(list, new CardComparator());

        for (int startIndex = 0; startIndex < cardAmount(); startIndex++) {
            Value previousValue = Value.JOKER;
            int jokers = countJokers();
            for (int currentIndex = 0; currentIndex < cardAmount(); currentIndex++) {
                int cardIndex = (currentIndex + startIndex) % cardAmount();
                Card card = list.get(cardIndex);
                if (card.getSuit() != Suit.JOKER) {
                    if (card.getValue().toInt() == (previousValue.toInt()) % (Value.values().length - 1) + 1 || previousValue == Value.JOKER) {
                        previousValue = card.getValue();
                    } else {
                        int difference = (card.getValue().toInt() - previousValue.toInt() + Value.values().length - 1) % (Value.values().length - 1) - 1;
                        if (jokers > difference) {
                            jokers -= difference;
                        } else {
                            break;
                        }
                    }
                }
                if (currentIndex == cardAmount() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean testGroup(int groupSize) {
        if (cardAmount() == 0 || cardAmount() % groupSize != 0) {
            return false;
        }
        List<Card> list = new ArrayList(cards);
        Collections.sort(list, new CardComparator());

        int missing = 0;

        for (Card card : list) {
            if (card.getSuit() != Suit.JOKER) {
                int group = countValue(card.getValue());
                if (group > groupSize) {
                    return false;
                }
                missing += groupSize - group;
            }
        }

        return (missing == countJokers());
    }

    private int countJokers() {
        return countValue(Value.JOKER);
    }

    private int countValue(Value value) {
        int n = 0;
        for (Card card : cards) {
            if (card.getValue() == value) {
                n++;
            }
        }
        return n;
    }
}
