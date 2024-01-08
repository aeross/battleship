class Controller {
    static async start(url) {
        const response = await fetch(`${url}/api/start`, {
            method: "POST"
        });
        return response;
    }

    static async placeShip(url, shipType, shipCoords) {
        const requestBody = JSON.stringify({ "type": shipType, "loc": shipCoords });

        const response = await fetch(`${url}/api/ship`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: requestBody
        });
        return response;
    }

    // generate all possible valid ship placements, assuming empty board
    static generateValidShipPlacements(size) {
        const output = [];
        
        // disgusting nested loops.
        let coordY = "A";
        for (let i = 0; i < 10; i++) {  // board size
            let coordX = 1;
            
            for (let j = 0; j <= 10 - size; j++) {
                const validMove = [];

                for (let k = 0; k < size; k++) {  // ship size
                    validMove.push(coordY + coordX);
                    coordX++;
                }
                coordX = coordX - size + 1;
                output.push(validMove);
            }
            coordY = String.fromCharCode(coordY.charCodeAt(0) + 1);
        }
        
        // do this one more time, but now for all vertical moves.
        let coordX = 1;
        for (let i = 0; i < 10; i++) {  // board size
            let coordY = "A";
            
            for (let j = 0; j <= 10 - size; j++) {
                const validMove = [];

                for (let k = 0; k < size; k++) {  // ship size
                    validMove.push(coordY + coordX);
                    coordY = String.fromCharCode(coordY.charCodeAt(0) + 1);
                }
                coordY = String.fromCharCode(coordY.charCodeAt(0) - size + 1);
                output.push(validMove);
            }
            coordX++;
        }

        return output;
    }

    static async placeAllShipsRandomly(url) {
        // generate all valid placements
        let allPlacements = Controller.generateValidShipPlacements(5);

        // pick one placement out of all valid placements
        let randomPlacementCarrier = allPlacements[Math.floor(Math.random() * allPlacements.length)];
        let placed = randomPlacementCarrier.slice();
        await Controller.placeShip(url, "Carrier", randomPlacementCarrier);

        let randomPlacements = [];
        [4, 3, 3, 2].forEach(elem => {
            // generate all valid placements (again, one for each ship)
            allPlacements = Controller.generateValidShipPlacements(elem);

            // remove occupied placement from the list of all valid placements
            for (let i = 0; i < placed.length; i++) {
                let deletedIndex = 0;
                for (let j = 0; j < allPlacements.length; j++) {
                    if (allPlacements[deletedIndex].includes(placed[i])) {
                        allPlacements.splice(deletedIndex, 1);
                    } else {
                        deletedIndex++;
                    }
                }
            }

            // pick one placement out of all valid placements
            let randomPlacement = allPlacements[Math.floor(Math.random() * allPlacements.length)];
            placed = placed.concat(randomPlacement);
            randomPlacements.push(randomPlacement);
        })

        await Controller.placeShip(url, "Battleship", randomPlacements[0]);
        await Controller.placeShip(url, "Cruiser", randomPlacements[1]);
        await Controller.placeShip(url, "Submarine", randomPlacements[2]);
        await Controller.placeShip(url, "Destroyer", randomPlacements[3]);
    }

    // get all ships currently on the battlefield (returns an array of strings of ship names)
    static async getShips(url) {
        const response = await fetch(`${url}/api/ships`);
        if (response.ok) {
            const ships = await response.json();
            const shipsOutput = {};
            for (let ship in ships) {
                // ship is sunk
                if (!Object.values(ships[ship]).includes(false)) {
                    shipsOutput[ship] = false;
                } 
                // ship is alive
                else {
                    shipsOutput[ship] = true;
                }
            }
            return shipsOutput;
        }
        return response;
    }

    static async getBoard(url) {
        const response = await fetch(`${url}/api/board`);
        return response;
    }

    static async confirmPlacement(url) {
        const response = await fetch(`${url}/api/confirm-placement`, {
            method: "POST"
        })
        return response;
    }

    static async fire(url, coord) {
        const requestBody = JSON.stringify({ coord })

        const response = await fetch(`${url}/api/fire`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: requestBody
        });
        return response;
    }

    static async isGameOver(url) {
        const resStarted = await fetch(`${url}/api/status/is-started`);
        const resPlaced = await fetch(`${url}/api/status/is-placed`);

        if (resPlaced.ok && resStarted.ok) {
            let isStarted = await resStarted.text();
            let isPlayed = await resPlaced.text();
            if (isStarted === "false" && isPlayed === "false") {
                return true;
            }
        } else {
            return "error";
        }
        return false;
    }
}

export default Controller;