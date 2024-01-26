package org.example.entities;
import lombok.Getter;
import lombok.Setter;
import org.example.views.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private boolean isAlive = true;

    public Monster(Area area, Hero hero, Board board, Bomb bomb) {
        this.name = MonsterName.getName().toString();
        this.area = area;
        this.board = board;
        this.hero = hero;
        this.x = getRandomStartPosition(area)[0];
        this.y = getRandomStartPosition(area)[1];
        this.timer = initTimer();
        this.bomb = bomb;
        startTimer();
    }

    public boolean isMonsterAffectedByBomb(Bomb bomb) {
        return getX() == bomb.getPosX() && getY() == bomb.getPosY();
    }

   private int createRandomSpeed() {
       Random speed = new Random();
        return speed.nextInt(2000, 5000);
   }

    public int[] getRandomStartPosition(Area area) {
        List<int[]> allEmptyCoordinates = new ArrayList<>();
        for (int i = 0; i < area.getTiles().length; i++) {
            for (int j = 0; j < area.getTiles()[0].length; j++) {
                if (area.getTiles()[i][j] == area.getFloorValue()) {
                    int[] emptyCoordinate = new int[2];
                    emptyCoordinate[0] = i;
                    emptyCoordinate[1] = j;
                    allEmptyCoordinates.add(emptyCoordinate);
                }
            }
        }
        Random random = new Random();
        return allEmptyCoordinates.get(random.nextInt(0, allEmptyCoordinates.size() -1));
    }

    private Timer initTimer() {
        timer = new Timer(createRandomSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveTowardsHero();
            }
        });
        return timer;
    }

    public void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
    private void moveTowardsHero() {
        int targetX = hero.getCurrentX();
        int targetY = hero.getCurrentY();

        if (x < targetX && x < area.getSize() - 1 && area.getTiles()[x + 1][y] != area.getWallValue()) {
            x++;
        } else if (x > targetX && (x > 0 && area.getTiles()[x - 1][y] != area.getWallValue())) {
            x--;
        }

        if (y < targetY && area.getTiles()[x][y + 1] != area.getWallValue()) {
            y++;
        } else if (y > targetY && area.getTiles()[x][y - 1] != area.getWallValue()) {
            y--;
        }
        board.repaint();
        //System.out.println("coordinate of : " + name+ "iw alive: "+ isAlive() +" is: " + x + " : " + y + " comes from Monster class's method");
    }
}
