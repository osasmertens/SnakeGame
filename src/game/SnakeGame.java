package game;

import entities.Apple;
import entities.SnakePart;
import gui.GameWindow;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnakeGame {
    private final int WIDTH_WINDOW = 800;
    private final int HEIGHT_WINDOW = 600;
    private final int INCREMENT = 20;
    private final int SLEEP_INTERVAL = 100;
    private final int UNIT_SIZE = 20;
    private boolean gameOver;
    private GameWindow gameWindow;
    private CopyOnWriteArrayList<SnakePart> snake;
    private Apple apple;
    private Direction currentDirection;
    private Direction newDirection;

    public SnakeGame() {
        createSnake();
        currentDirection = Direction.RIGHT;
        newDirection = Direction.RIGHT;
        generateApple();
        gameWindow = new GameWindow(snake, apple, WIDTH_WINDOW, HEIGHT_WINDOW, UNIT_SIZE);
        setKeyBindings();
        gameOver = false;
        startGame();
    }

    private void createSnake() {
        snake = new CopyOnWriteArrayList<>();
        SnakePart head = new SnakePart(new Color(29, 61, 44), 0, 0);
        snake.add(head);
    }

    private void generateApple() {
        Random random = new Random();
        apple = new Apple(Color.RED, random.nextInt(WIDTH_WINDOW/ UNIT_SIZE)* UNIT_SIZE, random.nextInt(HEIGHT_WINDOW/ UNIT_SIZE)* UNIT_SIZE);
    }

    private void move() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            if (i == 0) {
                switch (currentDirection) {
                    case UP:
                        snake.get(i).decreaseY(INCREMENT);
                        checkApple();
                        sleep(SLEEP_INTERVAL);
                        gameWindow.repaint();
                        break;
                    case DOWN:
                        snake.get(i).increaseY(INCREMENT);
                        checkApple();
                        sleep(SLEEP_INTERVAL);
                        gameWindow.repaint();
                        break;
                    case RIGHT:
                        snake.get(i).increaseX(INCREMENT);
                        checkApple();
                        sleep(SLEEP_INTERVAL);
                        gameWindow.repaint();
                        break;
                    case LEFT:
                        snake.get(i).decreaseX(INCREMENT);
                        checkApple();
                        sleep(SLEEP_INTERVAL);
                        gameWindow.repaint();
                        break;
                }
            }
            else {
                snake.get(i).setX(snake.get(i - 1).getX());
                snake.get(i).setY(snake.get(i - 1).getY());
            }

        }
    }

    private void sleep(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setKeyBindings() {
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0, true), "move up");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0, true), "move down");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0, true), "move left");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0, true), "move right");
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentDirection != Direction.LEFT) {
                    newDirection = Direction.RIGHT;
                    System.out.println("Pressed Right");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentDirection != Direction.RIGHT) {
                    newDirection = Direction.LEFT;
                    System.out.println("Pressed Left");
                } else if (e.getKeyCode() == KeyEvent.VK_UP && currentDirection != Direction.DOWN) {
                    newDirection = Direction.UP;
                    System.out.println("Pressed Up");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentDirection != Direction.UP) {
                    newDirection = Direction.DOWN;
                    System.out.println("Pressed Down");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        gameWindow.getGamePanel().addKeyListener(keyListener);
    }

    private void startGame() {
        while (!gameOver) {
            move();
            currentDirection = newDirection;
        }
    }

    public void generateNewFoodPosition() {
        Random random = new Random();
        apple.setX(random.nextInt((WIDTH_WINDOW/UNIT_SIZE) - UNIT_SIZE)* UNIT_SIZE);
        apple.setY(random.nextInt((HEIGHT_WINDOW/UNIT_SIZE) - UNIT_SIZE)* UNIT_SIZE);
        for (SnakePart snakePart : snake) {
            if (snakePart.getX() == apple.getX() && snakePart.getY() == apple.getY()) {
                System.out.println("Apple on snake!");
                generateNewFoodPosition();
            }
        }
    }

    private void checkApple() {
        if (snake.get(0).getX() == apple.getX() && snake.get(0).getY() == apple.getY()) {
            snake.add(new SnakePart(Color.GREEN, snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY()));
            generateNewFoodPosition();
        }
    }


}
