package Boards;



import BoardComponents.PlayerPanel;
import GameManager.GameManager;
import Player.Player;

import javax.swing.*;
import java.awt.*;



public class PlayerWonBoard extends JPanel {


    public PlayerWonBoard(Player player1, Player player2, GameManager gameManager) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText(player1.getName() + "won!!!!");
        label.setFont(new Font("Arial", Font.BOLD, 60));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(label, gbc);

        // Create board panel
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        gbc.insets = new Insets(0, 0, 0, 0);
        add(player1.getVisBoard(), gbc);
        player1.getVisBoard().setCellClickHandler(this::boardListener);
        player2.getVisBoard().setCellClickHandler(this::boardListener);


        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(player2.getVisBoard(), gbc);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(100, 0));
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(spacer, gbc);

        PlayerPanel player1Panel = new PlayerPanel(player1.getName());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make it fill the cell horizontally
        add(player1Panel, gbc);

        PlayerPanel player2Panel = new PlayerPanel(player2.getName());
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(player2Panel, gbc);

        JPanel spacer2 = new JPanel();
        spacer.setPreferredSize(new Dimension(50, 0));
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(spacer2, gbc);

        JButton newGameButton = new JButton("new game>>");
        newGameButton.setMaximumSize(new Dimension(50,20));
        newGameButton.addActionListener(e-> gameManager.visitMainMenu());
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(newGameButton,gbc);
    }

    public void boardListener(int row, int col) {
        JOptionPane.showMessageDialog(
                this,
                "Game is over",
                "SHOOTING DISABLED",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

