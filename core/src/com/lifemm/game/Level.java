package com.lifemm.game;

public class Level
{
    private int CurrentLevel;
    private int EnemiesPerLevel;
    private boolean LevelPause;
    private int EnemiesKilled;
    private int PauseFrames;
    private int TotalEnemiesKilled;

    public Level()
    {
        CurrentLevel = 1;
        LevelPause = false;
        EnemiesPerLevel = 2;
        EnemiesKilled = 0;
        TotalEnemiesKilled = 0;
        PauseFrames = 0;
    }
    public boolean isInLevelPause()
    {
        return LevelPause;
    }
    public void incrementEnemiesKilled()
    {
        EnemiesKilled++;
        TotalEnemiesKilled++;
    }
    public void updateLevelIfInPause()
    {
         if (LevelPause && PauseFrames < 200) {
             PauseFrames++;
         }
         else{
             LevelPause = false;
         }      
    }
    public void goToNextLevel()
    {
         CurrentLevel++;
         EnemiesKilled = 0;
         EnemiesPerLevel += 5;
         PauseFrames = 0;
         LevelPause = true;
    }
    public boolean AllEnemiesKilledInLevel()
    {
         return EnemiesKilled == EnemiesPerLevel;
    }
    
    public int getLevelNumber()
    {
         return CurrentLevel;
    }
    public int getTotalEnemiesKilled()
   {
        return TotalEnemiesKilled;
   }
}


