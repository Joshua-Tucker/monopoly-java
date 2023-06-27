package org.example;

import java.util.ArrayList;

public class Player {
    private String name;
    private String piece;
    private int money;
    private ArrayList<Data.Space> properties;
    private int playerNumber;
    private final boolean inJail;

    public Player(String name, String piece, int money, ArrayList<Data.Space> properties, int playerNumber, int currentSquare, boolean inJail) {
        this.name = name;
        this.piece = piece;
        this.money = money;
        this.properties = properties;
        this.playerNumber = playerNumber;
        this.currentSquare = currentSquare;
        this.inJail= inJail;
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

    public ArrayList<Data.Space> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Data.Space> properties) {
        this.properties = properties;
    }

    public void addProperties (Data.Space space){
        properties.add(space);
    }
}
