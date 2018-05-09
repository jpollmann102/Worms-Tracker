package com.apps.raj.wormstracker;

/**
 * Created by Josh on 5/7/2018.
 */

public class Player
{

    private String name;
    private int points;
    private int wins;
    private int gamesPlayed;
    private int friendlyKilled;
    private int friendlyDamage;
    private int damageDone;
    private int damageTaken;
    private int enemyKilled;
    private int numMostFriendlyKilled;
    private int numMostEnemyKilled;

    // first, second, third, fourth place count
    private int[] places = {0, 0, 0, 0};

    public Player(String name)
    {
        this.name = name;
        points = 0;
        wins = 0;
        gamesPlayed = 0;
        friendlyKilled = 0;
        friendlyDamage = 0;
        damageDone = 0;
        damageTaken = 0;
        enemyKilled = 0;
        numMostEnemyKilled = 0;
        numMostFriendlyKilled = 0;
    }

    public double pointsPerGame()
    {
        return points / gamesPlayed;
    }

    public double avgDamage()
    {
        return damageDone / gamesPlayed;
    }

    public double avgDamageTaken()
    {
        return damageTaken / gamesPlayed;
    }

    public double avgEnemyKilled()
    {
        return enemyKilled / gamesPlayed;
    }

    public double avgFriendlyKilled()
    {
        return friendlyKilled / gamesPlayed;
    }

    public void incrFriendlyDamage(int damage)
    {
        friendlyDamage += damage;
    }

    public void incrFriendlyKilled(int killed)
    {
        friendlyKilled += killed;
        numMostFriendlyKilled++;
    }

    public void incrEnemyKilled(int killed)
    {
        enemyKilled += killed;
        numMostEnemyKilled++;
    }

    public void incrDamageDone(int damage)
    {
        damageDone += damage;
    }

    public void incrDamageTaken(int damage)
    {
        damageTaken += damage;
    }

    public void first()
    {
        points += 3;
        wins++;
        gamesPlayed++;
        places[0]++;
    }

    public void second()
    {
        points += 2;
        gamesPlayed++;
        places[1]++;
    }

    public void third()
    {
        points += 1;
        gamesPlayed++;
        places[2]++;
    }

    public void fourth()
    {
        gamesPlayed++;
        places[3]++;
    }

    public String getName() { return name; }
    public int getPoints() { return points; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getNumMostFriendlyKilled() { return numMostFriendlyKilled; }
    public int getNumMostEnemyKilled() { return numMostEnemyKilled; }
}
