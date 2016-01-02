/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

/**
 * Enum for the type of the pile.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public enum PileType {

    NULL, SUIT, STRAIGHT, PAIRS, TRIPLES, QUADRUPLES;

    @Override
    public String toString() {
        switch (this) {
            case NULL:
                return "Empty";
            case SUIT:
                return "Suit";
            case STRAIGHT:
                return "Straight";
            case PAIRS:
                return "Pairs";
            case TRIPLES:
                return "Triples";
            case QUADRUPLES:
                return "Quadruples";
            default:
                return "Other";
        }
    }
}
