package org.example;

import org.example.Game;
import org.example.Narrator;

import java.util.ArrayList;

public class GamePlay {
    public static void main(String[] args) {
        // Create an instance of the Game class
        Game game = new Game();
        GameMechanics gameM = new GameMechanics();
        Narrator narrator = new Narrator();

        game.startGame();
        narrator.introduceGame();
        ArrayList<Player> playerNames = narrator.getPlayers();
        gameM.determinePlayerOrder(playerNames);
        gameM.printPlayerOrder();
    }
}
