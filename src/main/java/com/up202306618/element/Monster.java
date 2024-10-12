package com.up202306618.element;

import com.googlecode.lanterna.TextColor;
import com.up202306618.Arena;
import com.up202306618.utils.Position;

public class Monster extends Element implements Movable {
    private static final TextColor BACK_GROUND_COLOR = Arena.BACK_GROUND_COLOR;
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#20AA20");
    private static final char FIGURE_CHAR = '#';
    private int stamina;

    public Monster(Position position) {
        super(position);
        this.stamina = 0;
    }

    public void moveTo(Position position) {
        if (stamina >= 2) {
            this.setPosition(position);
            stamina -= 2;
        }

    }

    public void tick() {
        stamina++;
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
