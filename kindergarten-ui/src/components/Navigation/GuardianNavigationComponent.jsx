import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';
import logo from '../../images/logo.png';
import {
    BsListUl,
    BsFillPersonLinesFill,
    BsFiles,
    BsPersonFill,
    BsGraphUp
} from 'react-icons/bs';

const GuardianNavigationComponent = () => (
    <div className="templatemo-sidebar">
        <div className="logo-div">
            <img src={logo} alt="logo" className="logo mx-auto" />
        </div>
        <nav className="templatemo-left-nav">
            <Link to={`${urls.guardian.applicationBase}`}
                className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}` ? "active" : ""} left-nav-link`}>
                <BsListUl className="fa" />Prašymai</Link>
            <Link to={`${urls.guardian.applicationBase}/new`}
                className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/new` ? "active" : ""} left-nav-link`}>
                <BsFillPersonLinesFill className="fa" />Prašymo pildymas</Link>
            <Link to={`${urls.guardian.healthFormBase}`}
                className={`${window.location.pathname === `/kindergarten${urls.guardian.healthFormBase}` ? "active" : ""} left-nav-link`}>
                <BsFiles className="fa" />Sveikatos pažymos</Link>
            <Link to={`${urls.guardian.applicationBase}/passwordChange`}
                className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/passwordChange` ? "active" : ""} left-nav-link`}>
                <BsPersonFill className="fa" />Mano paskyra</Link>
            <Link to={`${urls.guardian.statisticsBase}`}
                className={`${window.location.pathname === `/kindergarten${urls.guardian.statisticsBase}` ? "active" : ""} left-nav-link`}>
                <BsGraphUp className="fa" />Statistika</Link>
        </nav>
    </div >
)

export default GuardianNavigationComponent;



