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
import java.util.concurrent.CopyOnWriteArrayList;

public class SnakeGame {
    private final int WIDTH_WINDOW = 800;
    private final int HEIGHT_WINDOW = 600;
    private final int INCREMENT = 1;
    private final int SLEEP_INTERVAL = 200;
    private final int MAX_X_BOUND = WIDTH_WINDOW/40;
    private final int MAX_Y_BOUND = HEIGHT_WINDOW/40;
    private boolean gameOver;
    private GameWindow gameWindow;
    private CopyOnWriteArrayList<SnakePart> snake;
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
        snake = new CopyOnWriteArrayList<>();
        SnakePart head = new SnakePart(new Color(91, 171, 63), 10, 10);
        snake.add(head);
    }

    private void generateApple() {
        Random random = new Random();
        apple = new Apple(Color.RED, random.nextInt(0,MAX_X_BOUND), random.nextInt(0,MAX_Y_BOUND));
    }

    private void move() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            if (i == 0) {
                switch (direction) {
                    case UP:
                        snake.get(0).decreaseY(INCREMENT);
                        sleep();
                        gameWindow.repaint();
                        break;
                    case DOWN:
                        snake.get(0).increaseY(INCREMENT);
                        sleep();
                        gameWindow.repaint();
                        break;
                    case RIGHT:
                        snake.get(0).increaseX(INCREMENT);
                        sleep();
                        gameWindow.repaint();
                        break;
                    case LEFT:
                        snake.get(0).decreaseX(INCREMENT);
                        sleep();
                        gameWindow.repaint();
                        break;
                }
                checkCollision();
            }
            else {
                snake.get(i).setX(snake.get(i - 1).getX());
                snake.get(i).setY(snake.get(i - 1).getY());
            }
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
                    System.out.println("Pressed up");
                    direction = Direction.UP;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move down", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Pressed down");
                    direction = Direction.DOWN;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move left", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Pressed left");
                    direction = Direction.LEFT;
                }
            });
            gameWindow.getGamePanel().getActionMap().put("move right", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Pressed right");
                    direction = Direction.RIGHT;
                    checkCollision();
                }
            });
        }
    }

    private void checkCollision() {
        if (snake.get(0).getX() == apple.getX() && snake.get(0).getY() == apple.getY()) {
            snake.add(new SnakePart(Color.GREEN, snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY()));
            generateNewFoodPosition();
        }
    }

    public void generateNewFoodPosition() {
        Random random = new Random();
        apple.setX(random.nextInt(0, MAX_X_BOUND));
        apple.setY(random.nextInt(0, MAX_Y_BOUND));
    }


}
