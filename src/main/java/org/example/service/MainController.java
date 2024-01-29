package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.views.Board;
import org.example.views.InfoPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Setter
@Getter
public class MainController implements KeyListener {

    private Board board;
    private Area area;
    private Monster monster;
    private MonsterController monsterController;
    private Hero hero;
    private int stepsOfHero;
    private Bomb bomb;
    private Gold gold;
    private Key key;
    private BombController bombController;
    private int remainingMonsters;
    private InfoPanel infoPanel;
    private String endMessage;
    private HeroService heroService;

    public MainController(Board board) {
        System.out.println("Hello from Main !!");
        this.board = board;
        this.area = new Area(12);

        infoPanel = new InfoPanel(this);


        this.monsterController = new MonsterController();
        board.setMonsterController(monsterController);
        this.hero = new Hero(1, 1, HeroDirection.DOWN);
        this.stepsOfHero = 0;
        System.out.println("from constructor: " + hero.getCurrentY() + " : " + hero.getCurrentY());

        this.gold = new Gold(area);
        board.setGold(gold);
        hero.setGold(gold);

        this.key = new Key(area);
        board.setKey(key);
        hero.setKey(key);

        //only one bomb "exist" which is visible when it is triggered.
        this.bomb = new Bomb(board, hero, this);
        board.setBomb(bomb);
        hero.setBomb(bomb);

        this.bombController = new BombController(area, board, hero);
        bombController.setBomb(bomb);
        board.setBombController(bombController);
        board.addMouseListener(bombController);
        hero.setBombController(bombController);

        for (int i = 0; i < area.getNumberOfMonster(); i++) {
            this.monster = new Monster(area, hero, board, bomb);
            monster.getRandomStartPosition(area);
            if (monster.isAlive()) {
                monsterController.addMonster(monster);
                monster.setHero(hero);
            }
        }
        this.remainingMonsters = area.getNumberOfMonster();

        board.setArea(area);
        hero.setArea(area);
        board.setHero(hero);

        board.setFocusable(true);
        board.requestFocusInWindow();
        //board.addKeyListener(this);
    }

    public void handleBombImpact() {
        for (Monster monster : monsterController.getMonsters()) {
            if (monster.isMonsterAffectedByBomb(bomb)) {
                monster.setAlive(false);
                remainingMonsters--;
                updateInfoPanelMonster();
                // Check for game completion
                if (remainingMonsters == 0) {
                    updateInfoPanelEndGame();
                }
            }
        }
        //to remove dead monsters from the controller
        monsterController.removeDeadMonsters();
        board.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released: " + e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.moveUp();
            hero.updateDirection(HeroDirection.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
            hero.updateDirection(HeroDirection.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
            hero.updateDirection(HeroDirection.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
            hero.updateDirection(HeroDirection.RIGHT);
        }
        stepsOfHero = hero.getSteps();
        hero.getGold();
        hero.getKey();
        board.repaint();
        updateInfoPanelMonster(); // Add this line to update the info panel after handling the key event
    }
    public void startGame() {
        // Perform any actions needed to start the game
        // You can add additional logic here, such as starting timers or initializing game state
        System.out.println("Game started!");
        board.requestFocusInWindow();
    }
    private void updateInfoPanelEndGame() {
        endMessage = "Game Over! All monsters are defeated.";
    }

    private void updateInfoPanelMonster() {
        infoPanel.updateMonsterInfo(remainingMonsters);
    }

    private void updateInfoPanelGold() {
        if(hero.isHasGold()) {
            String goldMessage = "Hoho, you have a gold !!!";
        }
        infoPanel.updateMonsterInfo(remainingMonsters);
    }

}
