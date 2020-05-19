package net.shvdy.food_tracker.model.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {
                        InitialContext context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/food_tracker");
                    } catch (NamingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }
}