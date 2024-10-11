package com.up202306618;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.up202306618.utils.Position;

public class Arena {
    private final Position positionOffset;
    private final int LENGTH;
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
        this.LENGTH = length;
        this.HEIGHT = height;
        this.hero = new Hero(new Position(0, 0));
    }

    public void draw(Screen screen) {
        this.hero.draw(screen, this.positionOffset);
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
        return x >= 0 && x < LENGTH && y >= 0 && y < HEIGHT;
    }
}
