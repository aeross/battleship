package game.battleship.battleship.exceptions;

public class InvalidFireException extends BadRequestException {
    public InvalidFireException() {
        super("Invalid fire - finish placing all your ships before firing");
    }
}
