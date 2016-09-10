package ua.com.study.student.artemenko.controller;

import java.util.*;

public class Controller{

    private static Scanner scanner = new Scanner(System.in);
    private UserScreen user;

    public void startProgramm(){
        try{
            user = new UserScreen();
            inputData();
        }finally {
            scanner.close();
        }

    }

    private void inputData(){
        int countHelp = 0;
        boolean endWork = false;

        while (!endWork) {
            if (countHelp == 0) {
                user.setInterfaceScreen("PROJECT");
            }
            if (scanner.hasNextInt()) {
                int inputNumber = scanner.nextInt();
                if ((inputNumber > 0) && (inputNumber <= user.getActiveScreen().getCountItemInList())) {
                    user.setInterfaceScreen(user.getActiveScreen().getMapListScreenView().get(inputNumber));
                    countHelp++;
                    //endWork = interfaceScreen.isEndWork();
                } else {
                    //controller.showString(messageErrorChoice);
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

    public Scanner getScanner() {
        return scanner;
    }


}
