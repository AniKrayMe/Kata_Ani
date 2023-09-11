package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

//    public static final String USERS_TABLE = "users";
//    public static final String USERS_ID = "idusers";
//    public static final String USERS_NAME = "name";
//    public static final String USERS_LASTNAME = "lastname";
//    public static final String USERS_AGE = "age";

    public static Connection connection;

    public static final String DB_URL = "jdbc:mysql://localhost:3306/kata_anikrayme";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";

    public static Connection getConnection() {

        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Соединение с СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
