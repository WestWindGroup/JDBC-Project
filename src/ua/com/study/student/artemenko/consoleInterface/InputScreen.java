package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controller.UserScreen;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;

import java.util.ArrayList;

public class InputScreen {

    private static WorkingWithMySQL workingWithMySQL = new WorkingWithMySQL();
    private static int changeNumberProject = 0;
    private static int changeNumberTeam = 0;

    public void choiceInputScreen(String nameScreen) {
        switch (nameScreen) {
            case ("CREATE PROJECT"):
                InputScreenCreateProject();
                break;
            case ("DELETE PROJECT"):
                InputScreenDeleteProject();
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
            case ("DELETE TEAM"):
                InputScreenDeleteTeam();
                break;
            case ("ADD TEAM"):
                InputScreenAddTeam();
                break;
            case ("EDIT TEAM"):
                InputScreenEditTeam();
                break;
            case ("DELETE A MEMBER OF THE TEAM"):
                InputScreenDeleteMemberTeam();
                break;
            case ("ADD A MEMBER OF THE TEAM"):
                InputScreenAddMemberTeam();
                break;
        }
    }

    private void InputScreenDeleteProject() {
        InputScreenShowProject();
        workingWithMySQL.changeDeleteProject(changeNumberProject);
    }

    private void InputScreenAddMemberTeam() {
        InputScreenShowProject();
        InputScreenShowTeam();
        boolean endWork = false;
        ArrayList<Integer> idMemberList = workingWithMySQL.requestIdMember(changeNumberTeam);
        while (!endWork) {
            int selectMember = inputInt("Select the Id of the member.\n" +
                                            "If the input ID is not in the list, then the output");
            for (Integer id : idMemberList) {
                if (id == selectMember) {
                    workingWithMySQL.addMemberTeam(changeNumberTeam,selectMember);
                    endWork = true;
                    break;
                }
            }
            UserScreen.setInterfaceScreen("CHANGE TEAMS");
        }
    }

    private void InputScreenDeleteMemberTeam() {
        InputScreenShowProject();
        InputScreenShowTeam();
        boolean endWork = false;
        ArrayList<Integer> idMemberList = workingWithMySQL.requestIdMemberTeam(changeNumberTeam);
        while (!endWork) {
            int selectMemberTeam = inputInt("Select the Id of the member in team.\n" +
                    "If the input ID is not in the list, then the output");
            for (Integer id : idMemberList) {
                if (id == selectMemberTeam) {
                    workingWithMySQL.deleteMemberTeam(selectMemberTeam);
                    endWork = true;
                    break;
                }
            }
            UserScreen.setInterfaceScreen("CHANGE TEAMS");
        }
    }

    private void InputScreenAddTeam() {
        InputScreenShowProject();
        boolean endWork = false;

        while (!endWork) {
            int selectTypeTeam = inputInt("Select the type of team. 1 developers , 2 testers .");
            if (selectTypeTeam == 1) {
                createMapTeam(changeNumberProject, "Developers");
                endWork = true;
            } else if (selectTypeTeam == 2) {
                createMapTeam(changeNumberProject, "Testers");
                endWork = true;
            } else {
                System.out.println("Incorrect input");
            }
        }

    }

    private void InputScreenEditTeam() {
        boolean showTeam = false;
        ArrayList<Integer> idTeamList = workingWithMySQL.requestAllTeam(changeNumberProject,showTeam);
        showTeam = true;
        if(idTeamList.size() != 0){
            String messenger = "Select the team.Input id.\nIf the input ID is not in the list, then the output";
            changeNumberTeam = inputInt(messenger);
            for (Integer id : idTeamList) {
                if (id == changeNumberTeam) {
                    workingWithMySQL.requestTeam(changeNumberTeam,showTeam);
                    break;
                }
            }
        }
    }

    private void InputScreenDeleteTeam() {
        InputScreenShowProject();
        boolean showTeam = true;
        ArrayList<Integer> idTeamList = workingWithMySQL.requestAllTeam(changeNumberProject,showTeam);
        if(idTeamList.size() != 0){
            String messenger = "Select the team.Input id.\nIf the input ID is not in the list, then the output";
            Integer deleteTeamId = inputInt(messenger);
            for (Integer id : idTeamList) {
                if (id == deleteTeamId) {
                    workingWithMySQL.changeDeleteTeam(deleteTeamId);
                    System.out.println("Team deleted");
                    break;
                }
            }
            UserScreen.setInterfaceScreen("CHANGE TEAMS");
        }
    }

    private void InputScreenNewProjectManager() {
        InputScreenShowProject();
        Integer idPM = idProjectManager();
        if(idPM != null){
            workingWithMySQL.changeProjectManager(changeNumberProject, idPM);
        }
    }

    private Integer idProjectManager() {
        Integer projectManagerId = null;
        boolean controlId = true;
        boolean endWork = false;
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        if (idProjectManagerList.size() != 0) {
            while (!endWork) {
                projectManagerId = inputInt("Select the project manager.Input id");
                for (Integer id : idProjectManagerList) {
                    if (id == projectManagerId) {
                        endWork = true;
                        controlId = false;
                        break;
                    }
                }
                if(controlId){
                    System.out.println("Incorrect input");
                }
            }
        }else{
            System.out.println("All project managers are busy.");
            return null;
        }
        return projectManagerId;
    }

