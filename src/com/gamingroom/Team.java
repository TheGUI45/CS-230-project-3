package com.gamingroom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team extends Entity {
    private final List<Player> players = new ArrayList<>();

    public Team(long id, String name) {
        super(id, name);
    }

    public Player addPlayer(String name) {
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }

        Player player = new Player(GameService.getInstance().getNextPlayerId(), name);
        players.add(player);
        return player;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public String toString() {
        return String.format("%s, players=%s", super.toString(), players);
    }
}