package com.up202306618;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.up202306618.utils.Position;

import java.util.Objects;

public class Hero {
    private Position position;

    Hero(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics, Position offset) {
        System.out.println(" Hero: " + this.position);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ef45ef"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(
                new TerminalPosition(
                        offset.x() + this.position.x(),
                        offset.y() + this.position.y()
                ),
                "X"
        );
    }

    public Position getPosition() {
        return new Position(this.position.x(), this.position.y());
    }

    public void setPosition(Position position) {
        this.position = position;
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
