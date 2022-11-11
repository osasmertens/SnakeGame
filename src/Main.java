import entities.Apple;
import game.SnakeGame;
import gui.GameWindow;
import entities.SnakePart;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*SnakePart snakePart = new SnakePart(Color.GREEN, 0, 0);
        Apple apple = new Apple(Color.RED, 300, 400);
        GameWindow game = new GameWindow(snakePart, apple);
        Thread.sleep(1000);
        System.out.println("Reached");
        snakePart.increaseX(100);
        game.repaint();*/

        SnakeGame game = new SnakeGame();
    }
}
