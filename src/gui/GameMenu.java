package gui;

import game.SnakeGame;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private JPanel gameMenuPanel;
    private JPanel buttonPanel;
    private JButton startGameButton;
    private JButton highScoreButton;
    private JButton quitGameButton;

    public GameMenu() {
        setTitle("Snake: Java Edition");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("src/resources/snakeicon.png");
        setIconImage(imageIcon.getImage());
        getContentPane().setBackground(Color.BLACK);
        createGameMenuPanel();
        createButtonPanel();
        getContentPane().add(gameMenuPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        revalidate();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createGameMenuPanel() {
        gameMenuPanel = new JPanel();
        gameMenuPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 200));
        gameMenuPanel.setBackground(Color.BLACK);
        JLabel gameNameLabel = new JLabel("Snake: Java Edition");
        ImageIcon snakeImage = new ImageIcon("src/resources/snakeimage.png");
        gameNameLabel.setIcon(snakeImage);
        gameNameLabel.setVerticalTextPosition(JLabel.TOP);
        gameNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        gameNameLabel.setFont(new Font("Courier", Font.BOLD, 64));
        gameNameLabel.setForeground(Color.GREEN);
        gameNameLabel.setHorizontalAlignment(JLabel.CENTER);
        gameNameLabel.setVerticalAlignment(JLabel.CENTER);
        gameMenuPanel.add(gameNameLabel);
        gameMenuPanel.revalidate();
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(WIDTH, 200));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        startGameButton = new JButton("start game".toUpperCase());
        startGameButton.setPreferredSize(new Dimension(150,50));
        startGameButton.setBackground(Color.LIGHT_GRAY);
        startGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        startGameButton.setVerticalAlignment(SwingConstants.CENTER);
        highScoreButton = new JButton("high score".toUpperCase());
        highScoreButton.setPreferredSize(new Dimension(150,50));
        highScoreButton.setBackground(Color.LIGHT_GRAY);
        highScoreButton.setHorizontalAlignment(SwingConstants.CENTER);
        highScoreButton.setVerticalAlignment(SwingConstants.CENTER);
        quitGameButton = new JButton("quit game".toUpperCase());
        quitGameButton.setPreferredSize(new Dimension(150,50));
        quitGameButton.setBackground(Color.LIGHT_GRAY);
        quitGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitGameButton.setVerticalAlignment(SwingConstants.CENTER);
        setUpActions();
        buttonPanel.add(startGameButton);
        buttonPanel.add(highScoreButton);
        buttonPanel.add(quitGameButton);
        buttonPanel.revalidate();
    }


    private void setUpActions() {
        startGameButton.addActionListener(e -> {
            dispose();
            Thread thread = new Thread(SnakeGame::new);
            thread.start();
        });
        quitGameButton.addActionListener(e -> System.exit(0));
    }
}
