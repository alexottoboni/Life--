package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Score;

import junit.framework.TestCase;

/**
 * Tests the Score object.
 * @author Wyatt Smith (wysmith)
 */

public class TestScore extends TestCase {

    public static final String NAME = "Test User";
    public void testGetScoreGeneral() {
        Score score = new Score(NAME, 10000);
        assertEquals(10000, score.getScore());
    }

    public void testGetNameGeneral() {
        Score score = new Score(NAME, 10000);
        assertEquals("Test User", score.getName());
    }

    public void testGetNameNull() {
        Score score = new Score(null, 10000);
        assertEquals(null, score.getName());
    }

    public void testToStringGeneral() {
        Score score = new Score(NAME, 10000);
        assertEquals("Test User: 10000", score.toString());
    }

    public void testToStringNull() {
        Score score = new Score(null, 10000);
        assertEquals("null: 10000", score.toString());
    }
}
