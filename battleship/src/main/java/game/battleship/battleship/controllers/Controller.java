package game.battleship.battleship.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import game.battleship.battleship.board.Board;
import game.battleship.battleship.board.Coord;
import game.battleship.battleship.board.Loc;
import game.battleship.battleship.exceptions.BadRequestException;
import game.battleship.battleship.exceptions.InvalidShipTypeException;
import game.battleship.battleship.ship.Battleship;
import game.battleship.battleship.ship.Carrier;
import game.battleship.battleship.ship.Cruiser;
import game.battleship.battleship.ship.Destroyer;
import game.battleship.battleship.ship.Ship;
import game.battleship.battleship.ship.Submarine;

/**
 * The controller. Two endpoints here:
 * GET api/board -> get current board
 * POST api/ship -> place a new ship into the battlefield
 */
@CrossOrigin(origins = { "http://localhost:5173" })
@RestController
public class Controller {
    /**
     * Get the current battlefield condition.
     */
    @GetMapping
    @RequestMapping(path = "api/board", method = RequestMethod.GET)
    public ResponseEntity<?> getBoard() {
        return ResponseEntity.ok().body(Board.getGameBoard());
    }

    /**
     * Get the total number of moves (or fires) to the ship a player makes.
     */
    @GetMapping
    @RequestMapping(path = "api/moves", method = RequestMethod.GET)
    public ResponseEntity<?> getMoves() {
        return ResponseEntity.ok().body(Board.getMoves());
    }

    /**
     * Get the total number of hits to the ship a player makes.
     */
    @GetMapping
    @RequestMapping(path = "api/hits", method = RequestMethod.GET)
    public ResponseEntity<?> getHits() {
        return ResponseEntity.ok().body(Board.getHits());
    }

    /**
     * Find out whether the game has been started.
     */
    @GetMapping
    @RequestMapping(path = "api/status/is-started", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusStarted() {
        return ResponseEntity.ok().body(Board.getStarted());
    }

    /**
     * Find out whether all ships have been placed.
     */
    @GetMapping
    @RequestMapping(path = "api/status/is-placed", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusPlaced() {
        return ResponseEntity.ok().body(Board.getAllShipsPlaced());
    }

    /**
     * Start the game.
     */
    @GetMapping
    @RequestMapping(path = "api/start", method = RequestMethod.POST)
    public ResponseEntity<?> start() {
        Board.restart();
        return ResponseEntity.ok().body(Board.getGameBoard());
    }

    /**
     * Confirm all ship placements, allowing a player to fire.
     */
    @GetMapping
    @RequestMapping(path = "api/confirm-placement", method = RequestMethod.POST)
    public ResponseEntity<?> confirmPlacement() {
        try {
            Board.confirmPlacement();
            return ResponseEntity.ok().body(Board.getGameBoard());
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Place a new ship into the battlefield.
     */
    @PostMapping
    @RequestMapping(path = "api/ship", method = RequestMethod.POST)
    public ResponseEntity<?> setShip(@RequestBody ShipInput input) {
        try {
            String type = input.getType();
            List<String> loc = input.getLoc();

            // validate type and instantiate new ship
            Ship newShip;
            switch (type) {
                case "Battleship":
                    newShip = new Battleship();
                    break;
                case "Carrier":
                    newShip = new Carrier();
                    break;
                case "Cruiser":
                    newShip = new Cruiser();
                    break;
                case "Destroyer":
                    newShip = new Destroyer();
                    break;
                case "Submarine":
                    newShip = new Submarine();
                    break;
                default:
                    throw new InvalidShipTypeException();
            }

            // validate loc (location string is valid, when converted
            // to enum doesn't cause an exception)
            List<Coord> locCoord = new ArrayList<>();
            for (String l : loc) {
                locCoord.add(Coord.valueOf(l));
            }

            // set ship to a certain location on the board
            newShip.setLoc(locCoord);
            Board.insertShip(newShip);

            return ResponseEntity.ok().body(newShip);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Allows player to choose a certain location to fire.
     */
    @GetMapping
    @RequestMapping(path = "api/fire", method = RequestMethod.POST)
    public ResponseEntity<?> fire(@RequestBody FireInput input) {
        Coord coord = Coord.valueOf(input.getCoord());
        try {
            Loc hitLoc = Board.fire(coord);
            return ResponseEntity.ok().body(hitLoc);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

/**
 * Input for POST api/ship request.
 */
class ShipInput {
    private String type;
    private List<String> loc;

    public List<String> getLoc() {
        return loc;
    }

    public String getType() {
        return type;
    }

    public void setLoc(List<String> loc) {
        this.loc = loc;
    }

    public void setType(String type) {
        this.type = type;
    }
}

/**
 * Input for POST api/fire request.
 */
class FireInput {
    private String coord;

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }
}