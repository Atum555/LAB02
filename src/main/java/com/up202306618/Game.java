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
        this.screen.setCharacter(this.x, this.y, TextCharacter.fromCharacter('X')[0]);
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
            case KeyType.ArrowUp:
                this.y--;
                break;
            case KeyType.ArrowDown:
                this.y++;
                break;
            case KeyType.ArrowLeft:
                this.x--;
                break;
            case KeyType.ArrowRight:
                this.x++;
                break;
        }
    }

    private void processCharacter(char c) throws IOException {
        switch (c) {
            case 'Q':
            case 'q':
                this.close();
                break;
        }
    }
}
