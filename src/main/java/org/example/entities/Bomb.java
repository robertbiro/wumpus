package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.views.Board;


@Getter
@Setter
public class Bomb{

    private int posX, posY;
    private Board board;
    private Hero hero;
    private boolean isVisible;

    public Bomb(Board board,  Hero hero) {
        this.board = board;
        this.hero = hero;
        this.posX = hero.getX();
        this.posY = hero.getY();
        this.isVisible = false;
    }
}
