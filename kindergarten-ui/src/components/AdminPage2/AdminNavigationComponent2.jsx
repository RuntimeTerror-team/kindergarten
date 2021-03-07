import React from 'react';
import urls from '../../constants/urls';
import logo from '../../image/logo.png'

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
                <ul>
                    <li>
                        <a href={`${urls.admin.userBase}`}
                            className={`${window.location.pathname === `/kindergarten${urls.admin.userBase}` ? "btn-active" : ""}`}>
                            Paskyrų administravimas</a>
                    </li>
                    <li>
                        <a href={`${urls.admin.districtBase}`}
                            className={`${window.location.pathname === `/kindergarten${urls.admin.districtBase}` ? "btn-active" : ""}`}>
                            Rajonų administravimas</a>
                    </li>
                    <li>
                        <a href={`${urls.admin.queueBase}`}
                            className={`${window.location.pathname === `/kindergarten${urls.admin.queueBase}` ? "btn-active" : ""}`}>
                            Eilių administravimas</a>
                    </li>
                </ul>


            </nav>
        </div>
    )
}

export default AdminNavigationComponent