package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.service.BombController;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.views.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class Hero {

    private int x;
    private int y;
    private int life = 1;
    private int steps;
    private Bomb bomb;
    private BombController bombController;
    private Gold gold;
    private Key key;
    private boolean throwBomb;
    private boolean hasGold;
    private boolean hasKey;

    private HeroDirection heroDirection;
   private Timer timer;

    private Area area;

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public HeroDirection getDirection() {
        return heroDirection;
    }


    public Hero(int x, int y, HeroDirection heroDirection) {
        this.x = x;
        this.y = y;
        hasGold = false;
        hasKey = false;
        steps = 0;
        this.heroDirection = heroDirection;
    }

        public void moveUp() {
        if(y > 0 && area.getTiles()[x][y - 1] != area.getWallValue()) {
            y--;
            heroDirection = HeroDirection.UP;
            steps++;
            }
        }

        public void moveDown() {
            if(y < area.getSize() - 1 && area.getTiles()[x][y + 1] != area.getWallValue()) {
                y++;
                heroDirection = HeroDirection.DOWN;
                steps++;
            }
        }

        public void moveLeft() {
            if(x > 0 && area.getTiles()[x - 1][y] != area.getWallValue()) {
                x--;
                heroDirection = HeroDirection.LEFT;
                steps++;
            }
        }

        public void moveRight() {
        if( x < area.getSize() - 1 && area.getTiles()[x + 1][y] != area.getWallValue()) {
            x++;
            heroDirection = HeroDirection.RIGHT;
            steps++;
        }
    }

    public void updateDirection(HeroDirection newHeroDirection) {
        this.heroDirection = newHeroDirection;
    }

    public void lifeChanging() {

    }

    public void getGold() {
        System.out.println(" coordinate of gold: x" + gold.getX() + " y " + gold.getY());
        System.out.println(" coordinate of hero: x" + getCurrentX() + " y " + getCurrentY());
        if (getCurrentX() == gold.getX() && getCurrentY() == gold.getY()) {
            hasGold = true;
        }
    }
        public void getKey() {
            System.out.println(" coordinate of gold: x" + gold.getX() + " y " + gold.getY());
            System.out.println(" coordinate of hero: x" + getCurrentX() + " y " + getCurrentY());
            if(getCurrentX() == key.getX() && getCurrentY() == key.getY()) {
                hasKey = true;
            }
    }

    public void triggerBomb(Board board) {

        if (!throwBomb) {
            throwBomb = true;
            bomb.setVisible(true);
            bombController.currentCoordinatesOfBomb();

            if (timer == null || !timer.isRunning()) {
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Timer action: set the bomb to invisible
                        bomb.setVisible(false);
                        throwBomb = false;
                        board.repaint();
                        timer.stop();
                    }
                });
                timer.start(); // Start the timer
            }
        }
    }
}
