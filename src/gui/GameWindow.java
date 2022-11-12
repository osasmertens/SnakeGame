package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWindow extends JFrame {
    private JPanel gamePanel;

    public GameWindow(CopyOnWriteArrayList<SnakePart> snake, Apple apple, int width, int height, int unitsize) {
        super("Snake: The game");
        setResizable(false);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        gamePanel = new GamePanel(snake, apple, unitsize);
        getContentPane().add(gamePanel);
        setVisible(true);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

}
