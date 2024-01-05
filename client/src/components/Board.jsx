import React from 'react'
import Square from './Square'

function Board({ url, board }) {
    return (<>
        <div className="grid grid-cols-10">
            {
                board?.map(square => {
                    return <Square key={square.coord} url={url} square={square} />
                })
            }
        </div>
    </>)
}

export default Board