package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Hero {

    private int x;
    private int y;
    private int life = 5;
    private int steps;
    private Bomb bomb;
    private boolean throwBomb;
    private boolean hasGold;
    private boolean hasKey;
    private HeroDirection heroDirection;


    public Hero(int x, int y, HeroDirection heroDirection) {
        this.x = x;
        this.y = y;
        this.heroDirection = heroDirection;
        hasGold = false;
        hasKey = false;
        steps = 0;
    }

}
