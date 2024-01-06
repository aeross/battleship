package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * SUBMARINE - SIZE 3
 */
public class Submarine extends Ship {
    public static final int SIZE = 3;

    public Submarine() {
        super("Submarine");
    }

    public Submarine(String name) {
        super(name);
    }

    @Override
    public void setLoc(List<Coord> loc) throws InvalidShipLocException {
        super.setLoc(loc, SIZE);
    }
}