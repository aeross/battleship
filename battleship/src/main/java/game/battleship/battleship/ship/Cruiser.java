package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * CRUISER - SIZE 3
 */
public class Cruiser extends Ship {
    public static final int SIZE = 3;

    public Cruiser(String name) {
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