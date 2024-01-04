package game.battleship.battleship.board;

public class Loc {
    private final Coord coord;
    private LocStatus locStatus;

    public Loc(Coord coord) {
        this.coord = coord;
        this.locStatus = LocStatus.EMPTY;
    }

    public Loc(Coord coord, LocStatus locStatus) {
        this.coord = coord;
        this.locStatus = locStatus;
    }

    public Coord getCoord() {
        return coord;
    }

    public LocStatus getLocStatus() {
        return locStatus;
    }

    public void setLocStatus(LocStatus locStatus) {
        this.locStatus = locStatus;
    }
}
