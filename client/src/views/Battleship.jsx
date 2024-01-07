import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller';
import Square from '../components/Square';
import { FaGun } from "react-icons/fa6";
import { GiBullseye } from "react-icons/gi";

function Battleship({ url }) {
    const [board, setBoard] = useState();
    const [ships, setShips] = useState();
    const [lastMove, setLastMove] = useState("");
    const [moves, setMoves] = useState(0);
    const [hits, setHits] = useState(0);

    const [isGameOver, setIsGameOver] = useState(false);

    useEffect(() => {
        (async () => {
            const responseStart = await Controller.start(url);
            if (responseStart.ok) {
                const board = await responseStart.json();
                setBoard(board);
            }

            // place all the ships at random
            // we have: "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"
            await Controller.placeShip(url, "Carrier", ["A1", "A2", "A3", "A4", "A5"]);
            await Controller.placeShip(url, "Battleship", ["B3", "B4", "B5", "B6"]);
            await Controller.placeShip(url, "Cruiser", ["F5", "G5", "H5"]);
            await Controller.placeShip(url, "Submarine", ["D3", "D4", "D5"]);
            await Controller.placeShip(url, "Destroyer", ["I7", "J7"]);
            await Controller.confirmPlacement(url);

            const responseShips = await Controller.getShips(url);
            setShips(responseShips);
        })();
    }, []);

    // calling setShips() in Square.jsx will trigger board re-render
    useEffect(() => {
        (async () => {
            const response = await Controller.getBoard(url);
            if (response.ok) {
                const board = await response.json();
                setBoard(board);  // trigger board re-render, which will also re-render each square
            }
        })();
    }, [ships])


    return (
        <div className="w-full min-h-screen flex justify-center items-center text-slate-600">
            <div className="grid grid-cols-[2fr_5fr_2fr] gap-4">
                <div className=" bg-slate-100 rounded border border-slate-500 shadow-sm shadow-slate-500 flex flex-col min-w-32 p-3">
                    <h2 className="mb-2 pb-2 border-b border-slate-400 font-bold">SHIPS</h2>
                    {
                        ships && Object.keys(ships).map(ship => {
                            let shipStatus = ships[ship] ? "" : "line-through text-opacity-60"
                            return (<>
                                <span key={ship} className={`lowercase font-semibold my-1 ${shipStatus}`}>
                                    {ship}
                                </span>
                            </>)
                        })
                    }
                </div>

                <div className="grid grid-cols-10 shadow-md border border-slate-600 rounded-md">
                    {
                        board?.map(square => {
                            return <Square key={square.coord} url={url} square={square} ship={{ ships, setShips }} last={{ lastMove, setLastMove }} move={{ moves, setMoves }} hit={{ hits, setHits }} />
                        })
                    }
                </div>

                <div className="bg-slate-100 rounded border border-slate-500 shadow-sm shadow-slate-500 flex flex-col justify-between min-w-32 p-3">
                    <div>
                        <h2 className="mb-2 pb-2 border-b border-slate-400 font-bold">STATS</h2>
                        {
                            lastMove &&
                            <div className={`font-bold my-2 ${lastMove.split(" ")[2]}`}>
                                {lastMove.split(" ")[0] + " - " + lastMove.split(" ")[1]}
                            </div>
                        }
                        <div className="font-semibold my-1 flex items-center gap-3">
                            <FaGun /> {moves}
                        </div>
                        <div className="font-semibold my-1 flex items-center gap-3">
                            <GiBullseye /> {hits}
                        </div>
                    </div>
                    <div>
                        {/* <h2 className="mb-2 font-bold">GAME OVER</h2> */}
                        <div className="font-semibold">score: {Math.round(moves ? (hits * 100 / moves) : 0)}</div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Battleship