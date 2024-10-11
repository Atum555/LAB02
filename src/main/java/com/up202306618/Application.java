package com.up202306618;

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
