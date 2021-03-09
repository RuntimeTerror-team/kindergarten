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

