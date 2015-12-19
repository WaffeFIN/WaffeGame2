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
        switch (type) {
            case NONE:
            case SUIT:
                if (testSuit()) {
                    type = PileType.SUIT;
                    return true;
                }
            case STRAIGHT:
                if (testStraight()) {
                    type = PileType.STRAIGHT;
                    return true;
                }
            case PAIRS:
            case TRIPLES:
            case QUADRUPLES:
                if (testGroup(2)) {
                    type = PileType.PAIRS;
                    return true;
                }
                if (testGroup(3)) {
                    type = PileType.TRIPLES;
                    return true;
                }
                if (testGroup(4)) {
                    type = PileType.QUADRUPLES;
                    return true;
                }
        }
        return false;
    }

    private boolean testSuit() {
        List<Card> list = getNonJokerCardsSorted();
        if (list.isEmpty()) {
            return true;
        }

        Suit suit = list.get(0).getSuit();
        for (Card card : list) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean testStraight() {
        if (cardAmount() >= Value.values().length) {
            return false;
        }

        List<Card> list = getNonJokerCardsSorted();
        if (list.isEmpty()) {
            return true;
        }
        return testStraightMethod(list, 0, false);
    }

    private boolean testStraightMethod(List<Card> list, int index, boolean second) {
        int jokers = countJokers();
        Value previousValue = list.get(index).getValue();
        int i = index;
        while (true) {
            i++;
            if (i >= list.size()) {
                i = 0;
            }
            if (i == index) {
                return true;
            }

            Card card = list.get(i);
            Value value = card.getValue();
            if (previousValue.toInt() != value.toInt() - 1) {
                int jokersNeeded = valueDifference(previousValue, value) - 1;
                if (jokersNeeded < 0) {
                    return false;
                }
                if (jokers >= jokersNeeded) {
                    jokers -= jokersNeeded;
                } else {
                    if (second) {
                        return false;
                    } else {
                        return testStraightMethod(list, i, true);
                    }
                }
            }
            previousValue = value;
        }
    }

    private int valueDifference(Value value1, Value value2) {
        int max = Value.max();
        int difference = (value1.toInt() - value2.toInt() + max) % max;
        if (difference > (double) max / 2) {
            difference = max - difference;
        }
        return difference;
    }

    private boolean testGroup(int groupSize) {
        if (cardAmount() % groupSize != 0) {
            return false;
        }

        List<Card> list = getNonJokerCardsSorted();
        if (list.isEmpty()) {
            return true;
        }
        int jokers = countJokers();

        int missing = 0;
        Value previousValue = Value.JOKER;
        for (Card card : list) {
            if (card.getValue() != previousValue) {
                int group = countValue(card.getValue());
                if (group > groupSize) {
                    return false;
                }
                missing += groupSize - group;
                previousValue = card.getValue();
            }
        }
        if (missing > jokers){
            return false;
        }
        jokers -= missing;
        return (jokers % groupSize == 0);
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

    private List<Card> getNonJokerCardsSorted() {
        List<Card> list = new ArrayList();
        for (Card card : cards) {
            if (!card.isJoker()) {
                list.add(card);
            }
        }
        Collections.sort(list, new CardComparator());
        return list;
    }

}
