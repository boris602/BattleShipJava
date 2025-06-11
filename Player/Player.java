package Player;


import BoardComponents.BattleShipBoard;
import Options.Options;

import java.awt.*;

public class Player {
    private final String name;
    private int life;
    private boolean[][] board;
    private final boolean[][] hitBoard;
    private int hits;
    private BattleShipBoard visibleBoard;
    private final BattleShipBoard unVisibleBoard;

    public Player(Options options, String name) {
        this.hits = 1;
        this.life = options.getShipSize2()*2 + options.getShipSize3()*3
                + options.getShipSize4()*4 + options.getShipSize5()*5;
        this.name = name;
        this.unVisibleBoard = new BattleShipBoard(options.getBoardSize());
        this.hitBoard = new boolean[options.getBoardSize()][options.getBoardSize()];
    }

    public void decreaseHits(){
        this.hits --;
    }

    public void setHits(int hits){
        this.hits = hits;
    }

    public int getHits(){
        return this.hits;
    }

    public void setBoard(BattleShipBoard board){
        this.visibleBoard = board;
    }

    public BattleShipBoard getVisBoard(){
        return this.visibleBoard;
    }

    public BattleShipBoard getUnVisibleBoard(){
        return this.unVisibleBoard;
    }

    public boolean getHitBoard(int row, int col){
        return this.hitBoard[row][col];
    }

    public void setBoardButtonYellow(int row, int col){
        this.visibleBoard.buttons[row][col].setBackground(Color.yellow);
        this.unVisibleBoard.buttons[row][col].setBackground(Color.yellow);
        this.hitBoard[row][col] = true;
    }

    public void setBoardButtonRed(int row, int col){
        this.visibleBoard.buttons[row][col].setBackground(Color.red);
        this.visibleBoard.buttons[row][col].setText("x");
        this.visibleBoard.buttons[row][col].setFont(new Font("Arial",Font.BOLD,11));

        this.unVisibleBoard.buttons[row][col].setBackground(Color.red);
        this.unVisibleBoard.buttons[row][col].setText("x");
        this.unVisibleBoard.buttons[row][col].setFont(new Font("Arial",Font.BOLD,11));

        this.hitBoard[row][col] = true;
    }

    public String getName() {
        return this.name;
    }

    public void decreaseLife(){
        this.life --;
    }

    public int getLife(){
        return this.life;
    }

    public boolean getBoard_value(Integer row, Integer col) {
        return board[row][col];
    }


    public void setBoard(boolean[][] board){
        this.board = board;
}}
