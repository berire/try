package com.mygdx.game.strategies;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 8.8.2016.
 */
public class Random implements Strategy {
    Cell.CellValue cV;
    public LinkedList<CellPosition> forbiddenS=new LinkedList<CellPosition>();
    public LinkedList<CellPosition> forbiddenO=new LinkedList<CellPosition>();
    CellPosition CP;

    public CellPosition determineBestPosition(Board board) {
        CP=new CellPosition(0,0);
        List<CellPosition> EMPTYCells = new LinkedList<CellPosition>();
        for(int i=0; i<board.getNumRow(); i++)
        {
            for(int m=0; m<board.getNumColumn(); m++)
            {
                if(board.cellAtPosition(new CellPosition(i,m)).getValue()== Cell.CellValue.EMPTY)
                {
                    EMPTYCells.add(new CellPosition(i,m));
                }
            }
        }
        int randomIndex = (int)(Math.random()*((EMPTYCells.size()-1)+0));
        CP=EMPTYCells.get(randomIndex);
        return CP;
    }

    @Override
    public Cell.CellValue determineValue(Board board) {
        cV= Cell.CellValue.EMPTY;
        for(int x=0;x<board.getNumRow();x++)
        {
            for(int y=0;y<board.getNumColumn();y++)
            {
                if(board.cellAtPosition(new CellPosition(x,y)).getValue()== Cell.CellValue.S_cell) {
                    if(x-2>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x-2,y)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x-2,y));
                    }
                    if((x-2)>= 0 &&(y-2)>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x-2,y-2)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x-2,y-2));
                    }
                    if((x-2)>= 0 &&(y+2)< board.getNumColumn())
                    {
                        if(board.cellAtPosition(new CellPosition(x-2,y+2)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x-2,y+2));
                    }
                    if((x+2)< board.getNumRow())
                    {
                        if(board.cellAtPosition(new CellPosition(x+2,y)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x+2,y));
                    }
                    if((x+2)< board.getNumRow() && (y-2)>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x+2,y-2)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x+2,y-2));
                    }
                    if((x+2)< board.getNumRow() && (y+2)< board.getNumColumn())
                    {
                        if(board.cellAtPosition(new CellPosition(x+2,y+2)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenS.add(new CellPosition(x+2,y+2));
                    }
                    /////////////////////////////////////////
                    if(x-1>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x-1,y)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x-1,y));
                    }
                    if((x-1)>= 0 &&(y-1)>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x-1,y-1)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x-1,y-1));
                    }
                    if((x-1)>= 0 &&(y+1)< board.getNumColumn())
                    {
                        if(board.cellAtPosition(new CellPosition(x-1,y+1)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x-1,y+1));
                    }
                    if((x+1)< board.getNumRow())
                    {
                        if(board.cellAtPosition(new CellPosition(x+1,y)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x+1,y));
                    }
                    if((x+1)< board.getNumRow() && (y-1)>=0)
                    {
                        if(board.cellAtPosition(new CellPosition(x+1,y-1)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x+1,y-1));
                    }
                    if((x+1)< board.getNumRow() && (y+1)< board.getNumColumn())
                    {
                        if(board.cellAtPosition(new CellPosition(x+1,y+1)).getValue()== Cell.CellValue.EMPTY)
                            forbiddenO.add(new CellPosition(x+1,y+1));
                    }
                }
            }
        }
        int randomIndex=  MathUtils.random(1,50);
        if((randomIndex%2)!=0)
            cV= Cell.CellValue.O_cell;
        else
            cV= Cell.CellValue.S_cell;

        for(int m=0;m<forbiddenS.size();m++)
        {
            for(int n=0;n<forbiddenO.size();n++)
            {   if(CP==forbiddenS.get(m)) {cV= Cell.CellValue.O_cell;}
                if(CP==forbiddenO.get(n)) {cV=Cell.CellValue.S_cell;}
            }
        }

        return cV;
    }
}