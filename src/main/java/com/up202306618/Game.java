package com.up202306618;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;

public class Game {
    private static final int LENGTH = 90;
    private static final int HEIGHT = 30;
    private final Screen screen;
    private boolean run;
    private int x = 10;
    private int y = 10;

    Game() throws IOException {
        this.screen = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(LENGTH, HEIGHT))
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.getDefaultOfSize(20)
                )
                .createScreen();
        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
    }

    public void close() throws IOException {
        this.screen.close();
    }

    public void run() {
    }

    private void draw() {
    }
}
