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

public class yourScore2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnReturn;

    private TextView highScoreT;
    private TextView finalScore;
    private TextView allTimeHigh2;

    private MediaPlayer player;
    private int allHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score2);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        allHigh = getIntent().getIntExtra("allTimeHigh2", 0);
        showScore(allHigh);

    }
    public void showScore(int allHighPass){
        finalScore = (TextView) findViewById(R.id.finalScore);
        highScoreT = (TextView)findViewById(R.id.highScore);
        allTimeHigh2 = (TextView) findViewById(R.id.highScore2a);
        int score = getIntent().getIntExtra("score", 0);

        SharedPreferences settings = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if(score>=20){
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
            highScoreT.setText("High Score: " + highScore);
        }
        allTimeHigh2.setText("Global High Score: "+ allHighPass);
    }

    public void onClick(View v) {
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}


