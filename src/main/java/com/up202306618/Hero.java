package com.up202306618;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.util.Objects;

public class Hero {
    private int x;
    private int y;

    Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Screen screen) {
        int x = this.x % screen.getTerminalSize().getColumns();
        int y = this.y % screen.getTerminalSize().getRows();
        if (x < 0) x += screen.getTerminalSize().getColumns();
        if (y < 0) y += screen.getTerminalSize().getRows();

        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp() {
        this.y--;
    }

    public void moveDown() {
        this.y++;
    }

    public void modeRight() {
        this.x++;
    }

    public void modeLeft() {
        this.x--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return x == hero.x && y == hero.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Hero{" + "x=" + x + ", y=" + y + '}';
    }
}
