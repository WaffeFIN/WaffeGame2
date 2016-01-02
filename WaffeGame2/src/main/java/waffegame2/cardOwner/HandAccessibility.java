/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

/**
 * Enum for the accessibility level of a hand.
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public enum HandAccessibility {

    HIDDEN, //Can't be used until type has been changed
    PRIVATE, //Visible and usable only by the owner
    VISIBLE, //Publicly visible, but only usable by the owner
    PUBLIC; //Visible and usable by everyone

    public boolean isUsableByAnyone() {
        switch (this) {
            case PUBLIC:
                return true;
            default:
                return false;
        }
    }

    public boolean isUsableByOwner() {
        switch (this) {
            case HIDDEN:
                return false;
            default:
                return true;
        }
    }

    public boolean isVisibleToEveryone() {
        switch (this) {
            case VISIBLE:
            case PUBLIC:
                return true;
            default:
                return false;
        }
    }

    public boolean isVisibleToOwner() {
        switch (this) {
            case HIDDEN:
                return false;
            default:
                return true;
        }
    }
}
