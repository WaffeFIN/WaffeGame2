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

/**
 * A text based user interface
 *
 * @author      Walter Grönholm
 * @version     1.0
 * @since       2016-01-02
 */
public class TextBasedUI implements UI {

    private Scanner sc;

    public TextBasedUI() {
        this(new Scanner(System.in));
    }

    public TextBasedUI(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void setPack(Pack pack) {
        //Nothing
    }

    @Override
    public void setPile(Pile pile) {
        //Nothing
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
    public void showSeparator() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------------------------------------------\n");
    }

    @Override
    public String getString() {
        return (sc.nextLine());
    }

    @Override
    public void showWinner(String name) {
        println(name + " is the Winner!\n");
    }

    @Override
    public void showWinners(List<String> names) {
        String str = "";
        for (int i = 0; i < names.size() - 1; i++) {
            str += names.get(i) + ", ";
        }
        println(str + names.get(names.size() - 1) + " are the Winners!\n");
    }

    @Override
    public void showInstructions(String name) {
        println(name + ", select cards (enter the numbers corresponding to the cards, separated by a space. E.g. '1 4 5').\nHit the selected cards by entering no numbers");
    }

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        String cmd = getString();
        if (cmd.equals("")) {
            return null;
        }
        return (getSelection(cmd.split(" "), player, playable));
    }

    private List<Card> getSelection(String[] selections, Player player, List<Hand> playable) {
        List<Card> selection = new ArrayList();
        for (String str : selections) {
            try {
                int n = Integer.parseInt(str);
                int i = 0;
                for (Hand hand : playable) {
                    if (canSeeCards(player, hand) && canUseCards(player, hand)) {
                        for (Card card : hand.getCards()) {
                            if (i == n) {
                                selection.add(card);
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

    private boolean canUseCards(Player player, Hand hand) {
        return (hand.getAccessibility().isUsableByAnyone() || (hand.getPlayer() == player && hand.getAccessibility().isUsableByOwner()));
    }

    private boolean canSeeCards(Player player, Hand hand) {
        return (hand.getAccessibility().isVisibleToEveryone() || (hand.getPlayer() == player && hand.getAccessibility().isVisibleToOwner()));
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
    public void showPreTurn(String str) {
        println(str);
    }

    @Override
    public void waitForPlayerToContinue() {
        getString();
    }
}
