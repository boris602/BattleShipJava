package BoardComponents;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class BattleShipBoard extends JPanel {
    public JButton[][] buttons;
    private BiConsumer<Integer, Integer> cellClickHandler;

    public BattleShipBoard(int boardSize) {
        // Use a GridBagLayout for more control over component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create the buttons grid
        buttons = new JButton[boardSize][boardSize];

        // Add alphabetic labels to the LEFT of the board (column letters)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVW";
        for (int row = 0; row < boardSize; row++) {
            JLabel alphaLabel = new JLabel(String.valueOf(alphabet.charAt(boardSize - 1 - row)));
            gbc.gridx = 0; // Position to the left of the first column of buttons
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.CENTER; // Center the label vertically
            add(alphaLabel, gbc);
        }

        // Add all the game board buttons (shifted right by 1 column)
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                JButton button = getJButton(row, col);
                buttons[row][col] = button;
                
                // Position in the grid - shifted right by 1 to make room for alphabet labels
                gbc.gridx = col + 1; // +1 to account for the alphabet column
                gbc.gridy = row;
                add(button, gbc);
            }
        }
        
        // Add numeric labels BELOW the board (row numbers)
        for (int col = 0; col < boardSize; col++) {
            JLabel numLabel = new JLabel(String.valueOf(col + 1));
            gbc.gridx = col + 1; // +1 to account for the alphabet column
            gbc.gridy = boardSize; // Position below the last row of buttons
            add(numLabel, gbc);
        }
    }

    private JButton getJButton(int row, int col) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(40, 40));
        button.setBackground(Color.WHITE);

        // Store row and col for use in the action listener
        final int finalRow = row;
        final int finalCol = col;

        button.addActionListener(e -> {
            if (cellClickHandler != null) {
                cellClickHandler.accept(finalRow, finalCol);
            }
        });
        return button;
    }

    // Method to set or update the click handler
    public void setCellClickHandler(BiConsumer<Integer, Integer> handler) {
        this.cellClickHandler = handler;
    }
}