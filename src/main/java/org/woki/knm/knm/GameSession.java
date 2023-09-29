package org.woki.knm.knm;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameSession {
    private int difficult;
    private boolean playerIsAlive;
    private boolean bossIsDead;
    private int attackBonus;
    private int defenceBonus;

}
