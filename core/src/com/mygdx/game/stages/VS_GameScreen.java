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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.Results;
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;
import com.mygdx.game.player.Player;
import com.mygdx.game.strategies.Random;
import com.mygdx.game.strategies.Rule_Based;
import com.mygdx.game.strategies.Strategy;

import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class VS_GameScreen extends ScreenAdapter{
    SOSGame game;

    //GUI VALUES
    private Skin skin;
    private TextureAtlas atlas;
    private Stage vs_stage,cross_stage;

    private Group actorGroup,cellGroup;
    private BitmapFont main_font,other_font;

    //Background
    public static Sprite THEBOARD;
    private Sprite Background,Black_bg,Blue_bg,ThreeBoard,FourBoard,FiveBoard,SevenBoard,SixBoard,EightBoard,humanPlayer1,humanPlayer2,vs;
    private Sprite lineBlue,lineYellow,lineRed,player1_line,player2_line;
    private Image crown;
    private Image circle1,circle2;

    //Game-Gui Variables
    private ImageButton generalO,generalS,backButton,renewButton;
    private LinkedList<Button> buttons;
    private Button CELLS [][] ;

    //////

    private Button.ButtonStyle  O_style_y, O_style_r, O_style_b, S_style_y, S_style_r, S_style_b;
    private ImageButton.ImageButtonStyle style1,style2,style3,style4,style5,style6;
    ImageButton.ImageButtonStyle player1_o,player2_0,player1_s,player2_s;


    //GAME VALUES
    Player p1,p2,winner;
    Results results;
    static Player currentPlayer;
    String playerName1;
    String playerName2;
    static int countofMoves=0;
    Board board;
    public static int row;
    public static int column;
    CellPosition cellPosition;
    public static int MAX_MOVES;
    Cell.CellValue choosenValue;

    private int score1,score2;
    private String ScoreName1,ScoreName2;
    private Strategy strategy;

    public VS_GameScreen(SOSGame game ) {


        this.game=game;
        //START: GUI PART
        vs_stage=new Stage(SOSGame.view,SOSGame.batch);
        cross_stage=new Stage(SOSGame.view,SOSGame.batch);

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        actorGroup=new Group();
        cellGroup=new Group();


        //STABLE Texture Loading
        humanPlayer1 = new Sprite(atlas.createSprite("player"));
        humanPlayer1.setSize((SOSGame.WIDTH/100)*13  , (SOSGame.HEIGHT/100)*13);

        humanPlayer2 = new Sprite(atlas.createSprite("player"));
        humanPlayer2.setSize((SOSGame.WIDTH/100)*13 , (SOSGame.HEIGHT/100)*13);

        crown=new Image(new Sprite(atlas.createSprite("crown")));


        vs =  new Sprite(atlas.createSprite("vs"));
        vs.setSize((SOSGame.WIDTH/100)*13 , (SOSGame.HEIGHT/100)*13);

        //UNSTABLE TEXTURES
        lineBlue = new Sprite(atlas.createSprite("line_blue"));
        lineYellow = new Sprite(atlas.createSprite("line_yellow"));
        lineRed = new Sprite(atlas.createSprite("line_red"));

        ThreeBoard=new Sprite(atlas.createSprite("3x3"));
        FourBoard= new Sprite(atlas.createSprite("4x4"));
        FiveBoard= new Sprite(atlas.createSprite("5x5"));
        SixBoard= new Sprite(atlas.createSprite("6x6"));
        SevenBoard= new Sprite(atlas.createSprite("7x7"));
        EightBoard=new Sprite(atlas.createSprite("8x8"));

        Black_bg = new Sprite(atlas.createSprite("bg_black"));
        Blue_bg= new Sprite(atlas.createSprite("bg_blue"));

        main_font=new BitmapFont(Gdx.files.internal("plain18.fnt"));
        other_font=new BitmapFont(Gdx.files.internal("plain28.fnt"));


        O_style_y = new Button.ButtonStyle(skin.getDrawable("o_yellow"),null,null);
        style1 = new ImageButton.ImageButtonStyle(O_style_y);


        S_style_y = new Button.ButtonStyle(skin.getDrawable("s_yellow"),null,null);
        style2 = new ImageButton.ImageButtonStyle(S_style_y);

        O_style_r = new Button.ButtonStyle(skin.getDrawable("o_red"),null,null);
        style3 = new ImageButton.ImageButtonStyle(O_style_r);

        S_style_r = new Button.ButtonStyle(skin.getDrawable("s_red"),null,null);
        style4 = new ImageButton.ImageButtonStyle(S_style_r);

        S_style_b = new Button.ButtonStyle(skin.getDrawable("s_blue"),null,null);
        style5 = new ImageButton.ImageButtonStyle(S_style_b);

        O_style_b = new Button.ButtonStyle(skin.getDrawable("o_blue"),null,null);
        style6 = new ImageButton.ImageButtonStyle(O_style_b);


        generalO=new ImageButton(skin.getDrawable("o_blue"));
        generalO.setPosition((SOSGame.WIDTH/100)*38,(SOSGame.HEIGHT/100)*2);
        generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
        actorGroup.addActor(generalO);


        generalS=new ImageButton(skin.getDrawable("s_blue"));
        generalS.setPosition((SOSGame.WIDTH/100)*62,(SOSGame.HEIGHT/100)*2);
        generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
        actorGroup.addActor(generalS);


        // SETTINGS MANIPULATION

        playerName1="AAA";
        playerName2= "BBB";

        player1_o=style6;
        player1_s=style5;

        player2_s=style4;
        player2_0=style3;

        player1_line=lineBlue;
        player2_line=lineRed;

        Background=Black_bg;
        strategy=new Random();

        if(Options.Player1Name!=null)
        {
            playerName1=Options.Player1Name;
        }
        if(Options.Player2Name!=null)
        {
            playerName2=Options.Player2Name;
        }
        if(Options.B_colors==Options.BackgroundColor.Blue)
            Background=Blue_bg;
        if(Options.S_colors==Options.SymbolColors.Yellows)
        {
            player2_s=style2;
            player2_0=style1;
            player2_line=lineYellow;
        }
        if(Options.difficulty== Options.Difficulty.HARD)
        {
            strategy=new Rule_Based();
        }

        /////////OUT BUTTONS
        buttons=new LinkedList<Button>();

        renewButton=new ImageButton(skin.getDrawable("bttn_replay"));
        renewButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        renewButton.setPosition((SOSGame.WIDTH-renewButton.getWidth()),0);
        actorGroup.addActor(renewButton);

        backButton=new ImageButton(skin.getDrawable("bttn_back"));
        backButton.setPosition(0,0);
        backButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(backButton);

        circle1=new Image(new Sprite(atlas.createSprite("circle")));
        circle1.setVisible(true);
        circle1.setPosition((SOSGame.WIDTH/100)*24,(SOSGame.HEIGHT/100)*5);
        circle1.setSize((SOSGame.WIDTH/100)*21,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(circle1);


        circle2=new Image(new Sprite(atlas.createSprite("circle")));
        circle2.setVisible(false);
        circle2.setPosition((SOSGame.WIDTH/100)*59,(SOSGame.HEIGHT/100)*5);
        circle2.setSize((SOSGame.WIDTH/100)*21,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(circle2);

        ////////BOARD SIZED AGAIN
        THEBOARD.setSize((SOSGame.WIDTH/100)*93 , (SOSGame.HEIGHT/100)*55);

        //CELLS-BUTTONS CREATION
        for (int a=0; a<MAX_MOVES; a++)
        {
            ImageButton btn = new ImageButton(skin.getDrawable("crown"));
            btn.setColor(1f,1f,1f,0);
            btn.setName("Button " + a);
            cellGroup.addActor(btn);
            buttons.add(btn);
        }

        CELLS=new Button[row][column];

        for(int i=0; i<row ;i++){
            for(int j=0;j<column ;j++)
                CELLS[i][j] = buttons.get((j*row) + i);
        }



        //BUTTON ALIGNMENT

        float y=(SOSGame.HEIGHT/100)*25;
        float x=(SOSGame.WIDTH/100)*5;
        for(int a=0;a<row; a++ )
        {
            for(int f=0;f<column; f++)
            {
                CELLS[a][f].setPosition(x,y); //** Button location **/
                CELLS[a][f].setHeight((THEBOARD.getWidth()/column)); //** Button Height **//
                CELLS[a][f].setWidth((THEBOARD.getHeight()/row)); //** Button Width **//
                y=(y+(int)(THEBOARD.getHeight()/row));
            }
            y=(SOSGame.HEIGHT/100)*25;
            x=(x+(THEBOARD.getWidth()/column));
        }

        playSolo();
    }

    public void playSolo( ) {

        //Creating the PLAY
        p1= new Player(playerName1,Player.Player_type.Human);
        p2= new Player(playerName2,Player.Player_type.Human);

        System.out.println("PLAY SOLO");

        board = new Board(row, column);
        results=new Results(board,p1,p2);

        countofMoves = 0;
        currentPlayer=p1;

        score1 = 0; score2=0;
        ScoreName1 = ": 0"; ScoreName2 = ": 0";

        actorGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (generalO.isPressed()) {
                    //Assets.playSound(Assets.hitSound);

                    choosenValue = Cell.CellValue.O_cell;

                    circle1.setVisible(true);
                    circle2.setVisible(false);

                } else if (generalS.isPressed()) {
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    choosenValue = Cell.CellValue.S_cell;
                    circle1.setVisible(false);
                    circle2.setVisible(true);
                }else if(backButton.isPressed())
                {
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Main_Menu(game));
                }else if(renewButton.isPressed())
                {
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new VS_GameScreen(game));
                }
                else {
                    //SoundAssets.playSound(SoundAssets.clickSound);
                    System.out.println("Choose A Cell Value");
                }
                return false;
            }
        });

        //ACTION LISTENER

        cellGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button)
            {
                for (int i = 0; i < row; i++) {
                        for (int g = 0; g < column; g++) {
                            if (CELLS[i][g].isPressed() == true && board.cells[i][g].getValue() == Cell.CellValue.EMPTY && (choosenValue==Cell.CellValue.S_cell ||choosenValue==Cell.CellValue.O_cell))
                            {

                                CELLS[i][g].setColor(1f,1f,1f,1f);

                                if(currentPlayer==p1) {
                                    cellPosition = new CellPosition(i, g);
                                    board.setCell(cellPosition, choosenValue);

                                    if (choosenValue == Cell.CellValue.O_cell) {
                                        CELLS[i][g].setStyle(player1_o);
                                        SoundAssets.playSound(SoundAssets.Osound);
                                        CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                    } else if (choosenValue == Cell.CellValue.S_cell) {
                                        CELLS[i][g].setStyle(player1_s);
                                        SoundAssets.playSound(SoundAssets.Ssound);
                                        CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                    }
                                    board.CROSS( );
                                    if(board.cellAtPosition(cellPosition).ISCrossed())
                                    {
                                        SoundAssets.playSound(SoundAssets.crossSound);
                                        score1++;
                                        ScoreName1 = ": " + score1;
                                        p1.SCORE();
                                        Crossing(player1_line);
                                    }
                                    countofMoves++;
                                    currentPlayer=p2;

                                    generalO.setStyle(style3);
                                    generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
                                    generalS.setStyle(style4);
                                    generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);




                                    if(Options.S_colors==Options.SymbolColors.Yellows)
                                    {
                                        generalO.setStyle(style1);
                                        generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
                                        generalS.setStyle(style2);
                                        generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                    }

                                }
                                else if(currentPlayer == p2)
                                {
                                    cellPosition = new CellPosition(i, g);
                                    board.setCell(cellPosition, choosenValue);
                                    //SoundAssets.playSound(SoundAssets.clickSound);
                                    if (choosenValue == Cell.CellValue.O_cell) {
                                        CELLS[i][g].setStyle(player2_0);
                                        SoundAssets.playSound(SoundAssets.Osound);
                                        CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                    } else if (choosenValue == Cell.CellValue.S_cell) {
                                        CELLS[i][g].setStyle(player2_s);
                                        SoundAssets.playSound(SoundAssets.Ssound);
                                        CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                    }
                                    board.CROSS();
                                    if(board.cellAtPosition(cellPosition).ISCrossed())
                                    {
                                        SoundAssets.playSound(SoundAssets.crossSound);
                                        score2++;
                                        ScoreName2 = ": " + score2;
                                        p2.SCORE();
                                        Crossing(player2_line);
                                    }
                                    countofMoves++;
                                    currentPlayer=p1;

                                    generalO.setStyle(style6);
                                    generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                    generalS.setStyle(style5);
                                    generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
                                }}}
                }

                if(results.ISgameOver()==true)
                {

                    SoundAssets.playSound(SoundAssets.endgameSound);
                    winner=results.getWinner();
                    if(winner==p1)
                    {
                        crown.setSize((SOSGame.WIDTH/100)*20  , (SOSGame.HEIGHT/100)*10);
                        crown.setPosition((SOSGame.WIDTH/100)*18,(SOSGame.HEIGHT/100)*92);
                        actorGroup.addActor(crown);

                    }else if(winner==p2)
                    {
                        crown.setSize((SOSGame.WIDTH/100)*20  , (SOSGame.HEIGHT/100)*10);
                        crown.setPosition((SOSGame.WIDTH/100)*84,(SOSGame.HEIGHT/100)*92);
                        actorGroup.addActor(crown);
                    }

                    generalO.setVisible(false);
                    generalS.setVisible(false);
                    circle1.setVisible(false);
                    circle2.setVisible(false);

                    Label.LabelStyle Labelstyle= new Label.LabelStyle(main_font,Color.WHITE);
                    Label end=new Label("Game Over! The Winner is "+winner.getName(), Labelstyle);
                    end.setSize(end.getMinWidth(),end.getMinHeight());
                    end.setPosition((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
                    actorGroup.addActor(end);

                    backButton.setPosition((SOSGame.WIDTH/100)*38,(SOSGame.HEIGHT/100)*2);
                    renewButton.setPosition((SOSGame.WIDTH/100)*62,(SOSGame.HEIGHT/100)*2);
                }
                return false;
            }

        });

        vs_stage.addActor(cellGroup);
        vs_stage.addActor(actorGroup);

    }

    public void Crossing(Sprite sp)
    {
       int cnt1=0; int cnt2=0; int cnt3=0; int cnt4=0;
        for (int i = 0; i < row; i++) {
            for (int g = 0; g < column; g++) {
                if (board.cells[i][g].ISCrossed()) {
                    if (board.cells[i][g].getDegree() == Cell.CrossDegree.CR_D && board.cells[i][g].isdrawn1 == false )
                    {
                        System.out.println("crd");

                        System.out.println(" i: " +i + " g: " +g);
                        Image image1 = new Image(new SpriteDrawable(sp));
                        image1.setSize((THEBOARD.getWidth() / row), (THEBOARD.getHeight() / 5) / column);
                        image1.setOrigin(image1.getImageWidth() / 2, image1.getImageHeight() / 2);
                        image1.rotateBy((float) 45.0);

                        image1.setPosition((CELLS[i][g].getX() / 100) * 110, ((CELLS[i][g].getY() / 100) * 102));

                        cross_stage.addActor(image1);
                        board.cells[i][g].isdrawn1 = true;
                    }
                    if (board.cells[i][g].getDegree() == Cell.CrossDegree.CR_U && board.cells[i][g].isdrawn2 == false )
                    {

                        System.out.println("cru");
                        System.out.println("i: " +i + "g: " +g);
                        Image image4 = new Image(new SpriteDrawable(sp));
                        image4.setSize((THEBOARD.getWidth() / row) * 3, (THEBOARD.getHeight() / 5) / column);
                        image4.setOrigin((THEBOARD.getWidth() / row) / 2, (THEBOARD.getHeight() / 5) / column / 2);
                        image4.rotateBy((float) 135.0);

                        image4.setPosition((CELLS[i][g].getX() / 100) * 105, ((CELLS[i][g].getY() / 100) * 112));

                        cross_stage.addActor(image4);
                        board.cells[i][g].isdrawn2 = true;
                    }
                    if (board.cells[i][g].getDegree() == Cell.CrossDegree.FL && board.cells[i][g].isdrawn3 == false)
                    {
                        System.out.println("FL");
                        System.out.println("i: " +i + "g: " +g);

                        Image image2 = new Image(new SpriteDrawable(sp));
                        image2.setSize((THEBOARD.getWidth() / row) * 3, (THEBOARD.getHeight() / 5) / column);
                        image2.setPosition((CELLS[i][g].getX() / 100) * 110, ((CELLS[i][g].getY() / 100) * 110));
                        cross_stage.addActor(image2);
                        board.cells[i][g].isdrawn3 = true;
                    }
                    if (board.cells[i][g].getDegree() == Cell.CrossDegree.UD && board.cells[i][g].isdrawn4 == false )
                    {
                        System.out.println("UD");
                        System.out.println("i: " +i + "g: " +g);

                        Image image3 = new Image(new SpriteDrawable(sp));
                        image3.setSize((THEBOARD.getWidth() / row) * 3, (THEBOARD.getHeight() / 5) / column);
                        image3.setOrigin((THEBOARD.getWidth() / row) / 2, (THEBOARD.getHeight() / 5) / column / 2);
                        image3.rotateBy((float) 90.0);

                        System.out.println("i: " +i + "g: " +g);
                        image3.setPosition((CELLS[i][g].getX() / 100) * 103, ((CELLS[i][g].getY() / 100) * 112));
                        System.out.println("i: " +i + "g: " +g);

                        cross_stage.addActor(image3);


                        board.cells[i][g].isdrawn4 = true;
                    }
                }}}
    }

    public void render ( float delta) {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(vs_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        vs_stage.act();
        cross_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Background,0,0);
        SOSGame.batch.draw(THEBOARD,(SOSGame.WIDTH/100)*5,(SOSGame.HEIGHT/100)*25,(SOSGame.WIDTH/100)*93 , (SOSGame.HEIGHT/100)*55);
        SOSGame.batch.draw(humanPlayer1,(SOSGame.WIDTH/100)*12,(SOSGame.HEIGHT/100)*88,(SOSGame.WIDTH/100)*15, (SOSGame.HEIGHT/100)*13);
        SOSGame.batch.draw(vs,(SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*85,(SOSGame.WIDTH/100)*15, (SOSGame.HEIGHT/100)*15);
        SOSGame.batch.draw(humanPlayer2,(SOSGame.WIDTH/100)*75,(SOSGame.HEIGHT/100)*88,(SOSGame.WIDTH/100)*15, (SOSGame.HEIGHT/100)*13);

        main_font.setColor(Color.valueOf(("6BD6D7")));
        main_font.draw(SOSGame.batch,ScoreName1,(SOSGame.WIDTH/100)*22,(SOSGame.HEIGHT/100)*86);
        main_font.draw(SOSGame.batch,playerName1,(SOSGame.WIDTH/100)*12,(SOSGame.HEIGHT/100)*86);

        main_font.setColor(Color.valueOf(("eb4e6b")));
        if(Options.S_colors==Options.S_colors.Yellows)
        {main_font.setColor(Color.valueOf(("FFFF99")));}
        main_font.draw(SOSGame.batch,ScoreName2,(SOSGame.WIDTH/100)*85,(SOSGame.HEIGHT/100)*86);
        main_font.draw(SOSGame.batch,playerName2,(SOSGame.WIDTH/100)*75,(SOSGame.HEIGHT/100)*86);

        SOSGame.batch.end();

        vs_stage.draw();
        cross_stage.draw();
    }

    public void dispose () {
        vs_stage.dispose();
    }
}
