package com.mygdx.game.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8.8.2016.
 */
public class Board {

    public int row;
    public int column;
    public Cell[][] cells;

    //Creating board with array of cells and determining cell positions
    public Board (){
        row=0;
        column=0;
    }
    public Board(int r,int c) {
        this.row=r;
        this.column=c;
        this.cells= new Cell[r][c];
        for (int i = 0; i < r; i++) {
            for (int g = 0; g < c; g++)
                cells[i][g] = new Cell(new CellPosition(i, g));

        }

    }
    public int getNumRow(){
        return row;
    }
    public int getNumColumn(){
        return column;
    }
    public List<CellPosition> emptyCellPositions() {
        List<CellPosition> positions = new ArrayList<CellPosition>();
        for (int i = 0; i < cells.length; i++) {
            for (int x = 0; x < cells[i].length; x++) {
                if (cells[i][x].value == Cell.CellValue.EMPTY) {
                    positions.add(cells[i][x].position);
                }
            }
        }
        return positions;
    }

    /*public  Board boardAfterMove(CellPosition position, Cell.CellValue newValue) {
        Board nextBoard = new Board(row,column);
        nextBoard.cells[position.R][position.C].position = position;
        nextBoard.cells[position.R][position.C].value = newValue;
        return nextBoard;
    }*/

    /*private int calculateScoreForRow(HashMap<Cell.CellValue, Integer> counts) {
        int score = 0;
        int emptyCount = counts.get(Cell.CellValue.EMPTY);
        int SCount = counts.get(Cell.CellValue.S_cell);
        int OCount = counts.get(Cell.CellValue.O_cell);
        return score;
    }*/

    public Board renewBoard(Board b1){
        b1= new Board(b1.getNumRow(),b1.getNumColumn());
        return b1;
    }

    public void setCell(CellPosition position, Cell.CellValue value) {
        if (cells[position.R][position.C].value == Cell.CellValue.EMPTY) {
            cells[position.R][position.C].setValue(value);
            cells[position.R][position.C].setPosition(position);
        } else {
            ////////CELLL ALREADY TAKEN ERROR VER
            System.out.println("CELL ALREADY TAKEN");
        }
    }

    public void clearBoard() {
        this.cells = new Cell[row][column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                cells[r][c] = new Cell(new CellPosition(r, c));
            }
        }
    }

    public Cell cellAtPosition(CellPosition position) {
        return cells[position.R][position.C];
    }


    public void CROSS ( ) {
        System.out.println("CROSSED ıs called");
        for (int a = 0; a < this.row; a++) // for every row
        {
            for(int g=0; g<this.column; g++)

            if((g+2)<this.column)
            {
                if ((cells[a][g]).getValue() == Cell.CellValue.S_cell && (cells[a][g + 1]).getValue() == Cell.CellValue.O_cell && (cells[a][g + 2]).getValue() == Cell.CellValue.S_cell)
                {
                    System.out.println("IN IF");

                    (cells[a][g]).crossCell();
                    (cells[a][g]).setDegree(Cell.CrossDegree.UD);

                    (cells[a][g + 1]).crossCell();
                    (cells[a][g + 1]).setDegree(Cell.CrossDegree.UD);

                    (cells[a][g + 2]).crossCell();
                    (cells[a][g + 2]).setDegree(Cell.CrossDegree.UD);
                }
            }

        }
        for (int a = 0; a < this.row; a++) // for every column
        {
            for(int g=0; g< this.column; g++)
            if ((a+2) < this.column)
            {
                if ((cells[a][g]).getValue() == Cell.CellValue.S_cell && cells[a + 1][g].getValue() == Cell.CellValue.O_cell && cells[a + 2][g].getValue() == Cell.CellValue.S_cell) {

                    System.out.println("IN IF");

                    cells[a][g].crossCell();
                    cells[a][g].setDegree(Cell.CrossDegree.FL);

                    cells[a + 1][g].crossCell();
                    cells[a + 1][g].setDegree(Cell.CrossDegree.FL);

                    cells[a + 2][g].crossCell();
                    cells[a + 2][g].setDegree(Cell.CrossDegree.FL);

                }
            }
        }
        for (int i = 0; i < this.row; i++) {
            for (int g = 0; g < this.column; g++) {
                if ((i+2) < this.row && (g+2) < this.column)
                {
                    if (cells[i][g].getValue() == Cell.CellValue.S_cell && (cells[i + 1][g + 1]).getValue() ==  Cell.CellValue.O_cell && (cells[i + 2][g + 2]).getValue() == Cell.CellValue.S_cell)
                    {
                        cells[i][g].crossCell();
                        cells[i][g].setDegree(Cell.CrossDegree.CR_D);

                        cells[i + 1][g + 1].crossCell();
                        cells[i + 1][g + 1].setDegree(Cell.CrossDegree.CR_D);

                        cells[i + 2][g + 2].crossCell();
                        cells[i + 2][g + 2].setDegree(Cell.CrossDegree.CR_D);
                    }
                }
                if ((i-2)>= 0 && (g+2) < this.column)
                {
                    if (cells[i][g].getValue() == Cell.CellValue.S_cell && (cells[i - 1][g + 1]).getValue() == Cell.CellValue.O_cell && (cells[i - 2][g + 2]).getValue() == Cell.CellValue.S_cell) {

                        cells[i][g].crossCell();
                        cells[i][g].setDegree(Cell.CrossDegree.CR_U);

                        cells[i - 1][g + 1].crossCell();
                        cells[i - 1][g + 1].setDegree(Cell.CrossDegree.CR_U);

                        cells[i - 2][g + 2].crossCell();
                        cells[i - 2][g + 2].setDegree(Cell.CrossDegree.CR_U);
                    }
                }
            }
        }
    }
}