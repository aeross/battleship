package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;

public abstract class Ship {
    public final String name;
    protected List<Coord> loc;

    public Ship(String name) {
        this.name = name;
    }

    public List<Coord> getLoc() {
        return loc;
    }

    abstract public void setLoc(List<Coord> loc);
}
