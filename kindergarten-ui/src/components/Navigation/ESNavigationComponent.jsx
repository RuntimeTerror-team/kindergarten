import React from 'react';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls'

const ESNavigationComponent = () => {
    return (
        <nav className="nav flex-column col-4">
            <Link to={`${urls.educationSpecialist.kindergartenBase}`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.educationSpecialist.kindergartenBase}` ? "btn-active" : ""}`} >
                Darželių sąrašas
            </Link>
            <Link to={`${urls.educationSpecialist.ageRangeBase}`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.educationSpecialist.ageRangeBase}` ? "btn-active" : ""}`} >
                Amžiaus grupės sukūrimas
            </Link>
            <Link to={`${urls.educationSpecialist.queueBase}`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.educationSpecialist.queueBase}` ? "btn-active" : ""}`}>
                Eilių administravimas
            </Link>
            <Link to={`${urls.educationSpecialist.queueBase}/passwordChange`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.educationSpecialist.queueBase}/passwordChange` ? "btn-active" : ""}`} >
                    Mano paskyra
            </Link>
        </nav>
    )
}

export default ESNavigationComponent



