package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        try {
            userDao.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
