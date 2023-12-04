package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        try {
            userDao.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        try {
            UserServiceImpl userService = new UserServiceImpl(new UserDaoHibernateImpl());
            userService.createUsersTable();
            userService.saveUser("dadaya", "netya", (byte) 22);
            userService.saveUser("dadaya", "netya", (byte) 22);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
