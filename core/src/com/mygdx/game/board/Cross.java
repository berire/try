package com.mygdx.game.board;

import java.util.LinkedList;

/**
 * Created by user on 19.9.2016.
 */
public class Cross {

    public boolean isDrawn=false;


    private LinkedList<Cell> crossGroup;

    public Cross ()
    {
        crossGroup=new LinkedList<Cell>();
    }
    public void addCross(Cell a,Cell b, Cell c)
    {
        crossGroup.add(a);
        crossGroup.add(b);
        crossGroup.add(c);
    }
}
