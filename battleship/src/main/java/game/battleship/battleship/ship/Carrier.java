package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * CARRIER - SIZE 5
 */
public class Carrier extends Ship {
    public static final int SIZE = 5;

    public Carrier() {
        super("Carrier");
    }

    public Carrier(String name) {
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