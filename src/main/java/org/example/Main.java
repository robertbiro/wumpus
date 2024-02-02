package org.example;

import org.example.entities.*;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.repository.HibernateUserRepository;
import org.example.repository.UserRepository;
import org.example.service.*;
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
                    Board board = new Board(800, 800, area);
                    Key key = new Key(area);
                    Gold gold = new Gold(area);
                    Hero hero = new Hero(1, 1, HeroDirection.DOWN);

                    HeroService heroService = new HeroServiceImpl(area, board, gold, key, hero);

                    Bomb bomb = new Bomb(board, hero);

                    MonsterCollection monsterCollection = new MonsterCollection();
                    MonsterService monsterService = null;
                    for (int i = 0; i < area.getNumberOfMonster(); i++) {
                        Monster monster = new Monster(area, hero, board, bomb);
                        monsterCollection.addMonster(monster);
                        monsterService = new MonsterServiceImpl(hero, monster, monsterCollection);
                    }
                    board.setMonsterCollection(monsterCollection);
                    board.setGold(gold);
                    board.setKey(key);
                    BombService bombService = new BombServiceImpl(area, board, hero, heroService, monsterService);
                    MainController mainController = new MainController(area, board, hero, bomb, heroService, monsterService, bombService);
                    new GameFrame(userRepository, mainController, board);
                });
            //} else {
                //System.err.println("Unable to establish database connection. Exiting...");
            //}
        } catch (Exception e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
    }

}