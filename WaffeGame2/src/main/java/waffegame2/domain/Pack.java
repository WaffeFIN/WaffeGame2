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
public class Pack extends CardOwner {

    public Pack() {
        super();
    }

    public Pack(int jokers, int packs) {
        super();
        createCards(jokers, packs);
    }

    private void createCards(int jokers, int packs) {
        for (int i = 0; i < packs; i++) {
            for (int j = 0; j < jokers; j++) {
                addCard(new Card(Value.JOKER, Suit.JOKER));
            }
            for (Suit suit : Suit.values()) {
                if (suit != Suit.JOKER) {
                    for (Value value : Value.values()) {
                        if (value != Value.JOKER) {
                            addCard(new Card(value, suit));
                        }
                    }
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
