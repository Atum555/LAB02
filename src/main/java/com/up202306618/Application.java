package com.up202306618;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        try {
            Game game = new Game();
            game.run();
            game.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
