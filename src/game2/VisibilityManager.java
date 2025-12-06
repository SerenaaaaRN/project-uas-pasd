package game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener; // Import ini penting untuk parameter createUI

public class VisibilityManager {

    UI ui;

    public VisibilityManager(UI userInterface) {
        ui = userInterface;
    }

    public void showTitleScreen() {
        // Tampilkan layar judul
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        // Sembunyikan layar game
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
    }

    public void titleToGame() {
        // Sembunyikan layar judul
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        // Tampilkan layar game
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
    }
}
