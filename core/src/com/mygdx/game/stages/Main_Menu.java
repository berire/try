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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;
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
    private BitmapFont main_font,big_font;
    private Sprite background,Black_bg,Blue_bg;
    private Sprite SOS_textField,Solo,VS,settings,credits,help;
    private ImageButton sound_btn;
    private TextButton Solo_btn,VS_btn,settings_btn,credits_btn,help_btn;
    private LinkedList<Button> menu_btns;



    public Main_Menu(final SOSGame game) {
        this.game=game;

        menu_stage=new Stage(SOSGame.view,SOSGame.batch);
        menuGroup=new Group();
        menu_btns=new LinkedList<Button>();

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        main_font=new BitmapFont(Gdx.files.internal("general_font.fnt"));
        big_font=new BitmapFont(Gdx.files.internal("bigfont.fnt"));


        Black_bg = new Sprite(atlas.createSprite("bg_black"));
        Blue_bg= new Sprite(atlas.createSprite("bg_blue"));
        background=Black_bg;
        if(Options.B_colors==Options.BackgroundColor.Blue)
        {background=Blue_bg;}

        SOS_textField= new Sprite(atlas.createSprite("main_menu_decoration"));

        Solo=new Sprite(atlas.createSprite("bttn_android"));
        VS= new Sprite(atlas.createSprite("bttn_vsplay"));
        settings =new Sprite(atlas.createSprite("bttn_settings"));
        credits=new Sprite(atlas.createSprite("bttn_credits"));
        help=new Sprite(atlas.createSprite("bttn_help"));


        TextButton.TextButtonStyle style =new TextButton.TextButtonStyle(null,null,null, big_font);
        style.fontColor= new Color(Color.WHITE);

        Solo_btn=new TextButton("SOLO PLAY",style);
        menuGroup.addActor(Solo_btn);
        menu_btns.add(Solo_btn);

        VS_btn=new TextButton("VS. PLAY",style);
        menuGroup.addActor(VS_btn);
        menu_btns.add(VS_btn);

        settings_btn =new TextButton("SETTINGS",style);
        menuGroup.addActor(settings_btn);
        menu_btns.add(settings_btn);

        credits_btn=new TextButton("CREDITS",style);
        menuGroup.addActor(credits_btn);
        menu_btns.add(credits_btn);

        help_btn=new TextButton("HELP",style);
        menuGroup.addActor(help_btn);
        menu_btns.add(help_btn);

        int y=(SOSGame.HEIGHT/100)*2;
        int x=(SOSGame.WIDTH/100)*30 ;
        for(int i=menu_btns.size()-1; i>=0 ; i--)
        {
            menu_btns.get(i).setPosition(x,y);
            menu_btns.get(i).setHeight(menu_btns.get(i).getMinHeight());
            menu_btns.get(i).setWidth(menu_btns.get(i).getMinWidth());
            y=y+(SOSGame.HEIGHT/100)*14;
        }

        sound_btn=new ImageButton(skin.getDrawable("bttn_soundON"),null,null);
        sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
        sound_btn.setWidth((SOSGame.WIDTH/100)*30);
        sound_btn.setPosition(((SOSGame.WIDTH)-sound_btn.getWidth()),-(sound_btn.getHeight()/4));
        sound_btn.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               if( getTapCount()%2 == 0) {
                   soundEnabled=true;
                   sound_btn.setStyle(new ImageButton.ImageButtonStyle(skin.getDrawable("bttn_soundOFF"),null,null,null,null,null));
                   sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
                   sound_btn.setWidth((SOSGame.WIDTH/100)*30);
               }else
               {
                   soundEnabled=false;
                   sound_btn.setStyle(new ImageButton.ImageButtonStyle(skin.getDrawable("bttn_soundON"),null,null,null,null,null));
                   sound_btn.setHeight((SOSGame.HEIGHT/100)*35);
                   sound_btn.setWidth((SOSGame.WIDTH/100)*30);
               }}});
        menuGroup.addActor(sound_btn);

        menuGroup.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button){
                if(Solo_btn.isPressed()){
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    change=1;
                    if(Helper.getInstance().f2==1){
                        game.setScreen(new Transition(game));
                    }
                    else if(Helper.getInstance().f2==2 ){
                        game.setScreen(Transition.SOLO);}
                }
                if(VS_btn.isPressed()){
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    change=2;
                    if(Helper.getInstance().fl==1){
                        game.setScreen(new Transition(game));

                    }
                    else if(Helper.getInstance().fl==2 ){
                        game.setScreen(Transition.VS);}
                }if(settings_btn.isPressed()){
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Options(game));
                }else if(credits_btn.isPressed()){
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Credits(game));
                }else if(help_btn.isPressed()){
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Help(game));
                }
                return false;
            }
        });

        /*if (soundEnabled)
            SoundAssets.music.play();
        else
            SoundAssets.music.pause();*/


    }


    public void render(float delta) {
        menu_stage.addActor(menuGroup);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menu_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        menu_stage.act(Gdx.graphics.getDeltaTime());

        SOSGame.batch.begin();
        SOSGame.batch.draw(background,0,0);
        SOSGame.batch.end();

        SOSGame.batch.begin();
        SOSGame.batch.draw(SOS_textField,0,(SOSGame.HEIGHT/100)*70,SOSGame.WIDTH, (SOSGame.HEIGHT/100)*35);
        SOSGame.batch.draw(Solo,(SOSGame.WIDTH/100)*11,Solo_btn.getY(),(SOSGame.WIDTH/100)*12, (SOSGame.HEIGHT/100)*10);
        SOSGame.batch.draw(VS,(SOSGame.WIDTH/100)*11,VS_btn.getY(),(SOSGame.WIDTH/100)*12, (SOSGame.HEIGHT/100)*10);
        SOSGame.batch.draw(settings,(SOSGame.WIDTH/100)*11,settings_btn.getY(),(SOSGame.WIDTH/100)*12, (SOSGame.HEIGHT/100)*10);
        SOSGame.batch.draw(credits,(SOSGame.WIDTH/100)*11,credits_btn.getY(),(SOSGame.WIDTH/100)*12, (SOSGame.HEIGHT/100)*10);
        SOSGame.batch.draw(help,(SOSGame.WIDTH/100)*11,help_btn.getY(),(SOSGame.WIDTH/100)*12, (SOSGame.HEIGHT/100)*10);

        SOSGame.batch.end();

        menu_stage.draw();
    }

    public void dispose() {

        menu_stage.dispose();
    }


}
