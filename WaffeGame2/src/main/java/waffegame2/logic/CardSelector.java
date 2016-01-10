/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;

/**
 * Interface for a card selector e.g. the UI or the AI
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public interface CardSelector {

    public void waitToContinue();

    public List<Card> selectCards(Player player, List<Hand> playable);
}
