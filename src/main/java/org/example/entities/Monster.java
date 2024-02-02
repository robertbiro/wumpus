package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.service.MonsterService;
import org.example.views.Board;

import javax.swing.*;

@Getter
@Setter
public class Monster {
    private String name;
    private int x;
    private int y;
    private Timer timer;
    private Area area;
    private Board board;
    private Hero hero;
    private Bomb bomb;
    private MonsterService monsterService;
    private boolean isAlive = true;

    public Monster(Area area, Hero hero, Board board, Bomb bomb) {
        this.name = MonsterName.getName().toString();
        this.area = area;
        this.board = board;
        this.hero = hero;
        this.bomb = bomb;
    }

}
