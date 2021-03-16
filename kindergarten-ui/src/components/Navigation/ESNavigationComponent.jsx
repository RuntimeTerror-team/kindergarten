import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';
import logo from '../../images/logo.png';
import {
    BsListUl,
    BsListCheck,
    BsCalendar,
    BsPersonFill,
    BsListOl,
    BsGraphUp
} from 'react-icons/bs';

const ESNavigationComponent = () => {
    return (
        <div className="templatemo-sidebar">
            <div className="logo-div">
                <img src={logo} alt="logo" className="logo mx-auto" />
            </div>
            <nav className="templatemo-left-nav">
                <Link to={`${urls.educationSpecialist.kindergartenBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.kindergartenBase}` ? "active" : ""} left-nav-link`}>
                    <BsListUl className="fa" />Darželių sąrašas</Link>
                <Link to={`${urls.educationSpecialist.ageRangeBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.ageRangeBase}` ? "active" : ""} left-nav-link`}>
                    <BsListCheck className="fa" />Amžiaus grupės sukūrimas</Link>
                <Link to={`${urls.educationSpecialist.queueBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.queueBase}` ? "active" : ""} left-nav-link`}>
                    <BsCalendar className="fa" />Eilių administravimas</Link>
                <Link to={`${urls.educationSpecialist.applicationsBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.applicationsBase}` ? "active" : ""} left-nav-link`}>
                    <BsListOl className="fa" />Prašymų sąrašas</Link>
                <Link to={`${urls.educationSpecialist.queueBase}/passwordChange`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.queueBase}/passwordChange` ? "active" : ""} left-nav-link`}>
                    <BsPersonFill className="fa" />Mano paskyra</Link>
                <Link to={`${urls.educationSpecialist.ESstatisticsBase}`}
                    className={`${window.location.pathname === `/kindergarten${urls.educationSpecialist.ESstatisticsBase}` ? "active" : ""} left-nav-link`}>
                    <BsGraphUp className="fa" />Statistika</Link>
            </nav>
        </div >
    )
}

export default ESNavigationComponent



