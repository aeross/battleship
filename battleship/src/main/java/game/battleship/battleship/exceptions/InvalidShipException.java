package game.battleship.battleship.exceptions;

public class InvalidShipException extends BadRequestException {
    public InvalidShipException() {
        super("Invalid ship");
    }

    public InvalidShipException(String message) {
        super("Invalid ship - " + message);
    }
}
