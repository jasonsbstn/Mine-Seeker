package com.example.assignment3cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.assignment3cmpt276.MainActivity.mineNum;
import static com.example.assignment3cmpt276.MainActivity.sizex;
import static com.example.assignment3cmpt276.MainActivity.sizey;

public class option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        createRadioButton();
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
                }
            });
            group.addView(button);
        }
        RadioGroup group2 = (RadioGroup) findViewById(R.id.minenum);
        final int [] mineNumber= getResources().getIntArray(R.array.mines);
        for(int i = 0; i<mineNumber.length;i++)
        {
            RadioButton button = new RadioButton(this);
            final int mineNums=mineNumber[i];
            button.setText(mineNum+" mines");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mineNum=mineNums;
                }
            });
            group2.addView(button);
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, option.class);
    }
}
