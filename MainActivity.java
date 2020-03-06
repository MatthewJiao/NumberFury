package number.fury.application;
// show factorization
// imports
// link to leader board/upload scores both ways/save state of progress bar/score multiplier based on difficulty
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import number.fury.application.*;


public class MainActivity extends AppCompatActivity implements number.fury.application.DownloadResultReceiver.Receiver{
    //new stuff
    private Button game1;
    private Button game2;
    private SeekBar seekBar;
    private number.fury.application.DownloadResultReceiver mReceiver;
    int [] highh;
    final String url = "https://numberfury.000webhostapp.com/leaderboard.html";
    private Handler mHandler1 = new Handler();
    private Handler mHandler2 = new Handler();

    @Override

    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);

        mReceiver = new number.fury.application.DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, number.fury.application.DownloadService.class);

        /* Send optional extras to Download IntentService */

        intent.putExtra("receiver", mReceiver);
        startService(intent);

        //animate buttons
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);
        game1 = (Button) findViewById(R.id.game1);
        game2 = (Button) findViewById(R.id.game2);
        game1.setAnimation(myAnim);
        game2.setAnimation(myAnim);

        game1.startAnimation(myAnim);
        game2.startAnimation(myAnim);

/*
        SharedPreferences pre = getSharedPreferences("pre_name", Context.MODE_PRIVATE);
        pre.edit().putInt("KEY_PROGRESS_VALUE", 30).commit();
        int progress = pre.getInt("KEY_PROGRESS_VALUE",0); // 0: default value
        seekBar.setProgress(progress);
*/

    }

    @Override
    public void onReceiveResult(int score, Bundle resultData) {
        highh = resultData.getIntArray("HIGH");
        //     Log.d("highh array", Integer.toString(highh[0])+"dj"+Integer.toString(highh[1]));
    }




    public void toGame1(View view) {
        mHandler1.postDelayed(new Runnable() {
            public void run() {
                toDo1();
            }
        }, 500);
    }
    public void toDo1(){
        Intent intent = new Intent(this, number.fury.application.primeNumber.class);
        intent.putExtra("n", seekBar.getProgress()+1);
        intent.putExtra("allTimeHigh1", highh[0]);
        startActivity(intent);
    }

    public void toGame2(View view) {
        mHandler2.postDelayed(new Runnable() {
            public void run() {
                toDo2();
            }
        }, 500);
    }
    public void toDo2(){
        Intent intent = new Intent(this, number.fury.application.summationGame.class);
        intent.putExtra("n", seekBar.getProgress()+1);
        intent.putExtra("allTimeHigh2", highh[1]);
        startActivity(intent);
    }

}





