package Boards;


import BoardComponents.BoardNextButton;
import BoardComponents.PlayerPanel;
import GameManager.GameManager;
import Options.Options;
import Player.Player;

import javax.swing.*;
import java.awt.*;


public class PlayerGameBoard extends JPanel {
    private final Player player1;
    private final Player player2;
    private final GameManager gameManager;
    private final Options options;


    public PlayerGameBoard(Player player1, Player player2, GameManager gameManager, Options options) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameManager = gameManager;
        this.options = options;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create board panel
        this.player1.getVisBoard().setCellClickHandler(this::board1Listener);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        gbc.insets = new Insets(0, 0, 0, 0);
        add(this.player1.getVisBoard(), gbc);

        this.player2.getUnVisibleBoard().setCellClickHandler(this::board2Listener);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(this.player2.getUnVisibleBoard(), gbc);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(100, 0));
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(spacer, gbc);

        PlayerPanel player1Panel = new PlayerPanel(player1.getName());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make it fill the cell horizontally
        add(player1Panel, gbc);

        PlayerPanel player2Panel = new PlayerPanel(player2.getName());
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(player2Panel, gbc);

        JPanel spacer2 = new JPanel();
        spacer.setPreferredSize(new Dimension(50, 0));
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(spacer2, gbc);

        BoardNextButton boardNextButton = new BoardNextButton();
        boardNextButton.addActionListener(e -> this.nextListener());
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(boardNextButton, gbc);

        // color own board
    }

    public void nextListener() {
        if (options.getPlayerName1().equals(this.player1.getName())) {
            gameManager.visitNextBoard1();
        } else gameManager.visitNextBoard2();
    }


    public void board2Listener(int row, int col) {
        System.out.println(player2.getLife());
        if (player1.getHits()==0){
            JOptionPane.showMessageDialog(
                    this,
                    "You are out of shots",
                    "Attack Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if (player2.getHitBoard(row, col)) {
            JOptionPane.showMessageDialog(
                    this,
                    "This field already got attacked",
                    "Attack Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if (player2.getBoard_value(row, col)){
            player2.setBoardButtonRed(row, col);
            player2.decreaseLife();
            if (player2.getLife()==0){
                gameManager.createPlayerWonBoard(player1, player2);
            }
        }
        else {
            player2.setBoardButtonYellow(row, col);
            player1.decreaseHits();
        }
    }


    public void board1Listener(int row, int col){
        JOptionPane.showMessageDialog(
                this,
                "u cannot attack ur own field",
                "Invalid Shooting",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

