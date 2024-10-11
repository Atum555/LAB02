package com.up202306618;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.up202306618.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    public static final TextColor BACK_GROUND_COLOR = TextColor.Factory.fromString("#1f4220");
    public static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#000000");
    private static final char FIGURE_CHAR = ' ';

    private final Position arenaOffset;
    private final int WIDTH;
    private final int HEIGHT;

    private final Hero hero;
    private final List<Wall> walls;

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    Arena(Position arenaOffset, int length, int height) {
        this.arenaOffset = arenaOffset;
        this.WIDTH = length;
        this.HEIGHT = height;

        // Create Assets
        this.walls = this.createWalls();

        // Create Hero
        Position heroPosition;
        do heroPosition = Position.random(WIDTH, HEIGHT);
        while (!isValid(heroPosition));
        this.hero = new Hero(heroPosition);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(BACK_GROUND_COLOR);
        graphics.setForegroundColor(FORE_GROUND_COLOR);
        graphics.fillRectangle(
                new TerminalPosition(this.arenaOffset.x(), this.arenaOffset.y()),
                new TerminalSize(WIDTH, HEIGHT),
                ' '
        );

        for (Wall wall : walls) wall.draw(graphics, this.arenaOffset);

        this.hero.draw(graphics, this.arenaOffset);
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
        if (this.isValid(heroPosition)) this.hero.setPosition(heroPosition);
    }

    private boolean isValid(Position position) {
        int x = position.x();
        int y = position.y();
        if (!(x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)) return false;
        for (Wall wall : walls) if (wall.getPosition().equals(position)) return false;
        return true;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < WIDTH; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, HEIGHT - 1)));
        }
        for (int r = 1; r < HEIGHT - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(WIDTH - 1, r)));
        }
        return walls;
    }
}
