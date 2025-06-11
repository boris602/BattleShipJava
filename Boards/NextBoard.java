package Boards;

import GameManager.GameManager;
import Options.Options;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NextBoard extends JPanel {
    private final  Options options;
    private final Player player;
    private final GameManager gameManager;


    public NextBoard(Player player, Options options, GameManager gameManager){
        this.gameManager = gameManager;
        this.options = options;
        this.player = player;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel text= new JLabel(player.getName() + " is next");
        text.setFont(new Font("Arial", Font.BOLD, 75));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(text, gbc);


        JButton nextButton = new JButton(player.getName() + ">>");
        nextButton.setPreferredSize(new Dimension(100,50));
        nextButton.addActionListener(e -> this.buttonListener());

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No horizontal stretching
        gbc.weighty = 0.0; // No vertical stretching
        add(nextButton, gbc);


    }

    private void buttonListener(){
        this.player.setHits(1);
        if (Objects.equals(this.player.getName(), options.getPlayerName1())){
            gameManager.visitGameBoard1();
        }
        else{
            gameManager.visitGameBoard2();
        }

    }
}
