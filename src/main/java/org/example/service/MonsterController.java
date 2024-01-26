package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Monster;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MonsterController {

    private List<Monster> monsters;
    private BombController bombController;

    public MonsterController() {
        this.monsters = new ArrayList<>();
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeDeadMonsters() {
        monsters.removeIf(monster -> !monster.isAlive());
    }
}
