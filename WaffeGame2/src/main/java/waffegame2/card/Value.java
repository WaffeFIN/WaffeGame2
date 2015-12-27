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
public enum Value {

    JOKER(0), ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    private final int value;

    private Value(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }

    public static int max() {
        int max = Integer.MIN_VALUE;
        for (Value value : Value.values()) {
            if (value.toInt() > max) {
                max = value.toInt();
            }
        }
        return max;
    }

    @Override
    public String toString() {
        switch (value) {
            case 0:
                return "Joker";
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return "" + toInt();
        }
    }
}
