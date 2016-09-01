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

    public CellPosition determineBestPosition(Board board) {
        List<CellPosition> availableCells;
        availableCells = board.emptyCellPositions(board);
        int randomIndex = (int)(Math.random()*(availableCells.size()-1)+0);
        return availableCells.get(randomIndex);
    }

    @Override
    public Cell.CellValue determineValue(Board board) {
        cV= Cell.CellValue.EMPTY;

        int randomIndex=  MathUtils.random(1,3);

        if((randomIndex%2)!=0)
            cV= Cell.CellValue.O_cell;
        else
            cV= Cell.CellValue.S_cell;

        return cV;
    }
}
