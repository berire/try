package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;

import java.util.LinkedList;

public class Transition extends ScreenAdapter {

    final SOSGame game;

    private static Sprite Background,black_bg,blue_bg;
    private Stage transition_stage;
    private BitmapFont main_font;
    private Group transitionGroup;

    private LinkedList<TextButton> textButtons;
    private TextButton btn1,btn2,btn3,btn4,btn5;
    private ImageButton backbutton;

    public  Sprite ThreeBoard,FourBoard,FiveBoard,SevenBoard,SixBoard,EightBoard;

    private Skin skin;
    private TextureAtlas atlas;

    public VS_GameScreen VS;
    public Solo_Game_screen SOLO;

    public Transition(final SOSGame game){
        this.game=game;


        transition_stage=new Stage(SOSGame.view,SOSGame.batch);
        transitionGroup=new Group();
        textButtons=new LinkedList<TextButton>();

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin();
        skin.addRegions(atlas);

        main_font=new BitmapFont(Gdx.files.internal("bigfont.fnt"));

        TextButton.TextButtonStyle style =new TextButton.TextButtonStyle(null,null,null, main_font);
        style.fontColor= new Color(Color.WHITE);

        black_bg=new Sprite(atlas.createSprite("bg_black"));
        blue_bg=new Sprite(atlas.createSprite("bg_blue"));
        Background=black_bg;
        if(Options.B_colors== Options.BackgroundColor.Blue)
        {
            Background=blue_bg;
        }

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


        backbutton=new ImageButton(skin.getDrawable("bttn_back"));
        backbutton.setPosition((SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*10);
        backbutton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        backbutton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button)
            {
                if(backbutton.isPressed())
                {//SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Main_Menu(game));}

                return false;
            }});
        transitionGroup.addActor(backbutton);

        int y=(SOSGame.HEIGHT/100)*31;
        int x=(SOSGame.WIDTH/100)*40;

        for(int i=textButtons.size()-1; i>=0; i--)
        {
            textButtons.get(i).setPosition(x,y);
            textButtons.get(i).setHeight((SOSGame.HEIGHT/100)*8);
            textButtons.get(i).setWidth((SOSGame.WIDTH/100)*26);
            y=y+(SOSGame.HEIGHT/100)*11;
        }

        transition_stage.addActor(transitionGroup);

        ThreeBoard=new Sprite(atlas.createSprite("3x3"));
        FourBoard= new Sprite(atlas.createSprite("4x4"));
        FiveBoard= new Sprite(atlas.createSprite("5x5"));
        SixBoard= new Sprite(atlas.createSprite("6x6"));
        SevenBoard= new Sprite(atlas.createSprite("7x7"));
        EightBoard=new Sprite(atlas.createSprite("8x8"));

        transitionGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button){

                if(btn1.isPressed()){
                    SoundAssets.playSound(SoundAssets.clickSound);
                    VS_GameScreen.THEBOARD=FourBoard;
                    VS_GameScreen.row=4;
                    VS_GameScreen.column=4;
                    VS_GameScreen.MAX_MOVES=16;

                    Solo_Game_screen.THEBOARD=FourBoard;
                    Solo_Game_screen.row=4;
                    Solo_Game_screen.column=4;
                    Solo_Game_screen.MAX_MOVES=16;

                    if(Main_Menu.change==1)
                    {
                        SOLO= new Solo_Game_screen(game);
                        game.setScreen(SOLO);
                    }
                    if(Main_Menu.change==2)
                    {
                        VS= new VS_GameScreen(game);
                        game.setScreen(VS);
                    }

                }
                else if(btn2.isPressed()){
                    SoundAssets.playSound(SoundAssets.clickSound);
                    VS_GameScreen.THEBOARD=FiveBoard;
                    VS_GameScreen.row=5;
                    VS_GameScreen.column=5;
                    VS_GameScreen.MAX_MOVES=25;

                    Solo_Game_screen.THEBOARD=FiveBoard;
                    Solo_Game_screen.row=5;
                    Solo_Game_screen.column=5;
                    Solo_Game_screen.MAX_MOVES=25;

                    if(Main_Menu.change==1)
                    {
                        SOLO= new Solo_Game_screen(game);
                        game.setScreen(SOLO);
                    }
                    if(Main_Menu.change==2)
                    {
                        VS= new VS_GameScreen(game);
                        game.setScreen(VS);
                    }

                }else if(btn3.isPressed()){
                    SoundAssets.playSound(SoundAssets.clickSound);
                    VS_GameScreen.THEBOARD=SixBoard;
                    VS_GameScreen.row=6;
                    VS_GameScreen.column=6;
                    VS_GameScreen.MAX_MOVES=36;

                    Solo_Game_screen.THEBOARD=SixBoard;
                    Solo_Game_screen.row=6;
                    Solo_Game_screen.column=6;
                    Solo_Game_screen.MAX_MOVES=36;

                    if(Main_Menu.change==1)
                    {
                        SOLO= new Solo_Game_screen(game);
                        game.setScreen(SOLO);
                    }
                    if(Main_Menu.change==2)
                    {
                        VS= new VS_GameScreen(game);
                        game.setScreen(VS);
                    }
                }else if(btn4.isPressed()){
                    SoundAssets.playSound(SoundAssets.clickSound);
                    VS_GameScreen.THEBOARD=SevenBoard;
                    VS_GameScreen.row=7;
                    VS_GameScreen.column=7;
                    VS_GameScreen.MAX_MOVES=49;

                    Solo_Game_screen.THEBOARD=SevenBoard;
                    Solo_Game_screen.row=7;
                    Solo_Game_screen.column=7;
                    Solo_Game_screen.MAX_MOVES=49;

                    if(Main_Menu.change==1)
                    {
                        SOLO= new Solo_Game_screen(game);
                        game.setScreen(SOLO);
                    }
                    if(Main_Menu.change==2)
                    {
                        VS= new VS_GameScreen(game);
                        game.setScreen(VS);
                    }
                }else if(btn5.isPressed()){
                    SoundAssets.playSound(SoundAssets.clickSound);
                    VS_GameScreen.THEBOARD=EightBoard;
                    VS_GameScreen.row=8;
                    VS_GameScreen.column=8;
                    VS_GameScreen.MAX_MOVES=64;

                    Solo_Game_screen.THEBOARD=EightBoard;
                    Solo_Game_screen.row=8;
                    Solo_Game_screen.column=8;
                    Solo_Game_screen.MAX_MOVES=64;

                    if(Main_Menu.change==1)
                    {
                        SOLO= new Solo_Game_screen(game);
                        game.setScreen(SOLO);
                    }
                    if(Main_Menu.change==2)
                    {
                        VS= new VS_GameScreen(game);
                        game.setScreen(VS);
                    }
                }
                return false;
            }
        });

    }
    public void render(float deltaTime ) {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(transition_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        transition_stage.act();

        SOSGame.batch.begin();
        SOSGame.batch.draw(Background,0,0);
        SOSGame.batch.end();

        transition_stage.draw();
    }
    public void dispose() {
        transition_stage.dispose();
    }
}
