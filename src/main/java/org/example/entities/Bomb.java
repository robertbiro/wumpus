package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.service.MainController;
import org.example.views.Board;


@Getter
@Setter
public class Bomb{

    private int posX, posY;
    private Board board;
    private Hero hero;
    private boolean isVisible;
    private MainController mainController;

    public Bomb(Board board,  Hero hero, MainController mainController) {
        this.board = board;
        this.hero = hero;
        this.posX = hero.getCurrentX();
        this.posY = hero.getCurrentY();
        this.isVisible = false;
        this.mainController = mainController;
    }

    public void handleBombImpact() {
        mainController.handleBombImpact();
    }
}
