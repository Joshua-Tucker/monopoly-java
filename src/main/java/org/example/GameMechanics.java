package org.example;

import java.util.*;

public class GameMechanics {
    private int totalSpaces;
    private int diceOne;
    private int diceTwo;
    private ArrayList<Player> players;
    private ArrayList<Data.Space> spaces;
    private Player currentPlayer;
    private int bank;

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameOver;

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

    public boolean diceRoll(Player currentPlayer) {
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
                moveToSquare(currentPlayer);
                interactWithSquare(currentPlayer);
                return diceRoll(currentPlayer);
            }
        } else {
            return true;
        }
    }

    public void moveToSquare(Player currentPlayer) {
        int currentSquare = currentPlayer.getCurrentSquare();
        int newSquare = currentSquare + totalSpaces;

        if (newSquare > 39) {
            int pastGo = newSquare - 40;
            currentPlayer.setCurrentSquare(pastGo);
            setTotalSpaces(0);
            System.out.println("You are now on " + spaceFirst(getSpace(currentPlayer.getCurrentSquare())));
        } else {
            currentPlayer.setCurrentSquare(newSquare);
            setTotalSpaces(0);
            System.out.println("You are now on " + spaceFirst(getSpace(currentPlayer.getCurrentSquare())));
            System.out.println("-------");

        }
    }

    public void interactWithSquare(Player currentPlayer) {
        Data.Space space = getSpace(currentPlayer.getCurrentSquare());
        Scanner scanner = new Scanner(System.in);

        if (space.type.contains("Property")) {
            if (space.isAvailable()) {
                System.out.println("This property is available to buy for £" + space.getPrice() + ". Would you like to purchase the property?");
                System.out.println("1 = Yes! 2 = No:(");
                int ans = Integer.parseInt(scanner.nextLine());

                if (ans == 1) {
                    int oldMoney = currentPlayer.getMoney();
                    int newMoney = oldMoney - space.getPrice();
                    currentPlayer.setMoney(newMoney);
                    currentPlayer.addProperties(space);
                    space.setAvailable(false);
                    System.out.println("You now have £" + newMoney + " in your bank, and " + space.getName() + " in your portfolio!");
                } else {
                    System.out.println("You've chosen not to buy this property:(");
                    System.out.println("-------");
                }
            } else {
                Player rentPlayer = null;

                for (Player player : players) {
                    if (player.getProperties().contains(space)) {
                        rentPlayer = player;
                        break; // Exit the loop once rentPlayer is found
                    }
                }

                if (rentPlayer != currentPlayer) {
                    payRent(currentPlayer, rentPlayer);
                }
            }
        } else if (space.type.contains("Special")) {
            // Handle special space interaction here
        }
    }

    public void payRent(Player currentPlayer, Player rentPlayer) {
        Data.Space space = getSpace(currentPlayer.getCurrentSquare());
        int[] rent = space.getRent();
        int rentOwed = 0;
        int hotel = space.getHotel();
        int house = space.getHouses();
        int oldMoneyCurrent = currentPlayer.getMoney();
        int oldMoneyRent = rentPlayer.getMoney();

        if (hotel > 0) {
            rentOwed = rent[rent.length - 1];
        } else {
            rentOwed = rent[house];
        }

        currentPlayer.setMoney(oldMoneyCurrent - rentOwed);
        rentPlayer.setMoney(oldMoneyRent + rentOwed);
        System.out.println(currentPlayer.getName() + " has paid £" + rentOwed + " to " + rentPlayer.getName());
        System.out.println(currentPlayer.getName() + " has £" + currentPlayer.getMoney());
        System.out.println(rentPlayer.getName() + " has £" + rentPlayer.getMoney());
        System.out.println("-------");

    }

    public void bankrupt(Player currentPlayer, Player rentPlayer){
        Scanner scanner = new Scanner(System.in);
        if(currentPlayer.getMoney()<= 0){
            currentPlayer.setBankrupt(true);
            if(currentPlayer.getProperties().size()>=1){
                System.out.println("Oh no, you're bankrupt! You have assets that you can sell to " + rentPlayer + ". Would you like to sell or resign?");
                System.out.println("1) Sell my assets   2) I give up!");
                String answer = scanner.nextLine();
                if(answer == String.valueOf(1)){
                    System.out.println("You currently are in debt and have this amount to pay " + currentPlayer.getMoney());
                    //how much do i need to pay
                    System.out.println("You have these assets that you can sell");
                  ArrayList<Data.Space> properties = currentPlayer.getProperties();
                    for (Data.Space property: properties) {
                       if (property.getHotel()==1){

                       }

                    }



                    // properties that i have available to sell
                    //list with those with hosues first
                    //then the highest price that was paid
                    //sell price is 10% less for house and initial cost
                    // show price after each sell
                    //once money is above 0 say that itas fine and end turn

                }

            }


        }
        //boolean is player bankrupt
        //offers player a chance to sell houses/hotels=>property
        //sell properties for 10% less than what bought them for
        //if still not enough players property goes to rentplayer
    }

    public void movePastGo(Player currentPlayer) {
        //how do i determine that the players continue going around the board
        //if past go collect 200
        //if land on go collect 400
    }

    public void tax(Player currentPlayer) {
//if index is ... pay rent to bank x amount else pay this amount
    }

    public void chance(Player currentPlayer) {
//deck needs to be shuffled first on start up
        //picks top card then moves to back of deck
        //need action associated with each chance card
    }

    public void communityChest(Player currentPlayer) {
//deck needs to be shuffled first on start up
        //picks top card then moves to back of deck
        //need action associated with each chance card
    }

    public void goToJail(Player currentPlayer) {
        //boolean changes to true
        //currentsquare changes to jail
        //can only get out if roll a double or get out of jail free or after 3 go's
        //does not collect rent

    }

    public void freeParking(Player currentPlayer) {
        //all tax goes into this pot
        //if land on money gets added to your account
        //freeparking ersets to 0

    }


    public void endTurn() {
        // Options to trade
        // Options to see bank
        // Options to see properties
        // End turn and pass onto the next player
    }


    private void setCurrentPlayer(Player currentPlayer) {
        System.out.println("It's " + currentPlayer.getName() + "'s turn!");
        System.out.println("You are currently on " + spaceFirst(getSpace(currentPlayer.getCurrentSquare())));
        diceRoll(currentPlayer);
        moveToSquare(currentPlayer);
        interactWithSquare(currentPlayer);
    }

    public Data.Space getSpace(int currentSquare) {
        Data.GameData gameData = Data.getGameData();
        Data.Space[] spaces = gameData.spaces;

        for (Data.Space space : spaces) {
            if (currentSquare == space.index) {
                return space;
            }
        }
        return null;
    }

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
            System.out.println("1 = Yes please!  2 = No, I'm done for now.");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 2) {
                isFinished = true;
            }
        }
    }

    public void playerTurn() {
        for (Player currentPlayer : players) {
            setCurrentPlayer(currentPlayer);
        }
    }

    public ArrayList<Data.Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Data.Space> spaces) {
        this.spaces = spaces;
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
