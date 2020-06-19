package com.example.assignment3cmpt276;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class mineLoc implements Iterable<mine> {
    private List<mine> mines= new ArrayList<>();
    public void add(mine mine) {
        mines.add(mine);
    }
    public boolean containsMine(mine mine)
    {
        if(mines.contains(mine))
            return true;
        else
            return false;
    }
    @NonNull
    @Override
    public Iterator<mine> iterator() {
        return null;
    }
}
