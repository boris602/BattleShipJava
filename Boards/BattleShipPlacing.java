package Boards;

import BoardComponents.*;
import GameManager.GameManager;
import Options.Options;
import Player.Player;

import javax.swing.*;
import java.awt.*;

import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;


public class BattleShipPlacing extends JPanel {
    // main board with options
    private final BattleShipBoard board;
    private final Integer boardSize;
    private final BoardShipButtons shipButtons;

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final BoardVerifier boardVerifier;
    private final Player player;
    private final Options options;
    private final GameManager gameManager;


    public BattleShipPlacing(JPanel cardPanel, CardLayout cardLayout, Options options, Player player, GameManager gameManager) {
        this.options = options;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.boardSize = options.getBoardSize();
        this.boardVerifier = new BoardVerifier(options);
        this.player = player;
        this.gameManager = gameManager;

        // Use GridBagLayout for precise control
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create board panel
        this.board = new BattleShipBoard(this.boardSize);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(this.board, gbc);

        // create ship panel
        this.shipButtons = new BoardShipButtons(options, this.boardSize);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(shipButtons, gbc);


        // Add a 20px vertical gap using an empty component
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(Box.createRigidArea(new Dimension(10, 20)), gbc);


        // Add randomizedButton
        RandomizeButton randomizeButton = new RandomizeButton();
        randomizeButton.addActionListener(e-> this.randomizeShip());
        gbc.gridy = 4;
        add(randomizeButton, gbc);

        // create NextButton
        BoardNextButton nextButton = new BoardNextButton(player, options);
        nextButton.addActionListener(e->this.ActionListenerNextButton());
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(nextButton, gbc);

        JPanel titleLabel = new PlayerPanel(player.getName());
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(titleLabel, gbc);


        this.board.setCellClickHandler(this.addButtonBoardHandler());

        // add padding
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
    }

    private BiConsumer<Integer, Integer> addButtonBoardHandler() {
        return (row, col) -> {
            if (boardVerifier.updateBoolBoard(row, col, shipButtons.getPlacingLeft())) {
                board.buttons[row][col].setBackground(Color.blue);
                shipButtons.setPlacingLeft();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "u cannot place a ship here",
                        "Invalid Ship placement",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        };
    }

    private void randomizeShip(){
        Random random = new Random();
        randomize(random);
        for (int ind = 0; ind < 4; ind++){
            for(int ind2=0; ind2< shipButtons.getShipsLeft(ind+2); ind2++){
                shipButtons.setPlacingLeft(ind+2);
                randomize(random);
            }
        }
        shipButtons.setDefault();
    }

    private void randomize(Random random) {
        while (this.shipButtons.getPlacingLeft() > 0) {
            int row = random.nextInt(this.boardSize);
            int col = random.nextInt(this.boardSize);
            if (boardVerifier.updateBoolBoard(row, col, shipButtons.getPlacingLeft())) {
                board.buttons[row][col].setBackground(Color.blue);
                shipButtons.setPlacingLeft();
            }
        }
    }

    private void ActionListenerNextButton(){
        if (shipButtons.getShipsLeft(2) == 0 && shipButtons.getShipsLeft(3) == 0 &&
                shipButtons.getShipsLeft(4) == 0 && shipButtons.getShipsLeft(5) == 0 ){
            player.setBoard(boardVerifier.getBoard());
            this.player.setBoard(this.board);
            if (Objects.equals(player.getName(), options.getPlayerName1())){
                cardLayout.show(cardPanel, "PLAYER_PLACEMENT2");
            }
            else {
                this.gameManager.createGame();
            }
        }
        else{
            JOptionPane.showMessageDialog(
                    this,
                    "You must finish placing ur ships",
                    "Ships not placed Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}