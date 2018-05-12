package com.apps.raj.wormstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    private TableLayout statsTable;
    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsTable = findViewById(R.id.statsTable);
        players = MyApplication.getAllPlayers();

        populateTable();
    }

    private Player mostWins()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getWins() > max)
            {
                max = p.getWins();
                ret = p;
            }
        }

        return ret;
    }

    private Player mostFourths()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getPlaces(3) > max)
            {
                max = p.getPlaces(3);
                ret = p;
            }
        }

        return ret;
    }

    private Player mostTeamDmg()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getFriendlyDamage() > max)
            {
                max = p.getFriendlyDamage();
                ret = p;
            }
        }

        return ret;
    }

    private Player mostEnemyDmg()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getDamageDone() > max)
            {
                max = p.getDamageDone();
                ret = p;
            }
        }

        return ret;
    }

    private Player mostFriendlyKilled()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getNumMostFriendlyKilled() > max)
            {
                max = p.getNumMostFriendlyKilled();
                ret = p;
            }
        }

        return ret;
    }

    private Player mostEnemyKilled()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getNumMostEnemyKilled() > max)
            {
                max = p.getNumMostEnemyKilled();
                ret = p;
            }
        }

        return ret;
    }

    private Player highestPPG()
    {
        double max = Double.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getPPG() > max)
            {
                max = p.getPPG();
                ret = p;
            }
        }

        return ret;
    }

    private Player highestDPG()
    {
        double max = Double.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getDPG() > max)
            {
                max = p.getDPG();
                ret = p;
            }
        }

        return ret;
    }

    private Player mostDmgTaken()
    {
        int max = Integer.MIN_VALUE;
        Player ret = players.get(0);
        for(Player p : players)
        {
            if(p.getDamageTaken() > max)
            {
                max = p.getDamageTaken();
                ret = p;
            }
        }

        return ret;
    }

    private void populateTable()
    {
        TextView name;
        TextView pointsAndGP;
        TextView ppg;
        TextView wins;
        TextView places;
        TextView friendlyDMG;
        TextView enemyDMG;
        TextView dmgTaken;
        TextView friendlyDMGPG;
        TextView enemyDMGPG;
        TextView dmgTPG;
        TextView friendlyKilled;
        TextView enemyKilled;
        TextView friendlyKilledPG;
        TextView enemyKilledPG;
        TextView topWorms;
        TextView mostWinsT;
        TextView mostWins;
        TextView mostLossesT;
        TextView mostLosses;
        TextView mostTeamDMGT;
        TextView mostTeamDMG;
        TextView mostDMGT;
        TextView mostDMG;
        TextView mostDMGTakenT;
        TextView mostDMGTaken;
        TextView mostFriendlyT;
        TextView mostFriendly;
        TextView mostKilledT;
        TextView mostKilled;
        TextView mostPPGT;
        TextView mostPPG;
        TextView mostDPGT;
        TextView mostDPG;

        for(Player p : players)
        {
            name = new TextView(this);
            name.setText(p.getName());
            name.setTextSize(20);

            pointsAndGP = new TextView(this);
            pointsAndGP.setText(p.getPoints() + "PTS/" + p.getGamesPlayed() + "GP");
            pointsAndGP.setTextSize(14);

            ppg = new TextView(this);
            ppg.setText(String.format("%.1f", p.getPPG()) + " PPG");
            ppg.setTextSize(14);

            wins = new TextView(this);
            wins.setText(p.getWins() + " Wins");
            wins.setTextSize(14);

            places = new TextView(this);
            places.setText(p.getPlaces(0) + " 1st, " + p.getPlaces(1) + " 2nd, " + p.getPlaces(2) + " 3rd, " + p.getPlaces(3) + " 4th");
            places.setTextSize(14);

            friendlyDMG = new TextView(this);
            friendlyDMG.setText(p.getFriendlyDamage() + " Friendly DMG");
            friendlyDMG.setTextSize(14);

            enemyDMG = new TextView(this);
            enemyDMG.setText(p.getDamageDone() + " DMG Done");
            enemyDMG.setTextSize(14);

            dmgTaken = new TextView(this);
            dmgTaken.setText(p.getDamageTaken() + "DMG Taken");
            dmgTaken.setTextSize(14);

            friendlyDMGPG = new TextView(this);
            friendlyDMGPG.setText(String.format("%.1f", p.getFDPG()) + " Friendly DMG PG");
            friendlyDMGPG.setTextSize(14);

            enemyDMGPG = new TextView(this);
            enemyDMGPG.setText(String.format("%.1f", p.getDPG()) + " DMG PG");
            enemyDMGPG.setTextSize(14);

            dmgTPG = new TextView(this);
            dmgTPG.setText(String.format("%.1f", p.getDTPG()) + " DMG Taken PG");
            dmgTPG.setTextSize(14);

            friendlyKilled = new TextView(this);
            friendlyKilled.setText(p.getNumMostFriendlyKilled() + " Friendlies Killed");
            friendlyKilled.setTextSize(14);

            enemyKilled = new TextView(this);
            enemyKilled.setText(p.getNumMostEnemyKilled() + " Enemies Killed");
            enemyKilled.setTextSize(14);

            friendlyKilledPG = new TextView(this);
            friendlyKilledPG.setText(String.format("%.1f", p.getFKPG()) + " Friendlies Killed PG");
            friendlyKilledPG.setTextSize(14);

            enemyKilledPG = new TextView(this);
            enemyKilledPG.setText(String.format("%.1f", p.getEKPG()) + " Enemies Killed PG");
            enemyKilledPG.setTextSize(14);


            addRow(name);
            addRow(pointsAndGP);
            addRow(ppg);
            addRow(wins);
            addRow(places);
            addRow(friendlyDMG);
            addRow(enemyDMG);
            addRow(dmgTPG);
            addRow(friendlyDMGPG);
            addRow(enemyDMGPG);
            addRow(friendlyKilled);
            addRow(enemyKilled);
            addRow(friendlyKilledPG);
            addRow(enemyKilledPG);
        }

        topWorms = new TextView(this);
        topWorms.setText("Top Worms");
        topWorms.setTextSize(24);

        mostWinsT = new TextView(this);
        mostWinsT.setText("Most Wins");
        mostWinsT.setTextSize(20);

        mostWins = new TextView(this);
        mostWins.setText(mostWins().getName() + " - " + mostWins().getWins());
        mostWins.setTextSize(14);

        mostLossesT = new TextView(this);
        mostLossesT.setText("Most Losses");
        mostLossesT.setTextSize(20);

        mostLosses = new TextView(this);
        mostLosses.setText(mostFourths().getName() + " - " + mostFourths().getPlaces(3));
        mostLosses.setTextSize(14);

        mostTeamDMGT = new TextView(this);
        mostTeamDMGT.setText("Most Team Damage");
        mostTeamDMGT.setTextSize(20);

        mostTeamDMG = new TextView(this);
        mostTeamDMG.setText(mostTeamDmg().getName() + " - " + mostTeamDmg().getFriendlyDamage());
        mostTeamDMG.setTextSize(14);

        mostDMGT = new TextView(this);
        mostDMGT.setText("Most Damage Done");
        mostDMGT.setTextSize(20);

        mostDMG = new TextView(this);
        mostDMG.setText(mostEnemyDmg().getName() + " - " + mostEnemyDmg().getDamageDone());
        mostDMG.setTextSize(14);

        mostDMGTakenT = new TextView(this);
        mostDMGTakenT.setText("Most Damage Taken");
        mostDMGTakenT.setTextSize(20);

        mostDMGTaken = new TextView(this);
        mostDMGTaken.setText(mostDmgTaken().getName() + " - " + mostDmgTaken().getDamageTaken());
        mostDMGTaken.setTextSize(14);

        mostFriendlyT = new TextView(this);
        mostFriendlyT.setText("Most Friendlies Killed");
        mostFriendlyT.setTextSize(20);

        mostFriendly = new TextView(this);
        mostFriendly.setText(mostFriendlyKilled().getName() + " - " + mostFriendlyKilled().getNumMostFriendlyKilled());
        mostFriendly.setTextSize(14);

        mostKilledT = new TextView(this);
        mostKilledT.setText("Most Enemies Killed");
        mostKilledT.setTextSize(20);

        mostKilled = new TextView(this);
        mostKilled.setText(mostEnemyKilled().getName() + " - " + mostEnemyKilled().getNumMostEnemyKilled());
        mostKilled.setTextSize(14);

        mostPPGT = new TextView(this);
        mostPPGT.setText("Highest PPG");
        mostPPGT.setTextSize(20);

        mostPPG = new TextView(this);
        mostPPG.setText(highestPPG().getName() + " - " + String.format("%.1f", highestPPG().getPPG()));
        mostPPG.setTextSize(14);

        mostDPGT = new TextView(this);
        mostDPGT.setText("Highest DPG");
        mostDPGT.setTextSize(20);

        mostDPG = new TextView(this);
        mostDPG.setText(highestDPG().getName() + " - " + String.format("%.1f", highestDPG().getDPG()));
        mostDPG.setTextSize(14);

        addRow(topWorms);
        addRow(mostWinsT);
        addRow(mostWins);
        addRow(mostLossesT);
        addRow(mostLosses);
        addRow(mostTeamDMGT);
        addRow(mostTeamDMG);
        addRow(mostDMGT);
        addRow(mostDMG);
        addRow(mostDMGTakenT);
        addRow(mostDMGTaken);
        addRow(mostFriendlyT);
        addRow(mostFriendly);
        addRow(mostKilledT);
        addRow(mostKilled);
        addRow(mostPPGT);
        addRow(mostPPG);
        addRow(mostDPGT);
        addRow(mostDPG);
    }

    private void addRow(View v)
    {
        TableRow tr = new TableRow(this);
        tr.addView(v);
        statsTable.addView(tr);
    }
}
