package game.battleship.battleship.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import game.battleship.battleship.board.Board;
import game.battleship.battleship.board.Coord;
import game.battleship.battleship.exceptions.InvalidShipLocException;
import game.battleship.battleship.ship.Battleship;
import game.battleship.battleship.ship.Ship;

@RestController
public class ShipController {
    @GetMapping
    @RequestMapping(path = "api/ship", method = RequestMethod.GET)
    public List<Ship> getShips() {
        return List.of(
                new Battleship("Jackie"),
                new Battleship("Bob"));
    }

    @PostMapping
    @RequestMapping(path = "api/ship", method = RequestMethod.POST)
    public ResponseEntity<?> setShip(@RequestBody List<String> loc) {
        // validate input (string is valid, when converted to enum doesn't cause an
        // exception)
        List<Coord> locCoord = new ArrayList<>();
        for (String l : loc) {
            try {
                locCoord.add(Coord.valueOf(l));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        // set ship to a certain location on the board
        Ship newShip = new Battleship("GSX-RR");

        try {
            newShip.setLoc(locCoord);
            // Board.insertShip(newShip);
        } catch (InvalidShipLocException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok().body(List.of(newShip));
    }
}
