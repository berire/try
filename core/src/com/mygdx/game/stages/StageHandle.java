package com.mygdx.game.stages;

import com.mygdx.game.helper.Helper;

/**
 * Created by user on 18.8.2016.
 */
public class StageHandle  {
    private Main_Menu menu;
    private Transition trans;
    private VS_GameScreen vs;
    private Solo_Game_screen solo;

    /*private Settings settings;
    private Credits credits;
    private Help help;*/

    public StageHandle()
    {
        menu=new Main_Menu( );
        trans=new Transition( );
        vs= new VS_GameScreen( );
        solo= new Solo_Game_screen( );

    }
    public void creator( ){
        if(Helper.getInstance().screen== Helper.ScreenType.MAIN)
        {
            menu.render( );

        }
        /*else if(Helper.getInstance().screen== Helper.ScreenType.TRANS){
            menu.dispose();
            trans.render();
            if(menu.change==1&& trans.forward)
            {
                Helper.getInstance().screen= Helper.ScreenType.SOLO;
            }
            if(menu.change==2 && trans.forward)
            {
                Helper.getInstance().screen= Helper.ScreenType.VS;
            }

        }*/
        else if(Helper.getInstance().screen== Helper.ScreenType.SOLO)
        {
            trans.dispose();
            solo.render();
        }
        else if(Helper.getInstance().screen== Helper.ScreenType.VS)
        {

            trans.dispose();
           vs.render();
        }

    }
}
