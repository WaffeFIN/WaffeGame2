/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import waffegame2.card.Card;
import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;
import waffegame2.cardOwner.CardCollection;
import waffegame2.cardOwner.Hand;
import waffegame2.player.Player;
import static waffegame2.util.Util.canSeeCards;
import static waffegame2.util.Util.canUseCards;

/**
 * A text based user interface
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class TextBasedUI extends UI {

    private Pile pile;
    private Scanner sc;

    public TextBasedUI() {
        this(new Scanner(System.in));
    }

    public TextBasedUI(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void setup() {
        //None
    }

    @Override
    public void setPack(Pack pack) {
        //Nothing
    }

    @Override
    public void setPile(Pile pile) {
        this.pile = pile;
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void showTurnSeparator() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------------------------------------------\n");
    }

    @Override
    public String getString() {
        return (sc.nextLine());
    }

    @Override
    public void showWinner(Player player) {
        println(player.getName() + " is the Winner!\n");
    }

    @Override
    public void showWinners(List<Player> players) {
        String str = "";
        for (int i = 0; i < players.size() - 1; i++) {
            str += players.get(i).getName() + ", ";
        }
        println(str + players.get(players.size() - 1) + " are the Winners!\n");
    }

    @Override
    public void showInstructionsToPlayer(Player player) {
        println(player.getName() + ", select cards (enter the numbers corresponding to the cards, separated by a space. E.g. '1 4 5').\nHit the selected cards by entering no numbers");
    }

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        String input = getString();
        return (getSelection(input.split(" "), player, playable));
    }

    /**
     * Returns a list of cards from the selections made.
     * @param selections the selections in a form of an array of strings. The strings themselves should be sole integers
     * @param player the player who is selecting the cards
     * @param playable list of all hands that can be used to make the selection
     * @return the selection, a list of cards
     */
    private List<Card> getSelection(String[] selections, Player player, List<Hand> playable) {
        List<Card> selection = new ArrayList();
        for (String str : selections) {
            try {
                int n = Integer.parseInt(str);
                int i = 0;
                handLoop:
                for (Hand hand : playable) {
                    if (canSeeCards(player, hand) && canUseCards(player, hand)) {
                        for (Card card : hand.getCards()) {
                            if (i == n) {
                                selection.add(card);
                                break handLoop;
                            }
                            i++;
                        }
                    }
                }
            } catch (NumberFormatException ex) {
            }
        }
        return selection;
    }

    @Override
    public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected) {
        int i = 0;
        for (Hand hand : playable) {
            if (canSeeCards(player, hand)) {
                println(hand.getName());
                if (hand.cardAmount() == 0) {
                    println("<<empty>>");
                } else {
                    for (Card card : hand.getCards()) {
                        if (canUseCards(player, hand)) {
                            if (selected.getCards().contains(card)) {
                                println(">>[" + i + "]<< " + card);
                            } else {
                                println("  [" + i + "]   " + card);
                            }
                            i++;
                        } else {
                            println("        " + card);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void beforeTurn(Player player, String str) {
        println(str
                + "\n" + pile
                + "\nPress <Enter> to continue");
    }

    @Override
    public void inTurn() {
    }

    @Override
    public void afterTurn() {
        return; //:D
    }

    @Override
    public void turnPassed() {
        println("--- Turn passed ---");
    }

    @Override
    public void waitToContinue() {
        getString();
    }

    @Override
    public void run() {
    }

    @Override
    public boolean showOptionsBox() {
        return false;
    }
}
