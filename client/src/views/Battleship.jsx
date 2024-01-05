import React, { useEffect, useState } from 'react'
import Controller from '../controllers/controller';
import Board from '../components/Board';

function Battleship({ url }) {
    const [board, setBoard] = useState();
    useEffect(() => {
        (async () => {
            const response = await Controller.start(url);
            if (response.ok) {
                const board = await response.json();
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
        })();
    }, []);

    return (
        <div className="w-full min-h-screen bg-gray-50 flex justify-center items-center">
            <Board url={url} board={board} />
            {/* {console.log(board)} */}
        </div>
    )
}

export default Battleship