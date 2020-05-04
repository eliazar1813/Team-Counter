package com.example.teamscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    public static final String TAG = "WinnerActivity";
    private TextView Points;
    private TextView Winner;
    private int score_Winner;

    private RelativeLayout relativeLayout;
    private  String backgroundValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Points = findViewById(R.id.Points);
        Winner = findViewById(R.id.Winner_Team);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_KEY);
        int Team1_Points = intent.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int Team2_Points = intent.getIntExtra(MainActivity.EXTRA_INT2, 0);

        Log.d(TAG, "the message was: "+message);
        Winner.setText(message);

        Log.d(TAG, "Won By: " +score_Winner);
        score_Winner = (Math.max(Team1_Points,Team2_Points) - Math.min(Team1_Points,Team2_Points));
        Points.setText(""+score_Winner);

        relativeLayout = findViewById(R.id.WinnerTeam);

        PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        backgroundValue = sharedPreferences.getString("medalBackground","none");

        setBackground(backgroundValue);


    }


    public void setBackground(String value){

        switch (value){

            case "gold_medal":
                relativeLayout.setBackgroundResource(R.drawable.gold_medal);
                break;

            case "medal_cup":
                relativeLayout.setBackgroundResource(R.drawable.medalcup);
                break;

            case"thumbs_up":
                relativeLayout.setBackgroundResource(R.drawable.thumbsup);
                break;

            default:
                //nothing


        }




    }


    public void share(View view) {
        Intent intent = new Intent(this, ShareScore.class);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}
