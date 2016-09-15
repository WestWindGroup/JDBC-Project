package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controller.UserScreen;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;

import java.util.ArrayList;

public class InputScreen {

    private static WorkingWithMySQL workingWithMySQL = new WorkingWithMySQL();
    private static int changeNumberProject = 0;
    public void choiceInputScreen(String nameScreen) {
        switch (nameScreen) {
            case ("CREATE PROJECT"):
                InputScreenCreateProject();
                break;
            case ("SHOW PROJECTS"):
                InputScreenShowProject();
                break;
            case ("SELECT ANOTHER PROJECT"):
                InputScreenSelectProject();
                break;
            case ("CHANGE NAME PROJECT"):
                InputNewNameProject();
                break;
            case ("CHANGE DESCRIPTION"):
                InputNewDescriptionProject();
                break;
            case ("CHANGE PROJECT MANAGER"):
                InputScreenNewProjectManager();
                break;
            case ("CHANGE PROJECT"):
                changeNumberProject = InputScreenShowProject();
                break;
            case ("DELETE TEAM"):
                InputScreenDeleteTeam();
                break;
            case ("ADD TEAM"):
                InputScreenAddTeam();
                break;


        }
    }

    private void InputScreenAddTeam() {
        ArrayList<Integer> idTeamList = workingWithMySQL.showAllTeam(changeNumberProject);
        Integer selectTypeTeam = inputInt(idTeamList,"Select the type of team. 1 developers , 2 testers .");
        if(selectTypeTeam == 1){
            createMapTeam(changeNumberProject,"Developers");
        }else if(selectTypeTeam == 2){
            createMapTeam(changeNumberProject,"Testers");
        }

    }

    private void InputScreenDeleteTeam() {
       ArrayList<Integer> idTeamList = workingWithMySQL.showAllTeam(changeNumberProject);
        Integer deleteTeamId = inputInt(idTeamList,"Select a team.Input id");
        workingWithMySQL.changeDeleteTeam(deleteTeamId);
    }

    private void InputScreenNewProjectManager() {
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        Integer projectManagerId = inputInt(idProjectManagerList,"Select a project manager.Input id");
        workingWithMySQL.changeProjectManager(changeNumberProject,projectManagerId);
    }

    private void InputNewDescriptionProject() {
        String newDescriptionProject = inputString("Enter the description of the project");
        workingWithMySQL.changeDescriptionProject(changeNumberProject,newDescriptionProject);
    }

    private void InputNewNameProject() {
        String newNameProject = inputString("Enter the name of the project");
        workingWithMySQL.changeNameProject(changeNumberProject,newNameProject);
    }

    private void InputScreenSelectProject() {
        UserScreen.setInterfaceScreen("SHOW PROJECTS");
    }

    private Integer InputScreenShowProject() {
        ArrayList<Integer> listIdProject = workingWithMySQL.showProjects();
        if(listIdProject.size() != 0){
            Integer projectId = inputInt(listIdProject, "Select a project.Input id");
            workingWithMySQL.showProject(projectId);
            return projectId;
        }else{
            return null;
        }
    }

    private void InputScreenCreateProject() {
        boolean testNameProject = false;
        String nameProject = null;
        while (!testNameProject){
            nameProject = inputString("Enter the name of the project");
            testNameProject = workingWithMySQL.testNameProject(nameProject);
            if(!testNameProject){
                System.out.println("Project with the same name exists");
            }
        }

        String descriptionProject = inputString("Enter the description of the project");
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        Integer projectManagerId = inputInt(idProjectManagerList,"Select a project manager.Input id");

        int projectID = workingWithMySQL.writeProject( nameProject, descriptionProject, projectManagerId);

        createMapTeam(projectID,"Developers");
        createMapTeam(projectID,"Testers");

        System.out.println("Project create");
    }

    private void createMapTeam(int projectID,String messenger) {
        int typeTeam = workingWithMySQL.returnTypeTeam(messenger);
        boolean endWork = false;
        while (!endWork) {
            ArrayList<Integer> listIdStaffInTeam = inputTeam(messenger);
            if (listIdStaffInTeam.size() != 0){
                String nameTeam = inputString("Input name team");
                int idTeam = workingWithMySQL.writeTeams(nameTeam);

                workingWithMySQL.writeTeamsTypeteam(idTeam,typeTeam);
                workingWithMySQL.writeProjectTeams(projectID, idTeam);
                for (Integer integer : listIdStaffInTeam) {
                    workingWithMySQL.writeTeamStaff(idTeam, integer);
                }
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

    private Integer inputInt(ArrayList<Integer> idProjectManagerList,String messenger) {
        Integer returnInteger = null;

        boolean endWork = false;
        System.out.println(messenger);

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
        ArrayList<Integer> idList = new ArrayList<>();

        if (messenger.equals("Developers")) {
            idList = workingWithMySQL.showAllDevelopers();
        } else if (messenger.equals("Testers")) {
            idList = workingWithMySQL.showAllTesters();
        }

        System.out.println("Select a " + messenger + " in team.Input id");
        System.out.println("To exit click a");

        ArrayList<Integer> listIdStaffInTeam = new ArrayList<>();
        if(idList.size() != 0){
            while (!endWork) {
                if (Controller.getScanner().hasNextInt()) {
                    int help = Controller.getScanner().nextInt();
                    for (Integer id : idList) {
                        if (id == help) {
                            listIdStaffInTeam.add(help);
                            break;
                        }
                    }
                } else if((Controller.getScanner().hasNext("a"))&&
                        (listIdStaffInTeam.size() == 0)) {
                    countHelp++;
                    Controller.getScanner().nextLine();
                    if (countHelp == 1) {
                        Controller.getScanner().nextLine();
                        endWork = true;
                    } else {
                        countHelp = 0;
                    }
                } else if((Controller.getScanner().hasNext("a"))&&
                        (listIdStaffInTeam.size() != 0)){
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
            }
        }else{
            System.out.println("All " + messenger + " are busy.");
        }

        return listIdStaffInTeam;
    }

    public static WorkingWithMySQL getWorkingWithMySQL() {
        return workingWithMySQL;
    }

}