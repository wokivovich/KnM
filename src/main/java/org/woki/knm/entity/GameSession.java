package org.woki.knm.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GameSession {
    private Player player;
    private List<Monster> monsters;
    private int difficult;
    private boolean playerIsAlive;
    private boolean bossIsDead;
    private int attackBonus;
    private int defenceBonus;

}
