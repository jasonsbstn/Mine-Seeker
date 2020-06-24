package com.example.assignment3cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

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
