package waffegame2.card;

/**
 * Used to compare and sort cards. Compares only the cards' values
 * 
 * @author      Walter Grönholm
 * @version     1.0
 * @since       2016-01-02
 */
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return (o1.getValue().toInt()) - (o2.getValue().toInt());
    }
}
