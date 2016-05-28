package com.lifemm.game;

public class Level
{
    private int currentLevel;
    private int enemiesPerLevel;
    private boolean levelPause;
    private int enemiesKilled;
    private int pauseFrames;
    private int totalEnemiesKilled;

    public Level()
    {
        currentLevel = 1;
        levelPause = false;
        enemiesPerLevel = 2;
        enemiesKilled = 0;
        totalEnemiesKilled = 0;
        pauseFrames = 0;
    }
    public boolean isInlevelPause()
    {
        return levelPause;
    }
    public void incrementenemiesKilled()
    {
        enemiesKilled++;
        totalEnemiesKilled++;
    }
    public void updateLevelIfInPause()
    {
         if (levelPause && pauseFrames < 200) {
             pauseFrames++;
         }
         else{
             levelPause = false;
         }      
    }
    public void goToNextLevel()
    {
         currentLevel++;
         enemiesKilled = 0;
         enemiesPerLevel += 5;
         pauseFrames = 0;
         levelPause = true;
    }
    public boolean allenemiesKilledInLevel()
    {
         return enemiesKilled == enemiesPerLevel;
    }
    
    public int getLevelNumber()
    {
         return currentLevel;
    }
    public int gettotalEnemiesKilled()
   {
        return totalEnemiesKilled;
   }
}


