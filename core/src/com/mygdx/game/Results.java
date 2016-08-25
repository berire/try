package com.mygdx.game;

import com.mygdx.game.board.Board;
import com.mygdx.game.player.Player;

/**
 * Created by user on 8.8.2016.
 */
public class Results {

    public enum state{
      WINNER,DRAW,
    }
    Board board;
    Player p1,p2,winner;
    int row_size,column_size=0;
    public Results(Board board, Player p1,Player p2)
    {
        this.board=board;
        this.p1=p1;
        this.p2=p2;
        row_size=board.getNumRow();
        column_size=board.getNumColumn();
    }

    public Results.state getResults( )
    {
        Results.state currentstate=null;
        if(p1.getPlayerScore(board)>p2.getPlayerScore(board) || p2.getPlayerScore(board)>p1.getPlayerScore(board)){

            currentstate= state.WINNER;
        }else if(p1.getPlayerScore(board)== p2.getPlayerScore(board)){

            currentstate= state.DRAW;
        }

        return currentstate;
    }
    public Player getWinner(){
        if(p1.getPlayerScore(board)>p2.getPlayerScore(board))
        {
            winner=p1;
        }
        else if(p2.getPlayerScore(board)>p1.getPlayerScore(board))
        {
            winner=p2;
        }
        return winner;
    }
    public Boolean ISgameOver( ) {
        return getResults() == state.WINNER || board.emptyCellPositions().size() == 0;
    }
}
