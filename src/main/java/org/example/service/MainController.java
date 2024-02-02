package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.views.Board;
import org.example.views.InfoPanel;

@Setter
@Getter
public class MainController {

    private Board board;
    private Area area;
    private Monster monster;
    private MonsterServiceImpl monsterServiceImpl;
    private Hero hero;
    private int stepsOfHero;
    private Bomb bomb;
    private Gold gold;
    private Key key;
    private BombServiceImpl bombServiceImpl;
    private int remainingMonsters;
    private InfoPanel infoPanel;
    private String endMessage;
    private HeroService heroService;
    private MonsterService monsterService;
    private BombService bombService;

    public MainController(Area area, Board board, Hero hero, Bomb bomb, HeroService heroService, MonsterService monsterService,
                          BombService bombService) {
        this.area = area;
        this.board = board;
        this.hero = hero;
        this.bomb = bomb;
        this.heroService = heroService;
        this.monsterService = monsterService;
        this.bombService = bombService;
        this.remainingMonsters = area.getNumberOfMonster();
        this.stepsOfHero = heroService.getHeroSteps();
        infoPanel = new InfoPanel(this);
        updateInfoPanelSteps();
        updateInfoPanelGold();
        upateInfoPanelKey();
        board.setFocusable(true);
        board.requestFocusInWindow();
        board.addKeyListener(heroService);
        //-----------------

        board.setBomb(bomb);
        hero.setBomb(bomb);

        //bombServiceImpl.setBomb(bomb);
        board.setBombServiceImpl(bombServiceImpl);
        board.addMouseListener(bombServiceImpl);

        board.setHero(hero);

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

    public String updateInfoPanelGold() {
        String goldMessage = "";
        if(hero.isHasGold()) {
            goldMessage = "Hoho, you have a gold !!!";
        }
        return goldMessage;
        //infoPanel.updateMonsterInfo(remainingMonsters);
    }
    public String upateInfoPanelKey() {
        String keyMessage = "";
        if(hero.isHasKey()) {
            keyMessage = "Lucky man, you have a key! :-)";
        }
        return keyMessage;
    }
    public int updateInfoPanelSteps() {
        infoPanel.stepsInfo(hero.getSteps());
        return heroService.getHeroSteps();
    }
}
