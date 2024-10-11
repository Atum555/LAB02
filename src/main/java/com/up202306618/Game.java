package com.up202306618;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.up202306618.utils.Position;

import java.io.IOException;

import static java.lang.Math.floor;

public class Game {
    private static final int LENGTH = 90;
    private static final int HEIGHT = 30;
    private final Screen screen;
    private final Arena arena;
    private boolean run;

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

        int borderLength = (int) floor(LENGTH * 0.05);
        int borderHeight = (int) floor(HEIGHT * 0.05);
        if (borderLength % 2 == 1) borderLength++;
        if (borderHeight % 2 == 1) borderHeight++;

        int arenaLength = LENGTH - 2 * borderLength;
        int arenaHeight = HEIGHT - 2 * borderHeight;

        this.arena = new Arena(
                new Position(borderLength, borderHeight),
                arenaLength,
                arenaHeight
        );
    }

    public void close() throws IOException {
        this.screen.close();
        this.run = false;
    }

    public void run() throws IOException {
        this.run = true;

        while (this.run) {
            this.draw();
            this.processKey(this.screen.readInput());
        }
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.arena.draw(this.screen);
        this.screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case KeyType.Character:
                this.processCharacter(key.getCharacter());
                break;
            case KeyType.EOF:
                this.close();
                break;
            default:
                this.arena.processKey(key);
        }
    }

    private void processCharacter(char c) throws IOException {
        switch (c) {
            case 'Q':
            case 'q':
                this.close();
                break;
            default:
                this.arena.processCharacter(c);
        }
    }
}
