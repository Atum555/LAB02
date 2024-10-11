package com.up202306618;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        game.run();
        Thread.sleep(5000);
        game.close();
    }
}
