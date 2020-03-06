package number.fury.application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class summationGame extends AppCompatActivity implements View.OnClickListener {

    //declaring variables;
    static Random rand = new Random();  //random number generator
    static int mainSum;
    public static int score;

    private Button mainBtn;

    private TextView timerText;
    private TextView scoreText;
    private CountDownTimer countDownTimer;

    private Button[] buttons = new Button[10];   //list of buttons
    private boolean[] selected = new boolean[10]; //for each button; see if it is selected or not
    private MediaPlayer player;
    int numSolutions = rand.nextInt(10) + 1;    //plus randomly arranging those into the buttons and assigning all the other buttons randomnums

    private int[] solution = new int[numSolutions];
    private int original;
    private long timeLeftInMiliSeconds;

    private Handler mHandler = new Handler();
    private int allTimeHigh;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summation_game);
        timeLeftInMiliSeconds = 31000;

        setupGame();
        /*
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
        */
        startTimer();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        countDownTimer.cancel();
    }

    public void startTimer () {
        countDownTimer = new CountDownTimer(timeLeftInMiliSeconds, 1) {
            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished / 1000;


                timerText.setText(""+ seconds);

            }

            @Override
            public void onFinish(){
                endGame();
            }
        }.start();
    }

    public void setupGame() {
        int n = 50*getIntent().getIntExtra("n", 0);
        mainSum = rand.nextInt(n) + 30;
        original = mainSum;
        mainBtn = findViewById(R.id.mainBtn); //links this to the button on xml
        mainBtn.setBackgroundResource(R.drawable.circle);
        mainBtn.setText(String.valueOf(mainSum));   //sets the text to the number

        buttons[0] = findViewById(R.id.test1);
        buttons[1] = findViewById(R.id.test2);
        buttons[2] = findViewById(R.id.test3);
        buttons[3] = findViewById(R.id.test4);
        buttons[4] = findViewById(R.id.test5);
        buttons[5] = findViewById(R.id.test6);
        buttons[6] = findViewById(R.id.test7);
        buttons[7] = findViewById(R.id.test8);
        buttons[8] = findViewById(R.id.test9);
        buttons[9] = findViewById(R.id.test10);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);
        }


        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);

        for (int i = 0; i < 10; i++) {
            selected[i] = false;
        }

        setValues();
    }

    public void setValues() {                            //choosing a random number of solutions; and random numbers that properly add up to mainSum;

        for (int i = 0; i < numSolutions; i++) {       //creating array with the numbers that will add up to the sum
            solution[i] = 0;
        }

        int sum = 0;

        for (int i = 0; i < numSolutions - 1; i++) {
            solution[i] = rand.nextInt(mainSum / 3 * 2 - sum);
            sum = sum + solution[i];
        }
        solution[numSolutions - 1] = mainSum - sum;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 10; i > numSolutions; i--) {
            list.remove(list.size() - 1);
        }

        Collections.sort(list);

        int counter = 0;
        for (int i = 0; i < buttons.length; i++) {
            if (counter < list.size() && i == list.get(counter)) {
                buttons[i].setText(String.valueOf(solution[counter]));
                counter++;
            } else {
                buttons[i].setText(String.valueOf(rand.nextInt(mainSum / 3 * 2)));
            }
        }
        for(int i = 0; i < buttons.length; i++) {
            if (Integer.parseInt(buttons[i].getText().toString()) == 0) {
                buttons[i].setText(String.valueOf(rand.nextInt(mainSum / 3 * 2)+1));
            }
        }
    }

    @Override
    public void onClick(View view) {
        String stemp;
        int temp;
        switch (view.getId()) {
            case R.id.test1:
                if (selected[0] == false) {
                    stemp = buttons[0].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[0] = true;
                    buttons[0].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[0].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[0] = false;
                    buttons[0].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test2:
                if (selected[1] == false) {
                    stemp = buttons[1].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[1] = true;
                    buttons[1].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[1].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[1] = false;
                    buttons[1].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test3:
                if (selected[2] == false) {
                    stemp = buttons[2].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[2] = true;
                    buttons[2].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[2].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[2] = false;
                    buttons[2].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test4:
                if (selected[3] == false) {
                    stemp = buttons[3].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[3] = true;
                    buttons[3].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[3].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[3] = false;
                    buttons[3].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test5:
                if (selected[4] == false) {
                    stemp = buttons[4].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[4] = true;
                    buttons[4].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[4].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[4] = false;
                    buttons[4].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test6:
                if (selected[5] == false) {
                    stemp = buttons[5].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[5] = true;
                    buttons[5].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[5].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[5] = false;
                    buttons[5].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test7:
                if (selected[6] == false) {
                    stemp = buttons[6].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[6] = true;
                    buttons[6].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[6].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[6] = false;
                    buttons[6].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test8:
                if (selected[7] == false) {
                    stemp = buttons[7].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[7] = true;
                    buttons[7].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[7].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[7] = false;
                    buttons[7].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test9:
                if (selected[8] == false) {
                    stemp = buttons[8].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[8] = true;
                    buttons[8].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[8].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[8] = false;
                    buttons[8].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;

            case R.id.test10:
                if (selected[9] == false) {
                    stemp = buttons[9].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum - temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[9] = true;
                    buttons[9].setTextColor(Color.RED);
                    didWin(mainSum);

                } else {
                    stemp = buttons[9].getText().toString();          //get value of test button
                    temp = Integer.parseInt(stemp);
                    mainSum = mainSum + temp;
                    mainBtn.setText(String.valueOf(mainSum));
                    selected[9] = false;
                    buttons[9].setTextColor(Color.BLACK);
                    didWin(mainSum);
                }
                break;
//
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void didWin(int mainSum) {
        int temp;
        if (mainSum == 0) {

            if(player!=null) {
                player.release();
            }
            player = MediaPlayer.create(this, R.raw.ding);
            player.start();

            score+=10;
            scoreText.setText(String.valueOf(score));

            gameRestart();                     //give a new mainSum
        }
    }

    public void gameRestart() {
        for(int i = 0; i<10; i++){
            selected[i] = false;
            buttons[i].setTextColor(Color.BLACK);
        }
        mainSum = rand.nextInt(70) + 30;
        original = mainSum;
        mainBtn.setText(String.valueOf(mainSum));   //sets the text to the number
        setValues();
    }


    public void endGame() {

        String stest;
        int test;
        for (int i = 0; i < 10; i++) {
            buttons[i].setTextColor(Color.BLACK);
            buttons[i].setEnabled(false);
        }

        boolean [] switched = new boolean[10];

        for(int i = 0; i<10; i++) {
            switched[i] = false;
        }

        for(int i = 0; i<solution.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (switched[j]==false){
                    stest = (buttons[j].getText().toString());
                    test = Integer.valueOf(stest);
                    if (test == solution[i]) {
                        buttons[j].setTextColor(Color.GREEN);
                        switched[j] = true;
                        break;
                    }
                }
            }
        }

        mainBtn.setText(String.valueOf(original));
        mHandler.postDelayed(new Runnable() {
            public void run() {
                sendNext();
            }
        }, 5000);
    }

    public void sendNext(){
        Intent intent = new Intent(this, yourScore2.class);
        allTimeHigh = getIntent().getIntExtra("allTimeHigh2", 0);
        intent.putExtra("allTimeHigh2", allTimeHigh);
        intent.putExtra("score", score);
        startActivity(intent);
        score = 0;
    }
}//end of class

















