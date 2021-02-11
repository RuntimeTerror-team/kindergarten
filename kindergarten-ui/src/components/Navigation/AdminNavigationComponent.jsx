import React from 'react';
import { Link } from 'react-router-dom';

const AdminNavigationComponent = () => {
    return (
        <nav className="nav flex-column col-4">
            <Link to="/admin/users" className={`btn btn-main mb-2 w-100 ${window.location.pathname === "/kindergarten/admin/users" ? "btn-active" : ""}`}>
                Paskyrų administravimas
            </Link>
            <Link to="/admin/districts" className={`btn btn-main mb-2 w-100 ${window.location.pathname === "/kindergarten/admin/districts" ? "btn-active" : ""}`}>
                Rajonų administravimas
            </Link>
        </nav>
    )
}

export default AdminNavigationComponent



