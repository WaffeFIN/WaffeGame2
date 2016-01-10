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

    /**
     * @return the Pack created and used by the logic (only used in tests)
     */
    public Pack getPack() {
        return pack;
    }

    /**
     * Manually set the pack to be used inGame to something else (only used in
     * tests)
     *
     * @param pack
     */
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    /**
     * @return the Pack created and used by the logic
     */
    public Pile getPile() {
        return pile;
    }

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

    /**
     * Creates a new pack. Note that the pack doesn't create cards yet.
     */
    private void createPack() {
        pack = new Pack(1, 3);
    }

    /**
     * Creates a new Pile using the PileRule of WaffeGame2
     */
    private void createPile() {
        pile = new Pile(new PileRuleWaffeGame2());
    }

    /**
     * Creates the hands for each player, in the following order: index 0 is the
     * player's private hand, index 1 is the player's public/shared hand and
     * index 2 onwards are the other players' hands.
     */
    private void createHands() {
        List<Hand> handList = createNonPrivateHands();
        for (Player player : players) {
            player.addHand(new Hand(player, player.getName() + "'s Private Hand", rules.getMaxCardAmount()));
            addHands(player, handList, true);
            addHands(player, handList, false);
        }
    }

    /**
     * Creates and returns the non-private hands of each player.
     *
     * @return list of public/shared hands
     */
    private List<Hand> createNonPrivateHands() {
        List<Hand> handList = new ArrayList();
        for (Player player : players) {
            HandAccessibility type = HandAccessibility.VISIBLE;
            if (rules.areSharedHandsEnabled()) {
                type = HandAccessibility.PUBLIC;
            }
            handList.add(new Hand(player, player.getName() + "'s Shared Hand", rules.getMaxCardAmount(), type));
        }
        return handList;
    }

    /**
     * Adds the specified hand to the player.
     *
     * @param player player to which the hand is added to
     * @param handList list of all public/shared hands
     * @param ownHand whether to add the player's own hand or the other players'
     * hands.
     */
    private void addHands(Player player, List<Hand> handList, boolean ownHand) {
        for (Hand hand : handList) {
            if (hand.getName().equals(player.getName() + "'s Shared Hand") == ownHand) {
                player.addHand(hand);
            }
        }
    }

    /**
     * Deals cards to all players
     */
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

    /**
     * Checks all players for winners/a winner.
     *
     * @return true if the game ended
     */
    private boolean checkEndState() {
        List<Player> winners = getWinners();
        if (!winners.isEmpty()) {
            displayWinners(winners);
            return true;
        }
        return false;
    }

    /**
     * Tells the UI to display the winner/winners
     *
     * @param winners The players to be displayed
     */
    private void displayWinners(List<Player> winners) {
        if (winners.size() == 1) {
            ui.showWinner(winners.get(0));
        } else {
            ui.showWinners(winners);
        }
    }

    /**
     * Finds all players that have "won" the game. In WaffeGame2's case, all
     * players who have no cards left in their hands.
     *
     * @return a list of players with no cards left in their hands.
     */
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

    /**
     * Gives the turn to the player.
     *
     * @param player the player to give the turn to
     */
    private void playTurn(Player player) {
        ui.beforeTurn(player, player.getName() + ", it's your turn!"
                + "\nPack size: " + pack.cardAmount()
                + "\nPile: " + pile.getType());
        player.waitToContinue();
        while (true) {
            if (playedLegalTurn(player)) {
                break;
            }
        }
        ui.afterTurn();
    }

    /**
     * Gets the play of the player and checks whether it is legal.
     *
     * @param player the player whose turn it is
     * @return true if the player made a proper play for their turn.
     */
    private boolean playedLegalTurn(Player player) {
        ui.inTurn();
        for (Hand hand : player.getHands()) {
            hand.sort();
        }
        pile.sort();

        CardCollection play = getPlay(player);
        Collection<Card> cardsPlayed = play.getCards();
        if (cardsPlayed.isEmpty()) {
            if (!rules.mustHitIfAbleTo() || isAbleToHit(player)) {
                turnPassed(player);
                return true;
            } else {
                ui.println("--- You must hit a visible card if you are able to! ---");
                return false;
            }
        }
        if (play.transferCards(pile)) {
            return true;
        }
        ui.println("--- Invalid selection! You cannot hit the cards you selected! ---");
        return false;
    }

    /**
     * Gets a CardCollection of all the cards to be played this turn.
     *
     * @param player the player whose turn it is
     * @return a CardCollection of all cards selected by the player
     */
    private CardCollection getPlay(Player player) {
        CardCollection selected = new CardCollection();
        List<Hand> playable = new ArrayList(player.getHands());

        while (true) {
            ui.showSelectedCards(player, playable, selected);
            ui.showInstructionsToPlayer(player);
            List<Card> selection = player.selectCards(playable);
            if (selection == null) { //pass
                return new CardCollection();
            }
            if (selection.isEmpty()) { //hit
                break;
            }
            toggleSelectedCards(selection, playable, selected);
        }
        return selected;
    }

    /**
     * Toggles which cards are selected.
     *
     * @param selection the selection made by the player
     * @param playable the list of all playable hands
     * @param selected the CardCollection of all selected cards
     */
    private void toggleSelectedCards(List<Card> selection, List<Hand> playable, CardCollection selected) {
        for (Hand hand : playable) {
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

    /**
     * Deals cards to the player who passed their turn
     *
     * @param playerInTurn the player who passed their turn
     * @param pack the pack to deal cards from
     */
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

    /**
     * Return whether the player is able to hit on the pile. NOT YET IMPLEMENTED
     *
     * @param player the player whose turn it is
     * @return true if the player can hit on the pile (with a certain selection
     * of cards)
     */
    private boolean isAbleToHit(Player player) {
        return true;
    }

    /**
     * Method is called when the player passes their turn. The UI is updated,
     * the pile is cleared and the players hands are modified as a result.
     *
     * @param player the player whose turn it is
     */
    private void turnPassed(Player player) {
        ui.turnPassed();
        pile.clear();
        Hand privateHand = player.getHand();
        Hand sharedHand = player.getHand(1);
        privateHand.transferCards(sharedHand);
        dealCardsAfterRound(player, pack);
    }

}
