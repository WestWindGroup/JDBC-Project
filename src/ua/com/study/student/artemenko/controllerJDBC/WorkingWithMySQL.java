package ua.com.study.student.artemenko.controllerJDBC;

import ua.com.study.student.artemenko.project.Person;
import ua.com.study.student.artemenko.project.Specialty;
import ua.com.study.student.artemenko.project.Staff;

import java.sql.*;
import java.util.ArrayList;

public class WorkingWithMySQL {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/testproject";
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
        String sql = "SELECT staff.id,first_name,last_name,age,specialties.name,salary " +
                        "FROM staff,specialties,staff_specialties,projects " +
                        "WHERE staff.id=staff_specialties.staff_id AND " +
                                "staff_specialties.specialty_id=specialties.id AND  " +
                                "specialties.name='Project Manager' AND " +
                                "projects.projects_manager_id<>staff.id;";

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

    public void writeProject(String name,String description,int projects_manager_id) {
        String sql = "INSERT INTO projects (name,description,projects_manager_id) " +
                "VALUES('" + name + "','" + description + "'," + projects_manager_id + ");";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeTeamStaff(int team_id,int staff_id) {
        String sql = "UPDATE team_staff SET team_id="+ team_id +" WHERE staff_id=" + staff_id + ";";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeProjectTeams(int project_id, int teams_id) {
        String sql = "INSERT INTO project_teams (project_id,teams_id) " +
                "VALUES(" + project_id + "," + teams_id + ");";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeTeams(int numberTeam) {
        String sql = "INSERT INTO teams " +
                "VALUES(" + numberTeam + ");";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeTeamsTypeteam(int team_id, int typeteam_id) {
        String sql = "INSERT INTO teams_typeteam (team_id,typeteam_id) " +
                "VALUES(" + team_id + "," + typeteam_id + ");";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                String nameSpecialty = resultSet.getString("specialties.name");
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

    public void closeResources() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int returnTypeTeam(String messenger) {
        int id = 0;
        String sql = "SELECT id " +
                "FROM typeteam " +
                "WHERE name='" + messenger + "';";
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<Integer> showProjects() {
        ArrayList<Integer> helpList = new ArrayList<>();
        
        String sql = "SELECT projects.id,name,description,last_name AS project_manager " +
                "FROM projects,staff " +
                "WHERE projects_manager_id=staff.id;";
        System.out.println("------------------------------------------------------------------------");
        String strH =
                String.format("|  %5s  |  %15s  |  %15s  |  %15s  |",
                        "id", "name project", "description","project_manager");
        System.out.println(strH);
        System.out.println("------------------------------------------------------------------------");

        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("projects.id");
                helpList.add(id);
                String name_project = resultSet.getString("name");
                String description = resultSet.getString("description");
                String project_manager = resultSet.getString("project_manager");

                String str =
                        String.format("|  %5s  |  %15s  |  %15s  |  %15s  |",
                                id, name_project, description,project_manager);

                System.out.println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------------------------------------");
        return helpList;
    }

    public void showProject(Integer projectId) {
        String sql1 = "SELECT projects.id,name,description,last_name AS project_manager " +
                "FROM projects,staff " +
                "WHERE projects_manager_id=staff.id AND " +
                "       projects.id=" + projectId + ";";

        System.out.println(" =======================================================================");
        String strH1 =
                String.format("||  %5s  |  %15s  |  %15s  |  %15s  ||",
                        "id", "name project", "description","project_manager");
        System.out.println(strH1);
        System.out.println(" -----------------------------------------------------------------------");

        try (ResultSet resultSet = statement.executeQuery(sql1)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("projects.id");
                String name_project = resultSet.getString("name");
                String description = resultSet.getString("description");
                String project_manager = resultSet.getString("project_manager");

                String str =
                        String.format("||  %5s  |  %15s  |  %15s  |  %15s  ||",
                                id, name_project, description,project_manager);

                System.out.println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAllTeam(projectId);
//
    }

    public void changeNameProject(int changeNumberProject,String newNameProject) {
        String sql = "UPDATE projects SET name='" + newNameProject + "' WHERE projects.id=" + changeNumberProject + ";";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeDescriptionProject(int changeNumberProject,String newDescriptionProject) {
        String sql = "UPDATE projects SET description='" + newDescriptionProject + "' WHERE projects.id=" + changeNumberProject + ";";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeProjectManager(int changeNumberProject, int projectManagerId) {
        String sql = "UPDATE projects SET projects_manager_id='" + projectManagerId + "' WHERE projects.id=" + changeNumberProject + ";";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> showAllTeam(Integer projectId) {
        ArrayList<Integer> idTeamList = new ArrayList<>();
        String sql = "SELECT projects.id,teams.id,typeteam.name,staff.last_name,staff.id AS id_staff,specialties.name AS specialty,specialties.salary " +
                "FROM projects,teams,typeteam,staff,specialties,staff_specialties,teams_typeteam,project_teams,team_staff " +
                "WHERE projects.id=project_teams.project_id AND " +
                "      project_teams.teams_id=teams.id AND " +
                "      teams.id=teams_typeteam.team_id AND " +
                "      teams_typeteam.typeteam_id=typeteam.id AND " +
                "      staff.id=team_staff.staff_id AND " +
                "      team_staff.team_id=teams.id AND " +
                "      staff_specialties.staff_id=staff.id AND " +
                "      staff_specialties.specialty_id=specialties.id  AND " +
                "       projects.id=" + projectId +
                " ORDER BY teams.id;";
        System.out.println(" =================================================================================================================");
        String strH2 =
                String.format("||  %10s  |  %7s  |  %15s  |  %15s  |  %8s  |  %15s  |  %7s  ||",
                        "id_project","id_team","name","last_name", "id_staff","specialty","salary");
        System.out.println(strH2);
        System.out.println(" -----------------------------------------------------------------------------------------------------------------");

        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id_project = resultSet.getInt("projects.id");
                int teams_id = resultSet.getInt("teams.id");
                idTeamList.add(teams_id);
                String typeteam_name = resultSet.getString("typeteam.name");
                String last_name = resultSet.getString("staff.last_name");
                int id_staff = resultSet.getInt("id_staff");
                String specialty = resultSet.getString("specialty");
                double salary = resultSet.getInt("specialties.salary");

                String str =
                        String.format("||  %10s  |  %7s  |  %15s  |  %15s  |  %8s  |  %15s  |  %7s  ||",
                                id_project,teams_id,typeteam_name,last_name, id_staff,specialty,salary);

                System.out.println(str);
            }
            System.out.println(" =================================================================================================================");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTeamList;
    }

    public void changeDeleteTeam(int deleteTeamId) {

        String sql1 = "DELETE FROM teams_typeteam WHERE team_id="+ deleteTeamId + ";";
        String sql2 = "DELETE FROM project_teams WHERE teams_id="+ deleteTeamId + ";";
        String sql3 = "UPDATE team_staff SET team_id=NULL WHERE team_id=" + deleteTeamId + ";";
        String sql4 = "DELETE FROM teams WHERE id="+ deleteTeamId + ";";

        try {
            statement.execute(sql1);
            statement.execute(sql2);
            statement.execute(sql3);
            statement.execute(sql4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
