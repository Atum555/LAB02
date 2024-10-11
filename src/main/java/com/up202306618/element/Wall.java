package com.up202306618.element;

import com.googlecode.lanterna.TextColor;
import com.up202306618.utils.Position;

import java.util.Objects;

public class Wall extends Element {
    private static final TextColor BACK_GROUND_COLOR = TextColor.Factory.fromString("#1D1D1D");
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#000000");
    private static final char FIGURE_CHAR = ' ';

    public Wall(Position position) {
        super(position);
    }

    @Override
    protected TextColor getBackGroundColor() {
        return BACK_GROUND_COLOR;
    }

    @Override
    protected TextColor getForeGroundColor() {
        return FORE_GROUND_COLOR;
    }

    @Override
    protected char getFigureChar() {
        return FIGURE_CHAR;
    }

    @Override
    public String toString() {
        return "Wall{" + this.getPosition() + '}';
    }
}
