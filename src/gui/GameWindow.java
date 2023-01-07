package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWindow extends JFrame {
    private JPanel gamePanel;
    private InfoPanel infoPanel;

    public GameWindow(CopyOnWriteArrayList<SnakePart> snake, Apple apple, int width, int height, int unitsize) {
        super("Snake: The game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(new BorderLayout());
        gamePanel = new GamePanel(snake, apple, width, height, unitsize);
        infoPanel = new InfoPanel();
        add(infoPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

}
