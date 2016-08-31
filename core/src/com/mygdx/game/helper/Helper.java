package com.mygdx.game.helper;

/**
 * Created by user on 18.8.2016.
 */
public class Helper {
    public static Helper instance;

    public static Helper getInstance(){
        if(instance==null)
        {
            instance=new Helper ();
        }

        return instance;
    }
    public enum ScreenType{
        MAIN,VS,SOLO,TRANS
    }

    public ScreenType screen;
    public int fl,f2;

    public Helper(){
        screen=ScreenType.MAIN;
        fl=1;
        f2=1;
    }


}
