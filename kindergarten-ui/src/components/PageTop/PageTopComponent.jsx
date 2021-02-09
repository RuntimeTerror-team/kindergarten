import React from "react";
import logo from "../../image/logo.png";
import "../../styles/logo.css";
import { IoMdExit } from "react-icons/io";

const PageTopComponent = ({ handleLogout, usersName }) => {
  return (
    <div className="container-fluid p-3">
      <div className="row">
        <div className="offset-1 col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3">
          <img className="logo" src={logo} alt="Logo" />
        </div>
        <div className="offset-5 col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3">
          <p className="lead">
            <strong>{usersName}</strong>
          </p>
          <button className="btn btn-yellow" onClick={handleLogout}>
            Atsijungti <IoMdExit size={20} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default PageTopComponent;
