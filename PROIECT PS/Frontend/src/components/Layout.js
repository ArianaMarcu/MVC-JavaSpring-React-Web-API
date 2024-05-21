import { Outlet } from "react-router-dom";

import React from 'react' //generate boylerplate code for my layout component

const Layout = () => {
    return (
        <main>
            <Outlet/>
        </main>
    )
}

export default Layout