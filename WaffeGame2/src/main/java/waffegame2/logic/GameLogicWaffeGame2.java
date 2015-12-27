/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.*;
import waffegame2.cardOwner.pileRules.PileRuleStandard;
import waffegame2.player.Player;
import waffegame2.ui.UI;

/**
 *
 * @author Walter
 */
public class GameLogicWaffeGame2 extends GameLogic {

    private Pack pack;
    private Pile pile;

    public GameLogicWaffeGame2(UI ui) {
        super(ui);
    }

    @Override
    public void setup() {
        createHands();
        createPack();
        createPile();

        ui.setPack(pack);
        ui.setPile(pile);

        deal();
    }

    private void createPack() {
        pack = new Pack(1, 3);
        pack.shuffle();
    }

    private void createPile() {
        pile = new Pile(new PileRuleStandard());
    }

    private void createHands() {//<-------------------------------- make cleaner
        List<Hand> handList = new ArrayList();
        for (Player player : players) {
            handList.add(new Hand(player.getName() + "'s Shared Hand", 10, true));
        }
        for (Player player : players) {
            player.addHand(new Hand(player.getName() + "'s Private Hand", 10));
            for (Hand hand : handList) {
                if (hand.getName().equals(player.getName() + "'s Shared Hand")) {
                    player.addHand(hand);
                }
            }
            for (Hand hand : handList) {
                if (!hand.getName().equals(player.getName() + "'s Shared Hand")) {
                    player.addHand(hand);
                }
            }
        }
    }

    private void deal() {
        for (Player player : players) {
            pack.transferCards(player.getHand(), pack.getCards(10));
        }
    }

    @Override
    public List<Player> checkEndState() {
        List<Player> winners = new ArrayList();
        for (Player player : players) {
            Hand privateHand = player.getHand();
            Hand sharedHand = player.getHand(1);
            if (privateHand.cardAmount() + sharedHand.cardAmount() == 0) {
                winners.add(player);
            }
        }
        return winners;
    }

    @Override
    public int playTurn(Player player) {
        ui.printSeparator();
        while (true) {
            for (Hand hand : player.getHands()) {
                hand.sort();
            }
            pile.sort();
            Play play = player.selectCards();
            List<Card> cardsPlayed = play.getCards();
            if (cardsPlayed.isEmpty()) {
                ui.println("--- Turn passed ---");
                pile.clear();
                Hand privateHand = player.getHand();
                Hand sharedHand = player.getHand(1);
                privateHand.transferCards(sharedHand);
                dealCardsAfterRound(player, pack);
                return 1;
            }
            if (play.transferCards(pile)) {
                return 1;
            }
            ui.println("--- Invalid selection! You cannot hit the cards you selected! ---");
        }
    }

    @Override
    public Play selectCards(Player player) {//<-------------------------------- make cleaner

        printPreTurn(player);

        Play playable = new Play();
        Play selected = new Play();

        for (Hand hand : player.getHands()) {
            playable.addCards(hand, hand.getCards());
        }

        while (true) {
            printSelectedCards(playable, selected);

            ui.println(player + ", select cards (enter the numbers corresponding to the cards, separated by a space. E.g. '1 4 5').\nHit the selected cards by entering no numbers");
            String cmd = ui.getString();
            if (cmd.equals("")) {
                break;
            }
            toggleSelectedCards(cmd.split(" "), playable, selected);
        }
        return selected;
    }

    private void toggleSelectedCards(String[] split, Play playable, Play selected) {
        for (String str : split) {
            try {
                int n = Integer.parseInt(str);
                if (n >= 0 && n < playable.cardAmount()) {
                    int i = 0;
                    for (CardOwner owner : playable.getCardMap().keySet()) {
                        for (Card card : playable.getCardMap().get(owner)) {
                            if (i == n) {
                                if (selected.getCards().contains(card)) {
                                    selected.removeCard(owner, card);
                                } else {
                                    selected.addCard(owner, card);
                                }
                            }
                            i++;
                        }
                    }
                }
            } catch (NumberFormatException ex) {
            }
        }
    }

    private void printSelectedCards(Play playable, Play selected) {
        int i = 0;

        for (CardOwner owner : playable.getCardMap().keySet()) {
            ui.println(owner.getName());
            if (owner.cardAmount() == 0) {
                ui.println("<<empty>>");
            } else {
                for (Card card : playable.getCardMap().get(owner)) {
                    if (selected.getCards().contains(card)) {
                        ui.println(">>[" + i + "]<< " + card);
                    } else {
                        ui.println("  [" + i + "]   " + card);
                    }
                    i++;
                }
            }
        }
    }

    private void dealCardsAfterRound(Player playerInTurn, Pack pack) {
        Hand privateHand = playerInTurn.getHand();
        Hand sharedHand = playerInTurn.getHand(1);
        while (privateHand.cardAmount() + sharedHand.cardAmount() < 10) {
            if (pack.cardAmount() == 0) {
                break;
            }
            pack.transferCard(playerInTurn.getHand());
        }
    }

    private void printPreTurn(Player player) {
        ui.println(player.getName() + ", it's your turn!"
                + "\nPack size: " + pack.cardAmount()
                + "\nPile: " + pile.getType()
                + "\n" + pile
                + "\nPress <Enter> to continue");
        ui.getString();
    }
}
