/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.ui;

/**
 *
 * @author Walter
 */
public enum EventFlag {
    RUNNING, OPTIONS_CONTINUE_BUTTON, CHAT_STRING, PRETURN_CONTINUE_BUTTON, CARD_BUTTON;

    @Override
    public String toString() {
        switch(this) {
            case CHAT_STRING:
                return "Chat input";
            case OPTIONS_CONTINUE_BUTTON:
                return "Options continue button";
            case PRETURN_CONTINUE_BUTTON:
                return "Continue button";
            case CARD_BUTTON:
                return "Card button";
            default:
                return "";
        }
    }
    
    
}
