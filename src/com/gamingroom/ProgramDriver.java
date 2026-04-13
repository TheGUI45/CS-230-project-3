package com.gamingroom;

public class ProgramDriver {

    public static void main(String[] args) {
        SingletonTester tester = new SingletonTester();
        tester.testSingleton();

        GameService service = GameService.getInstance();

        Game game = service.addGame("Draw It or Lose It");
        Game sameGame = service.addGame("Draw It or Lose It");

        Team redTeam = game.addTeam("Red Rockets");
        Team blueTeam = game.addTeam("Blue Blazers");
        Team sameRedTeam = game.addTeam("Red Rockets");

        Player alex = redTeam.addPlayer("Alex");
        Player jamie = redTeam.addPlayer("Jamie");
        Player sameAlex = redTeam.addPlayer("Alex");
        blueTeam.addPlayer("Taylor");

        System.out.println();
        System.out.println("Game count: " + service.getGameCount());
        System.out.println("Duplicate game returns same instance: " + (game == sameGame));
        System.out.println("Duplicate team returns same instance: " + (redTeam == sameRedTeam));
        System.out.println("Duplicate player returns same instance: " + (alex == sameAlex));
        System.out.println("Named player created: " + jamie);
        System.out.println();
        System.out.println(game);
    }
}