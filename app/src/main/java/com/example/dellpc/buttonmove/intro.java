package com.example.dellpc.buttonmove;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;


public class intro extends AppCompatActivity {
    //android.os.Handler handler = new android.os.Handler();
    Button startButton;
    int colCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
       // handler.removeCallbacks(null);
        final timerClass timerintro = new timerClass(20000,500);
        timerintro.start();
        VideoView gameintro = (VideoView)findViewById(R.id.vintro);
        String pathvideo = "android.resource://com.example.dellpc.buttonmove/"+R.raw.blackgameintro;
        Uri u1 = Uri.parse(pathvideo);
        startButton=(Button)findViewById(R.id.startbutton);

        gameintro.setVideoURI(u1);
        gameintro.requestFocus();
        gameintro.start();
        startButton.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(intro.this, startandintro.class);
                        startActivity(i);
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

        }

        @Override
        public void onTick(long millisUntilFinished) {
          if(colCounter>=4) {
              if (colCounter % 2 == 0) {
                  startButton.setTextColor(Color.WHITE);
              } else
                  startButton.setTextColor(Color.BLACK);
          }
            colCounter++;
        }
    }

}
