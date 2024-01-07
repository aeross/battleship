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
        const resStarted = await fetch(`${url}/api/is-started`);
        const resPlaced = await fetch(`${url}/api/is-placed`);
    }
}

export default Controller;