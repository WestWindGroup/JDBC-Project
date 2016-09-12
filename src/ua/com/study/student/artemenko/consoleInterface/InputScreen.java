package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;
import ua.com.study.student.artemenko.controllerJDBC.WorkingWithMySQL;
import ua.com.study.student.artemenko.project.Staff;

import java.util.ArrayList;

public class InputScreen {

    private static WorkingWithMySQL workingWithMySQL = new WorkingWithMySQL();

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
        ArrayList<Staff> projectManagerList = workingWithMySQL.showAllProjectManager();
        while (!endWork) {
            int help = Controller.getScanner().nextInt();
            for (Staff staf:projectManagerList) {
                if(staf.getPerson().getId_person()==help){
                    projectManagerId = help;
                    break;
                }
            }
            if(projectManagerId == null){
                System.out.println("All project managers are busy.");
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
