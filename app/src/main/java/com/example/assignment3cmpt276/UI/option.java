/*
Created By: Jason Sebastian Aritanto
SFU ID : 301377046
Description : Creates activity for the option to change size and number of mines
*/
package com.example.assignment3cmpt276.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.assignment3cmpt276.Classes.highScore;
import com.example.assignment3cmpt276.R;

import static com.example.assignment3cmpt276.UI.MainActivity.mineNum;
import static com.example.assignment3cmpt276.UI.MainActivity.score;
import static com.example.assignment3cmpt276.UI.MainActivity.sizex;
import static com.example.assignment3cmpt276.UI.MainActivity.sizey;
public class option extends AppCompatActivity {
    public static int[] savedValues= {4,6,6};
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        createRadioButton();
        savedValues=getSettings(this);
        reset = findViewById(R.id.reset);
        resetBtn();
    }

    private void resetBtn() {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = new highScore();
                populateHighScore();
            }
        });
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

    private void createRadioButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.collumnrowRadio);
        int [] rowSize=getResources().getIntArray(R.array.row);
        int [] colSize=getResources().getIntArray(R.array.col);
        for(int i =0; i<rowSize.length;i++)
        {
            final int xsize = rowSize[i];
            final int ysize = colSize[i];
            RadioButton button = new RadioButton(this);
            button.setText(xsize+"x"+ysize);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sizex= xsize;
                    sizey= ysize;
                    saveSize(xsize,ysize);
                }
            });
            group.addView(button);
            //select default button
            if(xsize == getSettings(this)[0]&&ysize == getSettings(this)[1])
            {
                button.setChecked(true);
            }
        }
        RadioGroup group2 = (RadioGroup) findViewById(R.id.minenum);
        final int [] mineNumber= getResources().getIntArray(R.array.mines);
        for(int i = 0; i<mineNumber.length;i++)
        {
            RadioButton button = new RadioButton(this);
            final int mineNums=mineNumber[i];
            button.setText(mineNums+" mines");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mineNum=mineNums;
                    saveMine(mineNum);
                }
            });
            group2.addView(button);
            if(mineNums==getSettings(this)[2])
            {
                button.setChecked(true);
            }
        }

    }

    private void saveMine(int mineNums) {
        SharedPreferences prefs = this.getSharedPreferences("MinePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("MineNum",mineNums);
        editor.apply();
    }

    private void saveSize(int xsize, int ysize) {
        SharedPreferences prefs = this.getSharedPreferences("SizePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("NumRow",xsize);
        editor.putInt("NumCol",ysize);
        editor.apply();
    }
    static int[] getSettings(Context context){
        SharedPreferences prefs = context.getSharedPreferences("SizePrefs",MODE_PRIVATE);
        int [] x = new int[3];
        x[0]= prefs.getInt("NumRow",6);
        x[1] = prefs.getInt("NumCol",4);
        SharedPreferences prefs2 = context.getSharedPreferences("MinePrefs",MODE_PRIVATE);
        x[2] = prefs2.getInt("MineNum",4);
        return x;
    }
    public static Intent makeIntent(Context context){
        return new Intent(context, option.class);
    }
}
