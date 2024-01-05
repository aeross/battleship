package game.battleship.battleship.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.battleship.battleship.exceptions.InvalidFireLocException;
import game.battleship.battleship.exceptions.InvalidShipLocException;
import game.battleship.battleship.exceptions.InvalidShipTypeException;
import game.battleship.battleship.ship.Ship;

public class Board {
    public static final int SIZE = 10;
    // stores all the ships that are already on the board
    private static List<String> ships = new ArrayList<>();
    // all the coordinates where a ship is not located
    // boolean value indicates whether that location's been fired or not
    private static Set<Coord> firedCoords = new HashSet<>();

    private static Loc[] gameBoard = new Loc[SIZE * SIZE];
    // number of moves made by the player
    private static int moves = 0;

    // getter and setter
    public static Set<Coord> getFiredCoords() {
        return firedCoords;
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

    // insert new ship into the board
    public static void insertShip(Ship ship) throws InvalidShipLocException, InvalidShipTypeException {
        List<Coord> coords = ship.getLoc();

        // ensure the ship type is not a duplicate
        if (ships.contains(ship.getClass().getSimpleName())) {
            throw new InvalidShipTypeException(ship.name + " is already there!");
        }

        for (Coord coord : coords) {
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i].getCoord().equals(coord)) {
                    // ensure the ship does not occupy the place that's already occupied
                    if (gameBoard[i].getLocStatus().equals(LocStatus.EMPTY)) {
                        gameBoard[i].setLocStatus(LocStatus.OCCUPIED);
                    } else {
                        throw new InvalidShipLocException(
                                "Invalid ship location - " + coords + " - " + "location is already occupied");
                    }
                    break;
                }
            }
        }
        ships.add(ship.getClass().getSimpleName());
    }

    // fire at a coord location
    public static void fire(Coord coord) throws InvalidFireLocException {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].getCoord().equals(coord)) {
                LocStatus locStatus = gameBoard[i].getLocStatus();

                if (locStatus.equals(LocStatus.EMPTY)) {
                    gameBoard[i].setLocStatus(LocStatus.MISS);
                } else if (locStatus.equals(LocStatus.OCCUPIED)) {
                    gameBoard[i].setLocStatus(LocStatus.HIT);
                } else {
                    throw new InvalidFireLocException(
                            "Invalid fire location - " + coord + " - location is already fired");
                }
                break;
            }
        }
        moves++;
    }
}
