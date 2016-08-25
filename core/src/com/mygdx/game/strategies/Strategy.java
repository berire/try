package com.mygdx.game.strategies;

import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

/**
 * Created by user on 8.8.2016.
 */
public interface Strategy {
    CellPosition determineBestPosition(Board board);
    Cell.CellValue determineValue(Board board);
    void setStrategy(String st);
    String getStrategy();

}
