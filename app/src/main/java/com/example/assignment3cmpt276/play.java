package com.example.assignment3cmpt276;



import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static com.example.assignment3cmpt276.MainActivity.mineNum;
import static com.example.assignment3cmpt276.MainActivity.sizex;
import static com.example.assignment3cmpt276.MainActivity.sizey;

public class play extends AppCompatActivity {
    Button buttons[][]=new Button[sizex+1][sizey+1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        populateBtn();
        bombBtn();

    }
    mineLoc mineLoc= new mineLoc();
    int click;
    private void bombBtn() {
        Random rand = new Random();
        for(int i = 0 ; i<mineNum;i++)
        {

            final int x = rand.nextInt(sizex);
            final int y = rand.nextInt(sizey);
            mineLoc.add(new mine(sizex,sizey));
            final Button button = buttons[x][y];
            click =0;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lockButton();

                    if(click==0){
                        int newWidth = button.getWidth();
                        int newHeight= button.getHeight();
                        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mine);
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
                        Resources resource = getResources();
                        button.setBackground(new BitmapDrawable(resource,scaledBitmap));
                        click++;
                    }
                    else if(click == 1)
                    {
                        button.setText(""+scanMine(x,y));
                    }
                }
            });
        }

    }
    private int scanMine(int x, int y)
    {
        int count = 0;
        for(int i=0; i<sizex;i++)
        {
            if(mineLoc.containsMine(new mine(i, y)))
                count++;
        }
        for(int i=0; i<sizey;i++)
        {
            if(mineLoc.containsMine(new mine(x, i)))
                count++;
        }
        if(count!=0)
            return count-2; //minus double scanning for mine at x,y twice (horizontal and vertical scan)
        else return 0;
    }
    private void lockButton() {
        for(int row = 0; row<sizex; row++)
        {
            for( int col=0;col<sizey;col++)
            {
                Button button = buttons[row][col];
                int width = button.getWidth();
                int height = button.getHeight();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                button.setMinHeight(height);
                button.setHeight(height);
            }
        }
    }


    private void populateBtn() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForBtns);
        for(int row = 0; row<sizex;row++)
        {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f ));
            table.addView(tableRow);
            for(int col = 0; col<sizey;col++)
            {
                final int finalCol=col;
                final int finalRow=row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f ));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(finalCol,finalRow);
                    }
                });
                button.setPadding(0,0,0,0);
                tableRow.addView(button);
                buttons[row][col]=button;
            }
        }

    }

    private void gridButtonClicked(int x , int y) {
        Button button = buttons[x][y];
        button.setText(""+scanMine(x,y));
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, play.class);
    }

}
