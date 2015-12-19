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
public enum PileType {

    NONE(0), SUIT(1), STRAIGHT(2), PAIRS(3), TRIPLES(4), QUADRUPLES(5);

    private final int type;

    private PileType(int type) {
        this.type = type;
    }

    public int toInt() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case 0:
                return "None";
            case 1:
                return "Suit";
            case 2:
                return "Straight";
            case 3:
                return "Pairs";
            case 4:
                return "Triples";
            case 5:
                return "Quadruples";
            default:
                return "" + toInt();
        }
    }
}
