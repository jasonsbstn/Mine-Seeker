/*
Created By: Jason Sebastian Aritanto
SFU ID : 301377046
Description : This is where the help activity is created and create the hyperlink
*/
package com.example.assignment3cmpt276.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.assignment3cmpt276.R;

public class help extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        textView=findViewById(R.id.aboutMe);
        textView.setMovementMethod(LinkMovementMethod.getInstance());//https://stackoverflow.com/questions/9852184/android-textview-hyperlink
    }
    public static Intent makeIntent(Context context){
        return new Intent(context, help.class);
    }
}
