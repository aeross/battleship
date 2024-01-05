class Controller {
    static async start(url) {
        const response = await fetch(`${url}/api/start`, {
            method: "POST"
        });
        return await response.json();
    }

    static async placeShip(url, shipType, shipCoords) {
        const requestBody = JSON.stringify({ "type": shipType, "loc": shipCoords });

        const response = await fetch(`${url}/api/ship`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: requestBody
        });
        return await response.text();
    }
}

export default Controller;