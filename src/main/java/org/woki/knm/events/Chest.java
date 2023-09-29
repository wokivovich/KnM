package org.woki.knm.events;

import org.woki.knm.entity.GameSession;

import java.util.List;
import java.util.Random;

public class Chest implements Event {

    private GameSession session;
    private static final String ATTACK_ITEM_BONUS_MESSAGE = "Руна для меча... Возьму с собой, пригодится.";
    private static final String DEFENSE_ITEM_BONUS_MESSAGE = "О, зелье каменной кожи. На вкус дрянь, но, должно защитить";
    private static final String CHEST_IS_EMPTY_MESSAGE = "Пусто, проклятье!";

    public Chest(GameSession session) {
        this.session = session;
    }

    @Override
    public List<String> playEvent() {
        int randomRation = (new Random()).nextInt(2);
        switch (randomRation) {
            case 1:
                session.setAttackBonus(session.getAttackBonus() + 5);
                gameMessages.add(ATTACK_ITEM_BONUS_MESSAGE);
                break;
            case 2:
                session.setDefenceBonus(session.getDefenceBonus() + 5);
                gameMessages.add(DEFENSE_ITEM_BONUS_MESSAGE);
                break;
            default:
                gameMessages.add(CHEST_IS_EMPTY_MESSAGE);
                break;
        }

        return gameMessages;
    }

}
