pckage com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Score;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import junit.framework.TestCase;

/**
 * Tests the ScoreAPI object. Tests integration between server and our code.
 * @author Wyatt Smith (wysmith)
 */

public class TestScoreAPI extends TestCase {

    private static final Logger LOGGER = Logger.getLogger(TestScoreAPI.class.getName()); 

    public void testSaveScoreGeneral() {
        ScoreAPI api = ScoreAPI.getInstance();
        try {
            Waldo waldo = new Waldo();
            waldo.setScore(1000);
            api.saveScore("Wyatt Smith", waldo.getScore());
            assertTrue(true);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Exception found", exception);
            assertTrue(false);
        }
    }

    public void testGetScoresGeneral() {
        ScoreAPI api = ScoreAPI.getInstance();
        try {
            api.getScores();
            assertTrue(true);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Exception found", exception);
            assertTrue(false);
        }
    }
}
