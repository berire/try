package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;

/**
 * Created by user on 31.8.2016.
 */
public class Credits extends ScreenAdapter {
    SOSGame game;

    private static Sprite Background,black_bg,blue_bg,creditsImage;
    private Stage credits_stage;
    private BitmapFont small_font,big_font, regular;
    private ImageButton backbutton;
    private Skin skin;
    private TextureAtlas atlas1;
    private static String credits_text;
    public final static String credits= "CREDITS";

    public Credits(final SOSGame game)
    {
        this.game=game;
        credits_stage=new Stage(SOSGame.view,SOSGame.batch);
        atlas1=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin();
        skin.addRegions(atlas1);

        small_font=new BitmapFont(Gdx.files.internal("small.fnt"));
        big_font=new BitmapFont(Gdx.files.internal("bigfont.fnt"));
        regular=new BitmapFont(Gdx.files.internal("plain18.fnt"));


        black_bg=new Sprite(atlas1.createSprite("bg_black"));
        blue_bg=new Sprite(atlas1.createSprite("bg_blue"));
        Background=black_bg;
        if(Options.B_colors== Options.BackgroundColor.Blue)
        {
            Background=blue_bg;
        }

        creditsImage=new Sprite(atlas1.createSprite("bttn_credits"));

        backbutton=new ImageButton(skin.getDrawable("bttn_back"));
        backbutton.setPosition((SOSGame.WIDTH/100)*50,(SOSGame.HEIGHT/100)*10);
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

        credits_stage.addActor(backbutton);

        credits_text=" Director: Özgür ALAN \n \n Graphics & Design: Serj ARDOVIC \n \n Developer: Berire GÜNDÜZ \n \n Special Thanks: Can GÜLAYDIN, Paul KAZO, Ömer BAYKAL, Ahmet ABAK, Eren TOGAY and Tülay DEMETGÜL \n \n www.sngict.com ";


    }

    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(credits_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        credits_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Background,0,0);
        SOSGame.batch.draw(creditsImage,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*80);
        big_font.draw(SOSGame.batch,credits,(SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*90);
        regular.draw(SOSGame.batch,credits_text,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*70,(SOSGame.WIDTH/100)*80,10,true);
        SOSGame.batch.end();

        credits_stage.draw();
    }
}
