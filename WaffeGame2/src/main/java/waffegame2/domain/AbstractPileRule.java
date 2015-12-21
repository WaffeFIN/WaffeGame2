/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.List;

/**
 *
 * @author Walter
 */
public abstract class AbstractPileRule {

    protected List<Card> cards;

    public AbstractPileRule() {
        this.cards = null;
    }

    abstract public PileType checkType(List<Card> list);
}
