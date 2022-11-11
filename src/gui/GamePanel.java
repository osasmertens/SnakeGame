package gui;

import entities.Apple;
import entities.SnakePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel {

    private static final int HEIGHT_PANEL = 600;
    private static final int WIDTH_PANEL = 800;
    private static final int HEIGHT_BODY = 20;
    private static final int WIDTH_BODY = 20;
    private static final int DIAMETER = 20;
    private CopyOnWriteArrayList<SnakePart> snake;
    private Apple apple;

    public GamePanel(CopyOnWriteArrayList<SnakePart> snake, Apple apple) {
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
        //for (SnakePart snakePart : snake) {
        for (int i = 0; i < snake.size(); i++) {
            g.setColor(snake.get(i).getColor());
            g.fillRect(snake.get(i).getX() * (WIDTH_PANEL/WIDTH_BODY), snake.get(i).getY() * (HEIGHT_PANEL/HEIGHT_BODY), WIDTH_BODY, HEIGHT_BODY);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(apple.getColor());
        g.fillOval(apple.getX() * (WIDTH_PANEL/WIDTH_BODY), apple.getY() * (HEIGHT_PANEL/HEIGHT_BODY), DIAMETER, DIAMETER);
    }

}
