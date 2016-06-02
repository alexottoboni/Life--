package com.lifemm.game;

import java.util.List;
import java.util.LinkedList;


public class EnemyList {
    private List<Enemy> enemies;

    public EnemyList() {
        enemies = new LinkedList<>();
    }

    public void add(Enemy enemy) {
        enemies.add(enemy);
    }

    public void deleteDeadEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() <= 0) {
                enemies.remove(enemies.get(i));
            }
        }
    }

    public void remove(Enemy enemy) {
        enemies.remove(enemy);
    }

    public Enemy get(int index) {
        return enemies.get(index);
    }

    public List<Enemy> getIterable() {
        return enemies;
    }

    public int size() {
        return enemies.size();
    }

}
