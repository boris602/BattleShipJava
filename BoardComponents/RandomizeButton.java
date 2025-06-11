package BoardComponents;


import javax.swing.*;
import java.awt.*;

public class RandomizeButton extends JButton {


    public RandomizeButton() {
        setText("Randomize Ships");
        setSize(200, 40);
        setBackground(Color.BLUE);
        setForeground(Color.MAGENTA);
        addActionListener(e->
                setEnabled(false));
    }
}
