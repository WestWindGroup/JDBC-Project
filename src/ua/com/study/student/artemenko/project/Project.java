package ua.com.study.student.artemenko.project;

import java.util.Map;

public class Project {

    private String nameProject;
    private Staff projectManager;
    private Map<Integer,DevelopersTeam> developerTeamMap;
    private Map<Integer,TestersTeam> testersTeamMap;

    public Project(String nameProject, Staff projectManager, Map<Integer, DevelopersTeam> developerTeamMap, Map<Integer, TestersTeam> testersTeamMap) {
        this.nameProject = nameProject;
        this.projectManager = projectManager;
        this.developerTeamMap = developerTeamMap;
        this.testersTeamMap = testersTeamMap;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Staff getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Staff projectManager) {
        this.projectManager = projectManager;
    }

    public Map<Integer, DevelopersTeam> getDeveloperTeamMap() {
        return developerTeamMap;
    }

    public void setDeveloperTeamMap(Map<Integer, DevelopersTeam> developerTeamMap) {
        this.developerTeamMap = developerTeamMap;
    }

    public Map<Integer, TestersTeam> getTestersTeamMap() {
        return testersTeamMap;
    }

    public void setTestersTeamMap(Map<Integer, TestersTeam> testersTeamMap) {
        this.testersTeamMap = testersTeamMap;
    }
}
