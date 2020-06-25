/*
    Made By: Jason Sebastian Aritanto
    SFU ID : 301377046
    Description: stores where the mine is located
 */
package com.example.assignment3cmpt276.Classes;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class mineLoc {
    private List<String> mines= new ArrayList<>();
    public void add(String mine) {
        mines.add(mine);
    }
    public boolean containsMine(String mine)
    {
        if(mines.contains(mine))
            return true;
        else
            return false;
    }
    public void found(String mine) {
        mines.remove(mine);
    }
}
