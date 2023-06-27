package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Narrator {
    private static Scanner scanner;
    private static ArrayList<Player> players;

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public Narrator() {
        scanner = new Scanner(System.in);
    }

    public void introduceGame() {
        System.out.println("Welcome to Monopoly!");
        System.out.println("I am Mr. Monopoly, and I will guide you through the game.");
        System.out.println("Let's get started!");
        int numPlayers = askNumPlayers();
        System.out.println("Great! We will have " + numPlayers + " players in the game.");
        System.out.println("What are everybody's names?");
        askPlayerNames(numPlayers);

    }


    private int askNumPlayers() {
        int numPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("How many players will be playing? ");
            String input = scanner.nextLine();

            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers >= 2 && numPlayers <= 8) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter a number between 2 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        return numPlayers;
    }

    public static void askPlayerNames(int numPlayers) {
        players = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player : ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName, null, 2000, new ArrayList<>(), 0, 0, false ));
        }

    }


}
