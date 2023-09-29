package org.woki.knm.entity;

import org.woki.knm.exception.CreatureCreationException;

public class Creature {

    private String name;
    private int attack;
    private int defense;
    private int health;
    private int minDamage;
    private int maxDamage;

    public Creature(String name, int attack, int defense, int health, int minDamage, int maxDamage) {
        if (attack > 30 | attack < 1) {
            throw new CreatureCreationException("Attack must be equals or less than 30");
        }

        if (defense > 30 | defense < 1) {
            throw new CreatureCreationException("Defence must be equals or less than 30");
        }

        if (minDamage < 1) {
            throw new CreatureCreationException("Minimal damage must be more than 1");
        }

        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
}
