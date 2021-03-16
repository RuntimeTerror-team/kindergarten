import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';
import logo from '../../images/logo.png';
import {
    BsPeopleFill,
    BsGeoAlt,
    BsUnlockFill,
    BsCalendar,
    BsReverseLayoutTextSidebarReverse
} from 'react-icons/bs';

const AdminNavigationComponent = () => {
    return (
        <div className="templatemo-sidebar">
            <div className="logo-div">
                <img src={logo} alt="logo" className="logo mx-auto" />
            </div>
            <nav className="templatemo-left-nav">
                <Link to={`${urls.admin.userBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.userBase}` ? "active" : ""} left-nav-link`}>
                    <BsPeopleFill className="fa" />Paskyrų administravimas</Link>
                <Link to={`${urls.admin.districtBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.districtBase}` ? "active" : ""} left-nav-link`}>
                    <BsGeoAlt className="fa" />Rajonų administravimas</Link>
                <Link to={`${urls.admin.permissionsBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.permissionsBase}` ? "active" : ""} left-nav-link`}>
                    <BsUnlockFill className="fa" />Leidimų suteikimas</Link>
                <Link to={`${urls.admin.queueBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.queueBase}` ? "active" : ""} left-nav-link`}>
                    <BsCalendar className="fa" />Eilių administravimas </Link>
                <Link to={`${urls.admin.loggingBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.admin.loggingBase}` ? "active" : ""} left-nav-link`}>
                    <BsReverseLayoutTextSidebarReverse className="fa" />Įvykių žurnalas </Link>
            </nav>
        </div >
    )
}

export default AdminNavigationComponent
