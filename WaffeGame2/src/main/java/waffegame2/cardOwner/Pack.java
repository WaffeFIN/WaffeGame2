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

    public Pack() {
        super();
    }

    public Pack(int packs, int jokers) {
        super();
        createCards(packs, jokers);
    }

    private void createCards(int packs, int jokers) {
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
