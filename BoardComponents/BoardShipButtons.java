package BoardComponents;

import Options.Options;

import javax.swing.*;
import java.awt.*;


public class BoardShipButtons extends JPanel {
    private JButton[] shipButtons;
    private boolean placing;
    private Integer placingLeft;
    private Integer ships2;
    private Integer ships3;
    private Integer ships4;
    private Integer ships5;


    public BoardShipButtons(Options options, Integer maximumSize) {
        this.placingLeft = 0;
        this.ships2 = options.getShipSize2();
        this.ships3 = options.getShipSize3();
        this.ships4 = options.getShipSize4();
        this.ships5 = options.getShipSize5();
        setLayout(new GridLayout(1, 4, 10, 0));
        setMaximumSize(new Dimension(maximumSize - 50, 50));
        this.setShipButtons();
    }


    public void setShipButtons() {
        this.shipButtons = new JButton[4];
        for (int ind = 0; ind < 4; ind++) {
            JButton button = new JButton("<html>Ship " + (ind + 2) + "<br>Left: " + this.getShipsLeft(ind+2) + "</html>");
            button.setPreferredSize(new Dimension(80, 40));
            button.setBackground(Color.GREEN);
            button.addActionListener(e -> ActionListenerShips(button));
            shipButtons[ind] = button;
            add(button);
        }
        // Match the shipButtonsPanel width to the board's actual width
    }

    void ActionListenerShips(JButton button) {
        if (!placing) {
            placing = true;
            if (button == shipButtons[0]) {
                placingLeft = 2;
                this.setShipsLeft(2);
                if (this.getShipsLeft(2) == 0) {
                    button.setEnabled(false);
                }
                button.setText("<html>Ship " + (2) + "<br>Left: " + this.getShipsLeft(2) + "</html>");
            } else if (button == shipButtons[1]) {
                placingLeft = 3;
                this.setShipsLeft(3);
                if (this.getShipsLeft(3)==0) {
                    button.setEnabled(false);
                }
                button.setText("<html>Ship " + (3) + "<br>Left: " + this.getShipsLeft(3) + "</html>");
            } else if (button == shipButtons[2]) {
                placingLeft = 4;
                this.setShipsLeft(4);
                if (this.getShipsLeft(4)==0) {
                    button.setEnabled(false);
                }
                button.setText("<html>Ship " + (4) + "<br>Left: " + this.getShipsLeft(4) + "</html>");
            } else if (button == shipButtons[3])  {
                placingLeft = 5;
                this.setShipsLeft(5);
                if (this.getShipsLeft(5)== 0) {
                    button.setEnabled(false);
                }
                button.setText("<html>Ship " + (5) + "<br>Left: " + this.getShipsLeft(5) + "</html>");
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "You are currently placing a ship",
                    "Ship Placement Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getPlacingLeft() {
        return this.placingLeft;
    }

    public void setDefault() {
        this.ships2 = 0;
        this.ships3 = 0;
        this.ships4 = 0;
        this.ships5 = 0;
        shipButtons[0].setText("<html>Ship " + (2) + "<br>Left: " + this.ships2 + "</html>");
        shipButtons[1].setText("<html>Ship " + (3) + "<br>Left: " + this.ships3 + "</html>");
        shipButtons[2].setText("<html>Ship " + (4) + "<br>Left: " + this.ships4 + "</html>");
        shipButtons[3].setText("<html>Ship " + (5) + "<br>Left: " + this.ships5 + "</html>");
        shipButtons[0].setEnabled(false);
        shipButtons[1].setEnabled(false);
        shipButtons[2].setEnabled(false);
        shipButtons[3].setEnabled(false);
    }


    public int getShipsLeft(Integer ind) {
        return switch (ind) {
            case 2 -> this.ships2;
            case 3 -> this.ships3;
            case 4 -> this.ships4;
            case 5 -> this.ships5;
            default -> 0;
        };
    }

    public void setShipsLeft(int ind) {
        switch (ind) {
            case 2:
                this.ships2--;
                break;
            case 3:
                this.ships3--;
                break;
            case 4:
                this.ships4--;
                break;
            case 5:
                this.ships5--;
                break;
        }

    }


    public void setPlacingLeft() {
        this.placingLeft--;
        if (this.placingLeft == 0) {
            this.placing = false;
        }
    }

    public void setPlacingLeft(Integer placingLeft) {
        this.placingLeft = placingLeft;
    }
}
