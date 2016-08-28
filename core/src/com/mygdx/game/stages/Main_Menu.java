package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
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
public class Main_Menu extends ScreenAdapter {
    public SOSGame game;
    Rectangle soundBounds;

    public Stage menu_stage;
    public static int change=0;

    private Skin skin;
    private TextureAtlas atlas;

    private Group menuGroup;
    private BitmapFont main_font;
    private Sprite background;
    private Sprite SOS_textField;
    private ImageButton Solo_btn,VS_btn,settings_btn,credits_btn,help_btn,sound_btn;
    private LinkedList<Button> menu_btns;



    public Main_Menu(final SOSGame game) {
        this.game=game;
        soundBounds = new Rectangle(0, 0, 64, 64);

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
                    if(Helper.getInstance().fl==1){
                        game.setScreen(new Transition(game));
                        Helper.instance.fl=2;
                    }
                    else if(Helper.getInstance().fl==2 ){
                        game.setScreen(new Solo_Game_screen(game));}
                }
                if(VS_btn.isPressed()){
                    change=2;
                    //Assets.playSound(Assets.clickSound);
                    if(Helper.getInstance().fl==1){
                        game.setScreen(new Transition(game));
                        Helper.instance.fl=2;
                    }
                    else if(Helper.getInstance().fl==2 ){
                        game.setScreen(Transition.VS);}
                }if(settings_btn.isPressed()){

                }else if(credits_btn.isPressed()){

                }else if(help_btn.isPressed()){

                }else if(sound_btn.isPressed()) {
                    /*Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled)
                        Assets.music.play();
                    else
                        Assets.music.pause();*/
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

        game.batch.disableBlending();
        SOSGame.batch.begin();
        SOSGame.batch.draw(background,0,0);
        SOSGame.batch.end();

        game.batch.enableBlending();
        SOSGame.batch.begin();
        SOSGame.batch.draw(SOS_textField,0,(SOSGame.HEIGHT/100)*64,SOSGame.WIDTH , (SOSGame.HEIGHT/100)*25);
        //game.batch.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
        SOSGame.batch.end();

        menu_stage.draw();
    }

    public void dispose() {

        menu_stage.dispose();
    }


}
