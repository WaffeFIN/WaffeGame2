package waffegame2.domain;

/**
 *
 * @author Walter
 */
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return ((o1.getValue().toInt() * Suit.values().length + o1.getSuit().toInt()) 
              - (o2.getValue().toInt() * Suit.values().length + o2.getSuit().toInt()));
    }
}
