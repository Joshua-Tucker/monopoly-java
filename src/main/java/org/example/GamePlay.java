package org.example;

import org.example.Game;
import org.example.Narrator;

import java.util.ArrayList;

public class GamePlay {
    public static void main(String[] args) {

        Game game = new Game();
        GameMechanics gameM = new GameMechanics();
        Narrator narrator = new Narrator();

        game.startGame();
        gameM.setGameOver(false);
        narrator.introduceGame();
        ArrayList<Player> playerNames = Narrator.getPlayers();
        gameM.determinePlayerOrder(playerNames);
        gameM.printPlayerOrder();
        while (!gameM.isGameOver()) {
            gameM.playerTurn();
        }


    }
}
