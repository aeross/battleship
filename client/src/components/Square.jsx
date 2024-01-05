import React, { useState } from 'react'
import Controller from '../controllers/controller'

function Square({ url, square }) {
    const [status, setStatus] = useState("bg-sky-300");

    async function handleClick() {
        const response = await Controller.fire(url, square.coord);
        if (response.ok) {
            const fireOutcome = await response.json();
            switch (fireOutcome.locStatus) {
                case "HIT":
                    setStatus("bg-red-400");
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