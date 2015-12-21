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
public class PileRuleStandard extends AbstractPileRule {

    @Override
    public PileType checkType(List<Card> list) {
        this.cards = list;
        if (cards.isEmpty()) {
            return PileType.NULL;
        }
        if (testSuit()) {
            return PileType.SUIT;
        }
        if (testStraight()) {
            return PileType.STRAIGHT;
        }
        if (testGroup(2)) {
            return PileType.PAIRS;
        }
        if (testGroup(3)) {
            return PileType.TRIPLES;
        }
        if (testGroup(4)) {
            return PileType.QUADRUPLES;
        }
        return PileType.NULL;
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
        if (cards.size() > Value.max()) {
            return false;
        }
        List<Card> list = getNonJokerCardsSorted();
        if (list.isEmpty()) {
            return true;
        }

        for (int startIndex = 0; startIndex < list.size(); startIndex++) {
            switch (testStraightMethod(list, startIndex)) {
                case 1:
                    return true;
                case -1:
                    return false;
            }
        }
        return false;
    }

    private int testStraightMethod(List<Card> list, int startIndex) {
        int jokers = countJokers();
        Value previousValue = list.get(startIndex).getValue();
        int i = startIndex;
        while (true) {
            i++;
            if (i >= list.size()) {
                i = 0;
            }
            if (i == startIndex) {
                return 1;
            }

            Card card = list.get(i);
            Value value = card.getValue();
            if (previousValue.toInt() != value.toInt() - 1) {
                int jokersNeeded = valueDifference(previousValue, value) - 1;
                if (jokersNeeded < 0) {
                    return -1;
                }
                if (jokers < jokersNeeded) {
                    return 0;
                }
                jokers -= jokersNeeded;
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
        if (cards.size() % groupSize != 0) {
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
        if (missing > jokers) {
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
