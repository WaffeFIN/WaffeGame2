/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.card;

/**
 *
 * @author Walter
 */
public class Card {

    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;

        if (value == Value.JOKER ^ suit == Suit.JOKER) {
            throw new IllegalArgumentException("Tried creating a Joker card with non-matching suit and value");
        }
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isJoker() {
        return (getSuit() == Suit.JOKER);
    }

    @Override
    public String toString() {
        if (isJoker()) {
            return suit.toString();
        } else {
            return value + " of " + suit;
        }
    }
}
