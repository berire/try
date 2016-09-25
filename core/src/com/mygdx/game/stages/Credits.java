package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;

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
    public Label credits_label,creditsTitle;

    public Credits(final SOSGame game)
    {
        this.game=game;
        credits_stage=new Stage(SOSGame.view,SOSGame.batch);
        atlas1=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin();
        skin.addRegions(atlas1);

        small_font=new BitmapFont(Gdx.files.internal("small.fnt"));
        big_font=new BitmapFont(Gdx.files.internal("bigfont.fnt"));
        regular=new BitmapFont(Gdx.files.internal("plain28.fnt"));

        FreeTypeFontGenerator generator = new  FreeTypeFontGenerator(Gdx.files.internal("RightChalk11.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56;
        big_font = generator.generateFont(parameter);

        generator = new  FreeTypeFontGenerator(Gdx.files.internal("arialbd.ttf"));
        parameter.size = 20;
        regular = generator.generateFont(parameter);
        generator.dispose();






        black_bg=new Sprite(atlas1.createSprite("bg_black"));
        blue_bg=new Sprite(atlas1.createSprite("bg_blue"));
        Background=black_bg;
        if(Options.B_colors== Options.BackgroundColor.Blue)
        {
            Background=blue_bg;
        }

        creditsImage=new Sprite(atlas1.createSprite("bttn_credits"));

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
                {
                    SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Main_Menu(game));}

                return false;
            }});
        credits_text="Director: Ozgur ALAN\n\nGraphics & Design: Serj ARDOVIC\n\nDeveloper: Berire GUNDUZ\n\nSpecial Thanks: Can GULAYDIN\n\nSpecial Thanks: Tulay DEMETGUL\n\nwww.sngict.com";


        Label.LabelStyle labelStyle=new Label.LabelStyle(regular, Color.WHITE);
        Label.LabelStyle labelStyle1=new Label.LabelStyle(big_font, Color.WHITE);

        credits_label=new Label(credits_text,labelStyle);
        credits_label.setFontScale(1);
        credits_label.setPosition((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*40);
        credits_label.setSize((SOSGame.WIDTH/100)*60,(SOSGame.HEIGHT/100)*60);
        credits_label.setWrap(false);

        creditsTitle=new Label(credits,labelStyle1);
        creditsTitle.setFontScale(1);
        creditsTitle.setPosition((SOSGame.WIDTH/100)*40,(SOSGame.HEIGHT/100)*87);

        credits_stage.addActor(credits_label);
        credits_stage.addActor(creditsTitle);

        credits_stage.addActor(backbutton);



    }

    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(credits_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        credits_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Background,0,0,SOSGame.WIDTH,SOSGame.HEIGHT);
        SOSGame.batch.draw(creditsImage,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*85);
        SOSGame.batch.end();

        credits_stage.draw();
    }
}
