package com.example.assignment3cmpt276;

import android.util.Log;

import static com.example.assignment3cmpt276.MainActivity.sizex;
import static com.example.assignment3cmpt276.MainActivity.sizey;

public class Scores {
    private String boardSize;
    private int mineNumber;
    private int score;

    public Scores(String boardSize, int mineNumber,int highestScore) {
        this.boardSize = boardSize;
        this.mineNumber = mineNumber;
        score = highestScore;
    }
    /*public com.example.assignment3cmpt276.Scores(String boardSize,int mineNumber,int score)
    {
        this.boardSize = boardSize;
        this.mineNumber = mineNumber;
        this.score = score;
    }*/
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
