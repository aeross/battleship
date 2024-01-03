package game.battleship.battleship.ship;

import java.util.List;

import game.battleship.battleship.board.Coord;

public class Battleship extends Ship {
    public Battleship(String name) {
        super(name);
    }

    @Override
    public void setLoc(List<Coord> loc) {
        System.out.println(loc.size());
        this.loc = loc;
    }
}