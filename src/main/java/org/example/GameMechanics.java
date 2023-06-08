package org.example;

import java.util.*;


public class GameMechanics {
    private int totalSpaces;
    private int diceOne;
    private int diceTwo;
    private ArrayList<Player> players;
    private ArrayList<Data.Space> spaces;

    private Player currentPlayer;




    public void determinePlayerOrder(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
        Collections.shuffle(this.players);
        setPlayerNumbers();
        reorderPlayers();
    }

    private void reorderPlayers() {
        this.players.sort(Comparator.comparingInt(Player::getPlayerNumber));
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



    public boolean diceRoll() {
        Random random = new Random();
        this.diceOne = random.nextInt(6) + 1;
        this.diceTwo = random.nextInt(6) + 1;


        System.out.println("Dice 1: " + diceOne);
        System.out.println("Dice 2: " + diceTwo);

        int totalSpaces = diceOne + diceTwo;
        setTotalSpaces(totalSpaces);

        System.out.println("Total spaces: " + totalSpaces);

        boolean isDouble = diceOne == diceTwo;

        if (isDouble) {
            System.out.println("Congratulations! You've rolled a double!");

            int consecutiveDoubles = 1;

            if (consecutiveDoubles == 3) {
                System.out.println("You've rolled three consecutive doubles. Go to jail!");
                // Implement the logic for sending the player to jail
                return false; // End the turn without recursive roll
            } else {
                System.out.println("Roll again!");
                consecutiveDoubles++;
                return diceRoll(); // Recursive call to roll again
            }
        } else {
            return true; // End the turn without recursive roll
        }
    }




    public void moveToSquare(Player currentPlayer){
       int currentSquare = currentPlayer.getCurrentSquare();
       int newSquare = currentSquare + totalSpaces;
       currentPlayer.setCurrentSquare(newSquare);
       setTotalSpaces(0);

        //get THIS current player destination
        //move amount rolled on diceroll
        //reset totalspaces for next turn
        //sets new current player destination
    }

    public void interactWithSquare(){
        //gets player destination
        //if square is available=> ask to buy=> buy property and set values in player
        //=> else option for auction
        //if square is unavailable=> how much rent is due=> deduct from player bank
        // +add same amount to rented players bank

    }

    public void endTurn(){
        //options to trade
        //options to see bank
        //options to see properties
        //end turn and pass onto next
    }
    public void playerMove(){
        //get player 1
        for (int i = 0; i < players.size(); i++) {
            diceRoll();

        }
        //dice roll
        //tell player what square theyre on
        //interact with card
        //end turn
        //go to next player
    }

    public Data.Space getSpace(int currentSquare) {
        Data.GameData gameData = Data.getGameData();
        Data.Space[] spaces = gameData.spaces;

        for (Data.Space space : spaces) {
            if (currentSquare == space.index) {
                return space;

            }
            //getcurrentsquare value
            //iterate through object
            //whatever value currentsquare == to index in object
            //get object.name
        }
        return null;
    }

    //method to extract name for
    public String spaceFirst(Data.Space space) {
        String name = space.getName();
        return name;
    }

    public void squareInfoPlayer(Data.Space space) {
        String name = space.getName();
        String type = space.getType();
        String color = space.getColor();
        int[] rent = space.getRent();
        int index = space.getIndex();
        int houses = space.getHouses();
        int price = space.getPrice();
        int hotel = space.getHotel();
        int houseCost = space.getHouseCost();
        int hotelCost = space.getHotelCost();
        Scanner scanner = new Scanner(System.in);
        boolean isFinished = false;

        while (!isFinished) {
            System.out.println("What information would you like to see?");
            System.out.println("Press the number for the information.");
            System.out.println("1 = Name // 2 = Type // 3 = Color // 4 = Rent ");
            System.out.println("5 = Number of Houses // 6 = Number of Hotels");
            System.out.println("7 = Cost of Additional House // 8 = Cost of Additional Hotel");
            System.out.println("9 = Price ");
            String number = scanner.nextLine();

            switch (number) {
                case "1":
                    System.out.println("Name: " + name);
                    break;
                case "2":
                    System.out.println("Type: " + type);
                    break;
                case "3":
                    System.out.println("Color: " + color);
                    break;
                case "4":
                    System.out.println("Rent: " + Arrays.toString(rent));
                    break;
                case "5":
                    System.out.println("Number of Houses: " + houses);
                    break;
                case "6":
                    System.out.println("Number of Hotels: " + hotel);
                    break;
                case "7":
                    System.out.println("Cost of Additional House: " + houseCost);
                    break;
                case "8":
                    System.out.println("Cost of Additional Hotel: " + hotelCost);
                    break;
                case "9":
                    System.out.println("Price: " + price);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println("Would you like to see anything else?");
            System.out.println("1 = Yes please! // 2 = No, I'm done for now.");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 2) {
                isFinished = true;
            }
        }
    }


    public void playerTurn(){
    for (Player currentPlayer: players)
         { setCurrentPlayer(currentPlayer);
         }
}

    private void setCurrentPlayer(Player currentPlayer) {

        System.out.println("It's " + currentPlayer.getName() + "'s turn!");
        System.out.println("You are currently on " + spaceFirst(getSpace(currentPlayer.getCurrentSquare())));
        diceRoll();
        moveToSquare(currentPlayer);
        System.out.println("You are now on " + spaceFirst(getSpace(currentPlayer.getCurrentSquare())));

        // You can perform any additional actions specific to setting the current player
        // For example, displaying their properties, money, etc.
    }



    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public int getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public int getDiceOne() {
        return diceOne;
    }

    public void setDiceOne(int diceOne) {
        this.diceOne = diceOne;
    }

    public int getDiceTwo() {
        return diceTwo;
    }

    public void setDiceTwo(int diceTwo) {
        this.diceTwo = diceTwo;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


}
