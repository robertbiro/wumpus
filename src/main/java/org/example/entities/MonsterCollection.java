package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MonsterCollection {

    private List<Monster> monsters;
    private Monster monster;

    public MonsterCollection() {
        this.monsters = new ArrayList<>();
    }
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    public List<Monster> getMonsters() {
        return new ArrayList<>(monsters);
    }

    public int getSize() {
        return monsters.size();
    }

    public void removeDeadMonsters() {
        monsters.removeIf(monster -> !monster.isAlive());
    }
}
