package com.up202306618.element;

import com.googlecode.lanterna.TextColor;
import com.up202306618.Arena;
import com.up202306618.utils.Position;

public class Hero extends Element implements Movable {
    private static final TextColor BACK_GROUND_COLOR = Arena.BACK_GROUND_COLOR;
    private static final TextColor FORE_GROUND_COLOR = TextColor.Factory.fromString("#EF45EF");
    private static final char FIGURE_CHAR = 'X';
    private static final int MAX_HEALTH = 20;

    private int health = MAX_HEALTH;

    public Hero(Position position) {
        super(position);
    }

    public void coinCaught() {
        this.health = MAX_HEALTH;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void moveTo(Position position) {
        this.setPosition(position);
        this.health--;
    }

    @Override
    protected TextColor getBackGroundColor() {
        return BACK_GROUND_COLOR;
    }

    @Override
    protected TextColor getForeGroundColor() {
        return TextColor.Indexed.fromRGB(
                FORE_GROUND_COLOR.getRed(),
                (int) (FORE_GROUND_COLOR.getRed() * ((double) this.health / MAX_HEALTH)),
                (int) (FORE_GROUND_COLOR.getRed() * ((double) this.health / MAX_HEALTH))
        );
    }

    @Override
    protected char getFigureChar() {
        return FIGURE_CHAR;
    }

    @Override
    public String toString() {
        return "Hero{" + this.getPosition() + '}';
    }
}
