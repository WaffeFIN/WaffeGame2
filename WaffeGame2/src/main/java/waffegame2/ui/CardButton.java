/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import waffegame2.card.Card;
import static waffegame2.util.Util.getCardSpriteFileName;

/**
 * A JButton that symbolizes a Card object.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-09
 */
public class CardButton extends JButton {

    private Card card;
    private boolean faceUp;
    private boolean cardIsSelected;
    private boolean isOnPile;
    private Icon faceUpIcon;
    private static final Icon faceDownIcon = new ImageIcon("images/cBack.png");

    public CardButton(Card card) {
        super();

        this.card = card;
        this.faceUp = false;
        this.cardIsSelected = false;
        this.isOnPile = false;
        this.faceUpIcon = new ImageIcon(getCardSpriteFileName(card));
        updateIcon();
    }

    /**
     * @return true if the card is face up
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        updateIcon();
    }

    /**
     * @return true if the card has been selected
     */
    public boolean cardIsSelected() {
        return cardIsSelected;
    }

    public void selectCard(boolean selected) {
        this.cardIsSelected = selected;
        updateIcon();
    }

    /**
     * Updates the appearance of the button depending on state
     */
    private void updateIcon() {
        if (isOnPile) {
            super.setBackground(Color.orange);
            super.setIcon(faceUpIcon);
            super.setDisabledIcon(faceUpIcon);
            super.setSelectedIcon(faceUpIcon);
        } else {
            if (faceUp) {
                super.setIcon(faceUpIcon);
            } else {
                super.setIcon(faceDownIcon);
            }
            if (cardIsSelected) {
                super.setBackground(Color.YELLOW);
            } else {
                super.setBackground(Color.WHITE);
            }
        }
    }

    public Card getCard() {
        return card;
    }

    /**
     * Sets the card's isOnPile value to the parametre. If true, the card is
     * orange and disabled, but appears non-grayed.
     *
     * @param b whether the card is in the pile or not
     */
    public void setToPile(boolean b) {
        isOnPile = b;
        updateIcon();
    }

}
