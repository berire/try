package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;
import com.mygdx.game.helper.Helper;

import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class Main_Menu {
    public Stage menu_stage;
    public Boolean b1=false;Boolean b2=false;Boolean b3=false;Boolean b4=false;Boolean b5=false;Boolean b6=false;
    public static int change=0;

    private Skin skin;
    private TextureAtlas atlas;

    private Group menuGroup;
    private BitmapFont main_font;
    private Sprite background;
    private Sprite SOS_textField;
    private ImageButton Solo_btn,VS_btn,settings_btn,credits_btn,help_btn,sound_btn;
    private LinkedList<Button> menu_btns;

    private VS_GameScreen VS;
    public Main_Menu() {
        menu_stage=new Stage(SOSGame.view,SOSGame.batch);
        menuGroup=new Group();
        menu_btns=new LinkedList<Button>();

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        main_font=new BitmapFont(Gdx.files.internal("general_font.fnt"));

        background= new Sprite(atlas.createSprite("bg_black"));
        SOS_textField= new Sprite(atlas.createSprite("main_menu_decoration"));

        Solo_btn=new ImageButton(skin.getDrawable("bttn_android"));
        menuGroup.addActor(Solo_btn);
        menu_btns.add(Solo_btn);

        VS_btn= new ImageButton(skin.getDrawable("bttn_vsplay"));
        menuGroup.addActor(VS_btn);
        menu_btns.add(VS_btn);

        settings_btn=new ImageButton(skin.getDrawable("bttn_settings"));
        menuGroup.addActor(settings_btn);
        menu_btns.add(settings_btn);

        credits_btn=new ImageButton(skin.getDrawable("bttn_credits"));
        menuGroup.addActor(credits_btn);
        menu_btns.add(credits_btn);

        help_btn=new ImageButton(skin.getDrawable("bttn_help"));
        menuGroup.addActor(help_btn);
        menu_btns.add(help_btn);

        sound_btn=new ImageButton(skin.getDrawable("bttn_soundON"),skin.getDrawable("bttn_soundOFF"),skin.getDrawable("bttn_soundOFF"));
        sound_btn.setPosition((SOSGame.WIDTH/100)*75,0);
        sound_btn.setHeight((SOSGame.HEIGHT/100)*25);
        sound_btn.setWidth((SOSGame.WIDTH/100)*25);
        menuGroup.addActor(sound_btn);

        int y=(SOSGame.HEIGHT/100)*4;
        int x=(SOSGame.WIDTH/100)*5;
        for(int i=menu_btns.size()-1; i>=0 ; i--)
        {
            menu_btns.get(i).setPosition(x,y);
            menu_btns.get(i).setHeight((SOSGame.HEIGHT/100)*15);
            menu_btns.get(i).setWidth((SOSGame.WIDTH/100)*15);
            y=y+(SOSGame.HEIGHT/100)*11;
        }
        menuGroup.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button){
                if(Solo_btn.isPressed()){
                    change=1;
                    Helper.getInstance().fl=1;
                    Helper.getInstance().screen= Helper.ScreenType.SOLO;
                }
                else if(VS_btn.isPressed()){
                    b2=true;
                    change=2;
                    VS=new VS_GameScreen();


                    //Helper.getInstance().fl=1;
                    //Helper.getInstance().screen= Helper.ScreenType.VS;
                }else if(settings_btn.isPressed()){
                    b3=true;
                    change=0;
                }else if(credits_btn.isPressed()){
                    change=0;
                    b4=true;
                }else if(help_btn.isPressed()){
                    change=0;
                    b5=true;
                }else if(sound_btn.isPressed()) {
                    change=0;
                    b6=true;
                }
                return false;
            }
        });
    }

    public void render() {

        menu_stage.addActor(menuGroup);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menu_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        menu_stage.act(Gdx.graphics.getDeltaTime());

        SOSGame.batch.begin();
        SOSGame.batch.draw(background,0,0);
        SOSGame.batch.draw(SOS_textField,0,(SOSGame.HEIGHT/100)*64,SOSGame.WIDTH , (SOSGame.HEIGHT/100)*25);
        SOSGame.batch.end();

        menu_stage.draw();

        if(b2){
            VS_GameScreen.trans.render();
            if(VS_GameScreen.trans.forward)
            { VS_GameScreen.trans.dispose();
                VS.render();}
        }
    }

    public void dispose() {

        menu_stage.dispose();
    }


}
