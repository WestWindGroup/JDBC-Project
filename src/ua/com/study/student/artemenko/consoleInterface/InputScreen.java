package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controller.UserScreen;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;

import java.sql.SQLException;
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
                InputScreenShowProject();
                InputScreenDeleteProject();
                break;
            case ("SHOW PROJECTS"):
                InputScreenShowProject();
                break;
            case ("SELECT ANOTHER PROJECT"):
                InputScreenSelectProject();
                break;

            case ("CHANGE NAME PROJECT"):
                InputScreenShowProject();
                InputNewNameProject();
                break;
            case ("CHANGE DESCRIPTION"):
                InputScreenShowProject();
                InputNewDescriptionProject();
                break;
            case ("CHANGE PROJECT MANAGER"):
                InputScreenShowProject();
                InputScreenNewProjectManager();
                break;
            case ("CHANGE PROJECT"):
//                changeNumberProject = InputScreenShowProject();
                break;
            case ("DELETE TEAM"):
                InputScreenShowProject();
                InputScreenDeleteTeam();
                break;
            case ("ADD TEAM"):
                InputScreenShowProject();
                InputScreenAddTeam();
                break;
            case ("EDIT TEAM"):
                InputScreenEditTeam();
                break;
            case ("DELETE A MEMBER OF THE TEAM"):
                InputScreenShowProject();
                InputScreenShowTeam();
                InputScreenDeleteMemberTeam();
                break;
            case ("ADD A MEMBER OF THE TEAM"):
                InputScreenShowProject();
                InputScreenShowTeam();
                InputScreenAddMemberTeam();
                break;


        }
    }

    private void InputScreenDeleteProject() {

        workingWithMySQL.changeDeleteProject(changeNumberProject);
    }

    private void InputScreenAddMemberTeam() {
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
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        Integer projectManagerId = inputInt(idProjectManagerList, "Select the project manager.Input id");
        workingWithMySQL.changeProjectManager(changeNumberProject, projectManagerId);
    }

    private void InputNewDescriptionProject() {
        String newDescriptionProject = inputString("Enter the description of the project");
        workingWithMySQL.changeDescriptionProject(changeNumberProject, newDescriptionProject);
    }

    private void InputNewNameProject() {
        String newNameProject = inputString("Enter the name of the project");
        workingWithMySQL.changeNameProject(changeNumberProject, newNameProject);
    }

    private void InputScreenSelectProject() {
        UserScreen.setInterfaceScreen("SHOW PROJECTS");
    }

    private void InputScreenShowProject() {
        ArrayList<Integer> listIdProject = workingWithMySQL.showProjects();
        if ((listIdProject.size() != 0) && (InterfaceScreen.isShowInputScreen())) {
            changeNumberProject = inputInt(listIdProject, "Select the project.Input id");
            workingWithMySQL.showProject(changeNumberProject);
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
            testNameProject = workingWithMySQL.testNameProject(nameProject);
            if (!testNameProject) {
                System.out.println("Project with the same name exists");
            }
        }

        String descriptionProject = inputString("Enter the description of the project");
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        Integer projectManagerId = inputInt(idProjectManagerList, "Select the project manager.Input id");

        int projectID = workingWithMySQL.writeProject(nameProject, descriptionProject, projectManagerId);

        createMapTeam(projectID, "Developers");
        createMapTeam(projectID, "Testers");

        System.out.println("Project create");
    }

    private void createMapTeam(int projectID, String messenger) {
        int typeTeam = workingWithMySQL.returnTypeTeam(messenger);
        boolean endWork = false;
        while (!endWork) {
            ArrayList<Integer> listIdStaffInTeam = inputTeam(messenger);
            if (listIdStaffInTeam.size() != 0) {
                String nameTeam = inputString("Input name team");
                int idTeam = 0;
                try{
                    idTeam = workingWithMySQL.writeTeams(nameTeam);
                    workingWithMySQL.writeTeamsTypeteam(idTeam, typeTeam);
                    workingWithMySQL.writeProjectTeams(projectID, idTeam);
                    for (Integer integer : listIdStaffInTeam) {
                        workingWithMySQL.writeTeamStaff(idTeam, integer);
                    }
                    System.out.println("Create next team for project?");
                    System.out.println("Input yes/no");
                    String help = Controller.getScanner().nextLine();
                    if (help.equals("no")) {
                        endWork = true;
                    }
                }catch (SQLException e){
                    System.out.println("Incorrect name");
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

    private Integer inputInt(ArrayList<Integer> helpList, String messenger) {
        Integer returnInteger = null;

        boolean endWork = false;
        System.out.println(messenger);

        while (!endWork) {
            int help = Controller.getScanner().nextInt();
            for (Integer id : helpList) {
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