package game;

import entities.Apple;
import entities.SnakePart;
import gui.GameMenu;
import gui.GameOverPanel;
import gui.GameWindow;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnakeGame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int SLEEP_INTERVAL = 100;
    private final int UNIT_SIZE = 20;
    private boolean gameOver;
    private final GameWindow gameWindow;
    private CopyOnWriteArrayList<SnakePart> snake;
    private Apple apple;
    private Direction currentDirection;
    private Direction newDirection;
    private final Random random;
    private int score;
    private final JLabel scoreLabel;
    private final int ADDED_SCORE = 10;

    public SnakeGame() {
        score = 0;
        random = new Random();
        createSnake();
        currentDirection = Direction.RIGHT;
        newDirection = Direction.RIGHT;
        generateApple();
        gameWindow = new GameWindow(snake, apple, WIDTH, HEIGHT, UNIT_SIZE);
        setKeyBindings();
        scoreLabel = gameWindow.getInfoPanel().getScoreLabel();
        scoreLabel.setText("Score: " + score);
        gameOver = false;
        gameWindow.revalidate();
        gameWindow.repaint();
        startGame();
        setGameOver();
    }


    private void createSnake() {
        snake = new CopyOnWriteArrayList<>();
        SnakePart head = new SnakePart(new Color(29, 61, 44), 0, 0);
        snake.add(head);
    }

    private void generateApple() {
        Random random = new Random();
        apple = new Apple(Color.RED, random.nextInt(WIDTH / UNIT_SIZE)* UNIT_SIZE,
                random.nextInt(HEIGHT / UNIT_SIZE)* UNIT_SIZE);
    }

    private void move() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            if (i == 0) {
                switch (currentDirection) {
                    case UP -> {
                        snake.get(i).decreaseY(UNIT_SIZE);
                        checkSnakeCollision(snake.get(i));
                        checkApple();
                        sleep();
                        gameWindow.repaint();
                    }
                    case DOWN -> {
                        snake.get(i).increaseY(UNIT_SIZE);
                        checkSnakeCollision(snake.get(i));
                        checkApple();
                        sleep();
                        gameWindow.repaint();
                    }
                    case RIGHT -> {
                        snake.get(i).increaseX(UNIT_SIZE);
                        checkSnakeCollision(snake.get(i));
                        checkApple();
                        sleep();
                        gameWindow.repaint();
                    }
                    case LEFT -> {
                        snake.get(i).decreaseX(UNIT_SIZE);
                        checkSnakeCollision(snake.get(i));
                        checkApple();
                        sleep();
                        gameWindow.repaint();
                    }
                }
            }
            else {
                snake.get(i).setX(snake.get(i - 1).getX());
                snake.get(i).setY(snake.get(i - 1).getY());
            }
            currentDirection = newDirection;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setKeyBindings() {
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,
                0, true), "move up");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
                0, true), "move down");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,
                0, true), "move left");
        gameWindow.getGamePanel().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,
                0, true), "move right");
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentDirection != Direction.LEFT) {
                    newDirection = Direction.RIGHT;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentDirection != Direction.RIGHT) {
                    newDirection = Direction.LEFT;
                } else if (e.getKeyCode() == KeyEvent.VK_UP && currentDirection != Direction.DOWN) {
                    newDirection = Direction.UP;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentDirection != Direction.UP) {
                    newDirection = Direction.DOWN;
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
            checkBoundaries();
        }
    }

    public void generateNewFoodPosition() {
        apple.setX(random.nextInt((WIDTH /UNIT_SIZE) - UNIT_SIZE) * UNIT_SIZE );
        apple.setY(random.nextInt((HEIGHT /UNIT_SIZE)  - UNIT_SIZE) * UNIT_SIZE);
        for (SnakePart snakePart : snake) {
            if (snakePart.getX() == apple.getX() && snakePart.getY() == apple.getY()) {
                generateNewFoodPosition();
            }
        }
    }

    private void checkApple() {
        if (snake.get(0).getX() == apple.getX() && snake.get(0).getY() == apple.getY()) {
            increaseScore();
            snake.add(new SnakePart(Color.GREEN,
                    snake.get(snake.size() - 1).getX(),
                    snake.get(snake.size() - 1).getY()));
            generateNewFoodPosition();
        }
    }

    private void increaseScore() {
        score += ADDED_SCORE;
        scoreLabel.setText("Score: " + score);
    }

    private void checkSnakeCollision(SnakePart head) {
        for (int i = 1; i < snake.size() - 1; i++) {
            if (head.getX() == snake.get(i).getX() && head.getY() == snake.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void checkBoundaries() {
        if (snake.get(0).getX() == 0 - UNIT_SIZE) {
            snake.get(0).setX(WIDTH - UNIT_SIZE);
        } else if (snake.get(0).getX() == WIDTH) {
            snake.get(0).setX(0);
        } else if (snake.get(0).getY() ==  0 - UNIT_SIZE) {
            snake.get(0).setY(HEIGHT - UNIT_SIZE);
        } else if (snake.get(0).getY() == HEIGHT) {
            snake.get(0).setY(0);
        }
    }

    private void setGameOver() {
        if (gameOver) {
            gameWindow.remove(gameWindow.getGamePanel());
            GameOverPanel gameOverPanel  = new GameOverPanel(WIDTH, HEIGHT);
            gameOverPanel.getRestartGameButton().addActionListener(e -> {
                if (e.getSource() == gameOverPanel.getRestartGameButton()) {
                    gameWindow.dispose();
                    Thread thread = new Thread(SnakeGame::new);
                    thread.start();
                }
            });
            gameOverPanel.getQuitGameButton().addActionListener(e -> {
                if (e.getSource() == gameOverPanel.getQuitGameButton()) {
                    System.exit(0);
                }
            });
            gameOverPanel.getMainMenuButton().addActionListener(e -> {
                gameWindow.dispose();
                new GameMenu();
            });
            gameWindow.add(gameOverPanel, BorderLayout.CENTER);
            gameWindow.revalidate();
            gameWindow.repaint();
            gameWindow.pack();
        }
    }



}
