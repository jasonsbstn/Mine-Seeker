package com.example.assignment3cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.CollationElementIterator;

import static com.example.assignment3cmpt276.option.getSettings;
import static com.example.assignment3cmpt276.option.savedValues;

public class MainActivity extends AppCompatActivity {
    public static int  sizex=savedValues[0];
    public static int sizey=savedValues[1];
    public static int mineNum=savedValues[2];

    Button playBtn;
    Button optionBtn;
    Button helpBtn;
    static highScore score = new highScore();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn = findViewById(R.id.playBtn);
        optionBtn = findViewById(R.id.optionBtn);
        helpBtn = findViewById(R.id.helpBtn);

        startBtn();
        helpBtnAct();
        optionBtnAct();
        populateHighScore();
        refreshScreen();

    }
    private void populateHighScore() {
        int [] rowSize=getResources().getIntArray(R.array.row);
        int [] colSize=getResources().getIntArray(R.array.col);
        int [] highestScore = getResources().getIntArray(R.array.highscores);
        int [] mineNumber= getResources().getIntArray(R.array.mines);
        for(int i =0; i<rowSize.length;i++)
        {
            final int xsize = rowSize[i];
            final int ysize = colSize[i];
            final int highestScores =highestScore[i];
            for(int j = 0; j<mineNumber.length;j++)
            {
                score.add(xsize+"x"+ysize,mineNumber[j],highestScores);
            }
        }


    }
    private void refreshScreen() {
        //Refresh board size & mine number
        int settings[] = getSettings(this);
        TextView boardSize = findViewById(R.id.boardSize);
        boardSize.setText(settings[0] + "x" + settings[1]);
        sizex = settings[0];
        sizey = settings[1]; //so when refreshed it keeps the same layout
        mineNum = settings[2];

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
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
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = help.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
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
