package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private static final int HEIGHT_PANEL = 600;
    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_BODY = 20;
    private static final int WIDTH_BODY = 20;
    private static final int DIAMETER = 15;
    private ArrayList<SnakePart> snake;
    private Apple apple;

    public GamePanel(ArrayList<SnakePart> snake, Apple apple) {
        setBackground(Color.BLACK);
        setSize(WIDTH_PANEL, HEIGHT_PANEL);
        this.snake = snake;
        this.apple = apple;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawApple(g);
    }

    private void drawSnake(Graphics g) {
        for (SnakePart snakePart : snake) {
            g.setColor(snakePart.getColor());
            g.fillRect(snakePart.getX(), snakePart.getY(), WIDTH_BODY, HEIGHT_BODY);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(apple.getColor());
        g.fillOval(apple.getX() - DIAMETER/2, apple.getY() - DIAMETER/2, DIAMETER, DIAMETER);
    }

}
