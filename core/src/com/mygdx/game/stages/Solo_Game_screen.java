package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.Results;
import com.mygdx.game.SOSGame;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;
import com.mygdx.game.player.Player;
import com.mygdx.game.strategies.Rule_Based;

import java.util.LinkedList;

/**
 * Created by user on 8.8.2016.
 */
public class Solo_Game_screen extends ScreenAdapter {

    SOSGame game;

    static final int GAME_RENEWED = 0;
    static final int GAME_FINISHED =1;
    private int state;

    //GUI VALUES
    private Skin skin;
    private TextureAtlas atlas;
    private Stage solo_stage,cross_stage;

    private Group actorGroup,cellGroup;
    private BitmapFont main_font;

    //Background
    public static Sprite THEBOARD;
    private Sprite Black_bg,ThreeBoard,FourBoard,FiveBoard,SevenBoard,SixBoard,EightBoard,humanPlayer1,androidPlayer,vs;
    private Sprite lineBlue,lineYellow;

    //Game-Gui Variables
    private ImageButton O_blue,S_blue,backButton,renewButton;
    private LinkedList<Button> buttons;
    private Button CELLS [][] ;

    //GAME VALUES
    Player p1,p2;
    Results results;
    static Player currentPlayer;
    String player_name="Deneme";
    static int countofMoves=0;
    Board board;
    public static int row;
    public static int column;
    CellPosition cellPosition;
    public static int MAX_MOVES;
    Cell.CellValue choosenValue;

    public Solo_Game_screen (SOSGame game) {
        this.game=game;

        //START: GUI PART
        solo_stage=new Stage(SOSGame.view,SOSGame.batch);
        cross_stage=new Stage(SOSGame.view,SOSGame.batch);

        atlas=new TextureAtlas(Gdx.files.internal("guisos.pack"));
        skin= new Skin ();
        skin.addRegions(atlas);

        actorGroup=new Group();
        cellGroup=new Group();

        main_font=new BitmapFont(Gdx.files.internal("thefont.fnt"));

        //STABLE Texture Loading

        Black_bg = new Sprite(atlas.createSprite("bg_black"));

        humanPlayer1 = new Sprite(atlas.createSprite("player"));
        humanPlayer1.setSize((SOSGame.WIDTH/100)*13  , (SOSGame.HEIGHT/100)*13);

        androidPlayer = new Sprite(atlas.createSprite("android_new"));
        androidPlayer.setSize((SOSGame.WIDTH/100)*13 , (SOSGame.HEIGHT/100)*13);

        vs =  new Sprite(atlas.createSprite("vs"));
        vs.setSize((SOSGame.WIDTH/100)*13 , (SOSGame.HEIGHT/100)*13);

        //UNSTABLE TEXTURES
        lineBlue = new Sprite(atlas.createSprite("line_blue"));
        lineYellow = new Sprite(atlas.createSprite("line_yellow"));

        ThreeBoard=new Sprite(atlas.createSprite("3x3"));
        FourBoard= new Sprite(atlas.createSprite("4x4"));
        FiveBoard= new Sprite(atlas.createSprite("5x5"));
        SixBoard= new Sprite(atlas.createSprite("6x6"));
        SevenBoard= new Sprite(atlas.createSprite("7x7"));
        EightBoard=new Sprite(atlas.createSprite("8x8"));

        /////////OUT BUTTONS
        buttons=new LinkedList<Button>();

        renewButton=new ImageButton(skin.getDrawable("bttn_replay"));
        renewButton.setPosition((SOSGame.WIDTH-60),0);
        renewButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(renewButton);

        backButton=new ImageButton(skin.getDrawable("bttn_back"));
        backButton.setPosition(0,0);
        backButton.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(backButton);

        O_blue = new ImageButton(skin.getDrawable("o_blue"));
        O_blue.setPosition((SOSGame.WIDTH/100)*40,(SOSGame.HEIGHT/100)*2);
        O_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(O_blue);

        S_blue = new ImageButton(skin.getDrawable("s_blue"));
        S_blue.setPosition((SOSGame.WIDTH/100)*60,(SOSGame.HEIGHT/100)*2);
        S_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);
        actorGroup.addActor(S_blue);

        ////////BOARD SIZED AGAIN
        THEBOARD.setSize((SOSGame.WIDTH/100)*105 , (SOSGame.HEIGHT/100)*40);

        //CELLS-BUTTONS CREATION
        for (int a=0; a<MAX_MOVES; a++)
        {
            ImageButton btn = new ImageButton(skin.getDrawable("crown"));
            btn.setName("Button " + a);
            cellGroup.addActor(btn);
            buttons.add(btn);
        }

        CELLS=new Button[row][column];

        for(int i=0; i<row ;i++){
            for(int j=0;j<column ;j++)
                CELLS[i][j] = buttons.get((j*row) + i);
        }

        //Creating the PLAY
        p1= new Player("ALİ",Player.Player_type.Human);
        p2= new Player("Android",Player.Player_type.AI);

