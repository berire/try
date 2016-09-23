package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;
import com.mygdx.game.helper.Helper;

/**
 * Created by user on 28.8.2016.
 */
public class Options extends ScreenAdapter {

    SOSGame game;

    public static String Player1Name;
    public static String Player2Name;
    public static String sb1,sb2,sb3;
    public final String Options= "SETTINGS";


    public enum BackgroundColor
    {
       Blue,Black
    }
    public enum SymbolColors
    {
        Reds,Yellows
    }

    public enum Difficulty
    {
        EASY,HARD
    }


    public static Difficulty difficulty=Difficulty.EASY;
    public static SymbolColors S_colors=SymbolColors.Yellows;
    public static BackgroundColor B_colors=BackgroundColor.Black;

    private Sprite Background;
    private static Sprite black_bg,blue_bg,optionsImage;
    private Stage options_stage;
    private BitmapFont main_font,big_font;

    private TextButton btn1,btn2,btn3;
    private ImageButton backbutton;
    private TextField field1,field2;
    private Skin skin;
    private TextureAtlas atlas1,atlas2;

    private TextField.TextFieldStyle styleT;
    public final static String file = ".superjumper";

    public Options(final SOSGame game)
    {
        this.game=game;
        options_stage=new Stage(SOSGame.view,SOSGame.batch);

        if(difficulty==Difficulty.HARD){sb1="DIFFICULTY: HARD";}else{sb1="DIFFICULTY: EASY";}
        if(Background==blue_bg){sb2="BACKGROUND COLOR: BLUE";}else{sb2="BACKGROUND COLOR: BLACK";}
        if(S_colors==SymbolColors.Yellows){sb3="SYMBOL COLOR: BLUE/YELLOW";}else{sb3="SYMBOL COLOR: BLUE/RED";}

        atlas1=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        atlas2=new TextureAtlas(Gdx.files.internal("sosadd.pack"));
        skin= new Skin();
        skin.addRegions(atlas1);
        skin.addRegions(atlas2);

        main_font=new BitmapFont(Gdx.files.internal("small.fnt"));
        big_font=new BitmapFont(Gdx.files.internal("bigfont.fnt"));

        black_bg=new Sprite(atlas1.createSprite("bg_black"));
        blue_bg=new Sprite(atlas1.createSprite("bg_blue"));


        if(Helper.getInstance().optionsbgcolor==0)
            Background=black_bg;
        else if(Helper.getInstance().optionsbgcolor==1)
            Background=blue_bg;
        else
            Background=black_bg;


        optionsImage=new Sprite(atlas1.createSprite("bttn_settings"));

        styleT= new TextField.TextFieldStyle(main_font, Color.WHITE , null,null,null);

        field1=new TextField("NAME 1",styleT );
        field1.setSize(field1.getMinWidth(),field1.getMinHeight());
        field1.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*70);

        field2= new TextField("NAME 2", styleT);
        field2.setSize(field2.getMinWidth(),field2.getMinHeight());
        field2.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*60);

        field1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener textListener1 = new Input.TextInputListener()
                {
                    //SoundAssets.playSound(SoundAssets.clickSound);

                    @Override
                    public void input(String input)
                    {
                        Player1Name=input.toUpperCase();

                    }

                    @Override
                    public void canceled()
                    {

                    }

                };
                Gdx.input.getTextInput(textListener1, "SET PLAYER 1 NAME ", "VELI", "");
            }

        });
        field2.addListener(new ClickListener() {
            @Override
                public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener textListener2 = new Input.TextInputListener()
                {



                    @Override
                    public void input(String input)
                    {
                        Player2Name=input.toUpperCase();

                    }

                    @Override
                    public void canceled()
                    {

                    }

                };
                System.out.println(Player2Name);
                Gdx.input.getTextInput(textListener2, "SET PLAYER 2 NAME", "ALI", "");
            }

        });

        TextButton.TextButtonStyle style =new TextButton.TextButtonStyle(null,null,null, main_font);
        style.fontColor= new Color(Color.WHITE);

        backbutton=new ImageButton(skin.getDrawable("bttn_back"));
        backbutton.setPosition((SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*10);
        backbutton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);

        btn1 = new TextButton(sb1,style);
        btn1.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*50);
        btn1.setSize(btn1.getMinWidth(),btn1.getMinHeight());

        btn2 = new TextButton(sb2,style);
        btn2.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*40);
        btn2.setSize(btn2.getMinWidth(),btn2.getMinHeight());

        btn3 = new TextButton(sb3,style);
        btn3.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*30);
        btn3.setSize(btn3.getMinWidth(),btn3.getMinHeight());

        backbutton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button)
            {
                if(backbutton.isPressed())
                {
                    SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Main_Menu(game));}

                return false;
            }});

        btn1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( btn1.isChecked()) { // put breakpoint here
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn1.setText("DIFFICULTY: HARD");
                    difficulty=Difficulty.HARD;

                }else
                {
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn1.setText("DIFFICULTY: EASY");
                    difficulty=Difficulty.EASY;
                }}});
        btn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( btn2.isChecked()) { // put breakpoint here
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn2.setText("BACKGROUND COLOR: BLUE");
                    Helper.getInstance().optionsbgcolor=1;
                    B_colors= BackgroundColor.Blue;
                    Background=blue_bg;
                }else
                {
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn2.setText("BACKGROUND COLOR: BLACK");
                    Helper.getInstance().optionsbgcolor=0;
                    B_colors= BackgroundColor.Black;
                    Background=black_bg;
                }}});
        btn3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( btn3.isChecked()) { // put breakpoint here
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn3.setText("SYMBOL COLOR: BLUE/YELLOW");
                    S_colors= SymbolColors.Yellows;

                }else
                {
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    btn3.setText("SYMBOL COLOR: BLUE/RED");
                    S_colors= SymbolColors.Reds;


                }}});

        options_stage.addActor(btn1);
        options_stage.addActor(btn2);
        options_stage.addActor(btn3);
        options_stage.addActor(backbutton);
        options_stage.addActor(field1);
        options_stage.addActor(field2);


    }

    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(options_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        options_stage.act();
        SOSGame.batch.begin();
        SOSGame.batch.draw(Background,0,0,SOSGame.WIDTH,SOSGame.HEIGHT);
        SOSGame.batch.draw(optionsImage,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*80);
        big_font.draw(SOSGame.batch,Options,(SOSGame.WIDTH/100)*42,(SOSGame.HEIGHT/100)*85);
        SOSGame.batch.end();
        options_stage.draw();
    }

    public void dispose()
    {
        atlas1.dispose();
        atlas2.dispose();

    }

}
