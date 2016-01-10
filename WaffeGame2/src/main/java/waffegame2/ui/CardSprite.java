/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import waffegame2.card.Card;
import static waffegame2.util.Util.distanceSquared;
import static waffegame2.util.Util.getCardSpriteFileName;
import static waffegame2.util.Util.sqr;

/**
 * A JButton that symbolizes a Card object.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-09
 */
public class CardSprite extends JButton {

    private double preciseX;
    private double preciseY;
    private Card card;
    private boolean faceUp;
    private boolean cardIsSelected;
    private Icon faceUpIcon;
    private static final Icon faceDownIcon = new ImageIcon("cBack.png");

    public CardSprite(Card card, int x, int y) {
        super();
        super.setLocation(x, y);
        
        this.preciseX = x;
        this.preciseY = y;
        
        this.card = card;
        this.faceUp = false;
        this.cardIsSelected = false;
        this.faceUpIcon = new ImageIcon(getCardSpriteFileName(card));
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.preciseX = x;
        this.preciseY = y;
    }

    public void stepTowards(int x, int y, int speed) {
        if (Math.abs(this.preciseX - x) < 0.01 && Math.abs(this.preciseY - y) < 0.01) {
            return;
        }
        double distanceSquared = distanceSquared(this.preciseX, this.preciseY, x, y);
        this.preciseX += speed * sqr(this.preciseX - x) / distanceSquared;
        this.preciseY += speed * sqr(this.preciseY - y) / distanceSquared;
        super.setLocation((int) this.preciseX, (int) this.preciseY);
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        updateIcon();
    }

    public boolean cardIsSelected() {
        return cardIsSelected;
    }

    public void selectCard(boolean selected) {
        this.cardIsSelected = selected;
        updateIcon();
    }

    private void updateIcon() {
        if (faceUp) {
            super.setIcon(faceUpIcon);
        } else {
            super.setIcon(faceDownIcon);
        }
        if (cardIsSelected) {
            
        } else {
            
        }
    }

    public Card getCard() {
        return card;
    }

}
