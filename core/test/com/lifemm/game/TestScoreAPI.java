package com.lifemm.game;

import org.junit.Ignore;
import org.junit.Test;
import com.lifemm.game.Score;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests the ScoreAPI object. Tests integration between server and our code.
 * @author Wyatt Smith (wysmith)
 */

public class TestScoreAPI {
    @Test
    public void testSaveScoreGeneral() {
        ScoreAPI api = ScoreAPI.getInstance();
        try {
            Waldo waldo = new Waldo();
            waldo.setScore(1000);
            api.saveScore("Wyatt Smith", waldo.getScore());
            assertTrue(true);
        } catch (IOException exception) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetScoresGeneral() {
        ScoreAPI api = ScoreAPI.getInstance();
        try {
            api.getScores();
            assertTrue(true);
        } catch (IOException exception) {
            assertTrue(false);
        }
    }
}
