package com.apps.raj.wormstracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddGameActivity extends AppCompatActivity {

    private Spinner firstSpinner;
    private Spinner secondSpinner;
    private Spinner thirdSpinner;
    private Spinner fourthSpinner;

    private Spinner mostFriendlySpinner;
    private Spinner mostEnemySpinner;
    private Spinner mostDamageSpinner;
    private Spinner mostDamageTakenSpinner;
    private Spinner mostFriendlyDamageSpinner;

    private EditText mostFriendlyET;
    private EditText mostEnemyET;
    private EditText mostDamageET;
    private EditText mostDamageTakenET;
    private EditText mostFriendlyDamageET;

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        firstSpinner = findViewById(R.id.firstSpinner);
        secondSpinner = findViewById(R.id.secondSpinner);
        thirdSpinner = findViewById(R.id.thirdSpinner);
        fourthSpinner = findViewById(R.id.fourthSpinner);

        mostFriendlySpinner = findViewById(R.id.friendlyKilledSpinner);
        mostEnemySpinner = findViewById(R.id.enemyKilledSpinner);
        mostDamageSpinner = findViewById(R.id.mostDamageSpinner);
        mostDamageTakenSpinner = findViewById(R.id.mostDamageTakenSpinner);
        mostFriendlyDamageSpinner = findViewById(R.id.mostFriendlyDamageSpinner);

        mostFriendlyET = findViewById(R.id.friendlyKilledET);
        mostEnemyET = findViewById(R.id.enemyKilledET);
        mostDamageET = findViewById(R.id.mostDamageET);
        mostDamageTakenET = findViewById(R.id.mostDamageTakenET);
        mostFriendlyDamageET = findViewById(R.id.mostFriendlyDamageET);

        confirmButton = findViewById(R.id.confirmButton);

        List<String> spinnerArray = new ArrayList<String>();

        for(Player p : MyApplication.getChosenPlayers())
        {
            spinnerArray.add(p.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        firstSpinner.setAdapter(adapter);
        secondSpinner.setAdapter(adapter);
        thirdSpinner.setAdapter(adapter);
        fourthSpinner.setAdapter(adapter);

        mostFriendlySpinner.setAdapter(adapter);
        mostEnemySpinner.setAdapter(adapter);
        mostDamageSpinner.setAdapter(adapter);
        mostDamageTakenSpinner.setAdapter(adapter);
        mostFriendlyDamageSpinner.setAdapter(adapter);
    }

    private void showAlert(String title, String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(AddGameActivity.this).create();
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

    public void confirmPressed(View v)
    {
        int first = firstSpinner.getSelectedItemPosition();
        int second = secondSpinner.getSelectedItemPosition();
        int third = thirdSpinner.getSelectedItemPosition();
        int fourth = fourthSpinner.getSelectedItemPosition();

        int mostFriendly = mostFriendlySpinner.getSelectedItemPosition();
        int mostEnemy = mostEnemySpinner.getSelectedItemPosition();
        int mostDamage = mostDamageSpinner.getSelectedItemPosition();
        int mostDamageTaken = mostDamageTakenSpinner.getSelectedItemPosition();
        int mostFriendlyDamage = mostFriendlyDamageSpinner.getSelectedItemPosition();

        if(mostFriendlyET.getText().equals(""))
        {
            showAlert("Missing Information", "Please enter a value for most friendlies killed");
            return;
        }else if(mostEnemyET.getText().equals(""))
        {
            showAlert("Missing Information", "Please enter a value for most enemies killed");
            return;
        }else if(mostDamageET.getText().equals(""))
        {
            showAlert("Missing Information", "Please enter a value for most damage done");
            return;
        }else if(mostDamageTakenET.getText().equals(""))
        {
            showAlert("Missing Information", "Please enter a value for most damage taken");
            return;
        }else if(mostFriendlyDamageET.getText().equals(""))
        {
            showAlert("Missing Information", "Please enter a value for most friendly damage done");
            return;
        }
        int mostFriendlyNum = Integer.parseInt(mostFriendlyET.getText().toString());
        int mostEnemyNum = Integer.parseInt(mostEnemyET.getText().toString());
        int mostDamageNum = Integer.parseInt(mostDamageET.getText().toString());
        int mostDamageTakenNum = Integer.parseInt(mostDamageTakenET.getText().toString());
        int mostFriendlyDamageNum = Integer.parseInt(mostFriendlyDamageET.getText().toString());

        if(first == second || first == third || first == fourth)
        {
            showAlert("Create Game Failed", "Only one player can be in each place");
            return;
        }else if(second == third || second == fourth)
        {
            showAlert("Create Game Failed", "Only one player can be in each place");
            return;
        }else if(third == fourth)
        {
            showAlert("Create Game Failed", "Only one player can be in each place");
            return;
        }

        MyApplication.getChosenPlayers().get(first).first();
        MyApplication.getChosenPlayers().get(second).second();
        MyApplication.getChosenPlayers().get(third).third();
        MyApplication.getChosenPlayers().get(fourth).fourth();

        MyApplication.getChosenPlayers().get(mostFriendly).incrFriendlyKilled(mostFriendlyNum);
        MyApplication.getChosenPlayers().get(mostEnemy).incrEnemyKilled(mostEnemyNum);
        MyApplication.getChosenPlayers().get(mostDamage).incrDamageDone(mostDamageNum);
        MyApplication.getChosenPlayers().get(mostDamageTaken).incrDamageTaken(mostDamageTakenNum);
        MyApplication.getChosenPlayers().get(mostFriendlyDamage).incrFriendlyDamage(mostFriendlyDamageNum);

        finish();
    }
}
