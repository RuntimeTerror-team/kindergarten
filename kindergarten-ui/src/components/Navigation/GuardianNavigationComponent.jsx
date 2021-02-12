import React from 'react';
import { Link } from 'react-router-dom';

const ESNavigationComponent = () => {
    return (
        <nav className="nav flex-column col-4">
            <Link to="/guardian/applications" className={`btn btn-main mb-2 w-100 ${window.location.pathname === "/kindergarten/guardian/applications" ? "btn-active" : ""}`} >
                Prašymai
            </Link>
        </nav>
    )
}

export default ESNavigationComponent



