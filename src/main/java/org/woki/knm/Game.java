package org.woki.knm;

import org.woki.knm.entity.GameSession;
import org.woki.knm.game.Gameplay;
import org.woki.knm.game.View;

public class Game {
    Gameplay gameplay;
    View view;

    public Game(Gameplay gameplay, View view) {
        this.gameplay = gameplay;
        this.view = view;
    }

    public void startGame() {
        boolean gameIsRunning = true;
        GameSession session = gameplay.createGameSession();
        while(gameIsRunning) {
            gameIsRunning = gameplay.enterTheRoom(session);
            gameplay.getGameMessage(view);
        }
    }
}
