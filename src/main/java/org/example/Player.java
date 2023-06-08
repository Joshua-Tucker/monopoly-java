package org.example;

import java.util.ArrayList;

public class Player {
    private String name;
    private String piece;
    private int money;
    private ArrayList<String> properties;
    private int playerNumber;

    public Player(String name, String piece, int money, ArrayList<String> properties, int playerNumber, int currentSquare) {
        this.name = name;
        this.piece = piece;
        this.money = money;
        this.properties = properties;
        this.playerNumber = playerNumber;
        this.currentSquare = currentSquare;
    }

    public int getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(int currentSquare) {
        this.currentSquare = currentSquare;
    }

    private int currentSquare;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }
}
