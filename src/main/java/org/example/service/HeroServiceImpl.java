package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;
import org.example.entities.Bomb;
import org.example.entities.Hero;
import org.example.entities.HeroDirection;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.views.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
@Setter
@Getter
public class HeroServiceImpl implements HeroService {

    private Hero hero;
    private Area area;
    private HeroDirection heroDirection;
    private Bomb bomb;
    private BombController bombController;
    private Gold gold;
    private Key key;


    private Timer timer;

    public void moveUp() {
        if(hero.getY() > 0 && area.getTiles()[hero.getX()][hero.getY() - 1] != area.getWallValue()) {
            hero.setY(hero.getY() -1);
            heroDirection = HeroDirection.UP;
            hero.setSteps(hero.getSteps() + 1);
        }
    }

    public void moveDown() {
        if(hero.getY() < area.getSize() - 1 && area.getTiles()[hero.getX()][hero.getY() + 1] != area.getWallValue()) {
            hero.setY(hero.getY());
            heroDirection = HeroDirection.DOWN;
            hero.setSteps(hero.getSteps() + 1);
        }
    }

    public void moveLeft() {
        if(hero.getX() > 0 && area.getTiles()[hero.getX() - 1][hero.getY()] != area.getWallValue()) {
            hero.setX(hero.getX() - 1);
            heroDirection = HeroDirection.LEFT;
            hero.setSteps(hero.getSteps() + 1);
        }
    }

    public void moveRight() {
        if(hero.getX() < area.getSize() - 1 && area.getTiles()[hero.getX() + 1][hero.getY()] != area.getWallValue()) {
            hero.setX(hero.getX() + 1);
            heroDirection = HeroDirection.RIGHT;
            hero.setSteps(hero.getSteps() + 1);
        }
    }

    public void updateDirection(HeroDirection newHeroDirection) {
        this.heroDirection = newHeroDirection;
    }

    public void lifeChanging() {
    }

    public void getGold() {
        if (hero.getX() == gold.getX() && hero.getY() == gold.getY()) {
            hero.setHasGold(true);
        }
    }
    public void getKey() {
        if(hero.getX() == key.getX() && hero.getY() == key.getY()) {
            hero.setHasKey(true);
        }
    }

    public void triggerBomb(Board board) {

        if (!hero.isThrowBomb()) {
            hero.setThrowBomb(true);
            bomb.setVisible(true);
            bombController.currentCoordinatesOfBomb();

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
