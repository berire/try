package com.mygdx.game.board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 8.8.2016.
 */
public class Board {

    public int row;
    public int column;
    public Cell[][] cells;
    private LinkedList<Cross> crosses;


    //Creating board with array of cells and determining cell positions
    public Board (){
        row=0;
        column=0;
        crosses=new LinkedList<Cross>();
    }
    public Board(int r,int c) {
        this.row=r;
        this.column=c;
        this.cells= new Cell[r][c];
        for (int i = 0; i < r; i++) {
            for (int g = 0; g < c; g++)
                cells[i][g] = new Cell(new CellPosition(i, g));

        }

        crosses=new LinkedList<Cross>();

    }
    public int getNumRow(){
        return row;
    }
    public int getNumColumn(){
        return column;
    }
    public List<CellPosition> emptyCellPositions(Board board) {
        List<CellPosition> positions = new ArrayList<CellPosition>();
        for (int i = 0; i < board.cells.length; i++) {
            for (int x = 0; x < board.cells[i].length; x++) {
                if (board.cells[i][x].value == Cell.CellValue.EMPTY) {
                    positions.add(board.cells[i][x].position);
                }
            }
        }
        return positions;
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

    public Cell cellAtPosition(CellPosition position) {
        return cells[position.R][position.C];
    }

    public LinkedList<Cross> getCrosses()
    {
        return crosses;
    }


    public void CROSS ( ) {
        for (int a = 0; a < this.row; a++) // for every row
        {
            for(int g=0; g<this.column; g++)

            if((g+2)<this.column)
            {
                if ((cells[a][g]).getValue() == Cell.CellValue.S_cell && (cells[a][g + 1]).getValue() == Cell.CellValue.O_cell && (cells[a][g + 2]).getValue() == Cell.CellValue.S_cell)
                {
                    Cross crossUD= new Cross();

                    (cells[a][g]).crossCell();
                    (cells[a][g]).setDegree(Cell.CrossDegree.UD);

                    (cells[a][g + 1]).crossCell();
                    (cells[a][g + 1]).setDegree(Cell.CrossDegree.UD);

                    (cells[a][g + 2]).crossCell();
                    (cells[a][g + 2]).setDegree(Cell.CrossDegree.UD);

                    crossUD.addCross((cells[a][g]),(cells[a][g + 1]),(cells[a][g + 2]));
                    crosses.add(crossUD);
                }
            }

        }
        for (int a = 0; a < this.row; a++) // for every column
        {
            for(int g=0; g< this.column; g++)
            if ((a+2) < this.column)
            {
                if ((cells[a][g]).getValue() == Cell.CellValue.S_cell && cells[a + 1][g].getValue() == Cell.CellValue.O_cell && cells[a + 2][g].getValue() == Cell.CellValue.S_cell) {

                    Cross crossFL= new Cross();

                    cells[a][g].crossCell();
                    cells[a][g].setDegree(Cell.CrossDegree.FL);

                    cells[a + 1][g].crossCell();
                    cells[a + 1][g].setDegree(Cell.CrossDegree.FL);

                    cells[a + 2][g].crossCell();
                    cells[a + 2][g].setDegree(Cell.CrossDegree.FL);

                    crossFL.addCross(cells[a][g],cells[a + 1][g],cells[a + 2][g]);
                    crosses.add(crossFL);

                }
            }
        }
        for (int i = 0; i < this.row; i++) {
            for (int g = 0; g < this.column; g++) {
                if ((i+2) < this.row && (g+2) < this.column)
                {
                    if (cells[i][g].getValue() == Cell.CellValue.S_cell && (cells[i + 1][g + 1]).getValue() ==  Cell.CellValue.O_cell && (cells[i + 2][g + 2]).getValue() == Cell.CellValue.S_cell)
                    {
                        Cross crossCRD= new Cross();

                        cells[i][g].crossCell();
                        cells[i][g].setDegree(Cell.CrossDegree.CR_D);

                        cells[i + 1][g + 1].crossCell();
                        cells[i + 1][g + 1].setDegree(Cell.CrossDegree.CR_D);

                        cells[i + 2][g + 2].crossCell();
                        cells[i + 2][g + 2].setDegree(Cell.CrossDegree.CR_D);

                        crossCRD.addCross(cells[i][g],cells[i + 1][g + 1],cells[i + 2][g + 2]);
                        crosses.add(crossCRD);

                    }
                }
                if ((i-2)>= 0 && (g+2) < this.column)
                {
                    if (cells[i][g].getValue() == Cell.CellValue.S_cell && (cells[i - 1][g + 1]).getValue() == Cell.CellValue.O_cell && (cells[i - 2][g + 2]).getValue() == Cell.CellValue.S_cell) {

                        Cross crossCRU= new Cross();

                        cells[i][g].crossCell();
                        cells[i][g].setDegree(Cell.CrossDegree.CR_U);


                        cells[i - 1][g + 1].crossCell();
                        cells[i - 1][g + 1].setDegree(Cell.CrossDegree.CR_U);


                        cells[i - 2][g + 2].crossCell();
                        cells[i - 2][g + 2].setDegree(Cell.CrossDegree.CR_U);

                        crossCRU.addCross(cells[i][g],cells[i - 1][g + 1], cells[i - 2][g + 2]);
                        crosses.add(crossCRU);
                    }
                }
            }
        }
    }
}