package Options;



import javax.swing.*;
import java.awt.*;



public class OptionMenu extends JPanel {
    private final Options options;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;


    public OptionMenu(Options options, CardLayout cardLayout, JPanel cardPanel) {
        this.options = options;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        // Set up the panel
        setLayout(new BorderLayout());
        add(createOptionsPanel(), BorderLayout.CENTER);
    }

    private JPanel createOptionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel optionsContent = new JPanel(new GridLayout(0, 2, 10, 10));
        optionsContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Ship size 2 settings
        optionsContent.add(new JLabel("Ship Size 2 Count:"));
        JSpinner shipSize2Spinner = new JSpinner(new SpinnerNumberModel(
                options.getShipSize2(), 1, 10, 1));
        optionsContent.add(shipSize2Spinner);

        // Ship size 3 settings
        optionsContent.add(new JLabel("Ship Size 3 Count:"));
        JSpinner shipSize3Spinner = new JSpinner(new SpinnerNumberModel(
                options.getShipSize3(), 1, 10, 1));
        optionsContent.add(shipSize3Spinner);

        // Ship size 4 settings
        optionsContent.add(new JLabel("Ship Size 4 Count:"));
        JSpinner shipSize4Spinner = new JSpinner(new SpinnerNumberModel(
                options.getShipSize4(), 1, 10, 1));
        optionsContent.add(shipSize4Spinner);

        // Ship size 5 settings
        optionsContent.add(new JLabel("Ship Size 5 Count:"));
        JSpinner shipSize5Spinner = new JSpinner(new SpinnerNumberModel(
                options.getShipSize5(), 1, 10, 1));
        optionsContent.add(shipSize5Spinner);

        // Board size settings
        optionsContent.add(new JLabel("Board Size:"));
        JSpinner boardSizeSpinner = new JSpinner(new SpinnerNumberModel(
                options.getBoardSize(), 10, 15, 1));
        optionsContent.add(boardSizeSpinner);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton saveButton = new JButton("Save & Return");
        saveButton.addActionListener(e -> {
            // Get the values from spinners
            int ship2 = (Integer)shipSize2Spinner.getValue();
            int ship3 = (Integer)shipSize3Spinner.getValue();
            int ship4 = (Integer)shipSize4Spinner.getValue();
            int ship5 = (Integer)shipSize5Spinner.getValue();
            int boardSize = (Integer)boardSizeSpinner.getValue();

            // Check if configuration is valid
            if (shipCondition(ship2, ship3, ship4, ship5, boardSize)) {
                // Save the options
                options.setShipSize2(ship2);
                options.setShipSize3(ship3);
                options.setShipSize4(ship4);
                options.setShipSize5(ship5);
                options.setBoardSize(boardSize);

                // Return to main menu
                cardLayout.show(cardPanel, "MAIN_MENU");
            }
            // If shipCondition returned false, we stay on this screen (error already displayed)
        });


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Resize frame for menu panel

            // Return to main menu without saving
            cardLayout.show(cardPanel, "MAIN_MENU");
        });

        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        panel.add(new JLabel("Game Options.Options", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(optionsContent, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        return panel;
    }

    private Boolean shipCondition(int shipSize2, int shipSize3, int shipSize4, int shipSize5, int boardSize){
        int amount = shipSize2 * 2 + shipSize3 * 3 + shipSize4 * 4 + shipSize5 * 5;
        if (amount > 0.35 * boardSize * boardSize){
            // Show error message with red "X" icon
            JOptionPane.showMessageDialog(
                    this,  // Parent component
                    "Too many ships for the board size!",  // Message
                    "Invalid Configuration",  // Title
                    JOptionPane.ERROR_MESSAGE  // Message type (shows red X)
            );
            return false;
        }
        return true;
    }

}