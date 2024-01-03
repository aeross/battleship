package game.battleship.battleship.board;

import java.util.HashMap;
import java.util.List;

import game.battleship.battleship.ship.Ship;

public class Board {
    public static final int SIZE = 10;
    // all the coordinates where a ship is located -- boolean value indicates
    // whether the ship's been hit or not
    private HashMap<Coord, Boolean> gameBoard;

    // constructor
    public Board() {
        this.gameBoard = new HashMap<>();
    }

    // getter and setter
    public HashMap<Coord, Boolean> getBoard() {
        return gameBoard;
    }

    public void insertShip(Ship ship) {
        List<Coord> locs = ship.getLoc();
        for (Coord l : locs) {
            gameBoard.put(l, false);
        }
    }
}
