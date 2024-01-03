package game.battleship.battleship.exceptions;

public class InvalidShipTypeException extends Exception {
    public InvalidShipTypeException() {
        super("Invalid ship type");
    }

    public InvalidShipTypeException(String message) {
        super(message);
    }
}
