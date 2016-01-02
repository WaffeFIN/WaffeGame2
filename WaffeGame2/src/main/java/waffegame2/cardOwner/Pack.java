/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import waffegame2.card.Value;
import waffegame2.card.Suit;
import waffegame2.card.Card;
import java.util.Collections;

/**
 *
 * @author Walter
 */
public class Pack extends CardOwner {

    private int packs;
    private int jokers;

    public Pack() {
        super();
        this.packs = 0;
        this.jokers = 0;
    }

    public Pack(int packs, int jokers) {
        super();
        this.packs = packs;
        this.jokers = jokers;
        createCards();
    }

    public void createCards() {
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

    @Override
    public String getName() {
        return "Pack";
    }
}
