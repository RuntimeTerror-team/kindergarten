import React from 'react';
import { Link } from 'react-router-dom';

const ESNavigationComponent = () => {
    return (
        <nav className="nav flex-column col-4">
            <Link to="/education-specialist/kindergartens" className={`btn btn-main mb-2 w-100 ${window.location.pathname === "/kindergarten/education-specialist/kindergartens" ? "btn-active" : ""}`} >
                Darželių sąrašas
            </Link>
            <Link to="/education-specialist/age-ranges" className={`btn btn-main mb-2 w-100 ${window.location.pathname === "/kindergarten/education-specialist/age-ranges" ? "btn-active" : ""}`} >
                Amžiaus grupės sukūrimas
            </Link>
        </nav>
    )
}

export default ESNavigationComponent



