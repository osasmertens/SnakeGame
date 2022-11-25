package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class InfoPanel extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 50;
    private JLabel scoreLabel;
    private Integer score;

    public InfoPanel() {
        this.score = score;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        scoreLabel = new JLabel("Score: ", JLabel.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Courier", 1, 26));
        add(scoreLabel);
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

}
