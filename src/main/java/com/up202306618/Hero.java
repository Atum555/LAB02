package com.up202306618;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.up202306618.utils.Position;

import java.util.Objects;

public class Hero {
    private Position position;

    Hero (Position position) {
        this.position = position;
    }

    public void draw(Screen screen) {
        int x = this.position.x() % screen.getTerminalSize().getColumns();
        int y = -this.position.y() % screen.getTerminalSize().getRows();
        if (x < 0) x += screen.getTerminalSize().getColumns();
        if (y < 0) y += screen.getTerminalSize().getRows();

        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp() {
        this.position.moveUp();
    }

    public void moveDown() {
        this.position.moveDown();
    }

    public void modeRight() {
        this.position.moveRight();
    }

    public void modeLeft() {
        this.position.moveLeft();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(position, hero.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public String toString() {
        return "Hero{" + position + '}';
    }
}
