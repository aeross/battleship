import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller'

function Square({ url, square, ship }) {
    const { ships, setShips } = ship;
    const [status, setStatus] = useState("bg-sky-300");

    useEffect(() => {
        switch (square.locStatus) {
            case "HIT":
                setStatus("bg-red-400");
                break;
            case "SANK":
                setStatus("bg-red-600");
                break;
            case "MISS":
                setStatus("bg-blue-400");
                break;
        }
    }, [square])

    async function handleClick() {
        const response = await Controller.fire(url, square.coord);
        if (response.ok) {
            const fireOutcome = await response.json();
            switch (fireOutcome.locStatus) {
                case "HIT":
                    setStatus("bg-red-400");
                    break;
                case "SANK":
                    // re-fetch all ships
                    const response = await Controller.getShips(url);
                    setShips(response);
                    setStatus("bg-red-600");
                    break;
                case "MISS":
                    setStatus("bg-blue-400");
                    break;
            }
        } else {
            // show error...
            console.log(await response.text());
        }
    }

    return (
        <button
            className={`p-1 border text-center ${status}`}
            onClick={handleClick}
        >
            {square.coord}
        </button>
    )
}

export default Square