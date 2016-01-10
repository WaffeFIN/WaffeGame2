/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.*;
import waffegame2.cardOwner.pileRules.PileRuleWaffeGame2;
import waffegame2.player.Player;
import waffegame2.ui.UI;

/**
 * The main logic to the WaffeGame2
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class GameLogicWaffeGame2 extends GameLogic {

    private Pack pack;
    private Pile pile;
    private GameRulesWaffeGame2 rules;

    public GameLogicWaffeGame2(UI ui) {
        super(ui);
        this.rules = new GameRulesWaffeGame2();
    }

    //for tests
    public Pack getPack() {
        return pack;
    }

    //for tests
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    //for tests
    public Pile getPile() {
        return pile;
    }

    //for tests
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String getGameName() {
        return "WaffeGame2";
    }

    @Override
    public GameRules getRules() {
        return rules;
    }

    @Override
    public void init() {
        createHands();
        createPack();
        createPile();

        ui.setPack(pack);
        ui.setPile(pile);
    }

    @Override
    public void setup() {
        for (Player player : players) {
            for (Hand hand : player.getHands()) {
                hand.clear();
            }
        }
        pile.clear();

        pack.clear();
        pack.createCards();
        pack.shuffle();

        deal();
    }

    private void createPack() {
        pack = new Pack(1, 3);
    }

    private void createPile() {
        pile = new Pile(new PileRuleWaffeGame2());
    }

    private void createHands() {//<-------------------------------- make cleaner
        List<Hand> handList = new ArrayList();
        for (Player player : players) {
            HandAccessibility type = HandAccessibility.VISIBLE;
            if (rules.areSharedHandsEnabled()) {
                type = HandAccessibility.PUBLIC;
            }
            handList.add(new Hand(player, player.getName() + "'s Shared Hand", rules.getMaxCardAmount(), type));
        }
        for (Player player : players) {
            player.addHand(new Hand(player, player.getName() + "'s Private Hand", rules.getMaxCardAmount()));
            addHands(player, handList, true);
            addHands(player, handList, false);
        }
    }

    private void addHands(Player player, List<Hand> handList, boolean ownHand) {
        for (Hand hand : handList) {
            if (hand.getName().equals(player.getName() + "'s Shared Hand") == ownHand) {
                player.addHand(hand);
            }
        }
    }

    private void deal() {
        int cardAmount = rules.getStartCardAmount();
        while (players.size() * cardAmount > pack.cardAmount()) {
            cardAmount--;
        }
        for (Player player : players) {
            pack.transferCards(player.getHand(), pack.getCards(cardAmount));
        }
    }

    @Override
    public void playGame() {
        for (int turn = 0; true;) {
            ui.showTurnSeparator();
            if (checkEndState()) {
                break;
            }
            Player playerInTurn = players.get(turn % players.size());
            playTurn(playerInTurn);
            turn++;
        }
    }

    private boolean checkEndState() {
        List<Player> winners = getWinners();
        if (!winners.isEmpty()) {
            if (winners.size() == 1) {
                ui.showWinner(winners.get(0));
            } else {
                ui.showWinners(winners);
            }
            return true;
        }
        return false;
    }

    private List<Player> getWinners() {
        List<Player> winners = new ArrayList();
        for (Player player : players) {
            boolean allHandsAreEmpty = true;
            for (Hand hand : player.getHands()) {
                if (hand.getPlayer() == player && hand.cardAmount() > 0) {
                    allHandsAreEmpty = false;
                    break;
                }
            }
            if (allHandsAreEmpty) {
                winners.add(player);
            }
        }
        return winners;
    }

    private void playTurn(Player player) {
        ui.beforeTurn(player, player.getName() + ", it's your turn!"
                + "\nPack size: " + pack.cardAmount()
                + "\nPile: " + pile.getType()
                + "\n" + pile);
        player.waitToContinue();
        while (true) {
            ui.inTurn();
            for (Hand hand : player.getHands()) {
                hand.sort();
            }
            pile.sort();

            CardCollection play = getPlay(player);
            Collection<Card> cardsPlayed = play.getCards();
            if (cardsPlayed.isEmpty()) {
                if (!rules.mustHitIfAbleTo() || isAbleToHit(player, pile)) {
                    turnPassed(player);
                    break;
                } else {
                    ui.println("--- You must hit a visible card if you are able to! ---");
                }
            }
            if (play.transferCards(pile)) {
                break;
            }
            ui.println("--- Invalid selection! You cannot hit the cards you selected! ---");
        }
        ui.afterTurn();
    }

    private CardCollection getPlay(Player player) {

        CardCollection selected = new CardCollection();
        List<Hand> handList = new ArrayList(player.getHands());

        while (true) {
            ui.showSelectedCards(player, handList, selected);
            ui.showInstructionsToPlayer(player);
            List<Card> selection = player.selectCards(handList);
            if (selection == null) { //pass
                return new CardCollection();
            }
            if (selection.isEmpty()) { //hit
                break;
            }
            toggleSelectedCards(selection, handList, selected);
        }
        return selected;
    }

    private void toggleSelectedCards(List<Card> selection, List<Hand> handList, CardCollection selected) {
        for (Hand hand : handList) {
            for (Card card : hand.getCards()) {
                if (selection.contains(card)) {
                    if (selected.getCards().contains(card)) {
                        selected.removeCard(hand, card);
                    } else {
                        selected.addCardFrom(hand, card);
                    }
                }
            }
        }
    }

    private void dealCardsAfterRound(Player playerInTurn, Pack pack) {
        Hand privateHand = playerInTurn.getHand();
        Hand sharedHand = playerInTurn.getHand(1);
        while (privateHand.cardAmount() + sharedHand.cardAmount() < rules.getMaxCardAmount()) {
            if (pack.cardAmount() == 0) {
                break;
            }
            pack.transferCard(playerInTurn.getHand());
        }
    }

    private boolean isAbleToHit(Player player, Pile pile) {
        return true;
    }

    private void turnPassed(Player player) {
        ui.println("--- Turn passed ---");
        pile.clear();
        Hand privateHand = player.getHand();
        Hand sharedHand = player.getHand(1);
        privateHand.transferCards(sharedHand);
        dealCardsAfterRound(player, pack);
    }

}
