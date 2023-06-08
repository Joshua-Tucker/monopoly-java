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

        public Space(int index, String name, String type, int price, int[] rent, String color, int houseCost, int hotelCost, int houses, int hotel) {
            this.index = index;
            this.name = name;
            this.type = type;
            this.price = price;
            this.rent = rent;
            this.color = color;
            this.houseCost = houseCost;
            this.hotelCost = hotelCost;
            this.houses = houses;
            this.hotel = hotel;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int[] getRent() {
            return rent;
        }

        public void setRent(int[] rent) {
            this.rent = rent;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getHouseCost() {
            return houseCost;
        }

        public void setHouseCost(int houseCost) {
            this.houseCost = houseCost;
        }

        public int getHotelCost() {
            return hotelCost;
        }

        public void setHotelCost(int hotelCost) {
            this.hotelCost = hotelCost;
        }

        public int getHouses() {
            return houses;
        }

        public void setHouses(int houses) {
            this.houses = houses;
        }

        public int getHotel() {
            return hotel;
        }

        public void setHotel(int hotel) {
            this.hotel = hotel;
        }
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
