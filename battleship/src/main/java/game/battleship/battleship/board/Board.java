package game.battleship.battleship.board;

import java.util.ArrayList;
import java.util.List;

import game.battleship.battleship.exceptions.GameNotStartedException;
import game.battleship.battleship.exceptions.InvalidFireException;
import game.battleship.battleship.exceptions.InvalidFireLocException;
import game.battleship.battleship.exceptions.InvalidShipException;
import game.battleship.battleship.exceptions.InvalidShipLocException;
import game.battleship.battleship.exceptions.InvalidShipTypeException;
import game.battleship.battleship.ship.Ship;

public class Board {
    public static final int SIZE = 10;
    private static Loc[] gameBoard = new Loc[SIZE * SIZE];
    // stores all the ships that are already on the board
    private static List<String> ships = new ArrayList<>();
    // number of moves made by the player
    private static int moves = 0;
    // number of hits to the ship made by the player
    private static int hits = 0;
    // NOTE: to start the game, make sure you start it first by running restart().
    private static boolean started = false;
    // NOTE: to be able to fire, finish placing all the ships into the battlefield.
    // after you've done so, hit confirmPlacement() to start firing.
    private static boolean allShipsPlaced = false;

    // getter and setter
    public static Loc[] getGameBoard() {
        return gameBoard;
    }

    public static int getMoves() {
        return moves;
    }

    public static int getHits() {
        return hits;
    }

    public static boolean getStarted() {
        return started;
    }

    public static boolean getAllShipsPlaced() {
        return allShipsPlaced;
    }

    // methods
    private static void verifyStart() throws GameNotStartedException {
        if (!started)
            throw new GameNotStartedException();
    }

    private static void checkWin() {
        for (int i = 0; i < gameBoard.length; i++) {
            LocStatus locStatus = gameBoard[i].getLocStatus();
            if (locStatus.equals(LocStatus.OCCUPIED)) {
                return;
            }
        }
        started = false;
        allShipsPlaced = false;
    }

    // initialise or reset game
    public static void restart() {
        started = true;

        // reset game board
        int i = 0;
        for (Coord coord : Coord.values()) {
            gameBoard[i] = new Loc(coord);
            i++;
        }

        // reset move counter
        moves = 0;
        hits = 0;

        // reset ships
        ships = new ArrayList<>();
    }

    // insert new ship into the board
    public static void insertShip(Ship ship)
            throws InvalidShipLocException, InvalidShipTypeException, GameNotStartedException, InvalidShipException {
        verifyStart();
        if (allShipsPlaced)
            throw new InvalidShipException("you can't place any more ships");

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

    // confirm all ship placements
    public static void confirmPlacement() throws InvalidShipException {
        if (ships.size() == 0) {
            throw new InvalidShipException("you must place at least one ship");
        }
        allShipsPlaced = true;
    }

    // fire at a coord location
    public static void fire(Coord coord)
            throws InvalidFireLocException, GameNotStartedException, InvalidFireException {
        verifyStart();
        if (!allShipsPlaced)
            throw new InvalidFireException();

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].getCoord().equals(coord)) {
                LocStatus locStatus = gameBoard[i].getLocStatus();

                if (locStatus.equals(LocStatus.EMPTY)) {
                    gameBoard[i].setLocStatus(LocStatus.MISS);
                } else if (locStatus.equals(LocStatus.OCCUPIED)) {
                    gameBoard[i].setLocStatus(LocStatus.HIT);
                    hits++;
                } else {
                    throw new InvalidFireLocException(
                            "Invalid fire location - " + coord + " - location is already fired");
                }
                break;
            }
        }
        moves++;
        checkWin();
    }
}
