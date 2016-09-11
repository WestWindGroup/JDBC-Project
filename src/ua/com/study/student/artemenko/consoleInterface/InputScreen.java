package ua.com.study.student.artemenko.consoleInterface;

import ua.com.study.student.artemenko.controller.Controller;

public class InputScreen {

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
        System.out.println("Select a project manager");

        while (!endWork) {
            nameProject = Controller.getScanner().nextLine();
            if ((nameProject != null)&&(nameProject != "")) {
                endWork = true;
            }
        }
    }
}

//InputScreen
//        Enter the name of the project
//        Select a project manager
//        Select the employees in a group of developers
//        select the employees in a group of testers
