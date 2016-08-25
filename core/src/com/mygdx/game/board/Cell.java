package com.mygdx.game.board;

/**
 * Created by user on 8.8.2016.
 */
public class Cell extends Board {
    public enum CellValue {
        EMPTY, S_cell, O_cell
    }

    public enum CrossDegree{
        CR_U,CR_D,FL,UD,UN
    }

    CrossDegree degree;
    CellPosition position;
    CellValue value;
    public Boolean isCrossed=false;


    public Cell(){}

    public Cell(Cell cell) {
        this.degree=CrossDegree.UN;
        this.position = new CellPosition(cell.position);
        this.value = cell.value;
        this.isCrossed=false;
    }

    public Cell(CellPosition position) {
        this.degree=CrossDegree.UN;
        this.position = position;
        this.value = CellValue.EMPTY;
        this.isCrossed=false;
    }

    public Cell(CellPosition position, CellValue value) {
        this.degree=CrossDegree.UN;
        this.position = position;
        this.value = value;
        this.isCrossed=false;
    }

    public CellValue getValue() {return value;}
    public CrossDegree getDegree(){
        System.out.println("GET DEGREE");
        return this.degree;
    }
    public void setDegree(CrossDegree degree1)
    {
        System.out.println("SET DEGREE");
        this.degree=degree1;
    }

    public void setValue(CellValue value){this.value=value;}

    public CellPosition getPosition() { return position; }

    public void setPosition (CellPosition p){
        position=p;
    }

    public boolean ISCrossed(){
        return this.isCrossed;}

    public void crossCell(){
       this.isCrossed=true;
    }

}
