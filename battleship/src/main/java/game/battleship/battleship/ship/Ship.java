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
        int checkVertical = Integer.parseInt(loc.get(0).toString().substring(1));

        for (Coord l : loc) {
            // skip the first element because it's extracted already (above)
            if (l.equals(loc.get(0)))
                continue;

            char currHorizontal = l.toString().charAt(0);
            int currVertical = Integer.parseInt(l.toString().substring(1));

            // Horizontal check
            if (currHorizontal == checkHorizontal) {
                if (currVertical != checkVertical + 1) {
                    return false;
                }
                checkVertical++;
            }

            // Vertical check
            else if (currVertical == checkVertical) {
                if (currHorizontal != checkHorizontal + 1) {
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

    protected void setLoc(List<Coord> loc, int size) throws InvalidShipLocException {
        // size 4
        if (loc.size() != size) {
            throw new InvalidShipLocException("Invalid ship location - " + loc);
        }

        // check if valid
        boolean isValidLoc = this.validateLoc(loc);
        if (!isValidLoc) {
            throw new InvalidShipLocException("Invalid ship location - " + loc);
        }

        this.loc = loc;

    }

    // abstract methods
    abstract public void setLoc(List<Coord> loc) throws InvalidShipLocException;
}
