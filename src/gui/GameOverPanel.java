package gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class GameOverPanel extends JPanel {


    public GameOverPanel( int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        JLabel gameOverLabel = new JLabel("game over".toUpperCase());
        gameOverLabel.setFont(new Font("Courier", 1, 52));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOverLabel);
    }
}
