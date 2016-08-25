package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;

import java.util.LinkedList;

public class Transition extends Screens  {
    public boolean forward=false;
    public enum boardSizes{
        THREE,FOUR,FIVE,SEVEN,SIX,EIGHT
    }
    public static boardSizes TheBoardSize;

    private static Sprite Black_bg;
    private Stage transition_stage;
    private BitmapFont main_font;
    private Group transitionGroup;

    private LinkedList<TextButton> textButtons;
    private TextButton btn1,btn2,btn3,btn4,btn5;

    private boolean b1=false;
    private boolean b2=false;
    private boolean b3=false;
    private boolean b4=false;
    private boolean b5=false;

    public static Sprite ThreeBoard,FourBoard,FiveBoard,SevenBoard,SixBoard,EightBoard;

    private Skin skin;
    private TextureAtlas atlas;

    public Transition( ){
        transition_stage=new Stage(SOSGame.view,SOSGame.batch);
        transitionGroup=new Group();
        textButtons=new LinkedList<TextButton>();

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin();
        skin.addRegions(atlas);


        main_font=new BitmapFont(Gdx.files.internal("thefont.fnt"));

        Black_bg=new Sprite(atlas.createSprite("bg_black"));

        TextButton.TextButtonStyle style =new TextButton.TextButtonStyle(null,null,null, main_font);
        style.fontColor= new Color(Color.WHITE);

        btn1 = new TextButton("4 X 4",style);
        transitionGroup.addActor(btn1);
        textButtons.add(btn1);

        btn2 = new TextButton("5 X 5",style);
        transitionGroup.addActor(btn2);
        textButtons.add(btn2);

        btn3 = new TextButton("6 X 6",style);
        transitionGroup.addActor(btn3);
        textButtons.add(btn3);

        btn4 = new TextButton("7 X 7",style);
        transitionGroup.addActor(btn4);
        textButtons.add(btn4);

        btn5 = new TextButton("8 X 8",style);
        transitionGroup.addActor(btn5);
        textButtons.add(btn5);

        int y=(SOSGame.HEIGHT/100)*31;
        int x=(SOSGame.WIDTH/100)*40;

        for(int i=textButtons.size()-1; i>=0; i--)
        {
            textButtons.get(i).setPosition(x,y);
            textButtons.get(i).setHeight((SOSGame.HEIGHT/100)*8);
            textButtons.get(i).setWidth((SOSGame.WIDTH/100)*26);
            y=y+(SOSGame.HEIGHT/100)*11;
        }
        transitionGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
            float x,
            float y,
            int pointer,
            int button){

                if(btn1.isPressed()){
                    System.out.println("FOUR");
                    setBoardSize(boardSizes.FOUR);
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    b1=true;
                    forward=true;
                }
                else if(btn2.isPressed()){
                    setBoardSize(boardSizes.FIVE);
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    b2=true;
                    forward=true;
                }else if(btn3.isPressed()){
                    setBoardSize(boardSizes.SIX);
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    forward=true;
                    b3=true;
                }else if(btn4.isPressed()){
                    setBoardSize(boardSizes.SEVEN);
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    forward=true;
                    b4=true;
                }else if(btn5.isPressed()){
                    setBoardSize(boardSizes.EIGHT);
                    b5=true;
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    forward=true;
                }else{
                    setBoardSize(boardSizes.THREE);
                    System.out.println("BOARD SIZE IS "+ getBoardSize());
                    forward=false;
                }
                return false;
            }
        });
        transition_stage.addActor(transitionGroup);
        ThreeBoard=new Sprite(atlas.createSprite("3x3"));
        FourBoard= new Sprite(atlas.createSprite("4x4"));
        FiveBoard= new Sprite(atlas.createSprite("5x5"));
        SixBoard= new Sprite(atlas.createSprite("6x6"));
        SevenBoard= new Sprite(atlas.createSprite("7x7"));
        EightBoard=new Sprite(atlas.createSprite("8x8"));
    }

    public void render( ) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(transition_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        transition_stage.act();

        SOSGame.batch.begin();
        SOSGame.batch.draw(Black_bg,0,0);
        SOSGame.batch.end();

        transition_stage.draw();

        if(b1)
        {
            VS_GameScreen.THEBOARD=FourBoard;
            VS_GameScreen.row=4;
            VS_GameScreen.column=4;
            VS_GameScreen.MAX_MOVES=16;
        }
        if(b2)
        {
            VS_GameScreen.THEBOARD=FiveBoard;
            VS_GameScreen.row=5;
            VS_GameScreen.column=5;
            VS_GameScreen.MAX_MOVES=25;
        }
        if(b3)
        {
            VS_GameScreen.THEBOARD=SixBoard;
            VS_GameScreen.row=6;
            VS_GameScreen.column=6;
            VS_GameScreen.MAX_MOVES=36;
        }
        if(b4)
        {
            VS_GameScreen.THEBOARD=SevenBoard;
            VS_GameScreen.row=7;
            VS_GameScreen.column=7;
            VS_GameScreen.MAX_MOVES=49;
        }
        if(b5)
        {
            VS_GameScreen.THEBOARD=EightBoard;
            VS_GameScreen.row=8;
            VS_GameScreen.column=8;
            VS_GameScreen.MAX_MOVES=64;
        }



    }
    public static boardSizes getBoardSize()
    {return TheBoardSize;}
    public static void setBoardSize(boardSizes sp)
    {
        TheBoardSize=sp;
    }
    public void dispose() {
        transition_stage.dispose();
    }
}
