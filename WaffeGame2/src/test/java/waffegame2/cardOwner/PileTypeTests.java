package waffegame2.cardOwner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Walter
 */
public class PileTypeTests {

    public PileTypeTests() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void pileTypeTest() {
        assertEquals("Empty", PileType.NULL.toString());
        assertEquals("Suit", PileType.SUIT.toString());
        assertEquals("Straight", PileType.STRAIGHT.toString());
        assertEquals("Pairs", PileType.PAIRS.toString());
        assertEquals("Triples", PileType.TRIPLES.toString());
        assertEquals("Quadruples", PileType.QUADRUPLES.toString());
    }
}
