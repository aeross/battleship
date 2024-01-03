package game.battleship.battleship.exceptions;

public class InvalidShipLocException extends Exception {
    public InvalidShipLocException() {
        super("Invalid ship location");
    }

    public InvalidShipLocException(String message) {
        super(message);
    }
}
