<<<<<<< HEAD
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

=======
import React from "react";
import { Link } from "react-router-dom";
import urls from "../../constants/urls";
import positions from "../../constants/positions";

const AdminNavigationComponent = () => {
  return (
    <nav className={`nav flex-column ${positions.navigationPosition}`}>
      <Link
        to={`${urls.admin.userBase}`}
        className={`btn btn-main mb-2 w-100 ${
          window.location.pathname === `/kindergarten${urls.admin.userBase}` ? "btn-active" : ""
        }`}>
        Paskyrų administravimas
      </Link>
      <Link
        to={`${urls.admin.districtBase}`}
        className={`btn btn-main mb-2 w-100 ${
          window.location.pathname === `/kindergarten${urls.admin.districtBase}` ? "btn-active" : ""
        }`}>
        Rajonų administravimas
      </Link>
      <Link
        to={`${urls.admin.permissionsBase}`}
        className={`btn btn-main mb-2 w-100 ${
          window.location.pathname === `/kindergarten${urls.admin.permissionsBase}` ? "btn-active" : ""
        }`}>
        Leidimų suteikimas
      </Link>
      <Link
        to={`${urls.admin.queueBase}`}
        className={`btn btn-main mb-2 w-100 ${
          window.location.pathname === `/kindergarten${urls.admin.queueBase}` ? "btn-active" : ""
        }`}>
        Eilių administravimas
      </Link>
      <Link
        to={`${urls.admin.loggingBase}`}
        className={`btn btn-main mb-2 w-100 ${
          window.location.pathname === `/kindergarten${urls.admin.loggingBase}` ? "btn-active" : ""
        }`}>
        Įvykių žurnalas
      </Link>
    </nav>
  );
};

export default AdminNavigationComponent;

>>>>>>> 5737fef26870b1eed050aa4520ddca18367c7f16
