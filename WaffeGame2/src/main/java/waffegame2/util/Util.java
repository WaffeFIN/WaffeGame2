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

    /**
     * @param x
     * @return the square of the number x
     */
    public static double sqr(double x) {
        return x * x;
    }

    /**
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return the distance (squared) from (x1,y1) to (x2,y2)
     */
    public static double distanceSquared(double x1, double y1, double x2, double y2) {
        return sqr(x1 - x2) + sqr(y1 - y2);
    }

    /**
     * @param player the player to check
     * @param hand the hand to check
     * @return true if the player can use the cards in the hand
     */
    public static boolean canUseCards(Player player, Hand hand) {
        return (hand.getAccessibility().isUsableByAnyone() || (hand.getPlayer() == player && hand.getAccessibility().isUsableByOwner()));
    }

    /**
     * @param player the player to check
     * @param hand the hand to check
     * @return true if the player can see the cards in the hand
     */
    public static boolean canSeeCards(Player player, Hand hand) {
        return (hand.getAccessibility().isVisibleToEveryone() || (hand.getPlayer() == player && hand.getAccessibility().isVisibleToOwner()));
    }

    /**
     * @param str the string to check
     * @return true if str is an integer
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Generates the filepath needed for a specific card icon
     * @param card the card to illustrate
     * @return the filepath for the image
     */
    public static String getCardSpriteFileName(Card card) {
        String valueChars = card.getValue().toString();
        if (!isInteger(valueChars)) {
            valueChars = "" + valueChars.charAt(0);
        }
        char suitChar = card.getSuit().toString().charAt(0);
        return "images/c" + valueChars + suitChar + ".png";
    }
}
