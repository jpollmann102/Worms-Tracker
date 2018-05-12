package com.apps.raj.wormstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ManualInputActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editWins;
    private EditText editPoints;
    private EditText editGP;
    private EditText editFirsts;
    private EditText editSeconds;
    private EditText editThirds;
    private EditText editFourths;

    private Player p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        editName = findViewById(R.id.nameET);
        editWins = findViewById(R.id.editWins);
        editPoints = findViewById(R.id.editPoints);
        editGP = findViewById(R.id.editGP);
        editFirsts = findViewById(R.id.editFirsts);
        editSeconds = findViewById(R.id.editSeconds);
        editThirds = findViewById(R.id.editThirds);
        editFourths = findViewById(R.id.editFourths);

        p = MyApplication.getChosenPlayers().get(0);

        editName.setText(p.getName());
        editWins.setText(String.valueOf(p.getWins()));
        editPoints.setText(String.valueOf(p.getPoints()));
        editGP.setText(String.valueOf(p.getGamesPlayed()));
        editFirsts.setText(String.valueOf(p.getPlaces(0)));
        editSeconds.setText(String.valueOf(p.getPlaces(1)));
        editThirds.setText(String.valueOf(p.getPlaces(2)));
        editFourths.setText(String.valueOf(p.getPlaces(3)));
    }

    public void confirmPressed(View v)
    {
        if(!editName.getText().toString().equals(""))
        {
            p.setName(editName.getText().toString());
        }

        if(!editWins.getText().toString().equals(""))
        {
            p.setWins(Integer.parseInt(editWins.getText().toString()));
        }

        if(!editPoints.getText().toString().equals(""))
        {
            p.setPoints(Integer.parseInt(editPoints.getText().toString()));
        }

        if(!editGP.getText().toString().equals(""))
        {
            p.setGamesPlayed(Integer.parseInt(editGP.getText().toString()));
        }

        int[] newPlaces = new int[4];

        if(editFirsts.getText().toString().equals(""))
        {
            newPlaces[0] = p.getPlaces(0);
        }else
        {
            newPlaces[0] = Integer.parseInt(editFirsts.getText().toString());
        }

        if(editSeconds.getText().toString().equals(""))
        {
            newPlaces[1] = p.getPlaces(1);
        }else
        {
            newPlaces[1] = Integer.parseInt(editSeconds.getText().toString());
        }

        if(editThirds.getText().toString().equals(""))
        {
            newPlaces[2] = p.getPlaces(2);
        }else
        {
            newPlaces[2] = Integer.parseInt(editThirds.getText().toString());
        }

        if(editFourths.getText().toString().equals(""))
        {
            newPlaces[3] = p.getPlaces(3);
        }else
        {
            newPlaces[3] = Integer.parseInt(editFourths.getText().toString());
        }

        p.setPlaces(newPlaces);

        finish();
    }

}
