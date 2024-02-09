package org.example;

import org.example.entities.*;
import org.example.entities.mine.Mine;
import org.example.entities.mine.MineField;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.repository.HibernateUserRepository;
import org.example.repository.UserRepository;
import org.example.service.HeroService;
import org.example.service.HeroServiceImpl;
import org.example.service.MainController;
import org.example.views.Board;
import org.example.views.GameFrame;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            //Connection connection = DatabaseConnection.getConnection();
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();

            UserRepository userRepository = new HibernateUserRepository(factory);

                System.out.println("Database connection successful!");

                SwingUtilities.invokeLater(() -> {
                    Area area = new Area(12);
                    Board board = new Board(area, 800, 800);

                    Key key = new Key(area);
                    Gold gold = new Gold(area);
                    Hero hero = new Hero(1, 1, HeroDirection.DOWN);
                    board.setHero(hero);
                    board.setGold(gold);
                    board.setKey(key);
                    //DynamicBoard dynamicBoard = new DynamicBoard(area, hero, key, gold);
                    //Bomb bomb = new Bomb(dynamicBoard, hero);


                    //HeroService heroService = new HeroServiceImpl(area, dynamicBoard, gold, key, hero);
                    HeroService heroService = new HeroServiceImpl(area, board, gold, key, hero);
                    //dynamicBoard.setHeroService(heroService);

                    MineField mineField = new MineField(area);
                    for (int i = 0; i < mineField.getExplosionValueOccurrences(); i++) {
                        Mine mine = new Mine(area);
                        mineField.addMine(mine);
                        //ynamicBoard.setMine(mine);
                        System.out.println(mine.getX() + " : " + mine.getY());
                    }

                    //board.setMainController(mainController);

                    MonsterCollection monsterCollection = new MonsterCollection();

                    /*MonsterService monsterService = null;
                    for (int j = 0; j < mainController.getNumberOfMonster(); j++) {
                        Monster monster = new Monster(area, hero, dynamicBoard);
                        monsterCollection.addMonster(monster);
                        monsterService = new MonsterServiceImpl(hero, monster, monsterCollection);
                    } */
                    //board.setMonsterCollection(monsterCollection);
                    //mainController.setHero(hero);
                    //1BombService bombService = new BombServiceImpl(area, board, hero, heroService, monsterService);

                    MainController mainController = new MainController(area, board, userRepository);
                    mainController.setMineField(mineField);
                    mainController.setHero(hero);
                    mainController.setHeroService(heroService);
                    //mainController.setDynamicBoard(dynamicBoard);
                    GameFrame gameFrame =  new GameFrame(userRepository, mainController, board);

                    mainController.setGameFrame(gameFrame);
                    mainController.showBoard();
                   // UserPanel userPanel = new UserPanel(userRepository);
                    //mainController.setUserPanel(userPanel);
                });
            //} else {
                //System.err.println("Unable to establish database connection. Exiting...");
            //}
        } catch (Exception e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
    }

}