package com.mygdx.game.player;

import com.mygdx.game.board.Board;

/**
 * Created by user on 8.8.2016.
 */
public class Player {
    String playerName;
    int playerScore=0;

    public enum Player_type{
        AI,Human
    }
    Player_type Ptype;

    public Player(){
        this.playerName=null;
        this.Ptype=null;
    }

    public Player(String name,Player_type type)
    {
        this.playerName=name;
        this.Ptype=type;

    }
    public String getName( ){
        return playerName;
    }
    public Player_type getType(){
        return Ptype;
    }
    public int getPlayerScore(Board board){
        return this.playerScore;}
    public void SCORE() {
        this.playerScore ++;
    }
}
