/*
Created By: Jason Sebastian Aritanto
SFU ID : 301377046
Description : Class of scores to store the information for each settings
*/
package com.example.assignment3cmpt276.Classes;

import android.util.Log;

public class Scores {
    private String boardSize;
    private int mineNumber;
    private int score;

    public Scores(String boardSize, int mineNumber,int highestScore) {
        this.boardSize = boardSize;
        this.mineNumber = mineNumber;
        score = highestScore;
    }

    public boolean sameSetting(String boardSize, int mineNumber)
    {
        Log.d("jennie" , boardSize +" " + this.boardSize);
        Log.d("jennie", mineNumber + " "+ mineNumber);
        if( this.boardSize.equals(boardSize) && this.mineNumber == mineNumber)
            return true;
        return false;
    }
    public void setHighScore(int score)
    {
        if(score<this.score)
        {
            this.score = score;

        }
        Log.d("lalisa","new "+score);
    }

    public int getHighScore() {
        return score;
    }
}
