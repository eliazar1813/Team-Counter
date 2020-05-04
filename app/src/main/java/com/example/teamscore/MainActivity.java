package com.example.teamscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String EXTRA_INT1 = "com.example.teamscore.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.example.teamscore.EXTRA_INT2";
    public static final String EXTRA_MESSAGE_KEY = "com.example.teamscore.EXTRA_MESSAGE_KEY";

    private int mCount1 = 0;
    private int mCount2 = 0;
    private String Winning_Team;
    private int Winning_Score;

    private TextView Score_team1;
    private TextView Score_team2;

    private  String backgroundValue;
    private RelativeLayout relativeLayout;



    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "inside of MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        Score_team1 = findViewById(R.id.Score_team1);

        Score_team2 = findViewById(R.id.Score_team2);

        relativeLayout = findViewById(R.id.homeBackground);

        backgroundValue = (sharedPreferences.getString("teamBackground", "none"));

        setBackground(backgroundValue);

        Log.d(TAG, "End of MainActivity");
    }

    public void setBackground(String value){

        switch (value){

            case "baseball":
                relativeLayout.setBackgroundResource(R.drawable.baseball);
                break;

            case "basketball":
                relativeLayout.setBackgroundResource(R.drawable.basketball);
                break;
            case "cricket":
                relativeLayout.setBackgroundResource(R.drawable.cricket);
                break;

            case "nfl":
                relativeLayout.setBackgroundResource(R.drawable.nfl);
                break;

            case "soccer":
                relativeLayout.setBackgroundResource(R.drawable.soccer);
                break;

            case "tennis":
                relativeLayout.setBackgroundResource(R.drawable.tennis);
                break;


            default:
                //Nothing

        }




    }



    public void Count_up_Team1(View view) {
        mCount1++;
        if (Score_team1 != null)
            Score_team1.setText(Integer.toString(mCount1));
        if(mCount1 >=5){
            GameOver();
        }

    }


    public void Count_up_Team2(View view) {
        mCount2++;
        if (Score_team2 != null)
            Score_team2.setText(Integer.toString(mCount2));
        if (mCount2 >=5){
            GameOver();
    }

}


    private void GameOver() {

        if (mCount1 == Math.max(mCount1,mCount2)){
            Winning_Team = "TEAM 1";

        }
        else{
            Winning_Team = "TEAM 2";

        }

        Log.d(TAG, "launchSecondActivity");
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra(EXTRA_INT1,mCount1);
        intent.putExtra(EXTRA_INT2,mCount2);
        intent.putExtra(EXTRA_MESSAGE_KEY,Winning_Team);
        startActivity(intent); //starting but not expecting result

        }

    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"Inside of Menu Method");
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this , SettingsActivity.class);
                startActivity(intent);

                return true;


            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);

    }

}
