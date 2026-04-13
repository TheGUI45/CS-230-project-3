package com.gamingroom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends Entity {
    private final List<Team> teams = new ArrayList<>();

    public Game(long id, String name) {
        super(id, name);
    }

    public Team addTeam(String name) {
        Iterator<Team> iterator = teams.iterator();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }

        Team team = new Team(GameService.getInstance().getNextTeamId(), name);
        teams.add(team);
        return team;
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public String toString() {
        return String.format("%s, teams=%s", super.toString(), teams);
    }
}