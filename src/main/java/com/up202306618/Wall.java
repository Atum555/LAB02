package com.up202306618;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.up202306618.utils.Position;

import java.util.Objects;

public class Wall {
    private static final TextColor BACK_GROUND_COLOR = TextColor.Factory.fromString("#1d1d1d");
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#000000");
    private static final char FIGURE_CHAR = ' ';

    private final Position position;

    Wall(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics, Position offset) {
        graphics.setBackgroundColor(BACK_GROUND_COLOR);
        graphics.setForegroundColor(FORE_GROUND_COLOR);
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(
                new TerminalPosition(
                        offset.x() + this.position.x(),
                        offset.y() + this.position.y()
                ),
                String.valueOf(FIGURE_CHAR)
        );
    }

    public Position getPosition() {
        return  new Position(this.position.x(), this.position.y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return Objects.equals(position, wall.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public String toString() {
        return "Wall{" + "position=" + position + '}';
    }
}
