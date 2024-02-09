package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;
import org.example.entities.Bomb;
import org.example.entities.Hero;
import org.example.entities.mine.Mine;
import org.example.entities.mine.MineField;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.repository.UserRepository;
import org.example.views.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

@Setter
@Getter
public class MainController implements KeyListener {

    private Board board;
    private GameFrame gameFrame;
    private UserRepository userRepository;
    private Area area;
    private Hero hero;
    private HeroService heroService;
    private int stepsOfHero;
    private Bomb bomb;
    private Gold gold;
    private Key key;
    private MineField mineField;
    private int explosionXCoordinate = 0;
    private int explosionYCoordinate = 0;
    private Mine mine;
    private BombServiceImpl bombServiceImpl;
    private int remainingMonsters;
    private InfoPanel infoPanel;
    private String endMessage;
    private UserPanel userPanel;

    public MainController(Area area, Board board, UserRepository userRepository) {
        this.area = area;
        this.board = board;
        this.userRepository = userRepository;
        this.remainingMonsters = area.getNumberOfMonster();
        infoPanel = new InfoPanel(this);



        //board.addKeyListener(heroService); //instead of that I'm using own Listener for MainController
        //-----------------

    }

    public void showBoard() {
        System.out.println("show static");
        startGame();

        board.requestFocusInWindow(); // or any method to refresh/redraw the board

        // Wait for 2 seconds (adjust the time as needed)
        try {
            Thread.sleep(100);
            System.out.println("time");

            //gameFrame.showGamePanel(); // Show the initial setup
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //start the game, listen the moving of Hero
    public void startGame() {
        // Perform any actions needed to start the game
        // You can add additional logic here, such as starting timers or initializing game state
        System.out.println("Game started!");
        //staticBoard.requestFocusInWindow();e
        //dynamicBoard.requestFocusInWindow();
        //getNumberOfMonster();
        startListeningForKeyEvents();
        heroInDanger();
        updateInfoPanelSteps();
        updateInfoPanelGold();
        updateInfoPanelKey();
    }

    public int getNumberOfMonster() {
        int size = area.getSize();
        int numberOfNumpus = 0;
        if(size <= 8) {
            numberOfNumpus = 1;
        } else if (size > 8 && size <= 14) {
            numberOfNumpus = 2;
        } else {
            numberOfNumpus = 3;
        }
        return numberOfNumpus;
    }

    public boolean heroInDanger() {
        boolean safetyField = true;
        List<Mine> mineList = mineField.getMineField();
        for(int i = 0; i < mineList.size(); i++) {
            if(hero.getX() == mineList.get(i).getX() && hero.getY() == mineList.get(i).getY()) {
                explosionXCoordinate = mineList.get(i).getX();
                explosionYCoordinate = mineList.get(i).getY();
                System.out.println(explosionXCoordinate + " : " + explosionYCoordinate);
                safetyField = false;
                hero.setLife(hero.getLife() - 1);
            }
        }
        return safetyField;
    }
    //Information to the InfoPanel:
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
    public String updateInfoPanelKey() {
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

    //Own KeyListener to listen the moving of Hero -> it happened in the HeroService
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        heroService.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //Get information based on the Hero's moving:
    public void startListeningForKeyEvents() {
        board.addKeyListener(this);
    }
}
