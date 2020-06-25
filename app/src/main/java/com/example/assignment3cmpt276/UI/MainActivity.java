/*
Created By: Jason Sebastian Aritanto
SFU ID : 301377046
Description : Main activity where all the buttons to play option or help is located (Main Menu)
*/
package com.example.assignment3cmpt276.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3cmpt276.R;
import com.example.assignment3cmpt276.Classes.highScore;

import static com.example.assignment3cmpt276.UI.option.getSettings;
import static com.example.assignment3cmpt276.UI.option.savedValues;

public class MainActivity extends AppCompatActivity {
    public static int  sizex=savedValues[0];
    public static int sizey=savedValues[1];
    public static int mineNum=savedValues[2];

    Button playBtn;
    Button optionBtn;
    Button helpBtn;
    static highScore score = new highScore();
    static int numPlayed;
    SharedPreferences prefScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn = findViewById(R.id.playBtn);
        optionBtn = findViewById(R.id.optionBtn);
        helpBtn = findViewById(R.id.helpBtn);
        prefScore = this.getSharedPreferences("numPlay",MODE_PRIVATE);
        numPlayed=prefScore.getInt("numPlay",0);
        startBtn();
        helpBtnAct();
        optionBtnAct();
        populateHighScore();
        refreshScreen();

    }

    private void saveNumPlay() {
        SharedPreferences prefs = this.getSharedPreferences("numPlay",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("numPlay",numPlayed);
        editor.apply();
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
                startActivityForResult(intent,80);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
        case 80:
                saveNumPlay();//checks whether it has been reset or not

        }
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
                Intent intent = mineSeeker.makeIntent(MainActivity.this);
                numPlayed++;
                saveNumPlay();
                startActivity(intent);
            }
        });
    }
}
