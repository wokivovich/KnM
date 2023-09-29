package org.woki.knm;

import org.woki.knm.game.ConsoleOutput;
import org.woki.knm.game.ConsoleGame;
import org.woki.knm.game.Gameplay;
import org.woki.knm.game.View;

public class KnMApp {

    public static void main(String[] args) throws InterruptedException {

        Gameplay gameplay = new ConsoleGame();
        View view = new ConsoleOutput();
        Game game = new Game(gameplay, view);
        game.startGame();
    }
}
