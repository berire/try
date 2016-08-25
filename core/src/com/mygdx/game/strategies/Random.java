package com.mygdx.game.strategies;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

import java.util.List;

/**
 * Created by user on 8.8.2016.
 */
public class Random implements Strategy {
    static String  strategy;
    Cell.CellValue cV;


    static void setStrategy( ){
        strategy="EASY";
    }

    public String getStrategy(){
        return strategy;
    }

    @Override
    public void setStrategy(String st) {
        st="EASY";
    }

    public CellPosition determineBestPosition(Board board) {
        List<CellPosition> availableCells;
        availableCells = board.emptyCellPositions();
        int randomIndex = MathUtils.random(0, availableCells.size());
        return availableCells.get(randomIndex);
    }

    @Override
    public Cell.CellValue determineValue(Board board) {
        cV= Cell.CellValue.EMPTY;
        int randomIndex=  MathUtils.random(1,3);
        if(randomIndex==1)
            cV= Cell.CellValue.O_cell;
        else if(randomIndex==2)
            cV= Cell.CellValue.S_cell;
        return cV;
    }
}
