package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputScreen {

    private static WorkingWithMySQL workingWithMySQL = new WorkingWithMySQL();
    private static int countDevTeam = 1;
    private static int countTestTeam = 1;

    public void choiceInputScreen(String nameScreen) {
        switch (nameScreen){
            case ("CREATE PROJECT"):
                InputScreenCreateProject();
                break;

        }
    }

    private void InputScreenCreateProject() {

        String nameProject = "";
        String descriptionProject = "";
        Integer projectManagerId = null;
        Map<Integer,ArrayList<Integer>> mapTeamDeveloperInProject = new HashMap<>();
        Map<Integer,ArrayList<Integer>> mapTeamTestersInProject = new HashMap<>();

        boolean endWork = false;
        System.out.println("Enter the name of the project");
        while (!endWork) {
            nameProject = Controller.getScanner().nextLine();
            if ((nameProject != null)&&(!nameProject.equals(""))) {
                endWork = true;
            }
        }

        endWork = false;
        System.out.println("Enter description of the project");
        while (!endWork) {
            descriptionProject = Controller.getScanner().nextLine();
            if ((descriptionProject != null)&&(!descriptionProject.equals(""))) {
                endWork = true;
            }
        }

        endWork = false;
        System.out.println("Select a project manager.Input id");
        ArrayList<Integer> idProjectManagerList = workingWithMySQL.showAllProjectManager();
        while (!endWork) {
            int help = Controller.getScanner().nextInt();
            for (Integer id:idProjectManagerList) {
                if(id==help){
                    projectManagerId = help;
                    endWork = true;
                    break;
                }
            }
            if(projectManagerId == null){
                System.out.println("All project managers are busy.");
                return;
            }
        }

        boolean endWorkLoop = false;

        endWork = false;
        System.out.println("Select a developers in team.Input id");
        System.out.println("To exit click A");
        ArrayList<Integer> idDeveloperList = workingWithMySQL.showAllDevelopers();
        ArrayList<Integer> idDeveloperInTeam = new ArrayList<>();
        while (!endWork) {
            if(Controller.getScanner().hasNextInt()){
                int help = Controller.getScanner().nextInt();
                for (Integer id : idDeveloperList) {
                    if (id == help) {
                        idDeveloperInTeam.add(help);
                        break;
                    }
                }
            }else {
                mapTeamDeveloperInProject.put(countDevTeam,idDeveloperInTeam);
                idDeveloperInTeam = new ArrayList<>();
                countDevTeam++;
                System.out.println("Team D is create");
                System.out.println("Create next team for project?");
                System.out.println("Input yes/no");
                String help = Controller.getScanner().nextLine();
                if(help.equals("no")){
                    endWork = true;
                }
                //Controller.getScanner().nextLine();
            }
            if(idDeveloperList == null){
                System.out.println("All developers are busy.");
                return;
            }
        }

        endWork = false;
        System.out.println("Select a testers in team.Input id");
        System.out.println("To exit click A");
        ArrayList<Integer> idTestersList = workingWithMySQL.showAllDevelopers();
        ArrayList<Integer> idTestersInTeam = new ArrayList<>();
        while (!endWork) {
            if(Controller.getScanner().hasNextInt()){
                int help = Controller.getScanner().nextInt();
                for (Integer id : idTestersList) {
                    if (id == help) {
                        idTestersInTeam.add(help);
                        break;
                    }
                }
            }else {
                mapTeamTestersInProject.put(countTestTeam,idTestersInTeam);
                idTestersInTeam = new ArrayList<>();
                countTestTeam++;
                System.out.println("Team T is create");
                System.out.println("Create next team for project?");
                System.out.println("Input Yes/No");
                String help = Controller.getScanner().nextLine();
                if(help.equals("No")){
                    endWork = true;
                }
            }
            if(idTestersInTeam == null){
                System.out.println("All testers are busy.");
                return;
            }
        }
    }
}

//InputScreen
//        Enter the name of the project
//        Select a project manager
//        Select the employees in a group of developers
//        select the employees in a group of testers
