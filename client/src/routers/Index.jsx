import { createBrowserRouter } from "react-router-dom";
import Home from "../views/Home";
import Battleship from "../views/Battleship";

const BASE_URL = "http://localhost:8080";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Home />
    },
    {
        path: "/play",
        element: <Battleship url={BASE_URL} />
    }
]);

export default router;