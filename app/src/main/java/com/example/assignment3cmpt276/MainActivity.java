package com.example.assignment3cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.CollationElementIterator;

public class MainActivity extends AppCompatActivity {
    public static int  sizex=6;
    public static int sizey=4;
    public static int mineNum=4;
    Button playBtn;
    Button optionBtn;
    Button helpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView boardSize = findViewById(R.id.boardSize);
        boardSize.setText(sizex + "x" + sizey);
        playBtn = findViewById(R.id.playBtn);
        optionBtn = findViewById(R.id.optionBtn);
        helpBtn = findViewById(R.id.helpBtn);
        startBtn();
        helpBtnAct();
        optionBtnAct();
    }

    private void optionBtnAct() {
        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = option.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void helpBtnAct() {
    }

    public void startBtn(){
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = play.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
}
