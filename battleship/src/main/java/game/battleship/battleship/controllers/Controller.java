package game.battleship.battleship.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import game.battleship.battleship.board.Board;
import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;
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
@RestController
public class Controller {
    @GetMapping
    @RequestMapping(path = "api/start", method = RequestMethod.GET)
    public ResponseEntity<?> start() {
        Board.restart();
        return ResponseEntity.ok().body(Board.getGameBoard());
    }

    @GetMapping
    @RequestMapping(path = "api/board", method = RequestMethod.GET)
    public ResponseEntity<?> getBoard() {
        return ResponseEntity.ok().body(Board.getGameBoard());
    }

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
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InvalidShipLocException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InvalidShipTypeException e) {
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