import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller';
import Board from '../components/Board';
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

    useEffect(() => {
        (async () => {
            const response = await Controller.getBoard(url);
            if (response.ok) {
                const board = await response.json();
                setBoard(board);
            }
        })();
    }, [ships])

    return (
        <div className="w-full min-h-screen bg-gray-50 flex justify-center items-center">
            <div className="grid grid-cols-10">
                {
                    board?.map(square => {
                        return <Square key={square.coord} url={url} square={square} ship={{ ships, setShips }} />
                    })
                }
            </div>
            {
                // ships?.map(ship => {
                //     return <li key={ship}>{ship}</li>
                // })
                console.log(ships)
            }
        </div>
    )
}

export default Battleship