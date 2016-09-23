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
import com.mygdx.game.SOSGame;
import com.mygdx.game.SoundAssets;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;
import com.mygdx.game.player.Player;
import com.mygdx.game.strategies.MEasy;
import com.mygdx.game.strategies.Random;
import com.mygdx.game.strategies.Rule_Based;
import com.mygdx.game.strategies.Strategy;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class Solo_Game_screen extends ScreenAdapter {

    SOSGame game;
    //GUI VALUES
    private Skin skin;
    private TextureAtlas atlas;
    private Stage solo_stage,cross_stage;

    private Group actorGroup,cellGroup;
    private BitmapFont main_font,other_font;

    //Background
    public static Sprite THEBOARD;
    private Sprite Background,Black_bg,Blue_bg,ThreeBoard,FourBoard,FiveBoard,SevenBoard,SixBoard,EightBoard,humanPlayer1,android,vs;
    private Sprite lineBlue,lineYellow,lineRed,player1_line,player2_line;
    private Image crown;
    private Image circle1,circle2;

    //Game-Gui Variables
    private ImageButton generalO,generalS,backButton,renewButton;
    private LinkedList<Button> buttons;
    private Button CELLS [][] ;

    //////
    ImageButton.ImageButtonStyle player1_o,player2_0,player1_s,player2_s;

    //GAME VALUES
    Player p1,p2,winner;
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

    public Solo_Game_screen (SOSGame game ) {
        this.game=game;
        //START: GUI PART
        solo_stage=new Stage(SOSGame.view,SOSGame.batch);
        cross_stage=new Stage(SOSGame.view,SOSGame.batch);

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        actorGroup=new Group();
        cellGroup=new Group();

        ////////BOARD SIZED AGAIN
        THEBOARD.setSize((SOSGame.WIDTH/100)*93 , (SOSGame.HEIGHT/100)*55);
        THEBOARD.setPosition((SOSGame.WIDTH/100)*5,(SOSGame.HEIGHT/100)*25);


        //STABLE Texture Loading
        humanPlayer1 = new Sprite(atlas.createSprite("player"));
        humanPlayer1.setPosition((SOSGame.WIDTH/100)*12,(SOSGame.HEIGHT/100)*88);
        humanPlayer1.setSize((SOSGame.WIDTH/100)*11  , (SOSGame.HEIGHT/100)*11);

        android = new Sprite(atlas.createSprite("android_new"));
        android.setPosition((SOSGame.WIDTH/100)*75,(SOSGame.HEIGHT/100)*88);
        android.setSize((SOSGame.WIDTH/100)*11 , (SOSGame.HEIGHT/100)*11);

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

        Button.ButtonStyle O_style_y = new Button.ButtonStyle(skin.getDrawable("o_yellow"),null,null);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle(O_style_y);

        Button.ButtonStyle S_style_y = new Button.ButtonStyle(skin.getDrawable("s_yellow"),null,null);
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle(S_style_y);

        Button.ButtonStyle O_style_r = new Button.ButtonStyle(skin.getDrawable("o_red"),null,null);
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle(O_style_r);

        Button.ButtonStyle S_style_r = new Button.ButtonStyle(skin.getDrawable("s_red"),null,null);
        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle(S_style_r);

        Button.ButtonStyle S_style_b = new Button.ButtonStyle(skin.getDrawable("s_blue"),null,null);
        ImageButton.ImageButtonStyle style5 = new ImageButton.ImageButtonStyle(S_style_b);

        Button.ButtonStyle O_style_b = new Button.ButtonStyle(skin.getDrawable("o_blue"),null,null);
        ImageButton.ImageButtonStyle style6 = new ImageButton.ImageButtonStyle(O_style_b);

        generalO=new ImageButton(skin.getDrawable("o_blue"));
        generalO.setPosition((THEBOARD.getWidth()/100)*33,(THEBOARD.getHeight()/100)*2);
        generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
        actorGroup.addActor(generalO);


        generalS=new ImageButton(skin.getDrawable("s_blue"));
        generalS.setPosition((THEBOARD.getWidth()/100)*60,(THEBOARD.getHeight()/100)*2);
        generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);
        actorGroup.addActor(generalS);


        // SETTINGS MANIPULATION

        playerName1="AAA";
        playerName2= "OID";

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
        circle1.setPosition((generalO.getX()/100)*97,(THEBOARD.getHeight()/100)*8);
        circle1.setSize((generalO.getWidth()/100)*110,(generalO.getHeight()/100)*65);
        actorGroup.addActor(circle1);


        circle2=new Image(new Sprite(atlas.createSprite("circle")));
        circle2.setVisible(false);
        circle2.setPosition((generalS.getX()/100)*97,(THEBOARD.getHeight()/100)*8);
        circle2.setSize((generalS.getWidth()/100)*110,(generalS.getHeight()/100)*65);
        actorGroup.addActor(circle2);



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
        float x=(SOSGame.WIDTH/100)*4;
        for(int a=0;a<row; a++ )
        {
            for(int f=0;f<column; f++)
            {
                CELLS[a][f].setPosition(x,y); //** Button location **/
                CELLS[a][f].setHeight((THEBOARD.getHeight()/column/100)*95); //** Button Height **//
                CELLS[a][f].setWidth((THEBOARD.getWidth()/row/100)*95); //** Button Width **//
                y=(y+(int)(THEBOARD.getHeight()/row));
            }
            y=(SOSGame.HEIGHT/100)*25;
            x=(x+((THEBOARD.getWidth()/row/100)*95)+(THEBOARD.getWidth()/row/100)*8);
        }

        playSolo();
    }

    public void playSolo( ) {

        //Creating the PLAY
        p1= new Player(playerName1,Player.Player_type.Human);
        p2= new Player(playerName2,Player.Player_type.Human);

        System.out.println("PLAY SOLO");

        board = new Board(row, column);

        countofMoves = 0;
        currentPlayer=p1;

        score1 = 0; score2=0;
        ScoreName1 = " 0 "; ScoreName2 = " 0 ";

        Random random= new Random();
        Rule_Based ruleBased=new Rule_Based();
        MEasy mEasy=new MEasy();

        strategy=mEasy;
        if(Options.difficulty== Options.Difficulty.HARD)
        {
            strategy=ruleBased;
        }

        actorGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (generalO.isPressed()) {
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    choosenValue = Cell.CellValue.O_cell;
                    circle1.setVisible(true);
                    circle2.setVisible(false);

                } else if (generalS.isPressed()) {
                    SoundAssets.playSound(SoundAssets.chooseSound);
                    choosenValue = Cell.CellValue.S_cell;
                    circle1.setVisible(false);
                    circle2.setVisible(true);
                }else if(backButton.isPressed())
                {
                    SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Main_Menu(game));
                }else if(renewButton.isPressed())
                {
                    SoundAssets.playSound(SoundAssets.clickSound);
                    game.setScreen(new Solo_Game_screen(game));
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
                        if (board.cells[i][g].getValue() == Cell.CellValue.EMPTY )
                        {
                            if(currentPlayer==p1 && CELLS[i][g].isPressed() == true&& (choosenValue==Cell.CellValue.S_cell ||choosenValue==Cell.CellValue.O_cell)) {
                                CELLS[i][g].setColor(1f,1f,1f,1f);
                                cellPosition = new CellPosition(i, g);
                                board.setCell(cellPosition, choosenValue);

                                if (choosenValue == Cell.CellValue.O_cell) {
                                    CELLS[i][g].setStyle(player1_o);
                                    SoundAssets.playSound(SoundAssets.Osound);
                                    CELLS[i][g].setSize((THEBOARD.getHeight()/row/100)*95,(THEBOARD.getWidth()/column/100)*95);
                                } else if (choosenValue == Cell.CellValue.S_cell) {
                                    CELLS[i][g].setStyle(player1_s);
                                    SoundAssets.playSound(SoundAssets.Ssound);
                                    CELLS[i][g].setSize((THEBOARD.getHeight()/row/100)*95,(THEBOARD.getWidth()/column/100)*95);
                                }
                                board.CROSS(p1);
                                if(board.getLastCrosser()==p1 && board.cellAtPosition(cellPosition).ISCrossed())
                                {
                                    SoundAssets.playSound(SoundAssets.crossSound);
                                    Crossing(player1_line);
                                }
                                ScoreName1 = " " + score1;
                                countofMoves++;

                                generalO.getStyle().imageDown=skin.getDrawable("o_red");
                                generalO.getStyle().imageUp=skin.getDrawable("o_red");
                                generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                generalS.getStyle().imageDown=skin.getDrawable("s_red");
                                generalS.getStyle().imageUp=skin.getDrawable("s_red");
                                generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                if(Options.S_colors==Options.SymbolColors.Yellows)
                                {
                                    generalO.getStyle().imageDown=skin.getDrawable("o_yellow");
                                    generalO.getStyle().imageUp=skin.getDrawable("o_yellow");
                                    generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                    generalS.getStyle().imageDown=skin.getDrawable("s_yellow");
                                    generalS.getStyle().imageUp=skin.getDrawable("s_yellow");
                                    generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                }

                                currentPlayer=p2;
                            }
                            if(currentPlayer == p2 && board.emptyCellPositions(board).size()!=0)
                            {
                                boolean played=false;
                                CellPosition CP = strategy.determineBestPosition(board);
                                Cell.CellValue CV = strategy.determineValue(board);
                                board.setCell(CP,CV);
                                CELLS[CP.getRow()][CP.getColumn()].setColor(1f,1f,1f,1f);

                                if (CV == Cell.CellValue.O_cell ) {
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(player2_0);
                                    SoundAssets.playSound(SoundAssets.Osound);
                                    CELLS[CP.getRow()][CP.getColumn()].setSize((THEBOARD.getHeight()/row/100)*95,(THEBOARD.getWidth()/column/100)*95);
                                    played=true;
                                } else if (CV == Cell.CellValue.S_cell) {
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(player2_s);
                                    SoundAssets.playSound(SoundAssets.Ssound);
                                    CELLS[CP.getRow()][CP.getColumn()].setSize((THEBOARD.getHeight()/row/100)*95,(THEBOARD.getWidth()/column/100)*95);
                                    played=true;
                                }

                                board.CROSS(p2);
                                if(board.getLastCrosser()==p2 && board.cellAtPosition(CP).ISCrossed())
                                {
                                    SoundAssets.playSound(SoundAssets.crossSound);
                                    Crossing(player2_line);
                                }

                                ScoreName2 = " " + score2;
                                generalO.getStyle().imageDown=skin.getDrawable("o_blue");
                                generalO.getStyle().imageUp=skin.getDrawable("o_blue");
                                generalO.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                generalS.getStyle().imageDown=skin.getDrawable("s_blue");
                                generalS.getStyle().imageUp=skin.getDrawable("s_blue");
                                generalS.setSize((SOSGame.WIDTH/100)*20,(SOSGame.HEIGHT/100)*20);

                                countofMoves++;
                                if(played)
                                {currentPlayer=p1;}
                            }

                            if(board.emptyCellPositions(board).size() == 0)
                            {

                                SoundAssets.playSound(SoundAssets.endgameSound);
                                if(score1>score2)
                                {
                                    winner=p1;
                                    crown.setSize((SOSGame.WIDTH/100)*15 , (SOSGame.HEIGHT/100)*5);
                                    crown.setPosition(humanPlayer1.getX(),(humanPlayer1.getY()/100)*110); //(SOSGame.WIDTH/100)*18,(SOSGame.HEIGHT/100)*92
                                    actorGroup.addActor(crown);

                                }else if(score2>score1)
                                {
                                    winner=p2;
                                    crown.setSize((SOSGame.WIDTH/100)*15 , (SOSGame.HEIGHT/100)*5);
                                    crown.setPosition(android.getX(),(android.getY()/100)*110);
                                    actorGroup.addActor(crown);
                                }else if(score1==score2)
                                {
                                    winner=new Player("WORLD PEACE", Player.Player_type.AI);
                                }

                                generalO.setVisible(false);
                                generalS.setVisible(false);
                                circle1.setVisible(false);
                                circle2.setVisible(false);

                                Label.LabelStyle Labelstyle= new Label.LabelStyle(other_font,Color.WHITE);
                                Label end=new Label("Game Over! The Winner is "+winner.getName(), Labelstyle);
                                end.setSize(end.getMinWidth(),end.getMinHeight());
                                end.setPosition(THEBOARD.getX()+(THEBOARD.getWidth()/5),(SOSGame.HEIGHT/100)*15);
                                actorGroup.addActor(end);

                                backButton.setPosition((THEBOARD.getWidth()/100)*33,(THEBOARD.getHeight()/100)*2);
                                renewButton.setPosition((THEBOARD.getWidth()/100)*60,(THEBOARD.getHeight()/100)*2);
                            }

                        }}} return false;} });

        solo_stage.addActor(cellGroup);
        solo_stage.addActor(actorGroup);

    }

    public void Crossing (Sprite sp)
    {
        System.out.println("CROSSING");
        for (int i = 0; i < row; i++) {
            for (int g = 0; g < column; g++) {
                if (board.cells[i][g].ISCrossed()) {
                    Cell thecell= board.cellAtPosition(new CellPosition(i,g));

                    if(thecell.getValue()== Cell.CellValue.O_cell)
                    {
                        ArrayList<Cell.CrossDegree> degrees= thecell.getdegrees();

                        for(int x=0; x< degrees.size();x++)
                        {
                            System.out.println(degrees.get(x).toString());
                        }


                        if ( degrees.contains(Cell.CrossDegree.CR_D)  && thecell.isdrawn1 == false )
                        {
                            System.out.println("crd");
                            System.out.println(" i: " +i + " g: " +g);

                            Image image1 = new Image(new SpriteDrawable(sp));
                            image1.setSize((THEBOARD.getWidth()/row/10)*28, (THEBOARD.getHeight() / 8) / column);
                            image1.setOrigin(image1.getImageWidth() / 2, image1.getImageHeight() / 2);
                            image1.rotateBy((float) 45.0);
                            image1.setPosition((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getX())-(THEBOARD.getWidth()/row/2), ((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getY()))-((THEBOARD.getHeight()/column/2)));
                            cross_stage.addActor(image1);
                            thecell.isdrawn1 = true;
                            if(sp==player1_line)
                            {
                                score1++;
                            }
                            else if(sp==player2_line)
                            {
                                score2++;
                            }
                        }
                        if ( degrees.contains(Cell.CrossDegree.CR_U)  && thecell.isdrawn2 == false )
                        {
                            System.out.println("cru");

                            Image image4 = new Image(new SpriteDrawable(sp));
                            image4.setSize((THEBOARD.getWidth() / row/10)*28, (THEBOARD.getHeight() / 8) / column);
                            image4.setPosition((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getX())+((THEBOARD.getWidth()*3)/row/2), ((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getY()))-(((THEBOARD.getHeight())/column/3)));
                            image4.setOrigin(image4.getImageWidth() / 2, image4.getImageHeight() / 2);
                            image4.rotateBy((float) 135.0);
                            cross_stage.addActor(image4);
                            thecell.isdrawn2 = true;
                            if(sp==player1_line)
                            {
                                score1++;
                            }
                            else if(sp==player2_line)
                            {
                                score2++;
                            }
                        }
                        if ( degrees.contains(Cell.CrossDegree.FL) && thecell.isdrawn3 == false)
                        {
                            System.out.println("FL");
                            System.out.println("i: " +i + "g: " +g);

                            Image image2 = new Image(new SpriteDrawable(sp));
                            image2.setSize((THEBOARD.getWidth() / row/100)*230, (THEBOARD.getHeight() / 8) / column);
                            image2.setPosition((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getX())-(THEBOARD.getWidth()/row/2) , ((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getY())+(((THEBOARD.getHeight())/column/3)) ));
                            cross_stage.addActor(image2);
                            thecell.isdrawn3 = true;
                            if(sp==player1_line)
                            {
                                score1++;
                            }
                            else if(sp==player2_line)
                            {
                                score2++;
                            }
                        }

                        if (degrees.contains(Cell.CrossDegree.UD) && thecell.isdrawn4 == false )
                        {
                            System.out.println("UD");
                            System.out.println("i: " +i + "g: " +g);

                            Image image3 = new Image(new SpriteDrawable(sp));
                            image3.setSize((THEBOARD.getHeight() / row/100)*230, (THEBOARD.getHeight() / 8) / column);
                            image3.setOrigin((THEBOARD.getWidth() / row) / 2, (THEBOARD.getHeight() / 5) / column / 2);
                            image3.rotateBy((float) 90.0);
                            image3.setPosition((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getX()) , ((CELLS[thecell.getPosition().getRow()][thecell.getPosition().getColumn()].getY())-(((THEBOARD.getHeight())/column/3)) ));
                            cross_stage.addActor(image3);
                            thecell.isdrawn4 = true;
                            if(sp==player1_line)
                            {
                                score1++;
                            }
                            else if(sp==player2_line)
                            {
                                score2++;
                            }
                        }
                    }
                }
            }}
    }


    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(solo_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        solo_stage.act();
        cross_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Background,0,0,SOSGame.WIDTH,SOSGame.HEIGHT);
        SOSGame.batch.draw(THEBOARD,(SOSGame.WIDTH/100)*5,(SOSGame.HEIGHT/100)*25,(SOSGame.WIDTH/100)*93 , (SOSGame.HEIGHT/100)*55);
        SOSGame.batch.draw(humanPlayer1,(SOSGame.WIDTH/100)*12,(SOSGame.HEIGHT/100)*88,(SOSGame.WIDTH/100)*13, (SOSGame.HEIGHT/100)*13);
        SOSGame.batch.draw(vs,(SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*85,(SOSGame.WIDTH/100)*15, (SOSGame.HEIGHT/100)*15);
        SOSGame.batch.draw(android,(SOSGame.WIDTH/100)*75,(SOSGame.HEIGHT/100)*88,(SOSGame.WIDTH/100)*15, (SOSGame.HEIGHT/100)*13);

        other_font.setColor(Color.valueOf(("6BD6D7")));
        other_font.draw(SOSGame.batch,playerName1,(humanPlayer1.getX()*120)/100,(SOSGame.HEIGHT/100)*86);

        other_font.setColor(Color.WHITE);
        other_font.draw(SOSGame.batch,ScoreName1,(humanPlayer1.getX()*130)/100,(SOSGame.HEIGHT/100)*83);

        other_font.setColor(Color.valueOf(("eb4e6b")));
        if(Options.S_colors==Options.S_colors.Yellows)
        {other_font.setColor(Color.valueOf(("FFFF99")));}
        other_font.draw(SOSGame.batch,playerName2,(android.getX()*104)/100,(SOSGame.HEIGHT/100)*86);

        other_font.setColor(Color.WHITE);
        other_font.draw(SOSGame.batch,ScoreName2,(android.getX()*108)/100,(SOSGame.HEIGHT/100)*83);

        SOSGame.batch.end();

        solo_stage.draw();
        cross_stage.draw();
    }

    public void dispose () {
        solo_stage.dispose();
    }
}