        //BUTTON ALIGNMENT

        float y=(SOSGame.HEIGHT/100)*35;
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
            y=(SOSGame.HEIGHT/100)*35;
            x=(x+(THEBOARD.getWidth()/column));
        }

        playSolo();
    }

    public void playSolo( ) {

        System.out.println("PLAY SOLO");

        board = new Board(row, column);
        results=new Results(board,p1,p2);

        countofMoves = 0;
        currentPlayer=p1;

        actorGroup.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (O_blue.isPressed()) {
                    //Assets.playSound(Assets.hitSound);

                    choosenValue = Cell.CellValue.O_cell;
                    Button.ButtonStyle red_O = new Button.ButtonStyle(skin.getDrawable("o_red"), skin.getDrawable("o_red"), skin.getDrawable("o_red"));
                    ImageButton.ImageButtonStyle Ostyle = new ImageButton.ImageButtonStyle(red_O);
                    O_blue.setStyle(Ostyle);
                    O_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);


                    Button.ButtonStyle blue_S = new Button.ButtonStyle(skin.getDrawable("s_blue"), skin.getDrawable("s_blue"), skin.getDrawable("s_blue"));
                    ImageButton.ImageButtonStyle BLUEstyleS = new ImageButton.ImageButtonStyle(blue_S);
                    S_blue.setStyle(BLUEstyleS);
                    S_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);


                } else if (S_blue.isPressed()) {
                    choosenValue = Cell.CellValue.S_cell;
                    Button.ButtonStyle red_S = new Button.ButtonStyle(skin.getDrawable("s_red"), skin.getDrawable("s_red"), skin.getDrawable("s_red"));
                    ImageButton.ImageButtonStyle Sstyle = new ImageButton.ImageButtonStyle(red_S);
                    S_blue.setStyle(Sstyle);
                    S_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);

                    Button.ButtonStyle blue_O = new Button.ButtonStyle(skin.getDrawable("o_blue"), skin.getDrawable("o_blue"), skin.getDrawable("o_blue"));
                    ImageButton.ImageButtonStyle BLUEstyleO = new ImageButton.ImageButtonStyle(blue_O);
                    O_blue.setStyle(BLUEstyleO);
                    O_blue.setSize((SOSGame.WIDTH/100)*15,(SOSGame.HEIGHT/100)*15);

                }else if(backButton.isPressed())
                {
                    game.setScreen(new Main_Menu(game));
                }else if(renewButton.isPressed())
                {
                    state = GAME_RENEWED;
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
                        if (board.cells[i][g].getValue() == Cell.CellValue.EMPTY )
                        {
                            if(currentPlayer==p1 && CELLS[i][g].isPressed() == true&& (choosenValue==Cell.CellValue.S_cell ||choosenValue==Cell.CellValue.O_cell)) {
                                System.out.println("P1 STARTS");
                                cellPosition = new CellPosition(i, g);
                                board.setCell(cellPosition, choosenValue);
                                System.out.println("CELL POSITION "+cellPosition+" CELL VALUE "+choosenValue);
                                if (choosenValue == Cell.CellValue.O_cell) {
                                    Button.ButtonStyle O_style = new Button.ButtonStyle(skin.getDrawable("o_yellow"), skin.getDrawable("o_yellow"), skin.getDrawable("o_yellow"));
                                    ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle(O_style);
                                    CELLS[i][g].setStyle(style1);
                                    CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                } else if (choosenValue == Cell.CellValue.S_cell) {
                                    Button.ButtonStyle S_style = new Button.ButtonStyle(skin.getDrawable("s_yellow"), skin.getDrawable("s_yellow"), skin.getDrawable("s_yellow"));
                                    ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle(S_style);
                                    CELLS[i][g].setStyle(style2);
                                    CELLS[i][g].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                }

                                board.CROSS( );
                                Crossing(lineYellow);

                                if(board.cellAtPosition(cellPosition).ISCrossed())
                                {
                                    p1.SCORE();
                                }
                                countofMoves++;

                                currentPlayer=p2;

                            }
                            else if(currentPlayer == p2)
                            {
                                System.out.println("AI STARTS");

                                Rule_Based ruleBased=new Rule_Based();

                                CellPosition CP = ruleBased.determineBestPosition(board);
                                Cell.CellValue CV = ruleBased.determineValue(board);

                                board.setCell(CP,CV);

                               /* System.out.println("AI STARTS");
                                CellPosition CP  = random.determineBestPosition(board);
                                Cell.CellValue CV = random.determineValue(board);
                                board.setCell(CP,CV);*/

                                if (CV == Cell.CellValue.O_cell && CP!=null) {
                                    Button.ButtonStyle O_style1 = new Button.ButtonStyle(skin.getDrawable("o_blue"), skin.getDrawable("o_blue"), skin.getDrawable("o_blue"));
                                    ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle(O_style1);
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(style3);
                                    CELLS[CP.getRow()][CP.getColumn()].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                } else if (CV == Cell.CellValue.S_cell) {
                                    Button.ButtonStyle S_style1 = new Button.ButtonStyle(skin.getDrawable("s_blue"), skin.getDrawable("s_blue"), skin.getDrawable("s_blue"));
                                    ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle(S_style1);
                                    CELLS[CP.getRow()][CP.getColumn()].setStyle(style4);
                                    CELLS[CP.getRow()][CP.getColumn()].setSize((THEBOARD.getHeight()/row),(THEBOARD.getHeight()/column));
                                }
                                board.CROSS();
                                Crossing(lineBlue);
                                System.out.println("CELL POSITION "+cellPosition+" IS CROSSED "+ board.cells[i][g].ISCrossed());
                                if(board.cellAtPosition(cellPosition).ISCrossed())
                                {
                                    p2.SCORE();
                                }
                                countofMoves++;
                                currentPlayer=p1;

                            }

                        }
                        if(results.getResults()== Results.state.WINNER||results.getResults()== Results.state.DRAW)
                        {
                            state = GAME_FINISHED;
                        }
                    }
                }
                return false;
            }

        });

        solo_stage.addActor(cellGroup);
        solo_stage.addActor(actorGroup);

    }

    public void Crossing(Sprite sp)
    {
        System.out.println("CROSSING");
        for (int i = 0; i < row; i++) {
            for (int g = 0; g < column; g++) {
                if (board.cells[i][g].ISCrossed()) {
                    if(board.cells[i][g].getDegree()== Cell.CrossDegree.CR_D )
                    {
                        System.out.println("CR_D");
                        Image image1=new Image(new SpriteDrawable(sp));
                        image1.setOrigin(image1.getImageWidth()/2f, image1.getImageHeight()/2f);
                        image1.setSize((THEBOARD.getWidth()/row),(THEBOARD.getHeight()/6)/column);
                        image1.setPosition((CELLS[i][g].getX())+15,((CELLS[i][g].getY())));
                        image1.rotateBy((float)45.0);
                        cross_stage.addActor(image1);
                    }
                    if(board.cells[i][g].getDegree()== Cell.CrossDegree.CR_U)
                    {
                        System.out.println("CR_u");
                        Image image4=new Image(new SpriteDrawable(sp));
                        image4.setOrigin(image4.getImageWidth()/2f, image4.getImageHeight()/2f);
                        image4.setSize((THEBOARD.getWidth()/row),(THEBOARD.getHeight()/6)/column);
                        image4.setPosition((CELLS[i][g].getX()),((CELLS[i][g].getY())));
                        image4.rotateBy((float)135.0);
                        cross_stage.addActor(image4);
                    }
                    if(board.cells[i][g].getDegree()== Cell.CrossDegree.FL)
                    {
                        System.out.println("FL");
                        Image image2=new Image(new SpriteDrawable(sp));
                        image2.setSize((THEBOARD.getWidth()/row),(THEBOARD.getHeight()/6)/column);
                        image2.setPosition((CELLS[i][g].getX()),((CELLS[i][g].getY())));
                        image2.rotateBy((float)180.0);
                        cross_stage.addActor(image2);
                    }
                    if(board.cells[i][g].getDegree()== Cell.CrossDegree.UD)
                    {
                        System.out.println("UD");
                        Image image3=new Image(new SpriteDrawable(sp));
                        image3.setOrigin(image3.getImageWidth()/2f, image3.getImageHeight()/2f);
                        image3.setSize((THEBOARD.getWidth()/row),(THEBOARD.getHeight()/6)/column);
                        image3.setPosition((CELLS[i][g].getX()),((CELLS[i][g].getY())));
                        image3.rotateBy((float)90.0);
                        cross_stage.addActor(image3);
                    }
                }}}
    }

    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(solo_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        solo_stage.act();
        cross_stage.act();

        SOSGame.batch.begin();

        SOSGame.batch.draw(Black_bg,0,0);
        SOSGame.batch.draw(THEBOARD,(SOSGame.WIDTH/100)*5,(SOSGame.HEIGHT/100)*35,(SOSGame.WIDTH/100)*105 , (SOSGame.HEIGHT/100)*40);
        SOSGame.batch.draw(humanPlayer1,(SOSGame.WIDTH/100)*25,(SOSGame.HEIGHT/100)*87,(SOSGame.WIDTH/100)*13  , (SOSGame.HEIGHT/100)*13);
        SOSGame.batch.draw(vs,(SOSGame.WIDTH/100)*55,(SOSGame.HEIGHT/100)*87,(SOSGame.WIDTH/100)*13  , (SOSGame.HEIGHT/100)*13);
        SOSGame.batch.draw(androidPlayer,(SOSGame.WIDTH/100)*85,(SOSGame.HEIGHT/100)*87,(SOSGame.WIDTH/100)*13  , (SOSGame.HEIGHT/100)*13);

        SOSGame.batch.end();

        solo_stage.draw();
        cross_stage.draw();
    }

    public void dispose () {
        solo_stage.dispose();
    }
}
