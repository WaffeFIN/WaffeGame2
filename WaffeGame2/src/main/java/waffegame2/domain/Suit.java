/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

/**
 *
 * @author Walter
 */
public enum Suit {

    JOKER(0), HEARTS(1), DIAMONDS(2), CLUBS(3), SPADES(4);

    private final int suit;

    private Suit(int suit) {
        this.suit = suit;
    }

    public int toInt() {
        return suit;
    }

    @Override
    public String toString() {
        switch (suit) {
            case 0:
                return "Joker";
            case 1:
                return "Hearts";
            case 2:
                return "Diamonds";
            case 3:
                return "Clubs";
            case 4:
                return "Spades";
            default:
                return "" + toInt();
        }
    }
}
