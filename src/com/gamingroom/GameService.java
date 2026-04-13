package com.gamingroom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameService {
    private static final List<Game> games = new ArrayList<>();
    private static long nextGameId = 1L;
    private static long nextPlayerId = 1L;
    private static long nextTeamId = 1L;
    private static GameService service;

    private GameService() {
    }

    public static GameService getInstance() {
        if (service == null) {
            service = new GameService();
        }
        return service;
    }

    public Game addGame(String name) {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            Game game = iterator.next();
            if (game.getName().equalsIgnoreCase(name)) {
                return game;
            }
        }

        Game game = new Game(nextGameId++, name);
        games.add(game);
        return game;
    }

    public Game getGame(long id) {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            Game game = iterator.next();
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    public Game getGame(String name) {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            Game game = iterator.next();
            if (game.getName().equalsIgnoreCase(name)) {
                return game;
            }
        }
        return null;
    }

    public int getGameCount() {
        return games.size();
    }

    public long getNextPlayerId() {
        return nextPlayerId++;
    }

    public long getNextTeamId() {
        return nextTeamId++;
    }
}