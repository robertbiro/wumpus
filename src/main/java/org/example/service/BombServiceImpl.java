package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.views.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class BombServiceImpl implements BombService {

    private Board board;
    private Hero hero;
    private Area area;
    private Bomb bomb;
    private Monster monster;
    private HeroService heroService;
    private MonsterService monsterService;
    private Timer timer;
    private int distance = 3;


    public BombServiceImpl(Area area, Board board, Hero hero, HeroService heroService,
                           MonsterService monsterService) {
        this.area = area;
        this.board = board;
        this.hero = hero;
        this.heroService = heroService;
        this.monsterService = monsterService;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        triggerBomb(board);
        monsterService.handleBombImpact();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(org.w3c.dom.events.MouseEvent e) {

    }

    @Override
    public void currentCoordinatesOfBomb() {
        if (hero.getHeroDirection() == HeroDirection.UP) {
            bomb.setY(hero.getY() - distance);
            bomb.setX(hero.getX());
        } else if (hero.getHeroDirection() == HeroDirection.DOWN) {
            bomb.setX(hero.getX());
            bomb.setY(hero.getY() + distance);
        } else if (hero.getHeroDirection() == HeroDirection.LEFT) {
            bomb.setY(hero.getY());
            bomb.setX(hero.getX() - distance);
        } else if (hero.getHeroDirection() == HeroDirection.RIGHT) {
            bomb.setY(hero.getY());
            bomb.setX(hero.getX() + distance);
        }
    }
    @Override
    public boolean validDetonation(Area area, int bombXCoordinate, int bombYCoordinate) {
        System.out.println(area.getTiles()[bombXCoordinate][bombYCoordinate]);
        System.out.println(bombXCoordinate + " : " + bombYCoordinate);
       return area.getTiles()[bombXCoordinate][bombYCoordinate] == 4 && isValidCoordinate(area, bombXCoordinate, bombYCoordinate);

    }
    @Override
    public boolean isValidCoordinate(Area area, int bombXCoordinate, int bombYCoordinate) {
        return bombXCoordinate >= 0 && bombXCoordinate < area.getTiles().length &&
                bombYCoordinate >= 0 && bombYCoordinate < area.getTiles()[0].length;
    }

    //Bomb handling
    @Override
    public void triggerBomb(Board board) {

        if (!hero.isThrowBomb()) {
            hero.setThrowBomb(true);
            bomb.setVisible(true);
            currentCoordinatesOfBomb();

            if (timer == null || !timer.isRunning()) {
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Timer action: set the bomb to invisible
                        bomb.setVisible(false);
                        hero.setThrowBomb(false);
                        board.repaint();
                        timer.stop();
                    }
                });
                timer.start(); // Start the timer
            }
        }
    }
}
