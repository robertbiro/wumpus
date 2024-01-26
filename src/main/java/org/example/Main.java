package org.example;

import org.example.entities.User;
import org.example.repository.HibernateUserRepository;
import org.example.repository.UserRepository;
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
                    new GameFrame(userRepository);
                });
            //} else {
                //System.err.println("Unable to establish database connection. Exiting...");
            //}
        } catch (Exception e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
    }

}