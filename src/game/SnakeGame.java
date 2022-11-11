package game;

import entities.Apple;
import entities.SnakePart;
import gui.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame {
    private final int WIDTH_WINDOW = 800;
    private final int HEIGHT_WINDOW = 600;
    private final int INCREMENT = 20;
    private final int SLEEP_INTERVAL = 100;
    private boolean gameOver;
    private GameWindow gameWindow;
    private ArrayList<SnakePart> snake;
    private Apple apple;
    private Direction direction;

    public SnakeGame() {
        createSnake();
        direction = Direction.RIGHT;
        generateApple();
        gameWindow = new GameWindow(snake, apple, WIDTH_WINDOW, HEIGHT_WINDOW);
        gameOver = false;
        startGame();
        /*while (!gameOver) {
            move(Direction.RIGHT);
        }*/

    }

    private void createSnake() {
        snake = new ArrayList<>();
        SnakePart head = new SnakePart(Color.ORANGE, 10, 10);
        snake.add(head);
    }

    private void generateApple() {
        Random random = new Random();
        apple = new Apple(Color.RED, random.nextInt(WIDTH_WINDOW/2), random.nextInt(WIDTH_WINDOW/2));
    }

    private void move() {

        switch (direction) {
            case UP:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseY(INCREMENT);
                    gameWindow.repaint();
                    sleep();
                }
                break;
            case DOWN:
                for (SnakePart snakePart : snake) {
                    snakePart.increaseY(INCREMENT);
                    gameWindow.repaint();
                    sleep();
                }
                break;
            case LEFT:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseX(INCREMENT);
                    gameWindow.repaint();
                    sleep();
                }
                break;
            case RIGHT:
                for (SnakePart snakePart : snake) {
                    snakePart.increaseX(INCREMENT);
                    gameWindow.repaint();
                    sleep();
                }
                break;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startGame() {
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0, true), "move up");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0, true), "move down");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0, true), "move left");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0, true), "move right");
        while (!gameOver) {
            move();
            gameWindow.getGamePanel().getActionMap().put("move up", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    direction = Direction.UP;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move down", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    direction = Direction.DOWN;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move left", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    direction = Direction.LEFT;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move right", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    direction = Direction.RIGHT;
                }
            });
        }
    }


}
