package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.views.Board;


@Getter
@Setter
public class Bomb{

    private int x;
    private int y;
    private Board board;
    private Hero hero;
    private boolean isVisible;

    public Bomb(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
        this.x = hero.getX();
        this.y = hero.getY();
        this.isVisible = false;
    }
}
