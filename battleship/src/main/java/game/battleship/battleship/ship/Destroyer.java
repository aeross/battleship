package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

/**
 * DESTROYER - SIZE 2
 */
public class Destroyer extends Ship {
    public static final int SIZE = 2;

    public Destroyer() {
        super("Destroyer");
    }

    public Destroyer(String name) {
        super(name);
    }

    @Override
    public void setLoc(List<Coord> loc) throws InvalidShipLocException {
        super.setLoc(loc, SIZE);
    }
}