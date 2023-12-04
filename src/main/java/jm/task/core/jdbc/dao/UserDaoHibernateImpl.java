package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }

    private Session getOpenSession() {
        return Util.getSessionFactory().openSession();
    }


    @Override
    public void createUsersTable() {
        try (Session session = getOpenSession()) {

            String sql = "CREATE TABLE users (" +
                    "  ID INT NOT NULL AUTO_INCREMENT," +
                    "  NAME VARCHAR(60) NOT NULL," +
                    "  LASTNAME VARCHAR(60) NOT NULL," +
                    "  AGE INT(3) NOT NULL," +
                    "  PRIMARY KEY (ID))";
            session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getOpenSession()) {

            String sql = "DROP TABLE IF EXISTS users";
            session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        try (Session session = getOpenSession()) {
            session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getOpenSession()) {
            session.beginTransaction();
            User user = session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = getOpenSession()) {
            Query query = session.createSQLQuery("SELECT * FROM users").addEntity(User.class);
            list = (List<User>) query.list();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getOpenSession()) {

            String sql = "DELETE FROM users";
            session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }
}
