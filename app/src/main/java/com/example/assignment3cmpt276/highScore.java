package com.example.assignment3cmpt276;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.assignment3cmpt276.MainActivity.sizex;
import static com.example.assignment3cmpt276.MainActivity.sizey;

public class highScore
{
    private List<Scores> scores= new ArrayList<>();
    int highestScore;
    public void add(String size, int mineNumber,int highestScore) {
        this.highestScore = highestScore;
        scores.add(new Scores(size, mineNumber,highestScore));
    }
    public int getHighScore(String size, int mineNumber)
    {
        for(Scores score : scores)
        {
            if(score.sameSetting(size,mineNumber))
            {
                return score.getHighScore();
            }
        }
        return 0;//return x * y
    }

    public void setHighScore(String size, int mineNumber ,int newScore )
    {
        Log.d("jennie",size + mineNumber);
        for(Scores score : scores)
        {
            if(score.sameSetting(size,mineNumber))
            {
                score.setHighScore(newScore);
                return;
            }
        }

    }
}
