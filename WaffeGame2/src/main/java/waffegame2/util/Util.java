/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.util;

import waffegame2.card.Card;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;

/**
 * Utility methods
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-09
 */
public class Util {

    public static double sqr(double x) {
        return x * x;
    }

    public static double distanceSquared(double x1, double y1, double x2, double y2) {
        return sqr(x1 - x2) + sqr(y1 - y2);
    }

    public static boolean canUseCards(Player player, Hand hand) {
        return (hand.getAccessibility().isUsableByAnyone() || (hand.getPlayer() == player && hand.getAccessibility().isUsableByOwner()));
    }

    public static boolean canSeeCards(Player player, Hand hand) {
        return (hand.getAccessibility().isVisibleToEveryone() || (hand.getPlayer() == player && hand.getAccessibility().isVisibleToOwner()));
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static String getCardSpriteFileName(Card card) {
        String valueChars = card.getValue().toString();
        if (!isInteger(valueChars)) {
            valueChars = "" + valueChars.charAt(0);
        }
        char suitChar = card.getSuit().toString().charAt(0);
        return "images/c" + valueChars + suitChar + ".png";
    }
}
