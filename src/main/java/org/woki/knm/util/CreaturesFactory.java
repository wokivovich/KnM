package org.woki.knm.util;

import org.woki.knm.entity.Player;
import org.woki.knm.entity.Monster;

import java.util.List;

public class CreaturesFactory {

    public Player createPlayer() {

        return new Player("Странник",15, 15, 100, 1, 10);
    }

    public List<Monster> createMonstersList() {
        List<Monster> monsters = List.of(
                new Monster("Скелет", 8, 10, 20, 1, 5),
                new Monster("Бронированный скелет", 8, 15, 30, 3, 7),
                new Monster("Зомби", 15, 12, 50, 3, 6),
                new Monster("Лич", 30, 30, 100, 5, 10)
        );

        return monsters;
    }
}
