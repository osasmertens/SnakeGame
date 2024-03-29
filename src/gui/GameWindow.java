package gui;

import entities.Apple;
import entities.SnakePart;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWindow extends JFrame {
    private final JPanel gamePanel;
    private final InfoPanel infoPanel;

    public GameWindow(CopyOnWriteArrayList<SnakePart> snake,
                      Apple apple,
                      int width,
                      int height,
                      int unitSize) {
        setTitle("Snake: Java Edition");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("src/resources/snakeicon.png");
        setIconImage(imageIcon.getImage());
        getContentPane().setBackground(Color.BLACK);
        gamePanel = new GamePanel(snake, apple, width, height, unitSize);
        infoPanel = new InfoPanel();
        getContentPane().add(infoPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        pack();
        revalidate();
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
