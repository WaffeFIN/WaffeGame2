/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.cardOwner;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Walter
 */
public class HandAccessibilityTests {

    public HandAccessibilityTests() {
    }

    @Test
    public void testBooleans() {
        assertEquals(false, HandAccessibility.HIDDEN.isVisibleToOwner());
        assertEquals(true, HandAccessibility.PRIVATE.isVisibleToOwner());
        assertEquals(true, HandAccessibility.VISIBLE.isVisibleToOwner());
        assertEquals(true, HandAccessibility.PUBLIC.isVisibleToOwner());

        assertEquals(false, HandAccessibility.HIDDEN.isVisibleToEveryone());
        assertEquals(false, HandAccessibility.PRIVATE.isVisibleToEveryone());
        assertEquals(true, HandAccessibility.VISIBLE.isVisibleToEveryone());
        assertEquals(true, HandAccessibility.PUBLIC.isVisibleToEveryone());

        assertEquals(false, HandAccessibility.HIDDEN.isUsableByOwner());
        assertEquals(true, HandAccessibility.PRIVATE.isUsableByOwner());
        assertEquals(true, HandAccessibility.VISIBLE.isUsableByOwner());
        assertEquals(true, HandAccessibility.PUBLIC.isUsableByOwner());

        assertEquals(false, HandAccessibility.HIDDEN.isUsableByAnyone());
        assertEquals(false, HandAccessibility.PRIVATE.isUsableByAnyone());
        assertEquals(false, HandAccessibility.VISIBLE.isUsableByAnyone());
        assertEquals(true, HandAccessibility.PUBLIC.isUsableByAnyone());
    }
}
