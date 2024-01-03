package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;

public abstract class Ship {
    public final String name;
    protected List<Coord> loc;

    // constructor
    public Ship(String name) {
        this.name = name;
    }

    // getter
    public List<Coord> getLoc() {
        return loc;
    }

    // methods
    protected boolean validateLoc(List<Coord> loc) {
        // this method will check whether the ship is placed correctly
        // i.e., horizontal or vertical

        char checkHorizontal = loc.get(0).toString().charAt(0);
        char checkVertical = loc.get(0).toString().charAt(1);

        for (Coord l : loc) {
            // skip the first element because it's extracted already (above)
            if (l.equals(loc.get(0)))
                continue;

            String locStr = l.toString();

            // Horizontal check
            if ((locStr.charAt(0) == checkHorizontal)) {
                if (locStr.charAt(1) != checkVertical + 1) {
                    return false;
                }
                checkVertical++;
            }

            // Vertical check
            else if ((locStr.charAt(1) == checkVertical)) {
                if (locStr.charAt(0) != checkHorizontal + 1) {
                    return false;
                }
                checkHorizontal++;
            }

            else {
                return false;
            }
        }

        return true;
    }

    // abstract methods
    abstract public void setLoc(List<Coord> loc) throws InvalidShipLocException;
}
