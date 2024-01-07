import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller'

function Square({ url, square, ship, last, move, hit }) {
    const { ships, setShips } = ship;
    const { lastMove, setLastMove } = last;
    const { moves, setMoves } = move;
    const { hits, setHits } = hit;
    const [status, setStatus] = useState("bg-sky-300");

    // update square colour on every board re-render
    useEffect(() => {
        switch (square.locStatus) {
            case "HIT":
                setStatus("bg-red-400");
                break;
            case "SANK":
                setStatus("bg-red-600");
                break;
            case "MISS":
                setStatus("bg-blue-500");
                break;
        }
    }, [square])

    async function handleClick() {
        const response = await Controller.fire(url, square.coord);
        if (response.ok) {
            const fireOutcome = await response.json();
            setMoves(moves + 1);
            switch (fireOutcome.locStatus) {
                case "HIT":
                    setStatus("bg-red-400");
                    setLastMove(fireOutcome.coord + " " + fireOutcome.locStatus + " text-red-400");
                    setHits(hits + 1);
                    break;
                case "SANK":
                    // re-fetch all ships
                    const response = await Controller.getShips(url);
                    setShips(response);  // this will trigger board re-render
                    setStatus("bg-red-600");
                    setLastMove(fireOutcome.coord + " " + fireOutcome.locStatus + " text-red-600");
                    setHits(hits + 1);
                    break;
                case "MISS":
                    setStatus("bg-blue-500");
                    setLastMove(fireOutcome.coord + " " + fireOutcome.locStatus + " text-blue-500");
                    break;
            }
        } else {
            // show error...
            console.log(await response.text());
        }
    }

    // apply rounded property on board edges
    let rounded = "";
    switch (square.coord) {
        case "A1":
            rounded = "rounded-tl";
            break;
        case "A10":
            rounded = "rounded-tr";
            break;
        case "J1":
            rounded = "rounded-bl";
            break;
        case "J10":
            rounded = "rounded-br";
            break;
        default:
            break;
    }

    return (
        <button
            className={`p-1 border border-slate-600 h-10 w-10 text-center ${status} ${rounded}`}
            onClick={handleClick}
        >
        </button>
    )
}

export default Square