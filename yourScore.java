package number.fury.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import number.fury.application.*;

public class yourScore extends AppCompatActivity implements View.OnClickListener{
    private Button btnReturn;

    private TextView highScoreT;
    //  String server_url = "http://192.168.1.41/server.html";
    private TextView finalScore;
    private TextView allTimeHigh;
    private MediaPlayer player;
    private int allHigh=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        allHigh = getIntent().getIntExtra("allTimeHigh1", 0);
        showScore(allHigh);
    }



    public void showScore(int allHighPassed) {
        finalScore = (TextView) findViewById(R.id.finalScore);
        highScoreT = (TextView)findViewById(R.id.highScore);
        allTimeHigh = (TextView) findViewById(R.id.highScore2);
        int score = getIntent().getIntExtra("score", 0);

        // finalScore.setText("Score: " + score);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if(score>=30){
            if(player!=null) {
                player.release();
            }
            player = MediaPlayer.create(this, R.raw.tada);
            player.start();
        }

        if(score > highScore){
            finalScore.setText("Score: " + score);
            highScoreT.setText("High Score: " + score);
            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }
        else {
            finalScore.setText("Score: " + score);
            highScoreT.setText("Your High Score: " + highScore);
        }
        allTimeHigh.setText("Global High Score: "+ allHighPassed);
    }

    public void onClick(View v) {
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

}





