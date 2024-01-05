import React from 'react'

function Square({ square }) {
    return (
        <button
            className="p-1 border text-center"
            onClick={() => { console.log(`${square.coord} clicked`) }}
        >
            {square.coord}
        </button>
    )
}

export default Square