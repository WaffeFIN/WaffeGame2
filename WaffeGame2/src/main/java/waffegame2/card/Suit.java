/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.card;

/**
 * Enum for suits, including joker.
 *
 * @author      Walter Grönholm
 * @version     1.0
 * @since       2016-01-02
 */
public enum Suit {

    JOKER, HEARTS, DIAMONDS, CLUBS, SPADES;

    @Override
    public String toString() {
        switch (this) {
            case JOKER:
                return "Joker";
            case HEARTS:
                return "Hearts";
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            case SPADES:
                return "Spades";
            default:
                return "Other";
        }
    }
}
