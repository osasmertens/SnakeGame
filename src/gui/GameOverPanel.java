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

    private final JPanel gameOverPanel;
    private final JPanel buttonPanel;
    private final JButton restartGameButton;
    private final JButton quitGameButton;
    private final JButton mainMenuButton;

    public GameOverPanel( int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        gameOverPanel = new JPanel();
        gameOverPanel.setPreferredSize(new Dimension(width, height - 100));
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setLayout(new BorderLayout());
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Courier", Font.BOLD, 52));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverPanel.add(gameOverLabel);
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(width, 100));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        restartGameButton = new JButton("restart game".toUpperCase());
        quitGameButton = new JButton("quit game".toUpperCase());
        mainMenuButton = new JButton("main menu".toUpperCase());
        quitGameButton.setPreferredSize(new Dimension(150,50));
        quitGameButton.setBackground(Color.LIGHT_GRAY);
        quitGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitGameButton.setVerticalAlignment(SwingConstants.CENTER);
        restartGameButton.setPreferredSize(new Dimension(150,50));
        restartGameButton.setBackground(Color.LIGHT_GRAY);
        restartGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        restartGameButton.setVerticalAlignment(SwingConstants.CENTER);
        mainMenuButton.setPreferredSize(new Dimension(150,50));
        mainMenuButton.setBackground(Color.LIGHT_GRAY);
        mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuButton.setVerticalAlignment(SwingConstants.CENTER);
        buttonPanel.add(restartGameButton);
        buttonPanel.add(mainMenuButton);
        buttonPanel.add(quitGameButton);
        add(gameOverPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getRestartGameButton() {
        return restartGameButton;
    }

    public JButton getQuitGameButton() {
        return quitGameButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
