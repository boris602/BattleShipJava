package Options;


import GameManager.GameManager;
import javax.swing.*;
import java.awt.*;


public class PlayerGui extends JPanel {
    private JTextField player1Field;
    private JTextField player2Field;
    private final Options options;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final GameManager gamemanager;


    public PlayerGui(Options options, CardLayout cardLayout, JPanel cardPanel, GameManager gamemanager ) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.options = options;
        this.gamemanager = gamemanager;
        
        setLayout(new BorderLayout());
        add(createPlayerEntryPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createPlayerEntryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Title at the top
        JLabel titleLabel = new JLabel("Enter Player Names", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Player 1 label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Player 1 Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        player1Field = new JTextField("Player1",5);
        inputPanel.add(player1Field, gbc);
        
        // Player 2 label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        inputPanel.add(new JLabel("Player 2 Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        player2Field = new JTextField("Player2",5);
        inputPanel.add(player2Field, gbc);
        
        panel.add(inputPanel, BorderLayout.CENTER);
        
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel();

        JButton startButton= this.createStartButton();
        JButton backButton = this.backButton();
        
        buttonPanel.add(startButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            String player1Name = player1Field.getText().trim();
            String player2Name = player2Field.getText().trim();

            // Check if names are invalid
            if (this.checkPlayerNames(player1Name, player2Name) == 1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter names for both players",
                        "Missing Names",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (this.checkPlayerNames(player1Name, player2Name) == 2) {
                JOptionPane.showMessageDialog(
                        this,
                        "keep the size of player length at most 20",
                        "Wrong Input",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (this.checkPlayerNames(player1Name, player2Name) == 3) {
                JOptionPane.showMessageDialog(
                        this,
                        "The names of player 1 and 2 have to differ",
                        "Same Names",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                options.setPlayerName1(player1Name);
                options.setPlayerName2(player2Name);
                gamemanager.createPlayerPlacingPanels();
            }
        });
        return startButton;
    }

    private JButton backButton(){
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
       cardLayout.show(cardPanel, "MAIN_MENU");
    });
    return backButton;
    }


    Integer checkPlayerNames(String name1, String name2){
        if (name1.isEmpty() || name2.isEmpty()) {
            return 1;
        }
        else if(name1.length() > 20 || name2.length() > 20) {
            return 2;
        }
        else if (name1.equals(name2)) {
            return 3;
        }
        else return 4;
    };
}