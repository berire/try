package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;
import com.mygdx.game.helper.Helper;

import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class Main_Menu extends ScreenAdapter {
    public SOSGame game;

    public static boolean soundEnabled = true;

    public Stage menu_stage;
    public static int change=0;

    private Skin skin;
    private TextureAtlas atlas;

    private Group menuGroup;
    private BitmapFont big_font;
    private Sprite background,Black_bg,Blue_bg,SOS_textField;
    private ImageButton Solo,VS,settings,credits,help;

    private ImageButton sound_btn;
    private Label Solo_btn,VS_btn,settings_btn,credits_btn,help_btn;
    private LinkedList<Button> menu_btns;

    public Main_Menu(final SOSGame game) {
        this.game=game;

        menu_stage=new Stage(SOSGame.view,SOSGame.batch);
        menuGroup=new Group();
        menu_btns=new LinkedList<Button>();

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        FreeTypeFontGenerator generator = new  FreeTypeFontGenerator(Gdx.files.internal("RightChalk11.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        float densityIndependentSize = (parameter.size) * Gdx.graphics.getDensity();
        int fontSize = Math.round(densityIndependentSize );
        parameter.size=fontSize;
        big_font = generator.generateFont(parameter);
        generator.dispose();

        Black_bg = new Sprite(atlas.createSprite("bg_black"));
        Blue_bg= new Sprite(atlas.createSprite("bg_blue"));
        background=Black_bg;
        if(Options.B_colors==Options.BackgroundColor.Blue)
        {background=Blue_bg;}

        SOS_textField= new Sprite(atlas.createSprite("main_menu_decoration"));
        SOS_textField.setSize(SOSGame.WIDTH, (SOSGame.HEIGHT/100)*35);
        SOS_textField.setPosition(0,(SOSGame.HEIGHT-((SOS_textField.getHeight()/100)*40)));

        Solo=new ImageButton(skin.getDrawable("bttn_android"));
        Solo.setSize((SOSGame.WIDTH/100)*21, (SOSGame.HEIGHT/100)*16);
        Solo.setPosition(0+((SOS_textField.getWidth()/17)*1),(SOSGame.HEIGHT/100)*5+(SOSGame.HEIGHT/100)*56);
        menuGroup.addActor(Solo);

        VS= new ImageButton(skin.getDrawable("bttn_vsplay"));
        VS.setPosition(0+((SOS_textField.getWidth()/17)*1),(SOSGame.HEIGHT/100)*5+(SOSGame.HEIGHT/100)*42);
        VS.setSize((SOSGame.WIDTH/100)*21, (SOSGame.HEIGHT/100)*16);
        menuGroup.addActor(VS);

        settings =new ImageButton(skin.getDrawable("bttn_settings"));
        settings.setPosition(0+((SOS_textField.getWidth()/17)*1),(SOSGame.HEIGHT/100)*5+(SOSGame.HEIGHT/100)*28);
        settings.setSize((SOSGame.WIDTH/100)*21, (SOSGame.HEIGHT/100)*15);
        menuGroup.addActor(settings);

        credits=new ImageButton(skin.getDrawable("bttn_credits"));
        credits.setPosition(0+((SOS_textField.getWidth()/17)*1),(SOSGame.HEIGHT/100)*5+(SOSGame.HEIGHT/100)*14);
        credits.setSize((SOSGame.WIDTH/100)*21, (SOSGame.HEIGHT/100)*15);
        menuGroup.addActor(credits);

        help=new ImageButton(skin.getDrawable("bttn_help"));
        help.setPosition(0+((SOS_textField.getWidth()/17)*1),(SOSGame.HEIGHT/100)*5);
        help.setSize((SOSGame.WIDTH/100)*21, (SOSGame.HEIGHT/100)*15);
        menuGroup.addActor(help);
        /////////////////////////////////////////
        TextButton.TextButtonStyle style =new TextButton.TextButtonStyle(null,null,null, big_font);
        style.fontColor= new Color(Color.WHITE);

        Label.LabelStyle labelStyle=new Label.LabelStyle(big_font,Color.WHITE);


        Solo_btn=new Label("SOLO PLAY",labelStyle);
        Solo_btn.setSize(Solo_btn.getMinWidth(), Solo_btn.getMinHeight());
        Solo_btn.setPosition((Solo.getX()+Solo.getWidth())+(Solo.getWidth()/8),((Solo.getY()+Solo.getHeight())-(Solo.getHeight()/100)*80));
        Solo_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Solo_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f)));
                Solo.addAction(Actions.sequence(Actions.fadeOut(0.01f)));
                float delay = 1/25; // seconds
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        SoundAssets.playSound(SoundAssets.clickSound);
                        change=1;
                        game.setScreen(new Transition(game));

                    }
                }, delay);
            }
        });
        menuGroup.addActor(Solo_btn);

        VS_btn=new Label("VS. PLAY",labelStyle);
        VS_btn.setSize(VS_btn.getMinWidth(), VS_btn.getMinHeight());
        VS_btn.setPosition((VS.getX()+VS.getWidth())+(VS.getWidth()/8),((VS.getY()+VS.getHeight())-(VS.getHeight()/100)*80));
        VS_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VS.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide()));
                VS_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                float delay = 1/20; // seconds
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        SoundAssets.playSound(SoundAssets.clickSound);
                        change=2;
                        game.setScreen(new Transition(game));

                    }
                }, delay);
            }
        });
        menuGroup.addActor(VS_btn);

        settings_btn =new Label("SETTINGS",labelStyle);
        settings_btn.setFontScale(1);
        settings_btn.setSize(settings_btn.getMinWidth(), settings_btn.getMinHeight());
        settings_btn.setPosition((settings.getX()+settings.getWidth())+(settings.getWidth()/8),((settings.getY()+settings.getHeight())-(settings.getHeight()/100)*80));
        settings_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settings.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                settings_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                float delay = 1/20; // seconds
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        SoundAssets.playSound(SoundAssets.clickSound);
                        game.setScreen(new Options(game));
                    }
                }, delay);
            }
        });
        menuGroup.addActor(settings_btn);

        credits_btn=new Label("CREDITS",labelStyle);
        credits_btn.setSize(credits_btn.getMinWidth(), credits_btn.getMinHeight());
        credits_btn.setPosition((credits.getX()+credits.getWidth())+(credits.getWidth()/8),((credits.getY()+credits.getHeight())-(credits.getHeight()/100)*80));
        credits_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                credits.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                credits_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                float delay = 1/20; // seconds
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        SoundAssets.playSound(SoundAssets.clickSound);
                        game.setScreen(new Credits(game));
                    }
                }, delay);
            }
        });
        menuGroup.addActor(credits_btn);

        help_btn=new Label("HELP",labelStyle);
        help_btn.setSize(help_btn.getMinWidth(),help_btn.getMinHeight());
        help_btn.setPosition((help.getX()+help.getWidth())+(help.getWidth()/8),((help.getY()+help.getHeight())-(help.getHeight()/100)*80));
        help_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                help.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                help_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                float delay = 1/20; // seconds
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        SoundAssets.playSound(SoundAssets.clickSound);
                        game.setScreen(new Help(game));
                    }
                }, delay);
            }
        });
        menuGroup.addActor(help_btn);


        sound_btn=new ImageButton(skin.getDrawable("bttn_soundON"));

        if(Helper.getInstance().Sound==0)
        {
            sound_btn.getStyle().imageUp=skin.getDrawable("bttn_soundON");
            sound_btn.getStyle().imageDown=skin.getDrawable("bttn_soundON");
            sound_btn.getStyle().imageChecked=skin.getDrawable("bttn_soundOFF");
            sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
            sound_btn.setWidth((SOSGame.WIDTH/100)*30);
            sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));

        }
        else if(Helper.getInstance().Sound==1)
        {
            sound_btn.getStyle().imageUp=skin.getDrawable("bttn_soundOFF");
            sound_btn.getStyle().imageDown=skin.getDrawable("bttn_soundOFF");
            sound_btn.getStyle().imageChecked=skin.getDrawable("bttn_soundON");
            sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
            sound_btn.setWidth((SOSGame.WIDTH/100)*30);
            sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));

        }
        else
        {
            sound_btn.getStyle().imageUp=skin.getDrawable("bttn_soundON");
            sound_btn.getStyle().imageDown=skin.getDrawable("bttn_soundON");
            sound_btn.getStyle().imageChecked=skin.getDrawable("bttn_soundOFF");
            sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
            sound_btn.setWidth((SOSGame.WIDTH/100)*30);
            sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));

        }

        sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
        sound_btn.setWidth((SOSGame.WIDTH/100)*30);
        sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));
        sound_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(Helper.getInstance().Sound==0)
               {
                   sound_btn.getStyle().imageUp=skin.getDrawable("bttn_soundON");
                   sound_btn.getStyle().imageDown=skin.getDrawable("bttn_soundON");
                   sound_btn.getStyle().imageChecked=skin.getDrawable("bttn_soundOFF");
                   sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
                   sound_btn.setWidth((SOSGame.WIDTH/100)*30);
                   sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));

                   if(sound_btn.isChecked())
                    {
                        SoundAssets.music.pause();
                        Helper.getInstance().Sound=1;
                    }    else
                    {
                   Helper.getInstance().Sound=0;
                   SoundAssets.music.play();
                    }
               }else if(Helper.getInstance().Sound==1)
                {
                    sound_btn.getStyle().imageUp=skin.getDrawable("bttn_soundOFF");
                    sound_btn.getStyle().imageDown=skin.getDrawable("bttn_soundOFF");
                    sound_btn.getStyle().imageChecked=skin.getDrawable("bttn_soundON");
                    sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
                    sound_btn.setWidth((SOSGame.WIDTH/100)*30);
                    sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));
                    if(sound_btn.isChecked())
                    {
                        Helper.getInstance().Sound=0;
                        SoundAssets.music.play();
                    }    else
                    {
                        SoundAssets.music.pause();
                        Helper.getInstance().Sound=1;

                    }
                }
            }

        });


        menuGroup.addActor(sound_btn);


        menuGroup.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button){
                if(Solo.isPressed()){
                    Solo.addAction(Actions.sequence(Actions.fadeOut(0.01f)));
                    Solo_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f)));
                    float delay = 1/20; // seconds
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            SoundAssets.playSound(SoundAssets.clickSound);
                            change=1;
                            game.setScreen(new Transition(game));

                        }
                    }, delay);
                }
                if(VS.isPressed()){
                    VS.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide()));
                    VS_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    float delay = 1/20; // seconds
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            SoundAssets.playSound(SoundAssets.clickSound);
                            change=2;
                            game.setScreen(new Transition(game));

                        }
                    }, delay);
                }if(settings.isPressed()){
                    settings.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    settings_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    float delay = 1/20; // seconds
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            SoundAssets.playSound(SoundAssets.clickSound);
                            game.setScreen(new Options(game));
                        }
                    }, delay);

                }else if(credits.isPressed()){
                    credits.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    credits_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    float delay = 1/20; // seconds
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            SoundAssets.playSound(SoundAssets.clickSound);
                            game.setScreen(new Credits(game));
                        }
                    }, delay);
                }else if(help.isPressed()){
                    help.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    help_btn.addAction(Actions.sequence(Actions.fadeOut(0.01f),Actions.hide ()));
                    float delay = 1/20; // seconds
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            SoundAssets.playSound(SoundAssets.clickSound);
                            game.setScreen(new Help(game));
                        }
                    }, delay);
                }
                return false;
            }
        });


    }
    public void render(float delta) {
        menu_stage.addActor(menuGroup);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menu_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        menu_stage.act(Gdx.graphics.getDeltaTime());

        SOSGame.batch.begin();
        SOSGame.batch.draw(background,0,0,SOSGame.WIDTH,SOSGame.HEIGHT);
        SOSGame.batch.end();

        SOSGame.batch.begin();
        SOSGame.batch.draw(SOS_textField,0,(SOSGame.HEIGHT-((SOS_textField.getHeight()/100)*80)),SOSGame.WIDTH, (SOSGame.HEIGHT/100)*35);
        SOSGame.batch.end();

        menu_stage.draw();
    }

    public void dispose() {

        menu_stage.dispose();
    }


}