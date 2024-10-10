package com.up202306618;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private Screen screen;

    Game() throws IOException {
        this.screen = new DefaultTerminalFactory().createScreen();
    }

    public void close() throws IOException {
        this.screen.close();
    }

    public void run() {
    }

    private void draw() {
    }
}
