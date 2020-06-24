package com.example.assignment3cmpt276;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.assignment3cmpt276.MainActivity.mineNum;
import static com.example.assignment3cmpt276.MainActivity.score;
import static com.example.assignment3cmpt276.MainActivity.sizex;
import static com.example.assignment3cmpt276.MainActivity.sizey;


public class winPopUp extends Activity{
    TextView currentScore;
    TextView highScore;
    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        final Intent intent = getIntent();
        int height = dm.heightPixels;
        currentScore = findViewById(R.id.currentScore);
        highScore = findViewById(R.id.highScore);
        int newScore = intent.getIntExtra("currentScore",sizex*sizey);
        Log.d("lalisa","score"+newScore);

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));//https://www.youtube.com/watch?v=fn5OlqQuOCk
        currentScore.setText(""+newScore);
        score.setHighScore(sizex+"x"+sizey,mineNum,newScore);
        highScore.setText(""+score.getHighScore(sizex+"x"+sizey,mineNum));
        button = findViewById(R.id.returnToMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
