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
    private GameWindow gameWindow;

    @Override
    public void run() {
        gameWindow = new GameWindow(this);
        gameWindow.setVisible(true);
    }

    @Override
    public String getString() {
        pauseThread(EventFlag.CHAT_STRING);
        return bufferString;
    }

    public synchronized void recieveAction(EventFlag flag, Object o) {
        if (flag == state) {
            try {
                switch (flag) {
                    case CHAT_STRING:
                        bufferString = (String) o;
                        break;
                    case CARD_BUTTON:
                        bufferCard = (Card) o;
                        break;
                }
                continueThread();
            } catch (ClassCastException ex) {
                print("ClassCastException for flag '" + flag + "' and object '" + o + "'");
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
    public void waitForPlayerToContinue() {
        //show continue button
        pauseThread(EventFlag.PRETURN_CONTINUE_BUTTON);//swap String with JButton
    }

    @Override
    public void print(String text) {
        gameWindow.print(text);
    }

    @Override
    public void println(String text) {
        gameWindow.println(text);
    }

    @Override
    public void showTurnSeparator() {
        return; //hide all cards
    }

    @Override
    public void showInstructionsToPlayer(String name) {
        println(name + ", select cards you want to play.\nHit the selected cards by clicking the");
    }

    @Override
    public void showWinner(String name) {
        return; //
    }

    @Override
    public void showWinners(List<String> names) {
        return; //
    }

    @Override
    public void showSelectedCards(Player player, List<Hand> playable, CardCollection selected) {
        return; //
    }

    @Override
    public void showPreTurn(String str) {
        return; //:D
    }

    @Override
    public void setPack(Pack pack) {
        return; //:D
    }

    @Override
    public void setPile(Pile pile) {
        return; //:D
    }

    @Override
    public List<Card> selectCards(Player player, List<Hand> playable) {
        List<Card> card = new ArrayList();
        pauseThread(EventFlag.CARD_BUTTON);
        card.add(bufferCard);
        return card;
    }

    @Override
    public boolean showOptionsBox() {
        //create and show OptionsBox
        pauseThread(EventFlag.OPTIONS_CONTINUE_BUTTON);
        return true;
    }
}
