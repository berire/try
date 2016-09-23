package com.mygdx.game.strategies;

import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

/**
 * Created by user on 21.9.2016.
 */
public class MEasy implements Strategy {
    Cell.CellValue cV;
    Random random;
    Rule_Based rule;

    public CellPosition determineBestPosition(Board board) {

        random=new Random();
        rule=new Rule_Based();

        CellPosition position=random.determineBestPosition(board);

        int randomIndex = (int)(Math.random()*((100-1)+0));
        if(randomIndex >= 70)
        {
            position=random.determineBestPosition(board);
        }
        else
        {
            position=rule.determineBestPosition(board);
        }

        return position;
    }
    public Cell.CellValue determineValue(Board board) {

        cV= Cell.CellValue.EMPTY;
        int randomIndex = (int)(Math.random()*((100-1)+0));
        if(randomIndex>=75)
        {
            cV=random.determineValue(board);
        }
        else
        {
            cV=rule.determineValue(board);
        }
        return cV;
    }
}
