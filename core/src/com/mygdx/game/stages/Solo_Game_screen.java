package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.player.Player;
import com.mygdx.game.strategies.Random;

import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class Solo_Game_screen extends Screens {

    public static boolean back=false;

    //GAME VALUES
    Player p1,p2,currentPlayer;
    String player_name="Deneme";
    int countofMoves;
    //BoardSize_Name_Screen.Board_type b_type;
    public Board board;
    int row,column;
    public Cell cell;
    public CellPosition cellPosition;
    int MAX_MOVES;
    public Cell.CellValue choosenValue;


    //GUI VALUES
    private Skin skin;
    private TextureAtlas atlas;
    private Stage solo_stage;
    private Group actorGroup,cellGroup;
    private BitmapFont main_font;
    //Background
    private Sprite THEBOARD;
    private Sprite Black_bg,ThreeBoard,SixBoard,FourBoard,FiveBoard,SevenBoard,EightBoard,androidPlayer,humanPlayer,lineBlue,lineYellow,vs;

    //Game-Gui Variables
    private ImageButton O_blue,S_blue,backButton,renewButton;
    private LinkedList<Button> buttons;
    private Button CELLS [][] ;

    public Transition trans;

    public Solo_Game_screen( ) {
        //START: GUI PART
        solo_stage=new Stage(SOSGame.view,SOSGame.batch);

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        actorGroup=new Group();
        cellGroup=new Group();

        main_font=new BitmapFont(Gdx.files.internal("general_font.fnt"));

        //Texture Loading
        Black_bg = new Sprite(atlas.createSprite("bg_black"));
        androidPlayer = new Sprite(atlas.createSprite("android_new"));
        humanPlayer = new Sprite(atlas.createSprite("player"));
        lineBlue = new Sprite(atlas.createSprite("line_blue"));
        lineYellow = new Sprite(atlas.createSprite("line_yellow"));
        vs =  new Sprite(atlas.createSprite("vs"));

        ThreeBoard=new Sprite(atlas.createSprite("3x3"));
        FourBoard= new Sprite(atlas.createSprite("4x4"));
        FiveBoard= new Sprite(atlas.createSprite("5x5"));
        SixBoard= new Sprite(atlas.createSprite("6x6"));
        SevenBoard= new Sprite(atlas.createSprite("7x7"));
        EightBoard=new Sprite(atlas.createSprite("8x8"));

/////////////////////BOARD SIZE ALLIGNMENT

        THEBOARD=ThreeBoard;
        row=3; column=3; MAX_MOVES=9;

        Transition ts=new Transition();

        /*if(ts.getBoardSize()== Transition.boardSizes.FOUR)
        {
            THEBOARD=FourBoard;
            row=4; column=4; MAX_MOVES=16;
        }else if(ts.getBoardSize()== Transition.boardSizes.FIVE)
        {
            THEBOARD=FiveBoard;
            row=5; column=5; MAX_MOVES=25;
        }else if(ts.getBoardSize()== Transition.boardSizes.SIX)
        {
            THEBOARD=SixBoard;
            row=6; column=6; MAX_MOVES=36;
        }else if(ts.getBoardSize()== Transition.boardSizes.SEVEN)
        {
            THEBOARD=SevenBoard;
            row=7; column=7; MAX_MOVES=49;
        }else if(ts.getBoardSize()== Transition.boardSizes.EIGHT)
        {
            THEBOARD=EightBoard;
            row=8; column=8; MAX_MOVES=64;
        }*/



        THEBOARD.setSize((SOSGame.WIDTH/100)*105 , (SOSGame.HEIGHT/100)*40);

        //Creating the buttons
        buttons=new LinkedList<Button>();

        renewButton=new ImageButton(skin.getDrawable("bttn_replay"));
        renewButton.setPosition((SOSGame.WIDTH/100)*65,0);
        renewButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(renewButton);

        backButton=new ImageButton(skin.getDrawable("bttn_back"));
        backButton.setPosition(0,0);
        backButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(backButton);

        O_blue = new ImageButton(skin.getDrawable("o_blue"));
        O_blue.setPosition((SOSGame.WIDTH/100)*30,(SOSGame.HEIGHT/100)*2);
        O_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(O_blue);

        S_blue = new ImageButton(skin.getDrawable("s_blue"));
        S_blue.setPosition((SOSGame.WIDTH/100)*57,(SOSGame.HEIGHT/100)*2);
        S_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(S_blue);

        //BUTTON CREATION
        for (int a=0; a<MAX_MOVES; a++)
        {;
            Button.ButtonStyle style= new Button.ButtonStyle(skin.getDrawable("crown"),skin.getDrawable("circle"),skin.getDrawable("crown"));
            ImageButton.ImageButtonStyle Istyle= new ImageButton.ImageButtonStyle(style);
            ImageButton btn = new ImageButton(skin.getDrawable("crown"));
            btn.setName("Button " + a);
            btn.setStyle(Istyle);
            cellGroup.addActor(btn);
            buttons.add(btn);
        }

        CELLS=new Button[row][column];

        for(int i=0; i<row ;i++){
            for(int j=0;j<column ;j++)
                CELLS[i][j] = buttons.get((j*row) + i);
        }


        //Creating the PLAY
        p1= new Player(player_name,Player.Player_type.Human);
        p2= new Player("Android",Player.Player_type.AI);
        currentPlayer= p1;

        //BUTTON ALIGNMENT

        int y=(SOSGame.HEIGHT/100)*20;
        int x=0;

        for(int a=0;a<row; a++ )
        {
            for(int f=0;f<column; f++)
            {
                CELLS[a][f].setPosition(x,y); //** Button location **/
                CELLS[a][f].setHeight((THEBOARD.getHeight()/row)*2/3); //** Button Height **//
                CELLS[a][f].setWidth((THEBOARD.getWidth()/column)*2/3); //** Button Width **//
                y=(y+(int)(THEBOARD.getHeight()/row)*2/3);
            }
            y=((int)THEBOARD.getHeight()/100)*20;
            x=(x+(int)(THEBOARD.getWidth()/column)+50);
        }

        //ACTOR ADDITION &&INPUT PROCESSOR

        solo_stage.addActor(cellGroup);
        solo_stage.addActor(actorGroup);



        playSolo( );

    }

    public void playSolo( ){
        /*//choose hardness level
        if(Settings_Menu.difficulty=="EASY")
        {
            Random.setStrategy( );
        }else if(Settings_Menu.difficulty=="HARD") {
            Rule_Based.setStrategy( );
        }*/
        board=new Board(row,column);
        final Random r= new Random();
        countofMoves=0;
        currentPlayer=p1;



        actorGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (O_blue.isPressed()) {
                    choosenValue = Cell.CellValue.O_cell;
                    Button.ButtonStyle red_O = new Button.ButtonStyle(skin.getDrawable("o_red"), skin.getDrawable("o_red"), skin.getDrawable("o_red"));
                    ImageButton.ImageButtonStyle Ostyle = new ImageButton.ImageButtonStyle(red_O);
                    O_blue.setStyle(Ostyle);

                    Button.ButtonStyle blue_S = new Button.ButtonStyle(skin.getDrawable("s_blue"), skin.getDrawable("s_blue"), skin.getDrawable("s_blue"));
                    ImageButton.ImageButtonStyle BLUEstyleS = new ImageButton.ImageButtonStyle(blue_S);
                    S_blue.setStyle(BLUEstyleS);


                } else if (S_blue.isPressed()) {
                    choosenValue = Cell.CellValue.S_cell;
                    Button.ButtonStyle red_S = new Button.ButtonStyle(skin.getDrawable("s_red"), skin.getDrawable("s_red"), skin.getDrawable("s_red"));
                    ImageButton.ImageButtonStyle Sstyle = new ImageButton.ImageButtonStyle(red_S);
                    S_blue.setStyle(Sstyle);

                    Button.ButtonStyle blue_O = new Button.ButtonStyle(skin.getDrawable("o_blue"), skin.getDrawable("o_blue"), skin.getDrawable("o_blue"));
                    ImageButton.ImageButtonStyle BLUEstyleO = new ImageButton.ImageButtonStyle(blue_O);
                    O_blue.setStyle(BLUEstyleO);

                }else if(backButton.isPressed())
                {
                    Helper.getInstance().screen= Helper.ScreenType.MAIN;
                    Helper.getInstance().fl=1;
                    back=true;
                    dispose();
                }else if(renewButton.isPressed())
                {/////////////////////
                    board.clearBoard();
                }
                else {
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
                        if (board.cells[i][g].getValue() == Cell.CellValue.EMPTY)
                        {
                            if(currentPlayer==p1 && CELLS[i][g].isPressed() == true ){
                                System.out.println("P1 STARTS");
                                cellPosition = new CellPosition(i, g);
                                board.setCell(cellPosition, choosenValue);
                                if (choosenValue == Cell.CellValue.O_cell) {
                                    Button.ButtonStyle O_style = new Button.ButtonStyle(skin.getDrawable("o_yellow"), skin.getDrawable("o_yellow"), skin.getDrawable("o_yellow"));
                                    ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle(O_style);
                                    CELLS[i][g].setStyle(style1);
                                } else if (choosenValue == Cell.CellValue.S_cell) {
                                    Button.ButtonStyle S_style = new Button.ButtonStyle(skin.getDrawable("s_yellow"), skin.getDrawable("s_yellow"), skin.getDrawable("s_yellow"));
                                    ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle(S_style);
                                    CELLS[i][g].setStyle(style2);
                                }
                                board.CROSS();
                                countofMoves++;
                                currentPlayer=p2;

                            }
                            else if(currentPlayer==p2)
                            {
                                System.out.println("AI STARTS");
                                CellPosition CP  = r.determineBestPosition(board);
                                Cell.CellValue CV = r.determineValue(board);
                                board.setCell(CP,CV);

                                if (CV == Cell.CellValue.O_cell) {
                                    Button.ButtonStyle O_style1 = new Button.ButtonStyle(skin.getDrawable("o_blue"), skin.getDrawable("o_blue"), skin.getDrawable("o_blue"));
                                    ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle(O_style1);
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(style3);
                                } else if (CV == Cell.CellValue.S_cell) {
                                    Button.ButtonStyle S_style1 = new Button.ButtonStyle(skin.getDrawable("s_blue"), skin.getDrawable("s_blue"), skin.getDrawable("s_blue"));
                                    ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle(S_style1);
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(style4);
                                }
                                board.CROSS();
                                countofMoves++;
                                currentPlayer=p1;
                            }
                        }
                        if (board.cells[i][g].ISCrossed()) {
                            System.out.println(i+" "+g+" "+"CROSSED");
                            Button.ButtonStyle crossStyle = new Button.ButtonStyle(skin.getDrawable("line_blue"),skin.getDrawable("line_blue"), skin.getDrawable("line_blue"));
                            ImageButton.ImageButtonStyle cross = new ImageButton.ImageButtonStyle(crossStyle);
                            CELLS[i][g].setStyle(cross);
                        }
                    }
                }


                return false;
            }

        });

    }
    public void render () {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(solo_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        solo_stage.act(Gdx.graphics.getDeltaTime());

        SOSGame.batch.begin();
        SOSGame.batch.draw(Black_bg,0,0);
        SOSGame.batch.draw(THEBOARD,0,(SOSGame.HEIGHT/100)*20,SOSGame.WIDTH , (SOSGame.HEIGHT/100)*40);
        SOSGame.batch.draw(androidPlayer,(SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*70,(SOSGame.WIDTH/100)*15 , (SOSGame.HEIGHT/100)*15);
        SOSGame.batch.draw(humanPlayer,(SOSGame.WIDTH/100)*75,(SOSGame.HEIGHT/100)*70,(SOSGame.WIDTH/100)*15 , (SOSGame.HEIGHT/100)*15);
        SOSGame.batch.draw(vs,(SOSGame.WIDTH/100)*45,(SOSGame.HEIGHT/100)*70,(SOSGame.WIDTH/100)*15 , (SOSGame.HEIGHT/100)*15);
        SOSGame.batch.end();

        solo_stage.draw();

    }

    public void dispose () {
        solo_stage.dispose();

    }
}
