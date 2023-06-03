package org.example;

import org.example.DataFetcher;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static GameData gameData;

    public static GameData getGameData() {
        if (gameData == null) {
            gameData = DataFetcher.fetchData();
        }
        return gameData;
    }

    static class GameData {
        Space[] spaces;
        Cards cards;
        List<GamePiece> gamePieces;

        public GameData() {
            gamePieces = getAllGamePieces();
        }
    }

    static class Space {
        int index;
        String name;
        String type;
        int price;
        int[] rent;
        String color;
        int houseCost;
        int hotelCost;
        int houses;
        int hotel;
    }

    static class Cards {
        String[] chanceOptions;
        String[] communityChest;
    }

    public static List<GamePiece> getAllGamePieces() {
        List<GamePiece> gamePieces = new ArrayList<>();
        gamePieces.add(new GamePiece("Car"));
        gamePieces.add(new GamePiece("Hat"));
        gamePieces.add(new GamePiece("Shoe"));
        gamePieces.add(new GamePiece("Dog"));
        gamePieces.add(new GamePiece("Cat"));
        gamePieces.add(new GamePiece("Thimble"));
        gamePieces.add(new GamePiece("Battleship"));
        gamePieces.add(new GamePiece("Wheelbarrow"));

        return gamePieces;
    }
}

class GamePiece {
    private String name;

    public GamePiece(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
