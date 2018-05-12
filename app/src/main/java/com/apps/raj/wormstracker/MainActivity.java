package com.apps.raj.wormstracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Player> players;
    private ArrayList<Player> chosenPlayers = new ArrayList<Player>();
    private TableLayout mainTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        players = (ArrayList<Player>) getFromSharedPrefs(this);
        if(players == null) players = new ArrayList<Player>();

        MyApplication.setAllPlayers(players);

        mainTable = findViewById(R.id.mainTable);

        populateTable();

        FloatingActionButton apfab = findViewById(R.id.addPlayerFab);
        apfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
                updateTable();
            }
        });

        FloatingActionButton agfab = findViewById(R.id.addGameFab);
        agfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.setChosenPlayers(chosenPlayers);
                addGame();
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        saveToSharedPrefs(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        saveToSharedPrefs(this);
    }

    private void addGame()
    {
        if(chosenPlayers.size() != 4)
        {
            showAlert("Create Game Failed", "Please ensure exactly 4 players are selected for the game");
            chosenPlayers.clear();
            return;
        }

        startActivityForResult(new Intent(MainActivity.this, AddGameActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 0)
        {
            // Created a game
            View view = findViewById(R.id.main_layout_id);
            updateTable();
            Snackbar snackbar = Snackbar.make(
                    view, "Game added", Snackbar.LENGTH_SHORT);

            snackbar.show();
        }else if(requestCode == 1)
        {
            // Manually changed values
            View view = findViewById(R.id.main_layout_id);
            updateTable();
            Snackbar snackbar = Snackbar.make(
                    view, "Player values updated", Snackbar.LENGTH_SHORT);

            snackbar.show();
        }

        chosenPlayers.clear();
    }

    private void showAlert(String title, String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void addUser()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Player");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                players.add(new Player(input.getText().toString()));
                updateTable();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        builder.show();
        chosenPlayers.clear();
        MyApplication.setAllPlayers(players);
    }

    private void updateTable()
    {
        mainTable.removeAllViews();
        populateTable();
    }

    private void populateTable()
    {
        TableRow tr;
        CheckBox chosenPlayer;
        TextView nameAndPoints;

        Collections.sort(players);

        for(int i = 0; i < players.size(); i++)
        {
            final Player p = players.get(i);

            tr = new TableRow(this);
            chosenPlayer = new CheckBox(this);

            chosenPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b)
                    {
                        chosenPlayers.add(p);
                    }else
                    {
                        chosenPlayers.remove(p);
                    }
                }
            });

            nameAndPoints = new TextView(this);
            nameAndPoints.setText(p.getName() + ": " + p.getPoints() + "pts / " + p.getGamesPlayed() + "gp");
            tr.addView(chosenPlayer);
            tr.addView(nameAndPoints);
            mainTable.addView(tr);
        }
    }

    public List<Player> getFromSharedPrefs(Context context)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = sp.getString("players", "");
        players = gson.fromJson(json, new TypeToken<ArrayList<Player>>(){}.getType());
        return players;
    }

    public void saveToSharedPrefs(Context context)
    {
        System.out.println("Saving prefs");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();

        Gson gson = new Gson();
        String json = gson.toJson(players);
        editor.putString("players", json);

        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_refresh)
        {
            updateTable();
            return true;
        }

        if(id == R.id.menu_stats)
        {
            if(players.size() == 0)
            {
                showAlert("No Players", "Please add players in order to see stats");
            }else
            {
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
            }
        }

        if(id == R.id.menu_manual_input)
        {
            if(chosenPlayers.size() != 1)
            {
                showAlert("Choose Player For Manual Input", "Please ensure exactly 1 player is selected for manual input");
                chosenPlayers.clear();
            }else
            {
                MyApplication.setChosenPlayers(chosenPlayers);
                startActivityForResult(new Intent(MainActivity.this, ManualInputActivity.class), 1);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
