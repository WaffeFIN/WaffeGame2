/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.player;

import waffegame2.cardOwner.Play;

/**
 *
 * @author Walter
 */
public interface CardSelector {

    public Play selectCards(Player player);
}
