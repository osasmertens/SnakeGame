package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class GameOverPanel extends JPanel {

    private JPanel gameOverPanel;
    private JPanel buttonPanel;
    private JButton restartGameButton;
    private JButton quitGameButton;

    public GameOverPanel( int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        gameOverPanel = new JPanel();
        gameOverPanel.setPreferredSize(new Dimension(width, height - 100));
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setLayout(new BorderLayout());
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Courier", 1, 52));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverPanel.add(gameOverLabel);
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(width, 100));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        restartGameButton = new JButton("RESTART GAME");
        quitGameButton = new JButton("QUIT GAME");
        quitGameButton.setPreferredSize(new Dimension(150,50));
        quitGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitGameButton.setVerticalAlignment(SwingConstants.CENTER);
        restartGameButton.setPreferredSize(new Dimension(150,50));
        restartGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        restartGameButton.setVerticalAlignment(SwingConstants.CENTER);
        buttonPanel.add(quitGameButton);
        buttonPanel.add(restartGameButton);
        add(gameOverPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getRestartGameButton() {
        return restartGameButton;
    }

    public JButton getQuitGameButton() {
        return quitGameButton;
    }
}
