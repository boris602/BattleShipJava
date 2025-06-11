package BoardComponents;


import Options.Options;
import Player.Player;

import javax.swing.*;
import java.util.Objects;


public class BoardNextButton extends JButton {
    private Player player;
    private Options options;

    public BoardNextButton(Player player, Options options) {
        this.player = player;
        this.options = options;
        setText(setFieldText());
    }

    public BoardNextButton() {
        setText("next>>");
    }

    public String setFieldText() {
        if (Objects.equals(this.player.getName(), this.options.getPlayerName1())) {
            return "next>>";
        } else return "start>>";
    }
}
