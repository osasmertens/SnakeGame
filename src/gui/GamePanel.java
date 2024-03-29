package gui;

import entities.Apple;
import entities.SnakePart;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel {

    private final int UNIT_SIZE;
    private CopyOnWriteArrayList<SnakePart> snake;
    private Apple apple;

    public GamePanel(CopyOnWriteArrayList<SnakePart> snake, Apple apple, int width, int height, int unitsize) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        this.snake = snake;
        this.apple = apple;
        this.UNIT_SIZE = unitsize;
        revalidate();
    }

    public void paintComponent(Graphics g) {
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
