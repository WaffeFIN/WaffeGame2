/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

/**
 * An enum for the program state. Is used when pausing the gameLogic thread for
 * input from the GUI.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-09
 */
public enum EventFlag {

    RUNNING, OPTIONS_CONTINUE_BUTTON, CHAT_STRING, PRETURN_CONTINUE_BUTTON, SELECTION_BUTTONS, CARD_BUTTON, HIT_BUTTON, PASS_BUTTON, RESTART_BUTTON;

    @Override
    public String toString() {
        switch (this) {
            case RUNNING:
                return "Default state";
            case OPTIONS_CONTINUE_BUTTON:
                return "Options continue button";
            case CHAT_STRING:
                return "Chat input";
            case PRETURN_CONTINUE_BUTTON:
                return "Continue button";
            case SELECTION_BUTTONS:
                return "Selection buttons group";
            case CARD_BUTTON:
                return "Card";
            case HIT_BUTTON:
                return "Hit button";
            case PASS_BUTTON:
                return "Pass button";
            case RESTART_BUTTON:
                return "Game restart button";
            default:
                return "Other flag";
        }
    }

}
