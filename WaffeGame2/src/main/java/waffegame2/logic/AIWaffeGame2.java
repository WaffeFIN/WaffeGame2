
package waffegame2.logic;

import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;

/**
 * The class that does all AI logic for WaffeGame2. Implements CardSelector.
 * The class is yet to be implemented
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class AIWaffeGame2 implements CardSelector{

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        return null; //:D
    }

    @Override
    public void waitToContinue() {
        return; //:D
    }
    
}
