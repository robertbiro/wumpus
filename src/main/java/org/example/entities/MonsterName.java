package org.example.entities;

import lombok.Getter;

import java.util.Random;

@Getter
public enum MonsterName {

    GREED,
    SLOTH,
    WRATH,
    ENVY,
    PRIDE,
    LUST,
    GLUTTONY;

    private static final Random name = new Random();

    public static MonsterName getName() {
        MonsterName[] monsterNames = values();
        return monsterNames[name.nextInt(monsterNames.length)];
    }
}
