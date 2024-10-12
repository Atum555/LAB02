package com.up202306618.element;

import com.googlecode.lanterna.TextColor;
import com.up202306618.Arena;
import com.up202306618.utils.Position;

public class Monster extends Element implements Movable {
    private static final TextColor BACK_GROUND_COLOR = Arena.BACK_GROUND_COLOR;
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#20AA20");
    private static final char FIGURE_CHAR = '#';

    public Monster(Position position) {
        super(position);
    }

    public void moveTo(Position position) {
        this.setPosition(position);
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
        return "Monster{" + this.getPosition() + '}';
    }
}
