package com.example.assignment3cmpt276;



import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
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
import static java.lang.Integer.parseInt;

public class play extends AppCompatActivity {
    Button buttons[][]=new Button[sizex][sizey];
    Integer click[] = new Integer[mineNum];
    int scanUsed =0;
    int mineFound =0;
    TextView mineNumber;
    TextView scanNum;
    mineLoc mineLoc= new mineLoc();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mineNumber=findViewById(R.id.minesFound);
        scanNum= findViewById(R.id.scansUsed);
        scanNum.setText("#of scan used= " +scanUsed);
        mineNumber.setText("Found "+mineFound+"of "+mineNum +" mines");
        populateBtn();
        allMinesFound();

    }

    private void allMinesFound() {

    }

    private void bombBtn() {
        Random rand = new Random();
        for(int i = 0 ; i<mineNum;i++)
        {

            final int x = rand.nextInt(sizex);
            final int y = rand.nextInt(sizey);
            mineLoc.add(x+"+"+y);
            final Button button = buttons[x][y];
            click[i]=0;
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lockButton();

                    if(click[finalI]==0){
                        int newWidth = button.getWidth();
                        int newHeight= button.getHeight();
                        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mine);
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
                        Resources resource = getResources();
                        button.setBackground(new BitmapDrawable(resource,scaledBitmap));
                        mineLoc.found(x+"+"+y);
                        click[finalI]++;
                        mineFound++;
                        mineNumber.setText("Found "+mineFound+"of "+mineNum +" mines");
                        decreaseScanMineText(x,y);
                        Vibrator vibrate= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrate.vibrate(400); //taken from https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
                    }
                    else if(click[finalI] == 1)
                    {
                        button.setText(""+scanMine(x,y));
                        click[finalI]++;//prevents to click more than 1 times
                    }
                }
            });
        }

    }
    private void decreaseScanMineText(int x, int y)
    {
        for(int row=0; row<sizex;row++)
        {
           Button button = buttons[row][y];
            if(row!=x)
                if(button.getText().length()!=0) {
                    int num = parseInt(button.getText().toString())-1;
                    button.setText(""+num);
                }
        }
        for(int col=0; col<sizey;col++)
        {
            Button button = buttons[x][col];
            if(col!=y)
                if(button.getText().length()!=0)
                {
                    int num=(parseInt(button.getText().toString())-1);
                    button.setText(""+num);
                }
        }
    }
    private int scanMine(int x, int y)
            {
                int count = 0;
                for(int row=0; row<sizex;row++)
                {
                    if(row!=x)
                        if(mineLoc.containsMine(row+"+"+y)) {
                            count++;
                        }
                }
                for(int col=0; col<sizey;col++)
                {
            if(col!=y)
                if(mineLoc.containsMine(x+"+"+col))
             {
                 count++;
             }
        }
        scanUsed++;
        scanNum.setText("#of scan used= " +scanUsed);
        if(count!=0)
            return count; //minus double scanning for mine at x,y twice (horizontal and vertical scan)
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
                button.setPadding(0,0,0,0);
                tableRow.addView(button);
                buttons[row][col]=button;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(finalRow,finalCol);
                    }
                });
            }
        }
        bombBtn();
    }

    private void gridButtonClicked(int x , int y) {
        Button button = buttons[x][y];
        button.setText(""+scanMine(x,y));
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, play.class);
    }

}
