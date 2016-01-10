/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

import java.util.ArrayList;
import java.util.List;
import waffegame2.card.Card;
import waffegame2.cardOwner.CardCollection;
import waffegame2.cardOwner.Hand;
import waffegame2.cardOwner.Pack;
import waffegame2.cardOwner.Pile;
import waffegame2.player.Player;

/**
 * A graphical user interface
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-06
 */
public class GraphicalUI extends UI {

    private volatile EventFlag state;
    private String bufferString;
    private Card bufferCard;
    private EventFlag bufferFlag;
    private GameWindow gameWindow;

    @Override
    public void run() {
        gameWindow = new GameWindow(this);
        gameWindow.setVisible(true);
    }

    public synchronized void recieveAction(EventFlag flag, Object o) {
        recieveAction(flag, null, o);
    }

    public synchronized void recieveAction(EventFlag flag, EventFlag bufferFlag, Object o) {
        if (flag == state) {
            try {
                switch (flag) {
                    case CHAT_STRING:
                        bufferString = (String) o;
                        break;
                    case SELECTION_BUTTONS:
                        bufferCard = (Card) o;
                        break;
                }
                this.bufferFlag = bufferFlag;
                continueThread();
            } catch (ClassCastException ex) {
                printlnBoth("ClassCastException for flags '" + flag + "', and '" + bufferFlag + "' and object '" + o + "'");
            }
        }
    }

    public synchronized void continueThread() {
        state = EventFlag.RUNNING;
        notifyAll();
    }

    public void pauseThread(EventFlag flag) {
        state = flag;
        pauseMethod();
    }

    private void pauseMethod() {
        while (true) {
            try {
                Thread.sleep(40);
                if (state != EventFlag.RUNNING) {
                    synchronized (this) {
                        while (state != EventFlag.RUNNING) {
                            wait();
                        }
                    }
                }
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String getString() {
        pauseThread(EventFlag.CHAT_STRING);
        return bufferString;
    }

    @Override
    public void waitToContinue() {
        gameWindow.showContinueButton();
        pauseThread(EventFlag.PRETURN_CONTINUE_BUTTON);//swap String with JButton
        gameWindow.hideContinueButton();
    }

    @Override
    public void print(String text) {
        gameWindow.print(text);
    }

    @Override
    public void println(String text) {
        gameWindow.println(text);
    }

    private void printlnBoth(String text) {
        gameWindow.printlnBoth(text);
    }

    @Override
    public void showTurnSeparator() {
        gameWindow.hideCardSprites();
    }

    @Override
    public void showInstructionsToPlayer(Player player) {
        println(player.getName() + ", select cards you want to play.\nHit the selected cards by clicking the 'Hit' button");
    }

    @Override
    public void showWinner(Player player) {
        gameWindow.showWinner(player);
        pauseThread(EventFlag.RESTART_BUTTON);
        gameWindow.hideWinnerWindow();
    }

    @Override
    public void showWinners(List<Player> players) {
        gameWindow.showWinners(players);
        pauseThread(EventFlag.RESTART_BUTTON);
        gameWindow.hideWinnerWindow();
    }

    @Override
    public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected) {
        gameWindow.showSelectedCards(player, playable, selected);
    }

    @Override
    public void beforeTurn(Player player, String str) {
        gameWindow.println(str);
        gameWindow.preTurn(player);
    }

    @Override
    public void inTurn() {
        gameWindow.showSelectorButtons();
    }

    @Override
    public void afterTurn() {
        gameWindow.hideSelectorButtons();
    }

    @Override
    public void setPack(Pack pack) {
        gameWindow.setPack(pack);
    }

    @Override
    public void setPile(Pile pile) {
        gameWindow.setPile(pile);
    }

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        List<Card> card = new ArrayList();
        pauseThread(EventFlag.SELECTION_BUTTONS);
        switch (bufferFlag) {
            case CARD_BUTTON:
                card.add(bufferCard);
                return card;
            case HIT_BUTTON:
                return card;
            case PASS_BUTTON:
                return null;
            default:
                printlnBoth("Odd bufferFlag @selectCards: " + bufferFlag);
                return null;
        }
    }

    @Override
    public boolean showOptionsBox() {
//        gameWindow.showOptionWindow();
//        pauseThread(EventFlag.OPTIONS_CONTINUE_BUTTON);
//        gameWindow.hideOptionWindow();
        return true;
    }

}
