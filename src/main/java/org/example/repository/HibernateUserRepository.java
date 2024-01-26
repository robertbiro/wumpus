package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.logging.Level;
import java.util.logging.Logger;


@AllArgsConstructor
public class HibernateUserRepository implements UserRepository{

    private final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateUserRepository.class.getName());

    @Override
    public void saveUser(User user) {
        try {
            Session session = sessionFactory.openSession(); {
                Transaction transaction = session.beginTransaction();
                session.persist(user); //save is deprecated!!!
                transaction.commit();
            }
        } catch (Exception e) {
           logger.log(Level.WARNING,"Exception!");
        }
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public boolean isUserNameExists(String userName) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            String queryStr = "SELECT COUNT(*) FROM User WHERE userName = :userName";
            Query<Long> query = session.createQuery(queryStr, Long.class);
            query.setParameter("userName", userName);

            Long count = query.uniqueResult();
            boolean userNameExists = count != null && count > 0;

            session.getTransaction().commit();
            logger.log(Level.INFO, "Username '" + userName + "' exists: " + userNameExists);
            return userNameExists;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error checking username existence", e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isPasswordCorrect(String username, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            //This part of code is written by ChatGpt, because I wanted use SELECT *
            // -> it is not supported.
            String queryStr = "SELECT COUNT (*) FROM User WHERE userName = :userName AND password = :password";
            Query<Long> query = session.createQuery(queryStr, Long.class);
            query.setParameter("userName", username);
            query.setParameter("password", password);

            Long count = query.uniqueResult();
            boolean isCorrectPassword;
            if (count != null && count > 0) {
                isCorrectPassword = true;
            } else {
                isCorrectPassword = false;
            }

            transaction.commit();
            session.close();
            logger.log(Level.INFO, "Password for username " + username + " is correct: " + isCorrectPassword);
            return isCorrectPassword;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Wrong username or password!", e);
            e.printStackTrace();
            return false;
        }
    }
}
