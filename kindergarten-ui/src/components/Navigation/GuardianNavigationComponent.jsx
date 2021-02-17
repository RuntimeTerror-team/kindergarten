import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls'

const ESNavigationComponent = () => {
    return (
        <nav className="nav flex-column col-4">
            <Link to={`${urls.guardian.applicationBase}`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}` ? "btn-active" : ""}`} >
                Prašymai
            </Link>
            <Link to={`${urls.guardian.applicationBase}/new`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/new`
             ? "btn-active" : ""}`} >
                Prašymo pildymas
            </Link>
        </nav>
    )
}

export default ESNavigationComponent



