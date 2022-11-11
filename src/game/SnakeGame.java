package game;

import entities.Apple;
import entities.SnakePart;
import gui.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame {
    private final int WIDTH_WINDOW = 800;
    private final int HEIGHT_WINDOW = 600;
    private final int INCREMENT = 20;
    private final int SLEEP_INTERVAL = 200;
    private boolean gameover;
    private GameWindow gameWindow;
    private ArrayList<SnakePart> snake;
    private Apple apple;

    public SnakeGame() {
        createSnake();
        generateApple();
        gameWindow = new GameWindow(snake, apple, WIDTH_WINDOW, HEIGHT_WINDOW);
        gameover = false;
        //moveSnake(INCREMENT);

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

    private void moveSnake(int speed, Direction direction) {

        switch (direction) {
            case UP:
                for (SnakePart snakePart : snake) {
                    snakePart.increaseY(speed);
                    gameWindow.repaint();
                    sleep();
                }
            case DOWN:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseY(speed);
                    gameWindow.repaint();
                    sleep();
                }
            case LEFT:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseX(speed);
                    gameWindow.repaint();
                    sleep();
                }
            case RIGHT:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseX(speed);
                    gameWindow.repaint();
                    sleep();
                }
            default:
                for (SnakePart snakePart : snake) {
                    snakePart.decreaseX(speed);
                    gameWindow.repaint();
                    sleep();
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


}
