import React from 'react';
import { Link } from 'react-router-dom';

const AdminNavigationComponent = () => {
    return (
        <nav class="nav flex-column col-4">
            <Link to="/admin/users" className="btn btn-main mb-2 w-100" name="adminUsers">
                Paskyrų administravimas
            </Link>
            <Link to="/admin/districts" className="btn btn-main mb-2 w-100" name="adminDistricts">
                Rajonų administravimas
            </Link>
        </nav>
    )
}

export default AdminNavigationComponent



