package ua.com.study.student.artemenko.controllerJDBC;

import java.sql.*;

public class WorkingWithMySQL {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private  static final String URL = "jdbc:mysql://localhost:3306/projectinfo";
    private  static final String USERNAME = "root";
    private  static final String PASSWORD = "1234";
    private Connection connection;

    public WorkingWithMySQL() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No connection with Base Data");
        }

    }



}
