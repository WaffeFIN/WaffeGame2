/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;

/**
 *
 * @author Walter
 */
public class DummySelector implements CardSelector {

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        List<Card> cards = new ArrayList();
        for (Hand hand : playable) {
            cards.addAll(hand.getCards());
        }
        return cards; //echo
    }

}
