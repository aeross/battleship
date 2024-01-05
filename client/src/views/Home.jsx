import React from 'react'
import { Link } from 'react-router-dom'

function Home() {
    return (
        <div className="w-full min-h-screen bg-gray-50 flex flex-col justify-center items-center">
            <h1 className="m-5 text-2xl font-semibold">Battleship</h1>
            <Link
                to="/play"
                className="font-semibold rounded-lg py-2 px-5 bg-slate-200 shadow-md hover:bg-slate-300 active:bg-slate-300 active:translate-x-0.5 active:translate-y-0.5 active:shadow"
            >
                Play
            </Link>
        </div>
    )
}

export default Home