    private void InputNewDescriptionProject() {
        InputScreenShowProject();
        String newDescriptionProject = inputString("Enter the description of the project");
        workingWithMySQL.changeDescriptionProject(changeNumberProject, newDescriptionProject);
    }

    private void InputNewNameProject() {
        InputScreenShowProject();
        String newNameProject = inputString("Enter the name of the project");
        workingWithMySQL.changeNameProject(changeNumberProject, newNameProject);
    }

    private void InputScreenSelectProject() {
        UserScreen.setInterfaceScreen("SHOW PROJECTS");
    }

    private void InputScreenShowProject() {
        boolean endWork = false;
        boolean controlInput = false;
        ArrayList<Integer> listIdProject = workingWithMySQL.requestProjects();
        if (listIdProject.size() != 0) {
            while (!endWork) {
                changeNumberProject = inputInt("Select the project.Input id");
                try {
                    workingWithMySQL.requestProject(changeNumberProject);
                    controlInput = true;
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
                if(controlInput) {
                    endWork = true;
                }
            }
        }
    }

    private void InputScreenShowTeam() {
        boolean showTeams = false;
        ArrayList<Integer> listIdTeam = workingWithMySQL.requestAllTeam(changeNumberProject,showTeams);
        if (listIdTeam.size() != 0) {
            int help = inputInt("Select the Team.Input id");
            for (Integer id : listIdTeam) {
                if (id == help) {
                    changeNumberTeam = help;
                    break;
                }
            }
        }
    }

    private void InputScreenCreateProject() {
        boolean testNameProject = false;
        String nameProject = null;
        while (!testNameProject) {
            nameProject = inputString("Enter the name of the project");
            testNameProject = workingWithMySQL.testNamePr(nameProject);
            if (!testNameProject) {
                System.out.println("Project with the same name exists");
            }
        }
        String descriptionProject = inputString("Enter the description of the project");
        Integer projectManagerId = idProjectManager();
        int projectID = workingWithMySQL.writeProject(nameProject, descriptionProject, projectManagerId);

        createMapTeam(projectID, "Developers");
        createMapTeam(projectID, "Testers");

        System.out.println("Project create");
    }

    private void createMapTeam(int projectID, String messenger) {
        int typeTeam = workingWithMySQL.returnTypeTeam(messenger);
        boolean endWork = false;
        boolean controlName = true;
        while (!endWork) {
            ArrayList<Integer> listIdStaffInTeam = inputTeam(messenger);
            if (listIdStaffInTeam.size() != 0) {
                String nameTeam = inputString("Input name team");
                ArrayList<String> listNameTeams = workingWithMySQL.listNameTeams();
                for (String str:listNameTeams) {
                    if(str.equals(nameTeam)){
                        System.out.println("Incorrect name");
                        controlName = false;
                    }
                    else{
                        controlName = true;
                        break;
                    }
                }
                if(controlName){
                    int idTeam = workingWithMySQL.writeTeams(nameTeam);
                    workingWithMySQL.writeTeamsTypeteam(idTeam, typeTeam);
                    workingWithMySQL.writeProjectTeams(projectID, idTeam);
                    for (Integer integer : listIdStaffInTeam) {
                        workingWithMySQL.writeTeamStaff(idTeam, integer);
                    }
                    System.out.println("Create next team for project?");
                    boolean endWorkNext = false;
                    while (!endWorkNext){
                        System.out.println("Input y/n");
                        if (Controller.getScanner().hasNext("n")) {
                            Controller.getScanner().nextLine();
                            endWork = true;
                            endWorkNext = true;
                        }
                        else if (Controller.getScanner().hasNext("y")){
                            Controller.getScanner().nextLine();
                            endWorkNext = true;
                        }
                    }
                }
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

    private int inputInt(String messenger) {
        int returnInt = 0;

        boolean endWork = false;
        System.out.println(messenger);

        while (!endWork) {
            returnInt = Controller.getScanner().nextInt();
            endWork = true;
        }
        return returnInt;
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
        if (idList.size() != 0) {
            while (!endWork) {
                if (Controller.getScanner().hasNextInt()) {
                    int help = Controller.getScanner().nextInt();
                    for (Integer id : idList) {
                        if (id == help) {
                            listIdStaffInTeam.add(help);
                            break;
                        }
                    }
                } else if ((Controller.getScanner().hasNext("a")) &&
                        (listIdStaffInTeam.size() == 0)) {
                    countHelp++;
                    Controller.getScanner().nextLine();
                    if (countHelp == 1) {
                        Controller.getScanner().nextLine();
                        endWork = true;
                    } else {
                        countHelp = 0;
                    }
                } else if ((Controller.getScanner().hasNext("a")) &&
                        (listIdStaffInTeam.size() != 0)) {
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
        } else {
            System.out.println("All " + messenger + " are busy.");
        }

        return listIdStaffInTeam;
    }

    public static WorkingWithMySQL getWorkingWithMySQL() {
        return workingWithMySQL;
    }

}