package ua.com.study.student.artemenko.controller;


import ua.com.study.student.artemenko.consoleInterface.InputScreen;

import java.util.*;

public class Controller {

    private static Scanner scanner = new Scanner(System.in);
    private UserScreen user;
    private String nameBeginScreen = "PROJECT";

    public void startProgram() {
        try {
            user = new UserScreen();
            inputData();
        } finally {
            scanner.close();
            InputScreen.getWorkingWithMySQL().closeResources();
        }
    }

    private void inputData() {
        int countHelp = 0;
        boolean endWork = false;

        while (!endWork) {
            if (countHelp == 0) {
                UserScreen.setInterfaceScreen(nameBeginScreen);
            }
            if (scanner.hasNextInt()) {
                int inputNumber = scanner.nextInt();
                if ((inputNumber > 0) && (inputNumber <= user.getActiveScreen().getCountItemInList())) {
                    if ((user.getActiveScreen().getHeadScreen().equals(nameBeginScreen)) &&
                            (inputNumber == user.getActiveScreen().getCountItemInList())) {
                        endWork = true;
                    } else {
                        UserScreen.setInterfaceScreen(user.getActiveScreen().getMapListInterfaceScreen().get(inputNumber).getHeadScreen());
                        countHelp++;
                    }
                }
            } else {
                countHelp++;
                scanner.nextLine();
                if (countHelp == 1) {
                    //controller.showString(messageErrorChoice);
                } else {
                    countHelp = 0;
                }
            }
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }


}
