package game.battleship.battleship.exceptions;

public class GameNotStartedException extends BadRequestException {
    public GameNotStartedException() {
        super("ERROR: game is not started. Run `api/start` to start or restart the game.");
    }
}
