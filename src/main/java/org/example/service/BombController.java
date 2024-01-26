package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.views.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class BombController extends MouseAdapter {

    private Board board;
    private Hero hero;
    private Area area;
    private Bomb bomb;
    private Monster monster;
    private int distance = 3;


    public BombController(Area area, Board board, Hero hero) {
        this.area = area;
        this.board = board;
        this.hero = hero;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hero.triggerBomb(board);
        bomb.handleBombImpact();
    }

    public void currentCoordinatesOfBomb() {
       // int bombXCoordinate, bombYCoordinate;
        if (hero.getDirection() == HeroDirection.UP) {
            //bombXCoordinate = hero.getCurrentX();
            //bombYCoordinate = hero.getCurrentY() - distance;
            //if (!validDetonation(area, bombXCoordinate, bombYCoordinate)) {
                //distance--;
                //System.out.println(distance);
            //}
            bomb.setPosY(hero.getCurrentY() - distance);
            bomb.setPosX(hero.getCurrentX());
            distance = 3;
        } else if (hero.getDirection() == HeroDirection.DOWN) {
            //bombXCoordinate = hero.getCurrentX();
            //bombYCoordinate = hero.getCurrentY() + distance;
            //while (!validDetonation(area, bombXCoordinate, bombYCoordinate)) {
            //    distance--;
            //}
            bomb.setPosX(hero.getCurrentX());
            bomb.setPosY(hero.getCurrentY() + 3);
            //distance = 3;
        } else if (hero.getDirection() == HeroDirection.LEFT) {
            bomb.setPosY(hero.getCurrentY());
            bomb.setPosX(hero.getCurrentX() - 3);
        } else if (hero.getDirection() == HeroDirection.RIGHT) {
            bomb.setPosY(hero.getCurrentY());
            bomb.setPosX(hero.getCurrentX() + 3);
        }
    }
    public boolean validDetonation(Area area, int bombXCoordinate, int bombYCoordinate) {
        System.out.println(area.getTiles()[bombXCoordinate][bombYCoordinate]);
        System.out.println(bombXCoordinate + " : " + bombYCoordinate);
       return area.getTiles()[bombXCoordinate][bombYCoordinate] == 4 && isValidCoordinate(area, bombXCoordinate, bombYCoordinate);

    }

    public static boolean isValidCoordinate(Area area, int bombXCoordinate, int bombYCoordinate) {
        return bombXCoordinate >= 0 && bombXCoordinate < area.getTiles().length &&
                bombYCoordinate >= 0 && bombYCoordinate < area.getTiles()[0].length;
    }
}
