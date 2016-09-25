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
public class Help extends ScreenAdapter{
    SOSGame game;

    private static Sprite Background,black_bg,blue_bg,helpImage;
    private Stage help_stage;
    private BitmapFont big_font, regular;
    private ImageButton backbutton;
    private Skin skin;
    private TextureAtlas atlas1;
    private static String help_text;
    public final static String help= "HELP";
    private Label helpLabel,helpTitle;

    public Help(final SOSGame game)
    {
        this.game=game;

        help_stage=new Stage(SOSGame.view,SOSGame.batch);
        atlas1=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin();
        skin.addRegions(atlas1);

        FreeTypeFontGenerator generator = new  FreeTypeFontGenerator(Gdx.files.internal("RightChalk11.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56;
        big_font = generator.generateFont(parameter);

        generator = new  FreeTypeFontGenerator(Gdx.files.internal("arialbd.ttf"));
        parameter.size = 18;
        regular = generator.generateFont(parameter);
        generator.dispose();


        black_bg=new Sprite(atlas1.createSprite("bg_black"));
        blue_bg=new Sprite(atlas1.createSprite("bg_blue"));
        Background=black_bg;
        if(Options.B_colors== Options.BackgroundColor.Blue)
        {
            Background=blue_bg;
        }

        helpImage=new Sprite(atlas1.createSprite("bttn_help"));

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
        help_text="SOS Game is quite similar to TicTacToe in sense of it's game play. Players take turns to add either an S or an O to any square, with no requirement to use the same letter each turn. The object of the game is for each player to attempt to create the straight sequence S-O-S among connected squares (either diagonally,horizontally or vertically), and to create as many sequences as they can. If a player succeeds in creating an SOS, that player gets a point.";

        Label.LabelStyle labelStyle=new Label.LabelStyle(regular, Color.WHITE);
        Label.LabelStyle labelStyle1=new Label.LabelStyle(big_font,Color.WHITE);
        helpTitle=new Label(help,labelStyle1);
        helpLabel=new Label(help_text,labelStyle);

        helpLabel.setFontScale(1);
        helpTitle.setFontScale(1);

        helpLabel.setWrap(true);
        helpTitle.setWrap(true);

        helpLabel.setPosition((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*25);
        helpTitle.setPosition((SOSGame.WIDTH/100)*40,(SOSGame.HEIGHT/100)*87);
        helpLabel.setSize((SOSGame.WIDTH/100)*80,(SOSGame.HEIGHT/100)*60);
        help_stage.addActor(helpTitle);
        help_stage.addActor(backbutton);
        help_stage.addActor(helpLabel);



    }

    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(help_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        help_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Background,0,0,SOSGame.WIDTH,SOSGame.HEIGHT);
        SOSGame.batch.draw(helpImage,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*85);
        SOSGame.batch.end();

        help_stage.draw();
    }
}
