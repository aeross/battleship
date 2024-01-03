package game.battleship.battleship.ship;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/ship", method = RequestMethod.GET)
public class ShipController {
    @GetMapping
    public List<Ship> getShips() {
        return List.of(
                new Battleship("Jackie"),
                new Battleship("Bob"));
    }
}
