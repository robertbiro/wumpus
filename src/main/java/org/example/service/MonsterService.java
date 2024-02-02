package org.example.service;

import org.example.entities.Area;
import org.example.entities.Bomb;
import org.example.entities.Monster;

import javax.swing.*;
import java.util.List;

public interface MonsterService {

    int[] getRandomStartPosition(Area area);
    Timer initTimer();
    int createRandomSpeed();
    boolean isMonsterAffectedByBomb(Bomb bomb);
    void handleBombImpact();
    void startTimer();
    void stopTimer();
    void moveTowardsHero();
    List<Monster> getMonsters();
}
