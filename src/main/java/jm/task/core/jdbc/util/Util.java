package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/schemaex2";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "1234";

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                configuration.setProperty(Environment.URL, DB_URL);
                configuration.setProperty(Environment.USER, DB_LOGIN);
                configuration.setProperty(Environment.PASS, DB_PASSWORD);
                configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty(Environment.SHOW_SQL, "true");
                configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperty(Environment.HBM2DDL_AUTO, "");

                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {

            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
