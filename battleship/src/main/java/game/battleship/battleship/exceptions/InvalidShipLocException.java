package game.battleship.battleship.exceptions;

public class InvalidShipLocException extends BadRequestException {
    public InvalidShipLocException() {
        super("Invalid ship location");
    }

    public InvalidShipLocException(String message) {
        super(message);
    }
}
