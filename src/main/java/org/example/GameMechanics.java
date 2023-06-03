package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class GameMechanics {
    private ArrayList<Player> players;

    public void determinePlayerOrder(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
        Collections.shuffle(this.players);
        setPlayerNumbers();
    }

    private void setPlayerNumbers() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPlayerNumber(i + 1);
        }
    }

    public void printPlayerOrder() {
        for (Player player : players) {
            System.out.println("Player " + player.getPlayerNumber() + ": " + player.getName());
        }
    }
}
