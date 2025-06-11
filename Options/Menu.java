package Options;

import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel {

    public Menu(CardLayout cardLayout, JPanel cardPanel) {


        // Set up the panel
        setLayout(new BorderLayout());
        
        // Create a panel for the content with BoxLayout for vertical arrangement
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Add title
        JLabel titleLabel = new JLabel("Battleship Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create buttons
        JButton playButton = new JButton("Play Game");
        JButton optionsButton = new JButton("Options");
        JButton exitButton = new JButton("Exit");
        
        // Set button sizes
        Dimension buttonSize = new Dimension(200, 40);
        playButton.setMaximumSize(buttonSize);
        optionsButton.setMaximumSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        
        // Center-align buttons
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add action listeners
        playButton.addActionListener(e -> {
            // Add your play game logic here
            cardLayout.show(cardPanel, "PLAYER_INPUT");
        });
        
        optionsButton.addActionListener(e -> {
            // Resize frame for options panel
            
            // Switch to options panel
            cardLayout.show(cardPanel, "OPTIONS");
        });

        exitButton.addActionListener(e -> System.exit(0));
        
        // Add components to the panel with spacing
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(playButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(optionsButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(exitButton);
        
        // Add content panel to the main panel
        add(contentPanel, BorderLayout.CENTER);
    }
}