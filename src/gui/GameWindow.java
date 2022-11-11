package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameWindow extends JFrame {
    private JPanel gamePanel;

    public GameWindow(ArrayList<SnakePart> snake, Apple apple, int width, int height) {
        super("Snake: The game");
        setResizable(false);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        gamePanel = new GamePanel(snake, apple);
        getContentPane().add(gamePanel);
        setVisible(true);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

}
