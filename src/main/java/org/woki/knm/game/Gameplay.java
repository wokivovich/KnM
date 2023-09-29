package org.woki.knm.game;

import org.woki.knm.entity.GameSession;

public interface Gameplay {

    GameSession createGameSession();
    boolean enterTheRoom(GameSession session);
    void getGameMessage(View view);
}
