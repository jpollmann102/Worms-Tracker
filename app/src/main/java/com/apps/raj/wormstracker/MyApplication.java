package com.apps.raj.wormstracker;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application
{
    private static ArrayList<Player> chosenPlayers = new ArrayList<Player>();

    public static ArrayList<Player> getChosenPlayers() { return chosenPlayers; }

    public static void setChosenPlayers(ArrayList<Player> players)
    {
        chosenPlayers = players;
    }
}
