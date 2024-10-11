package com.up202306618;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.up202306618.element.Coin;
import com.up202306618.element.Hero;
import com.up202306618.element.Wall;
import com.up202306618.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private static final TextColor LOST_BACK_GROUND_COLOR = TextColor.Factory.fromString("#EE1010");
    private static final TextColor LOST_FORE_GROUND_COLOR = TextColor.Factory.fromString("#660000");
    public static final TextColor BACK_GROUND_COLOR = TextColor.Factory.fromString("#1f4220");
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#000000");
    private static final char FIGURE_CHAR = ' ';

    private final Position arenaOffset;
    private final int WIDTH;
    private final int HEIGHT;
    private final int COIN_COUNT;

    private final Hero hero;
    private final List<Wall> walls;
    private final List<Coin> coins;

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    protected Arena(Position arenaOffset, int length, int height) {
        this.arenaOffset = arenaOffset;
        this.WIDTH = length;
        this.HEIGHT = height;

        this.COIN_COUNT = (int) (WIDTH * HEIGHT * 0.03);

        // Create Assets
        this.walls = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.createWalls();
        this.createCoins();

        // Create Hero
        Position heroPosition;
        do heroPosition = Position.random(WIDTH, HEIGHT);
        while (!this.isPositionFree(heroPosition));
        this.hero = new Hero(heroPosition);
    }

    public boolean tick() {
        if (!this.hero.isAlive()) return false;
        while (this.coins.size() < COIN_COUNT) this.addCoin();
        return true;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(BACK_GROUND_COLOR);
        graphics.setForegroundColor(FORE_GROUND_COLOR);
        graphics.fillRectangle(
                new TerminalPosition(this.arenaOffset.x(), this.arenaOffset.y()),
                new TerminalSize(WIDTH, HEIGHT),
                FIGURE_CHAR
        );

        for (Wall wall : this.walls) wall.draw(graphics, this.arenaOffset);
        for (Coin coin : this.coins) coin.draw(graphics, this.arenaOffset);

        this.hero.draw(graphics, this.arenaOffset);
    }

    public void drawLost(TextGraphics graphics) {
        graphics.setBackgroundColor(LOST_BACK_GROUND_COLOR);
        graphics.setForegroundColor(LOST_FORE_GROUND_COLOR);
        graphics.fillRectangle(
                new TerminalPosition(this.arenaOffset.x(), this.arenaOffset.y()),
                new TerminalSize(WIDTH, HEIGHT),
                FIGURE_CHAR
        );
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(
                this.arenaOffset.x() + 39,
                this.arenaOffset.y() + 12,
                "LOST"
        );
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
                this.moveHero(Direction.UP);
                break;
            case 's':
            case 'S':
                this.moveHero(Direction.DOWN);
                break;
            case 'd':
            case 'D':
                this.moveHero(Direction.RIGHT);
                break;
            case 'a':
            case 'A':
                this.moveHero(Direction.LEFT);
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

        if (this.isPositionFree(heroPosition))
            this.hero.moveTo(heroPosition);

        else if (this.isPositionCoin(heroPosition)) {
            this.removeCoin(heroPosition);
            this.hero.coinCaught();
            this.hero.moveTo(heroPosition);
        }
    }

    private boolean isPositionFree(Position position) {
        int x = position.x();
        int y = position.y();
        if (!(x >= 0 && x < this.WIDTH && y >= 0 && y < this.HEIGHT)) return false;
        for (Wall wall : this.walls) if (wall.getPosition().equals(position)) return false;
        for (Coin coin : this.coins) if (coin.getPosition().equals(position)) return false;
        return true;
    }

    private boolean isPositionCoin(Position position) {
        for (Coin coin : this.coins) if (coin.getPosition().equals(position)) return true;
        return false;
    }

    private void createWalls() {
        for (int c = 0; c < WIDTH; c++) {
            this.walls.add(new Wall(new Position(c, 0)));
            this.walls.add(new Wall(new Position(c, HEIGHT - 1)));
        }
        for (int r = 1; r < HEIGHT - 1; r++) {
            this.walls.add(new Wall(new Position(0, r)));
            this.walls.add(new Wall(new Position(WIDTH - 1, r)));
        }
    }

    private void createCoins() {
        for (int i = 0; i < this.COIN_COUNT; i++) this.addCoin();
    }

    private void addCoin() {
        Position coinPosition;
        do coinPosition = Position.random(WIDTH, HEIGHT);
        while (!this.isPositionFree(coinPosition));
        this.coins.add(new Coin(coinPosition));
    }

    private void removeCoin(Position position) {
        boolean caught = false;
        for (Coin coin : this.coins)
            if (coin.getPosition().equals(position)) {
                this.coins.remove(coin);
                caught = true;
                break;
            }
        if (!caught) throw new IllegalStateException("Invalid Coin Position.");
    }
}
