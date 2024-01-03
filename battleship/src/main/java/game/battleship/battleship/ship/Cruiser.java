package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * CRUISER - SIZE 3
 */
public class Cruiser extends Ship {
    public static final int SIZE = 3;

    public Cruiser() {
        super("Cruiser");
    }

    public Cruiser(String name) {
        super(name);
    }

    @Override
    public void setLoc(List<Coord> loc) throws InvalidShipLocException {
        // size 4
        if (loc.size() != SIZE) {
            throw new InvalidShipLocException("Invalid ship location - " + loc);
        }

        // check if valid
        boolean isValidLoc = this.validateLoc(loc);
        if (!isValidLoc) {
            throw new InvalidShipLocException("Invalid ship location - " + loc);
        }

        this.loc = loc;
    }
}