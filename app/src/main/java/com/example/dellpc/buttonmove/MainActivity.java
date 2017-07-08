package com.example.dellpc.buttonmove;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    android.os.Handler handler = new android.os.Handler();
    int highscore = 0;
    Button b;
    Button bOut;
    Button exitButton;
    TextView score;
    TextView highScore;
    Button retry;
    TextView Timer;
    final int[] i = {0};
    int state =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences prefs = getSharedPreferences("bestscore",MODE_PRIVATE);
        final MediaPlayer clicksound = MediaPlayer.create(this, R.raw.soundeffect);
        final Random random = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button1);
        bOut =(Button)findViewById(R.id.button2);
        final int[] incr = {20};
        final timerClass timer = new timerClass(20000,1000);
        score  = (TextView)findViewById(R.id.score);
        highScore = (TextView)findViewById(R.id.hscore);
        Timer = (TextView)findViewById(R.id.timer);
        retry =(Button)findViewById(R.id.retry);
        exitButton = (Button)findViewById(R.id.exit);
        exitButton.setEnabled(false);
        exitButton.setVisibility(View.INVISIBLE);
        retry.setBackgroundColor(Color.RED);
        retry.setVisibility(View.VISIBLE);
        retry.setEnabled(true);
        retry.setText("START");
        retry.setTextColor(Color.WHITE);
        retry.setTextSize(40);
        bOut.setText("Hit The Black Box");
        bOut.setTextSize(40);
        bOut.setTextColor(Color.BLACK);
        b.setEnabled(false);
        b.setVisibility(View.INVISIBLE);
        bOut.setEnabled(false);
        int bscore = prefs.getInt("bestscore",0);
        highscore = bscore;
        highScore.setText("HIGH SCORE: " + highscore);
        retry.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        bOut.setEnabled(true);
                        bOut.setBackgroundColor(Color.WHITE);
                        exitButton.setEnabled(false);
                        exitButton.setVisibility(View.INVISIBLE);
                        bOut.setText("");
                        timer.start();
                        retry.setX(400);
                        retry.setY(212);
                        retry.setBackgroundColor(Color.WHITE);
                        retry.setTextColor(Color.BLACK);
                        retry.setText("Retry");
                        b.setVisibility(View.VISIBLE);
                        b.setEnabled(true);
                        state = 0;
                        score.setText("SCORE: 0");
                        retry.setEnabled(false);
                        retry.setVisibility(View.INVISIBLE);
                        i[0] = 0;
                        incr[0] = 20;
                    }
                }
        );
        bOut.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        bOut.setBackgroundColor(Color.BLACK);
                        bOut.setEnabled(false);
                        b.setEnabled(false);
                        retry.setEnabled(true);
                        retry.setVisibility(View.VISIBLE);
                        exitButton.setEnabled(true);
                        exitButton.setVisibility(View.VISIBLE);
                        if (i[0] > highscore) {
                            highscore = i[0];
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("bestscore",highscore);
                            editor.apply();
                        }
                        state = 1;
                        Timer.setText("GAME-OVER");
                        bOut.setTextSize(100);
                        highScore.setText("HIGH SCORE: " + highscore);
                        b.setVisibility(View.INVISIBLE);
                    }
                }
        );

        b.setOnClickListener(
                new Button.OnClickListener() {
                    int j = 6;

                    @Override
                    public void onClick(View v) {
                        clicksound.start();
                        int xco = random.nextInt(1025);
                        int yco = random.nextInt(395) + 45;
                        b.setX(xco);
                        b.setY(yco);
                        if (i[0] == 0)
                            j = 6;
                        i[0] = i[0] + 1;
                        if (i[0] == incr[0]) {
                            timer.start();
                            incr[0] = incr[0] + 20 + j;
                            j = j + 6;
                        }
                        score.setText("SCORE: " + i[0]);
                    }
                }

        );
       exitButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }
    public class timerClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public timerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            bOut.callOnClick();
            Timer.setText("GAME-OVER");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String ms = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis));
            if(state==1)
            Timer.setText("GAME-OVER");
            else
                Timer.setText(ms+" SECONDS");
        }
    }

}
