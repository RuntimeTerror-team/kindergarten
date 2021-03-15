import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';
import logo from '../../images/logo.png'

const AdminNavigationComponent = () => {
    return (
        <div className="templatemo-sidebar">
            <div className="logo-div">
                {/* <img src={logo} alt="logo" className="logo mx-auto" /> */}
                <div className="logo" style={{ backgroundImage: `${logo}` }}></div>
            </div>
            <div className="logo-div">
                {/* <img src={logo} alt="logo" className="logo mx-auto" /> */}
                <div className="logo-img" style={{ backgroundImage: `${logo}` }}></div>
            </div>
            <nav className="templatemo-left-nav">
                <Link to={`${urls.admin.userBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.userBase}` ? "active" : ""} left-nav-link`}>
                    Paskyrų administravimas</Link>
                <Link to={`${urls.admin.districtBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.districtBase}` ? "active" : ""} left-nav-link`}>
                    Rajonų administravimas</Link>
                <Link to={`${urls.admin.permissionsBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.permissionsBase}` ? "active" : ""} left-nav-link`}>
                    Leidimų suteikimas</Link>
                <Link to={`${urls.admin.queueBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.queueBase}` ? "active" : ""} left-nav-link`}>
                    Eilių administravimas </Link>
                <Link to={`${urls.admin.loggingBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.loggingBase}` ? "active" : ""} left-nav-link`}>Įvykių žurnalas </Link>
            </nav>
        </div >
    )
}

export default AdminNavigationComponent
