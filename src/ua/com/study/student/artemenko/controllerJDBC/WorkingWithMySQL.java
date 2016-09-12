package ua.com.study.student.artemenko.controllerJDBC;

import ua.com.study.student.artemenko.project.Person;
import ua.com.study.student.artemenko.project.Specialty;
import ua.com.study.student.artemenko.project.Staff;

import java.sql.*;
import java.util.ArrayList;

public class WorkingWithMySQL {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/projectinfo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private Connection connection;
    private Statement statement;
    private String lineHelp = "------------------------------------------------------------------------------------------";

    public WorkingWithMySQL() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No connection with Base Data");
        }

    }

    public ArrayList<Integer> showAllProjectManager() {

        String sql = "SELECT staff.id,first_name,last_name,age,name,salary " +
                        "FROM staff,specialties,staff_specialties,team_staff " +
                        "WHERE staff.id=staff_specialties.staff_id AND " +
                        "specialties.id=specialty_id AND " +
                        "specialties.name='Project Manager' AND " +
                        "team_staff.staff_id=staff.id AND " +
                        "team_staff.team_id IS NULL;";
        ArrayList<Integer> projectManagerList = helpRequest(sql);

//
        return projectManagerList;
    }


    public ArrayList<Integer> showAllDevelopers() {
        String sql = "SELECT staff.id,first_name,last_name,age,name,salary " +
                        "FROM staff,specialties,staff_specialties,team_staff " +
                        "WHERE staff.id=staff_specialties.staff_id AND " +
                        "specialties.id=specialty_id AND " +
                        "specialties.name<>'Project Manager' AND " +
                        "specialties.name NOT LIKE 'QA%' AND " +
                        "team_staff.staff_id=staff.id AND " +
                        "team_staff.team_id IS NULL;";
        ArrayList<Integer> projectManagerList = helpRequest(sql);

        return projectManagerList;
    }

    public ArrayList<Integer> showAllTesters() {
        String sql = "SELECT staff.id,first_name,last_name,age,name,salary " +
                "FROM staff,specialties,staff_specialties,team_staff " +
                "WHERE staff.id=staff_specialties.staff_id AND " +
                "specialties.id=specialty_id AND " +
                "specialties.name<>'Project Manager' AND " +
                "specialties.name LIKE 'QA%' AND " +
                "team_staff.staff_id=staff.id AND " +
                "team_staff.team_id IS NULL;";
        ArrayList<Integer> projectManagerList = helpRequest(sql);

        return projectManagerList;
    }

    private ArrayList<Integer> helpRequest(String sql){
        ArrayList<Integer> helpList = new ArrayList<>();

        System.out.println(lineHelp);
        String strH =
                String.format("|  %5s  |  %15s  |  %15s  |  %4s  |  %15s |  %6s  |",
                                "id", "first_name", "last_name","age", "specialty", "salary");
        System.out.println(strH);
        System.out.println(lineHelp);

        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("staff.id");
                helpList.add(id);
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String nameSpecialty = resultSet.getString("name");
                double salary = resultSet.getInt("salary");

                String str =
                        String.format("|  %5s  |  %15s  |  %15s  |  %4s  |  %15s |  %6s  |",
                                        id, first_name, last_name,age, nameSpecialty, salary);

                System.out.println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(lineHelp);
        return helpList;
    }

    public void closeResurs() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
