import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller';
import Square from '../components/Square';

function Battleship({ url }) {
    const [ships, setShips] = useState();
    const [board, setBoard] = useState();

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
            await Controller.placeShip(url, "Cruiser", ["E1", "E2", "E3"]);
            await Controller.placeShip(url, "Submarine", ["D3", "D4", "D5"]);
            await Controller.placeShip(url, "Destroyer", ["C1", "C2"]);
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
        <div className="w-full min-h-screen flex justify-center items-center">
            <div className="grid grid-cols-[2fr_5fr_2fr] gap-4">
                <div className=" bg-slate-100 rounded border border-slate-500 shadow-sm shadow-slate-500 flex flex-col min-w-32 p-3">
                    <h2 className="mb-2 pb-2 border-b border-slate-400 font-bold text-slate-600">SHIPS</h2>
                    {
                        ships && Object.keys(ships).map(ship => {
                            let shipStatus = ships[ship] ? "" : "line-through text-opacity-60"
                            return (<>
                                <span key={ship} className={`lowercase font-semibold my-1 text-slate-700 ${shipStatus}`}>
                                    {ship}
                                </span>
                            </>)
                        })
                    }
                </div>

                <div className="grid grid-cols-10 shadow-md border border-slate-600 rounded-md">
                    {
                        board?.map(square => {
                            return <Square key={square.coord} url={url} square={square} ship={{ ships, setShips }} />
                        })
                    }
                </div>

                <div className="bg-slate-100 rounded border border-slate-500 shadow-sm shadow-slate-500 flex flex-col min-w-32 p-3">
                    <h2 className="mb-2 pb-2 border-b border-slate-400 font-bold text-slate-600">STATS</h2>
                </div>
            </div>
        </div>
    )
}

export default Battleship