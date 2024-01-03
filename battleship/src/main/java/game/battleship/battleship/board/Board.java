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
    // all the coordinates where a ship is located -- boolean value indicates
    // whether the ship's been hit or not
    private static HashMap<Coord, Boolean> gameBoard = new HashMap<>();

    // getter and setter
    public static HashMap<Coord, Boolean> getBoard() {
        return gameBoard;
    }

    // methods

    // insert new ship into the board
    public static void insertShip(Ship ship) throws InvalidShipLocException, InvalidShipTypeException {
        List<Coord> locs = ship.getLoc();

        // ensure the ship type is not a duplicate
        if (ships.contains(ship.getClass().getSimpleName())) {
            throw new InvalidShipTypeException(ship.name + " is already there!");
        }

        for (Coord l : locs) {
            // ensure the ship does not occupy the place that's already occupied
            if (gameBoard.get(l) != null) {
                throw new InvalidShipLocException(
                        "Invalid ship location - " + locs + " - " + "location is already occupied");
            }
            gameBoard.put(l, false);
        }

        ships.add(ship.getClass().getSimpleName());
    }
}
