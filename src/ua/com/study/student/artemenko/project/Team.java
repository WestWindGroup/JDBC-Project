package ua.com.study.student.artemenko.project;

import java.util.Map;

public abstract class Team {
    private static int idTeam = 0;
    private Map<Integer, Staff> team;

    protected Team(Map<Integer, Staff> team) {
        this.team = team;
    }

    public Map<Integer, Staff> getTeam() {
        return team;
    }

    public void setTeam(Map<Integer, Staff> team) {
        this.team = team;
    }

    public static int getIdTeam() {
        return idTeam;
    }

    public static void setIdTeam(int idTeam) {
        Team.idTeam = idTeam;
    }
}
