package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;

import java.util.ArrayList;

public class InputScreen {

    private static WorkingWithMySQL workingWithMySQL = new WorkingWithMySQL();
    private static int countProject = 0;
    private static int countTeam = 0;

    public void choiceInputScreen(String nameScreen) {
        switch (nameScreen) {
            case ("CREATE PROJECT"):
                InputScreenCreateProject();
                break;

        }
    }

    private void InputScreenCreateProject() {

        String nameProject = inputString("Enter the name of the project");
        String descriptionProject = inputString("Enter the name of the project");
        Integer projectManagerId = inputInt("Select a project manager.Input id");
        countProject++;

        workingWithMySQL.writeProject(countProject, nameProject, descriptionProject, projectManagerId);
        createMapTeam(countProject,"Developers");
        createMapTeam(countProject,"Testers");



        System.out.println("Project create");
    }

    private void createMapTeam(int countProject,String messenger) {
        int typeTeam = workingWithMySQL.returnTypeTeam(messenger);
        boolean endWork = false;
        while (!endWork) {
            ArrayList<Integer> listIdStaffInTeam = inputTeam(messenger);
            countTeam++;
            workingWithMySQL.writeTeams(countTeam);
            workingWithMySQL.writeTeamsTypeteam(countTeam,typeTeam);
            workingWithMySQL.writeProjectTeams(countProject, countTeam);
            for (Integer integer : listIdStaffInTeam) {
                workingWithMySQL.writeTeamStaff(countTeam, integer);
            }
            System.out.println("Create next team for project?");
            System.out.println("Input yes/no");
            String help = Controller.getScanner().nextLine();
            if (help.equals("no")) {
                endWork = true;
            }
        }
    }


    private String inputString(String messenger) {
        String returnString = "";

        boolean endWork = false;
        System.out.println(messenger);
        while (!endWork) {
            returnString = Controller.getScanner().nextLine();
            if ((returnString != null) && (!returnString.equals(""))) {
                endWork = true;
            }
        }
        return returnString;
    }

    private Integer inputInt(String messenger) {
        Integer returnInteger = null;

        boolean endWork = false;
        System.out.println(messenger);
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        while (!endWork) {
            int help = Controller.getScanner().nextInt();
            for (Integer id : idProjectManagerList) {
                if (id == help) {
                    returnInteger = help;
                    endWork = true;
                    break;
                }
            }
            if (returnInteger == null) {
                System.out.println("All project managers are busy.");
                return null;
            }
        }
        return returnInteger;
    }

    private ArrayList<Integer> inputTeam(String messenger) {
        int countHelp = 0;
        boolean endWork = false;
        System.out.println("Select a " + messenger + " in team.Input id");
        System.out.println("To exit click A");
        ArrayList<Integer> idList;

        if (messenger.equals("Developers")) {
            idList = workingWithMySQL.showAllDevelopers();
        } else {
            idList = workingWithMySQL.showAllTesters();
        }

        ArrayList<Integer> listIdStaffInTeam = new ArrayList<>();
        while (!endWork) {
            if (Controller.getScanner().hasNextInt()) {
                int help = Controller.getScanner().nextInt();
                for (Integer id : idList) {
                    if (id == help) {
                        listIdStaffInTeam.add(help);
                        break;
                    }
                }
            } else {
                countHelp++;
                Controller.getScanner().nextLine();
                if (countHelp == 1) {
                    Controller.getScanner().nextLine();
                    endWork = true;
                    System.out.println("Team  is create");
                } else {
                    countHelp = 0;
                }
            }
            if (listIdStaffInTeam == null) {
                System.out.println("All " + messenger + " are busy.");
                return null;
            }
        }
        return listIdStaffInTeam;

    }
}