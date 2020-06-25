/*
Created By: Jason Sebastian Aritanto
SFU ID : 301377046
Description : The default start up for animations
*/
package com.example.assignment3cmpt276.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3cmpt276.R;
import com.example.assignment3cmpt276.UI.MainActivity;

public class WelcomeActivity extends AppCompatActivity {
    ImageView imageView;
    Button btn;
    TextView assignment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageView=findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
        imageView.setAnimation(animation);
        assignment = findViewById(R.id.assignment);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler= new Handler();
                Runnable r = new Runnable(){
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(getApplication(), MainActivity.class));
                    }};//automatically move to main activity after 4 second animation ends
                handler.postDelayed(r,4000); //https://stackoverflow.com/questions/29198262/android-sleep-without-blocking-ui
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                assignment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        assignment.setAnimation(animation2);
        btn = findViewById(R.id.skipButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });

    }

}
