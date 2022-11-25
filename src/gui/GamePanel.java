package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel {

    private static final int HEIGHT_PANEL = 800;
    private static final int WIDTH_PANEL = 600;
    private final int UNIT_SIZE;
    private CopyOnWriteArrayList<SnakePart> snake;
    private Apple apple;

    public GamePanel(CopyOnWriteArrayList<SnakePart> snake, Apple apple, int unitsize) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        this.snake = snake;
        this.apple = apple;
        this.UNIT_SIZE = unitsize;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawApple(g);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snake.size(); i++) {
            g.setColor(snake.get(i).getColor());
            g.fillRect(snake.get(i).getX(), snake.get(i).getY(), UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(apple.getColor());
        g.fillOval(apple.getX(), apple.getY(), UNIT_SIZE, UNIT_SIZE);
    }

}
