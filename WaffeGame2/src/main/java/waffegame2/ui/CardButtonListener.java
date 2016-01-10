/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.awt.event.ActionEvent;
import waffegame2.card.Card;

/**
 * An ActionListener to a CardButton.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-10
 */
public class CardButtonListener implements java.awt.event.ActionListener {

    private Card card;
    private CardButton cardButton;
    private GameWindow gameWindow;

    public CardButtonListener(GameWindow gameWindow, Card card, CardButton cardButton) {
        this.card = card;
        this.cardButton = cardButton;
        this.gameWindow = gameWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWindow.cardButtonHitActionPerformed(card, e);
    }

}
