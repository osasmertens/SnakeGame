package entities;

import java.awt.Color;

public class SnakePart {

    private Color color;
    private int x;
    private  int y;

    public SnakePart(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseX(int increment) {
        x += increment;
    }

    public void increaseY(int increment) {
        y += increment;
    }
}
