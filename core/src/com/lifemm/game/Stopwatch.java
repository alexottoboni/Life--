package com.lifemm.game;

/**
 *  Stopwatch
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
     * Restart starting time.
     */
    public void restart() {
        start = System.currentTimeMillis();
    }
    
    /**
     * Record elapsed time without stopping watch.
     */
    public void lap() {
        long now = System.currentTimeMillis();
        
        total += (now - start) / 1000.0;
        start = System.currentTimeMillis();
    }
    
    /**
     * Reset the stop watch.
     */
    public void clear() {
        start = System.currentTimeMillis();
        total = 0;
    }
    
    /**
     * Return total elapsed time.
     */
    public double getTime() {
        return total;
    }
} 
