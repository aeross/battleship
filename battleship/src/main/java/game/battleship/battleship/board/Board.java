package game.battleship.battleship.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.battleship.battleship.exceptions.InvalidShipLocException;
import game.battleship.battleship.exceptions.InvalidShipTypeException;
import game.battleship.battleship.ship.Ship;

public class Board {
    public static final int SIZE = 10;
    // stores all the ships that are already on the board
    private static List<String> ships = new ArrayList<>();
    // all the coordinates where a ship is located
    // boolean value indicates whether the ship's been hit or not
    private static HashMap<Coord, Boolean> shipCoords = new HashMap<>();
    // all the coordinates where a ship is not located
    // boolean value indicates whether that location's been fired or not
    private static HashMap<Coord, Boolean> nonShipCoords = new HashMap<>();

    private static Loc[] gameBoard = new Loc[SIZE * SIZE];
    // number of moves made by the player
    private static int moves = 0;

    // getter and setter
    public static HashMap<Coord, Boolean> getShipcoords() {
        return shipCoords;
    }

    public static HashMap<Coord, Boolean> getNonShipcoords() {
        return nonShipCoords;
    }

    public static Loc[] getGameBoard() {
        return gameBoard;
    }

    public static int getMoves() {
        return moves;
    }

    // methods
    // initialise or reset game
    public static void restart() {
        // reset game board
        int i = 0;
        for (Coord coord : Coord.values()) {
            gameBoard[i] = new Loc(coord);
            i++;
        }

        // reset move counter
        moves = 0;
    }

    // update location status on game board
    private static void updateBoard(Coord coord, LocStatus newStatus) {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].getCoord().equals(coord)) {
                gameBoard[i].setLocStatus(newStatus);
                break;
            }
        }
    }

    // insert new ship into the board
    public static void insertShip(Ship ship) throws InvalidShipLocException, InvalidShipTypeException {
        List<Coord> coords = ship.getLoc();

        // ensure the ship type is not a duplicate
        if (ships.contains(ship.getClass().getSimpleName())) {
            throw new InvalidShipTypeException(ship.name + " is already there!");
        }

        for (Coord l : coords) {
            // ensure the ship does not occupy the place that's already occupied
            if (shipCoords.get(l) != null) {
                throw new InvalidShipLocException(
                        "Invalid ship location - " + coords + " - " + "location is already occupied");
            }
            shipCoords.put(l, false);

            // all good -- now update loc status on the game board
            updateBoard(l, LocStatus.OCCUPIED);
        }

        ships.add(ship.getClass().getSimpleName());
    }

    // fire at a coord location
    public static void fire(Coord coord) {
        System.out.println(coord);
    }
}
