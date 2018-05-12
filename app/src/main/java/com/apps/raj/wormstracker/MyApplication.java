package com.apps.raj.wormstracker;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application
{
    private static ArrayList<Player> chosenPlayers = new ArrayList<Player>();
    private static ArrayList<Player> allPlayers = new ArrayList<Player>();

    public static ArrayList<Player> getChosenPlayers() { return chosenPlayers; }
    public static ArrayList<Player> getAllPlayers() { return allPlayers; }

    public static void setChosenPlayers(ArrayList<Player> players)
    {
        chosenPlayers = players;
    }
    public static void setAllPlayers(ArrayList<Player> players) { allPlayers = players; }
}
