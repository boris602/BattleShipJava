package BoardComponents;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    public PlayerPanel(String name) {
        JLabel titleLabel = new JLabel(name);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
    }
}
