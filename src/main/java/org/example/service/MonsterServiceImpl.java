package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.views.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class MonsterServiceImpl implements MonsterService {
    private Monster monster;
    private MonsterCollection monsters;
    private Bomb bomb;
    private Hero hero;
    private Area area;
    private Board board;
    private Timer timer;
    private int remainingMonsters;


    public MonsterServiceImpl(Hero hero, Monster monster, MonsterCollection monsters) {
        this.hero = hero;
        this.monster = monster;
        this.monsters = monsters;
        remainingMonsters = monsters.getMonsters().size();
    }

    @Override
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

    @Override
    public Timer initTimer() {
        timer = new Timer(createRandomSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveTowardsHero();
            }
        });
        return timer;
    }
    @Override
    public int createRandomSpeed() {
        Random speed = new Random();
        return speed.nextInt(2000, 5000);
    }

    @Override
    public boolean isMonsterAffectedByBomb(Bomb bomb) {
        return  monster.getX() == bomb.getX() && monster.getY() == bomb.getY();
    }

    @Override
    public void handleBombImpact() {
        for (Monster monster : monsters.getMonsters()) {
            if (isMonsterAffectedByBomb(bomb)) {
                monster.setAlive(false);
                remainingMonsters--;
                //updateInfoPanelMonster();
                // Check for game completion
                if (remainingMonsters == 0) {
                    //updateInfoPanelEndGame();
                }
            }
        }
        //to remove dead monsters from the controller
        monsters.removeDeadMonsters();
        board.repaint();
    }

    @Override
    public void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }
    @Override
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
    @Override
    public void moveTowardsHero() {
        int targetX = hero.getX();
        int targetY = hero.getY();

        if (monster.getX() < targetX && monster.getX() < area.getSize() - 1 && area.getTiles()[monster.getX() + 1][monster.getY()] != area.getWallValue()) {
            monster.setX(monster.getX() + 1);
        } else if (monster.getX() > targetX && (monster.getX() > 0 && area.getTiles()[monster.getX() - 1][monster.getY()] != area.getWallValue())) {
            monster.setX(monster.getX() - 1);
        }

        if (monster.getY() < targetY && area.getTiles()[monster.getX()][monster.getY() + 1] != area.getWallValue()) {
            monster.setY(monster.getY() + 1);
        } else if (monster.getY() > targetY && area.getTiles()[monster.getX()][monster.getY() - 1] != area.getWallValue()) {
            monster.setY(monster.getY() - 1);
        }
        board.repaint();
    }
    @Override
    public List<Monster> getMonsters() {
        return monsters.getMonsters();
    }
}
