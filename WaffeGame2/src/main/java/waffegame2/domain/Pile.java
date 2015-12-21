/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Pile extends AbstractCardOwner {

    private PileType type;
    private AbstractPileRule rule;

    public Pile(AbstractPileRule rule) {
        this.type = PileType.NULL;
        this.rule = rule;
    }

    @Override
    public boolean addCard(Card card) {
        ArrayList<Card> list = new ArrayList();
        list.add(card);
        return (addCards(list));
    }

    @Override
    public boolean addCards(Collection<Card> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        cards.addAll(collection);
        PileType newType = rule.checkType(cards);
        if (newType == PileType.NULL) {
            cards.removeAll(collection);
            return false;
        }
        type = newType;
        return true;
    }

    @Override
    public void clear() {
        super.clear();
        type = rule.checkType(cards);
    }

    public PileType getType() {
        return type;
    }
}
