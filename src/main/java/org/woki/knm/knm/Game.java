package org.woki.knm.knm;

import org.woki.knm.knm.entity.Monster;
import org.woki.knm.knm.entity.Player;
import org.woki.knm.knm.events.Battle;
import org.woki.knm.knm.events.Chest;
import org.woki.knm.knm.events.Event;
import org.woki.knm.knm.events.SafeRoom;
import org.woki.knm.knm.mechanic.CreaturesFactory;

import java.util.List;
import java.util.Random;

public class Game {

    private static final int STARTING_DIFFICULT = 1;
    private static final int STARTING_ATTACK_BONUS = 1;
    private static final int STARTING_DEFENSE_BONUS = 1;
    private static final int CHANCE_TO_FACE_WITH_MONSTER = 6;
    private static final int CHANCE_THAT_ROOM_IS_SAFE = 8;
    private static final int EVENT_CHANCE_RANGE = 10;
    private static final String VICTORY_MESSAGE = "Победа! Это было сложно, но лич побежден... " +
            "За ним есть небольшой постамент. Ура! Амулет у меня!";
    private static final String DEFEAT_MESSAGE = "Факел гаснет... Вас врядли смогут отыскать... " +
            "Теперь вы займете место в рядах нежити...";
    private static final String[] DIFFICULT_INCREASES_MESSAGES = new String[] {
            "Что это за шум? Нужно быть осторожней",
            "Странный стук...",
            "Старик говорил, что тут не безопасно, но мог бы предупредить про эту нечисть!",
            "Не нравится мне все это... Я легко одолел эти груды костей, надеюсь дальше хуже не будет.",
            "Фууух, это уже слишком! Кто надел на них броню?! Что это за склеп? И чем тут воняет?",
            "Тяжело. Не думал, что заберусь так далеко. Судя по рассказам старейшины, я почти добрался...",
            "Странно... Факел не справляется с тьмой... Почему так холодно?"
    };


    public void startGame() throws InterruptedException {
        CreaturesFactory factory = new CreaturesFactory();
        Player player = factory.createPlayer();
        GameSession session = GameSession.builder()
                .difficult(STARTING_DIFFICULT)
                .playerIsAlive(true)
                .bossIsDead(false)
                .attackBonus(STARTING_ATTACK_BONUS)
                .defenceBonus(STARTING_DEFENSE_BONUS)
                .build();
        System.out.println("Старейшина сказал, что здесь я смогу найти амулет... " +
                "Похоже эти старые двери давно не открывались.");

        List<Monster> monsters = factory.createMonstersList();

        while(session.isPlayerIsAlive() & !session.isBossIsDead()) {
            TypeOfRoom typeOfRoom = getTypeOfRoom();
            Thread.sleep(1000);
            switch (typeOfRoom) {
                case CHEST:
                    Event chest = new Chest(session);
                    chest.playEvent();
                case MONSTER:
                    Event battle = new Battle(session, player, monsters);
                    battle.playEvent();
                    if (!session.isBossIsDead()) {
                        System.out.println(difficultIncreaseMessage(session));
                        session.setDifficult(session.getDifficult() + 1);
                    }
                case SAFE:
                    Event safeRoom = new SafeRoom();
                    safeRoom.playEvent();
            }

        }

        if (session.isPlayerIsAlive()) {
            System.out.println(VICTORY_MESSAGE);
        } else {
            System.out.println(DEFEAT_MESSAGE);
        }

    }

    private String difficultIncreaseMessage(GameSession session) {

        return DIFFICULT_INCREASES_MESSAGES[session.getDifficult() - 1];
    }

    private TypeOfRoom getTypeOfRoom() {
        int randomRatio = (new Random()).nextInt(EVENT_CHANCE_RANGE);
        if (randomRatio < CHANCE_TO_FACE_WITH_MONSTER) {
            return TypeOfRoom.MONSTER;
        } else if (randomRatio > CHANCE_THAT_ROOM_IS_SAFE) {
            return TypeOfRoom.SAFE;
        } else {
            return TypeOfRoom.CHEST;
        }
    }

}
