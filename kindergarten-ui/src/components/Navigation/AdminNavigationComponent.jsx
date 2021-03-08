import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';
import logo from '../../images/logo.png'

const AdminNavigationComponent = () => {
    return (
        <div className="templatemo-sidebar">
            <header className="templatemo-site-header">
                <img src={logo} alt="logo" className="logo img-responsive" />
            </header>
            <div className="mobile-menu-icon">
                <i className="fa fa-bars"></i>
            </div>
            <nav className="templatemo-left-nav">
                <Link to={`${urls.admin.userBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.userBase}` ? "active" : ""}`}>
                    Paskyrų administravimas</Link>
                <Link to={`${urls.admin.districtBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.districtBase}` ? "btn-active" : ""}`}>
                    Rajonų administravimas</Link>
                <Link to={`${urls.admin.queueBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.queueBase}` ? "btn-active" : ""}`}>
                    Eilių administravimas </Link>
            </nav>
        </div >
    )
}

export default AdminNavigationComponent