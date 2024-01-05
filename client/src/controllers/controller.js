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
}

export default Controller;