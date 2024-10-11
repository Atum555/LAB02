package com.up202306618;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.up202306618.utils.Position;

public class Arena {
    private final Position positionOffset;
    private final int WIDTH;
    private final int HEIGHT;
    private final Hero hero;

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    Arena(Position position, int length, int height) {
        this.positionOffset = position;
        this.WIDTH = length;
        this.HEIGHT = height;
        this.hero = new Hero(new Position(0, 0));
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#1f4220"));
        graphics.fillRectangle(
                new TerminalPosition(this.positionOffset.x(), this.positionOffset.y()),
                new TerminalSize(WIDTH, HEIGHT),
                ' '
        );
        this.hero.draw(graphics, this.positionOffset);
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case KeyType.ArrowUp:
                this.moveHero(Direction.UP);
                break;
            case KeyType.ArrowDown:
                this.moveHero(Direction.DOWN);
                break;
            case KeyType.ArrowRight:
                this.moveHero(Direction.RIGHT);
                break;
            case KeyType.ArrowLeft:
                this.moveHero(Direction.LEFT);
                break;
        }
    }

    public void processCharacter(char c) {
        switch (c) {
            case 'w':
            case 'W':
                moveHero(Direction.UP);
                break;
            case 's':
            case 'S':
                moveHero(Direction.DOWN);
                break;
            case 'd':
            case 'D':
                moveHero(Direction.RIGHT);
                break;
            case 'a':
            case 'A':
                moveHero(Direction.LEFT);
                break;
            default:
        }
    }

    private void moveHero(Direction direction) {
        Position heroPosition = this.hero.getPosition();
        switch (direction) {
            case UP:
                heroPosition.moveUp();
                break;
            case DOWN:
                heroPosition.moveDown();
                break;
            case RIGHT:
                heroPosition.moveRight();
                break;
            case LEFT:
                heroPosition.moveLeft();
                break;
        }
        if (this.isInside(heroPosition)) this.hero.setPosition(heroPosition);
    }

    private boolean isInside(Position position) {
        int x = position.x();
        int y = position.y();
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }
}
