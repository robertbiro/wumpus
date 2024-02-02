package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;
import org.example.entities.Hero;
import org.example.entities.HeroDirection;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.views.Board;

import java.awt.event.KeyEvent;


@Setter
@Getter
public class HeroServiceImpl implements HeroService {

    private Hero hero;
    private HeroDirection heroDirection;
    private Area area;
    private Board board;
    private Gold gold;
    private Key key;
    public HeroServiceImpl(Area area, Board board, Gold gold, Key key, Hero hero) {
        this.area = area;
        this.board = board;
        this.gold = gold;
        this.key = key;
        this.hero = hero;
    }

    //moving of HERO
    @Override
    public void moveUp() {
        if(hero.getY() > 0 && area.getTiles()[hero.getX()][hero.getY() - 1] != area.getWallValue()) {
            hero.setY(hero.getY() -1);
            heroDirection = HeroDirection.UP;
        }
    }
    @Override
    public void moveDown() {
        if(hero.getY() < area.getSize() - 1 && area.getTiles()[hero.getX()][hero.getY() + 1] != area.getWallValue()) {
            hero.setY(hero.getY() + 1);
            heroDirection = HeroDirection.DOWN;
        }
    }
    @Override
    public void moveLeft() {
        if(hero.getX() > 0 && area.getTiles()[hero.getX() - 1][hero.getY()] != area.getWallValue()) {
            hero.setX(hero.getX() - 1);
            heroDirection = HeroDirection.LEFT;
        }
    }
    @Override
    public void moveRight() {
        if(hero.getX() < area.getSize() - 1 && area.getTiles()[hero.getX() + 1][hero.getY()] != area.getWallValue()) {
            hero.setX(hero.getX() + 1);
            heroDirection = HeroDirection.RIGHT;
        }
    }
    //Life of HERO
    public void lifeChanging() {
    }

    //Tools of HERO
    @Override
    public void getGold() {
        if (hero.getX() == gold.getX() && hero.getY() == gold.getY()) {
            hero.setHasGold(true);
        }
    }

    @Override
    public void getKey() {
        if(hero.getX() == key.getX() && hero.getY() == key.getY()) {
            hero.setHasKey(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Released: " + e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp();
            hero.setHeroDirection(HeroDirection.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown();
            hero.setHeroDirection(HeroDirection.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft();
            hero.setHeroDirection(HeroDirection.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight();
            hero.setHeroDirection(HeroDirection.RIGHT);
        }
        hero.setSteps(hero.getSteps() + 1);
        getGold();
        getKey();
        board.repaint();
        //updateInfoPanelMonster(); // Add this line to update the info panel after handling the key event
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public int getHeroSteps() {
        return hero.getSteps();
    }
}
