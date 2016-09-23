package com.mygdx.game.board;

import com.mygdx.game.player.Player;

import java.util.LinkedList;

/**
 * Created by user on 19.9.2016.
 */
public class Cross {

    public boolean isDrawn=false;
    public Player crosser;

    public LinkedList<Cell> crossGroup;

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
    public void setCrosser (Player p1)
    {
        this.crosser=p1;
    }

    public Player getCrosser()
    {
        return  this.crosser;
    }

    public LinkedList<Cell> getthree()
    {
        return crossGroup;
    }
}
