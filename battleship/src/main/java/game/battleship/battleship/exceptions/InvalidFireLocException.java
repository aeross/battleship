package game.battleship.battleship.exceptions;

public class InvalidFireLocException extends BadRequestException {
    public InvalidFireLocException() {
        super("Invalid fire location");
    }

    public InvalidFireLocException(String message) {
        super(message);
    }
}
