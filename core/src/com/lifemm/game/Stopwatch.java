/**
 *  StopWatch
 *
 *  Keeps track of time from start to stop. Adds elapsed time to a total.
 *
 *  Created by Jerrick Fong
 */
 
public class Stopwatch { 

    private long start;
    private double total;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
        total = 0;
    } 

    /**
     * Start time.
     */
    public void start() {
        start = System.currentTimeMillis();
    }
    
    /**
     * Stop time and add to total elapsed time.
     */
    public void stop() {
        long now = System.currentTimeMillis();
        total += (now - start) / 1000.0;
    }
    
    /**
     * Return total elapsed time.
     */
    public double getTime() {
        return total;
    }
} 
