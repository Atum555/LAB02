package com.up202306618.element;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.up202306618.utils.Position;

import java.util.Objects;

public abstract class Element {
    private Position position;

    Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return new Position(this.position.x(), this.position.y());
    }

    protected void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics, Position offset) {
        graphics.setBackgroundColor(this.getBackGroundColor());
        graphics.setForegroundColor(this.getForeGroundColor());
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(
                new TerminalPosition(
                        offset.x() + this.getPosition().x(),
                        offset.y() + this.getPosition().y()
                ),
                String.valueOf(this.getFigureChar())
        );
    }

    protected abstract TextColor getBackGroundColor();

    protected abstract TextColor getForeGroundColor();

    protected abstract char getFigureChar();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(position, element.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }
}
