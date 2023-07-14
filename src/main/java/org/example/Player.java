package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    private String name;
    private String piece;
    private int money;
    private ArrayList<Data.Space> properties;
    private int playerNumber;
    private boolean inJail;
    private boolean isBankrupt;


    public Player(String name, String piece, int money, ArrayList<Data.Space> properties, int playerNumber, int currentSquare, boolean inJail, boolean isBankrupt) {
        this.name = name;
        this.piece = piece;
        this.money = money;
        this.properties = properties;
        this.playerNumber = playerNumber;
        this.currentSquare = currentSquare;
        this.inJail= inJail;
        this.isBankrupt= isBankrupt;
    }

    public void sortPropertiesByPriceDescending() {
        Collections.sort(properties, new Comparator<Data.Space>() {
            @Override
            public int compare(Data.Space space1, Data.Space space2) {
                // Compare space prices in descending order
                return Integer.compare(space2.getPrice(), space1.getPrice());
            }
        });
    }

    public void sortPropertiesByHotel() {
        Collections.sort(properties, new Comparator<Data.Space>() {
            @Override
            public int compare(Data.Space space1, Data.Space space2) {

                // Compare space prices in descending order
                return Integer.compare(space2.getHotel(), space1.getHotel());
            }
        });
    }
    public void sortPropertiesByHouse() {
        Collections.sort(properties, new Comparator<Data.Space>() {
            @Override
            public int compare(Data.Space space1, Data.Space space2) {

                // Compare space prices in descending order
                return Integer.compare(space2.getHouses(), space1.getHouses());
            }
        });
    }
    public void countHousesPerProperty() {
        for (Data.Space property : properties) {
            int houseCount = 0;
            if (property instanceof Data.Space) {
                Data.Space propertySpace = (Data.Space) property;
                houseCount = propertySpace.getHouses();
            }
            System.out.println(property.getName() + ", Houses: " + houseCount);
        }
    }
    public void countHotelsPerProperty() {
        for (Data.Space property : properties) {
            int hotelCount = 0;
            if (property instanceof Data.Space) {
                Data.Space propertySpace = (Data.Space) property;
                hotelCount = propertySpace.getHotel();
            }
            System.out.println(property.getName() + ", Houses: " + hotelCount);
        }
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
    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
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
