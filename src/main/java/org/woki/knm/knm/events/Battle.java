package org.woki.knm.knm.events;

import org.woki.knm.knm.GameSession;
import org.woki.knm.knm.entity.Creature;
import org.woki.knm.knm.entity.Monster;
import org.woki.knm.knm.entity.Player;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;

public class Battle implements Event {

    private final GameSession session;
    private final Player player;
    private final List<Monster> monsters;

    private static final int HEAL_LIMIT = 4;
    private static final int HP_LEVEL_WHEN_START_HEALING = 70;
    private static final int HP_LEVEL_WHEN_CREATURE_DEFEAT = 0;

    public Battle(GameSession session, Player player, List<Monster> monsters) {
        this.session = session;
        this.player = player;
        this.monsters = monsters;
    }

    @Override
    public void playEvent() {
        ArrayDeque<Monster> monstersDeque = generateMonsterList(session, monsters);
        int healLimit = HEAL_LIMIT;

        while(!monstersDeque.isEmpty()) {
            Monster monster = monstersDeque.pop();
            boolean monsterIsAlive = true;

            while(session.isPlayerIsAlive() & monsterIsAlive) {
                if (healLimit > 0 && player.getHealth() > HP_LEVEL_WHEN_START_HEALING) {
                    heal(player);
                    healLimit--;
                    System.out.println("Вы немного восстановили здоровье");
                }
                attack(player, monster);

                if (monster.getHealth() <= 0) {
                    monsterIsAlive = false;
                    System.out.println(monster.getName() + " побежден.");
                    if (monster.getName().equals("Лич")) {
                        session.setBossIsDead(true);
                    }
                }
                if (monsterIsAlive)  {
                    attack(monster, player);
                    if (player.getHealth() <= HP_LEVEL_WHEN_CREATURE_DEFEAT) {
                        session.setPlayerIsAlive(false);
                        break;
                    }
                }
            }
        }
    }

    private void attack(Creature attacking, Creature defending) {

        int attackModify = calculateAttackModify(attacking, defending);

        int cubeResult = (new Random()).nextInt(1, 6);

        if (cubeResult > 4) {
            int damage = (new Random()).nextInt(attacking.getMinDamage(), attacking.getMaxDamage()) + attackModify;
            System.out.println(defending.getName() + " получает " + damage + " урона");
            defending.setHealth(defending.getHealth() - damage);
        } else {
            System.out.println(defending.getName() + " промахивается.");
        }
    }

    private ArrayDeque<Monster> generateMonsterList(GameSession session, List<Monster> monsters) {
        ArrayDeque<Monster> monstersDeque =  new ArrayDeque();
        switch (session.getDifficult()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                monstersDeque.add(monsters.get(0));
                break;
            case 4:
                monstersDeque.add(monsters.get(0));
                monstersDeque.add(monsters.get(0));
                monstersDeque.add(monsters.get(0));
                break;
            case 5:
                monstersDeque.add(monsters.get(1));
                break;
            case 6:
                monstersDeque.add(monsters.get(2));
                break;
            case 7:
                monstersDeque.add(monsters.get(3));
                break;
        }

        return  monstersDeque;
    }

    private void heal(Player player) {
        player.setHealth(player.getHealth() + (int)(player.getHealth() * 0.3));
    }

    private int calculateAttackModify(Creature attacking, Creature defending) {
        int attackValue = attacking instanceof Player ?
                attacking.getAttack() + session.getAttackBonus() :
                attacking.getAttack();
        int defenceValue = defending instanceof Player ?
                defending.getDefense() + session.getDefenceBonus() :
                defending.getDefense();
        return (attackValue - defenceValue + 1) > 0 ? (attackValue - defenceValue + 1) : 0;
    }
}
