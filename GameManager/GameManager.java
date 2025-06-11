package GameManager;
import Boards.BattleShipPlacing;
import Boards.NextBoard;
import Boards.PlayerGameBoard;
import Boards.PlayerWonBoard;
import Options.Options;
import Options.PlayerGui;
import Options.OptionMenu;
import Options.Menu;
import Player.Player;

import javax.swing.*;
import java.awt.*;




public class GameManager{
    private JFrame gameFrame;
    private  CardLayout cardLayout;
    private  JPanel cardPanel;
    private  Options options;

    private Player player1;
    private Player player2;

    public GameManager() {
        this.initializeGame();
    }

    public void initializeGame(){
        gameFrame = new JFrame("Battleship Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800, 600);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        options = new Options();
        createStarPanels();

        // Add the main panel container to the frame
        gameFrame.add(cardPanel);
        gameFrame.setLocationRelativeTo(null); // Center on screen
        gameFrame.setVisible(true);
        cardLayout.show(cardPanel, "MAIN_MENU");

    }

    private void createStarPanels() {
        // Create all the panels needed for the game
        Menu menuPanel = new Menu(cardLayout, cardPanel);
        OptionMenu optionMenuPanel = new OptionMenu(options, cardLayout, cardPanel);
        PlayerGui playerGui = new PlayerGui(options, cardLayout, cardPanel, this);

        // Add panels to the card layout with their respective names
        cardPanel.add(menuPanel, "MAIN_MENU");
        cardPanel.add(optionMenuPanel, "OPTIONS");
        cardPanel.add(playerGui, "PLAYER_INPUT");
    }

    public void createPlayerPlacingPanels(){
        gameFrame.setSize(500 + options.getBoardSize()*30, 470 + options.getBoardSize()*30);
        gameFrame.setLocationRelativeTo(null);
        this.player1 = new Player(options, options.getPlayerName1());
        this.player2 = new Player(options, options.getPlayerName2());
        BattleShipPlacing placing1 = new BattleShipPlacing(cardPanel, cardLayout, options, player1, this);
        // placementBoard for player 1.
        cardPanel.add(placing1, "PLAYER_PLACEMENT1");
        // placementBoard for player 2.
        BattleShipPlacing placing2 = new BattleShipPlacing(cardPanel, cardLayout, options, player2, this);
        cardPanel.add(placing2, "PLAYER_PLACEMENT2");
        // show cardPanel of player 1 at first.
        cardLayout.show(cardPanel, "PLAYER_PLACEMENT1");
    }

    public void createGame(){
        gameFrame.setSize(1000 + options.getBoardSize()*60, 470 + options.getBoardSize()*30);
        gameFrame.setLocationRelativeTo(null);
        PlayerGameBoard gameBoard1 = new PlayerGameBoard(this.player1, this.player2, this, this.options);
        cardPanel.add(gameBoard1, "GAMEBOARD1");
        cardLayout.show(cardPanel, "GAMEBOARD1");

        PlayerGameBoard gameBoard2 = new PlayerGameBoard(this.player2, this.player1, this, this.options);
        cardPanel.add(gameBoard2, "GAMEBOARD2");

        NextBoard nextBoard1 =  new NextBoard(this.player2, this.options, this);
        cardPanel.add(nextBoard1, "NEXTBOARD1");

        NextBoard nextBoard2 =  new NextBoard(this.player1, this.options, this);
        cardPanel.add(nextBoard2, "NEXTBOARD2");
    }

    public void visitNextBoard1(){
        cardLayout.show(cardPanel,"NEXTBOARD1");
    }

    public void visitNextBoard2(){
        cardLayout.show(cardPanel,"NEXTBOARD2");
    }

    public void visitGameBoard1(){
        cardLayout.show(cardPanel,"GAMEBOARD1");
    }

    public void visitGameBoard2(){
        cardLayout.show(cardPanel,"GAMEBOARD2");
    }

    public void visitMainMenu(){
        gameFrame.setSize(800,600);
        gameFrame.setLocationRelativeTo(null); // Center on screen
        cardLayout.show(cardPanel, "MAIN_MENU");
    }

    public void createPlayerWonBoard(Player player1, Player player2) {
        PlayerWonBoard wonBoard = new PlayerWonBoard(player1, player2, this);
        cardPanel.add(wonBoard, "WONBOARD");
        cardLayout.show(cardPanel, "WONBOARD");
    }
}
