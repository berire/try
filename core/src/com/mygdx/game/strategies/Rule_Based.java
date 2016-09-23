package com.mygdx.game.strategies;

import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

/**
 * Created by user on 8.8.2016.
 */
public class Rule_Based implements Strategy {

    // If I can win on this move, do it. (S0-  /S ) (S-S / O) (-OS /S)  (APPLIED RULE !!!)
    //If the other player can win on the next move, block that winning square
    //Take the center square if it’s free.
    //Take a corner or center square if one is free.

    private Cell.CellValue value;
    public boolean isWin=false;
    public Random random=new Random();
    public boolean isdetermined=false;


    public CellPosition determineBestPosition(Board board)
    {
        CellPosition theposition=null;
        theposition=Winner(board);

        if(isWin)
        {
            theposition=Winner(board);
            isdetermined=true;
        }
        else
        {
            isdetermined=true;
            theposition=random.determineBestPosition(board);
        }

        return theposition;
    }
    public Cell.CellValue determineValue(Board board)
    {
        Cell.CellValue thevalue=Cell.CellValue.EMPTY;
        if(isWin)
        {
            thevalue=this.value;
        }
        else
        {
            thevalue=random.determineValue(board);
        }

        return thevalue;
    }


    private CellPosition Winner(Board board)
    {
        CellPosition position=null;
        isWin=false;
        System.out.println("CROSSED ıs called");
        for (int a = 0; a < board.row; a++) // for every row
        {
            for(int g=0; g<board.column; g++)

                if((g+2)<board.column)
                {
                    if ((board.cells[a][g]).getValue() == Cell.CellValue.S_cell && (board.cells[a][g + 1]).getValue() == Cell.CellValue.O_cell && (board.cells[a][g + 2]).getValue() == Cell.CellValue.EMPTY)
                    {
                        System.out.println("IN IF");

                        position=new CellPosition(a,g+2);
                        value = Cell.CellValue.S_cell;
                        isWin=true;

                    }
                    if((board.cells[a][g]).getValue() == Cell.CellValue.S_cell && (board.cells[a][g + 2]).getValue() == Cell.CellValue.S_cell &&(board.cells[a][g + 1]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(a,g+1);
                        value = Cell.CellValue.O_cell;
                        isWin=true;
                    }
                    if((board.cells[a][g + 1]).getValue() == Cell.CellValue.O_cell && (board.cells[a][g + 2]).getValue() == Cell.CellValue.S_cell && (board.cells[a][g]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(a,g);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }

                }

        }
        for (int a = 0; a < board.row; a++) // for every column
        {
            for(int g=0; g< board.column; g++)
                if ((a+2) < board.column)
                {
                    if ((board.cells[a][g]).getValue() == Cell.CellValue.S_cell && board.cells[a + 1][g].getValue() == Cell.CellValue.O_cell && (board.cells[a+2][g]).getValue() == Cell.CellValue.EMPTY) {

                        System.out.println("IN IF");

                        position=new CellPosition(a+2,g);
                        value = Cell.CellValue.S_cell;
                        isWin=true;

                    }
                    if((board.cells[a][g]).getValue() == Cell.CellValue.S_cell && (board.cells[a+2][g]).getValue() == Cell.CellValue.S_cell && (board.cells[a+1][g]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(a+1,g);
                        value = Cell.CellValue.O_cell;
                        isWin=true;
                    }
                    if((board.cells[a+1][g]).getValue() == Cell.CellValue.O_cell && (board.cells[a+2][g]).getValue() == Cell.CellValue.S_cell && (board.cells[a][g]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(a,g);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }
                }
        }
        for (int i = 0; i < board.row; i++) {
            for (int g = 0; g < board.column; g++) {
                if ((i+2) < board.row && (g+2) < board.column)
                {
                    if (board.cells[i][g].getValue() == Cell.CellValue.S_cell && (board.cells[i + 1][g + 1]).getValue() ==  Cell.CellValue.O_cell && (board.cells[i+2][g + 2]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(i+2,g+2);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }
                    if((board.cells[i][g]).getValue() == Cell.CellValue.S_cell && (board.cells[i+2][g+2]).getValue() == Cell.CellValue.S_cell && (board.cells[i+1][g + 1]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(i+1,g+1);
                        value = Cell.CellValue.O_cell;
                        isWin=true;
                    }
                    if((board.cells[i+1][g+1]).getValue() == Cell.CellValue.O_cell && (board.cells[i+2][g+2]).getValue() == Cell.CellValue.S_cell && (board.cells[i][g]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(i,g);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }
                }
                if ((i-2)>= 0 && (g+2) < board.column)
                {
                    if (board.cells[i][g].getValue() == Cell.CellValue.S_cell && (board.cells[i - 1][g + 1]).getValue() == Cell.CellValue.O_cell && (board.cells[i-2][g + 2]).getValue() == Cell.CellValue.EMPTY) {

                        position=new CellPosition(i-2,g+2);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }

                    if((board.cells[i][g]).getValue() == Cell.CellValue.S_cell && (board.cells[i-2][g+2]).getValue() == Cell.CellValue.S_cell && (board.cells[i-1][g + 1]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(i-1,g+1);
                        value = Cell.CellValue.O_cell;
                        isWin=true;
                    }
                    if((board.cells[i-1][g+1]).getValue() == Cell.CellValue.O_cell && (board.cells[i-2][g+2]).getValue() == Cell.CellValue.S_cell && (board.cells[i][g]).getValue() == Cell.CellValue.EMPTY)
                    {
                        position=new CellPosition(i,g);
                        value = Cell.CellValue.S_cell;
                        isWin=true;
                    }

                }
            }
        }


        return position;
    }
}