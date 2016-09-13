package ua.com.study.student.artemenko.controller;

import ua.com.study.student.artemenko.consoleInterface.InterfaceScreen;

import java.util.*;

public class UserScreen {

    private String nameScreenProject = "PROJECT";
    private String nameScreenCreate = "CREATE PROJECT";
    private String nameScreenShow = "SHOW PROJECTS";
    private String nameScreenChange = "CHANGE PROJECT";
    private String nameScreenDelete = "DELETE PROJECT";
    private String nameScreenEditProject = "EDIT PROJECT";
    private String nameScreenEditTeam = "EDIT TEAM";
    private String nameScreenAddTeam = "ADD TEAM";
    private String nameScreenSelectProject = "SELECT ANOTHER PROJECT";

    private String pathToTheFileWithName_Project = "resources\\Project.txt";
    private String pathToTheFileWithName_Create = "resources\\Create.txt";
    private String pathToTheFileWithName_Show = "resources\\Show.txt";
    private String pathToTheFileWithName_Change = "resources\\Change.txt";
    private String pathToTheFileWithName_Delete = "resources\\Delete.txt";
    private String pathToTheFileWithName_EditProject = "resources\\EditProject.txt";
    private String pathToTheFileWithName_EditTeam = "resources\\EditTeam.txt";
    private String pathToTheFileWithName_AddTeam = "resources\\AddTeam.txt";
    private String pathToTheFileWithName_selectProject = "resources\\selectProject.txt";
    private static Map<String, InterfaceScreen> interfaceScreenMap;
    private static InterfaceScreen activeScreen;
    private static String nameScreenActive;


    public UserScreen() {
        setListScreen();
        setRelations();
    }

    private void setListScreen() {
        interfaceScreenMap = new HashMap<>();
        interfaceScreenMap.put(nameScreenProject, new InterfaceScreen(pathToTheFileWithName_Project));
        interfaceScreenMap.put(nameScreenCreate, new InterfaceScreen(pathToTheFileWithName_Create));
        interfaceScreenMap.put(nameScreenShow, new InterfaceScreen(pathToTheFileWithName_Show));
        interfaceScreenMap.put(nameScreenChange, new InterfaceScreen(pathToTheFileWithName_Change));
        interfaceScreenMap.put(nameScreenDelete, new InterfaceScreen(pathToTheFileWithName_Delete));
        interfaceScreenMap.put(nameScreenEditProject, new InterfaceScreen(pathToTheFileWithName_EditProject));
        interfaceScreenMap.put(nameScreenEditTeam, new InterfaceScreen(pathToTheFileWithName_EditTeam));
        interfaceScreenMap.put(nameScreenAddTeam, new InterfaceScreen(pathToTheFileWithName_AddTeam));
        interfaceScreenMap.put(nameScreenSelectProject, new InterfaceScreen(pathToTheFileWithName_selectProject));
    }

    private void setRelations() {

        for (Map.Entry<String, InterfaceScreen> sc : interfaceScreenMap.entrySet()) {
            for (Map.Entry<Integer, String> setMap : sc.getValue().getMapListScreenView().entrySet()) {
                for (Map.Entry<String, InterfaceScreen> scHelp : interfaceScreenMap.entrySet()) {
                    if ((setMap.getValue().equals(scHelp.getValue().getHeadScreen())) && (!setMap.getValue().equals("EXIT"))) {
                        sc.getValue().getMapListInterfaceScreen().put(setMap.getKey(), scHelp.getValue());
                        scHelp.getValue().getMapListInterfaceScreen().put(scHelp.getValue().getCountItemInList(), sc.getValue());

                    }
                }
            }
        }

    }


    public static void setInterfaceScreen(String nameScreen) {
        activeScreen = interfaceScreenMap.get(nameScreen);
        nameScreenActive = nameScreen;
        activeScreen.showInterfaceScreen();
    }

    public Map<String, InterfaceScreen> getInterfaceScreenMap() {
        return interfaceScreenMap;
    }

    public void setInterfaceScreenMap(Map<String, InterfaceScreen> interfaceScreenMap) {
        this.interfaceScreenMap = interfaceScreenMap;
    }

    public InterfaceScreen getActiveScreen() {
        return activeScreen;
    }

    public void setActiveScreen(InterfaceScreen activeScreen) {
        this.activeScreen = activeScreen;
    }

    public String getNameScreenActive() {
        return nameScreenActive;
    }

    public void setNameScreenActive(String nameScreenActive) {
        this.nameScreenActive = nameScreenActive;
    }
}
