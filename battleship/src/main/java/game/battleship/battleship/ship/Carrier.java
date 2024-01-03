package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * CARRIER - SIZE 5
 */
public class Carrier extends Ship {
    public static final int SIZE = 5;

    public Carrier(String name) {
        super(name);
    }

    @Override
    public void setLoc(List<Coord> loc) throws InvalidShipLocException {
        if (loc.size() != SIZE) {
            throw new InvalidShipLocException();
        }

        // check if valid
        boolean isValidLoc = this.validateLoc(loc);
        System.out.println(isValidLoc);

        this.loc = loc;
    }
}