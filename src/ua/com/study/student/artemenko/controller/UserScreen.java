package ua.com.study.student.artemenko.controller;

import ua.com.study.student.artemenko.consoleInterface.InterfaceScreen;

import java.util.*;

public class UserScreen {

    private String nameScreenProject = "PROJECT";
    private String nameScreenCreate = "CREATE PROJECT";
    private String nameScreenShow = "SHOW PROJECTS";
    private String nameScreenChange = "CHANGE PROJECT";
    private String nameScreenDelete = "DELETE PROJECT";
    private String nameScreenEditTeam = "EDIT TEAM";
    private String nameScreenAddTeam = "ADD TEAM";
    private String nameScreenSelectProject = "SELECT ANOTHER PROJECT";
    private String nameScreenChangeNamePr = "CHANGE NAME PROJECT";
    private String nameScreenChangeDiscr = "CHANGE DESCRIPTION";
    private String nameScreenChangePM = "CHANGE PROJECT MANAGER";
    private String nameScreenChangeTeams = "CHANGE TEAMS";
    private String nameScreenDeleteTeams = "DELETE TEAM";
    private String nameScreenDeleteMemberTeam = "DELETE A MEMBER OF THE TEAM";
    private String nameScreenAddMemberTeam = "ADD A MEMBER OF THE TEAM";
    private String nameScreenHomePage = "HOME PAGE";
    private String nameScreenPersonnelData = "PERSONNEL DATA";


    private String pathToTheFileWithName_Project = "resources\\Screen\\Project.txt";
    private String pathToTheFileWithName_Create = "resources\\Screen\\Create.txt";
    private String pathToTheFileWithName_Show = "resources\\Screen\\Show.txt";
    private String pathToTheFileWithName_Change = "resources\\Screen\\Change.txt";
    private String pathToTheFileWithName_Delete = "resources\\Screen\\Delete.txt";
    private String pathToTheFileWithName_EditTeam = "resources\\Screen\\EditTeam.txt";
    private String pathToTheFileWithName_AddTeam = "resources\\Screen\\AddTeam.txt";
    private String pathToTheFileWithName_selectProject = "resources\\Screen\\selectProject.txt";
    private String pathToTheFileWithName_ChangeName = "resources\\Screen\\ChangeName.txt";
    private String pathToTheFileWithName_ChangeDiscr = "resources\\Screen\\ChangeDiscr.txt";
    private String pathToTheFileWithName_ChangePM = "resources\\Screen\\ChangePM.txt";
    private String pathToTheFileWithName_ChangeTeam = "resources\\Screen\\ChangeTeam.txt";
    private String pathToTheFileWithName_DeleteTeam = "resources\\Screen\\DeleteTeam.txt";
    private String pathToTheFileWithName_DeleteMemberTeam = "resources\\Screen\\DeleteMemberTeam.txt";
    private String pathToTheFileWithName_AddMemberTeam = "resources\\Screen\\AddMemberTeam.txt";
    private String pathToTheFileWithName_HomePage = "resources\\Screen\\HomePage.txt";
    private String pathToTheFileWithName_PersonnelData = "resources\\Screen\\PersonnelData.txt";

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
        interfaceScreenMap.put(nameScreenEditTeam, new InterfaceScreen(pathToTheFileWithName_EditTeam));
        interfaceScreenMap.put(nameScreenAddTeam, new InterfaceScreen(pathToTheFileWithName_AddTeam));
        interfaceScreenMap.put(nameScreenSelectProject, new InterfaceScreen(pathToTheFileWithName_selectProject));
        interfaceScreenMap.put(nameScreenChangeNamePr, new InterfaceScreen(pathToTheFileWithName_ChangeName));
        interfaceScreenMap.put(nameScreenChangeDiscr, new InterfaceScreen(pathToTheFileWithName_ChangeDiscr));
        interfaceScreenMap.put(nameScreenChangePM, new InterfaceScreen(pathToTheFileWithName_ChangePM));
        interfaceScreenMap.put(nameScreenChangeTeams, new InterfaceScreen(pathToTheFileWithName_ChangeTeam));
        interfaceScreenMap.put(nameScreenDeleteTeams, new InterfaceScreen(pathToTheFileWithName_DeleteTeam));
        interfaceScreenMap.put(nameScreenDeleteMemberTeam, new InterfaceScreen(pathToTheFileWithName_DeleteMemberTeam));
        interfaceScreenMap.put(nameScreenAddMemberTeam, new InterfaceScreen(pathToTheFileWithName_AddMemberTeam));
        interfaceScreenMap.put(nameScreenHomePage, new InterfaceScreen(pathToTheFileWithName_HomePage));
        interfaceScreenMap.put(nameScreenPersonnelData, new InterfaceScreen(pathToTheFileWithName_PersonnelData));
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
