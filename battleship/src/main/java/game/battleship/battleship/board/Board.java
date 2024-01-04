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
    private static HashMap<Coord, Boolean> shipLocs = new HashMap<>();
    // all the coordinates where a ship is not located
    // boolean value indicates whether that location's been fired or not
    private static HashMap<Coord, Boolean> nonShipLocs = new HashMap<>();

    private static Loc[][] gameBoard = new Loc[SIZE][SIZE];
    // number of moves made by the player
    private static int moves = 0;

    // getter and setter
    public static HashMap<Coord, Boolean> getShipLocs() {
        return shipLocs;
    }

    public static HashMap<Coord, Boolean> getNonShipLocs() {
        return nonShipLocs;
    }

    public static Loc[][] getGameBoard() {
        return gameBoard;
    }

    public static int getMoves() {
        return moves;
    }

    // methods
    // initialise or reset game
    public static void restart() {
        // reset game board
        int j = 0;

        for (Coord coord : Coord.values()) {
            int i = coord.toString().charAt(0) - 'A';
            gameBoard[i][j] = new Loc(coord);

            if (j + 1 >= SIZE) {
                j = 0;
            } else {
                j++;
            }
        }

        // reset move counter
        moves = 0;
    }

    // visualise board
    public static String visualiseBoard() {
        System.out.println(shipLocs);
        // for (int i = 0; i < Board.SIZE; i++) {
        // for (int j = 0; j < Board.SIZE; j++) {

        // }
        // }

        return "";
    }

    // insert new ship into the board
    public static void insertShip(Ship ship) throws InvalidShipLocException, InvalidShipTypeException {
        List<Coord> locs = ship.getLoc();

        // ensure the ship type is not a duplicate
        if (ships.contains(ship.getClass().getSimpleName())) {
            throw new InvalidShipTypeException(ship.name + " is already there!");
        }

        for (Coord l : locs) {
            // ensure the ship does not occupy the place that's already occupied
            if (shipLocs.get(l) != null) {
                throw new InvalidShipLocException(
                        "Invalid ship location - " + locs + " - " + "location is already occupied");
            }
            shipLocs.put(l, false);
        }

        ships.add(ship.getClass().getSimpleName());
    }

    // fire at a coord location
    public static void fire(Coord loc) {
        Boolean hitShip = shipLocs.get(loc);
        Boolean missShip = nonShipLocs.get(loc);

        if (hitShip) {

        }
    }
}
