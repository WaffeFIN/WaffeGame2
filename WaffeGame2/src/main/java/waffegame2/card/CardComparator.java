package waffegame2.card;

/**
 *
 * @author Walter
 */
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return (o1.getValue().toInt()) - (o2.getValue().toInt());
    }
}
