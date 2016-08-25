package com.mygdx.game.board;

/**
 * Created by user on 8.8.2016.
 */
public class CellPosition extends Cell {
    int R;
    int C;

    public CellPosition(CellPosition position) {
        this.R = position.R;
        this.C = position.C;
    }

    public CellPosition(int r, int c) {
        this.R = r;
        this.C = c;
    }

    public int getRow() {
        return R;
    }

    public int getColumn() {
        return C;
    }

    public void setRow(int r) {
        this.R= r;
    }

    public void setColumn(int c) {
        this.C = c;
    }
}
