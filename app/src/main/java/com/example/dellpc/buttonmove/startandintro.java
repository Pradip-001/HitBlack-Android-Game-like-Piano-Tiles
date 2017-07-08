package com.example.dellpc.buttonmove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startandintro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startandintro);
        Button play = (Button)findViewById(R.id.pbutton);
        Button instruction = (Button)findViewById(R.id.instrbutton);
        play.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(startandintro.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
        );
        instruction.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(startandintro.this,hitblackinstruction.class);
                        startActivity(i);
                    }
                }
        );
    }
}
