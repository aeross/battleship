import React, { useEffect, useState } from 'react'
import Square from './Square'
import Controller from '../controllers/controller';

function Board({ url, board }) {
    const [ships, setShips] = useState();
    useEffect(() => {
        (async () => {
            const response = await Controller.getShips(url);
            setShips(response);
        })()
    }, [])

    useEffect(() => {
        (async () => {

        })();
    }, [ships])

    return (<>
        <div className="grid grid-cols-10">
            {
                board?.map(square => {
                    return <Square key={square.coord} url={url} square={square} ship={{ ships, setShips }} />
                })
            }
        </div>
        <ul>
            {
                ships?.map(ship => {
                    return <li key={ship}>{ship}</li>
                })
            }
        </ul>
    </>)
}

export default Board