package game.battleship.battleship.board;

import java.util.HashMap;

import game.battleship.battleship.ship.Ship;

public class Board {
    public static final int SIZE = 10;
    private HashMap<Coord, Ship> gameBoard;

    // constructor
    public Board() {
        this.gameBoard = new HashMap<>();
    }

    // getter and setter
    public HashMap<Coord, Ship> getBoard() {
        return gameBoard;
    }

    public void insertShip(Coord loc, Ship ship) {
        gameBoard.put(loc, ship);
    }
}
