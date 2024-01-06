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
        super.setLoc(loc, SIZE);
    }
}