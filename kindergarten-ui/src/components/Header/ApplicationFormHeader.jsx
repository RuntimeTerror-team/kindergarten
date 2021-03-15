import React, { useEffect, useState } from "react";
import Proptypes from "prop-types";
import "../../styles/logo.css";
import { BsBoxArrowRight } from "react-icons/bs";
import axios from "axios";
import baseUrl from "../../AppConfig";
import { useHistory } from "react-router";
import urls from '../../constants/urls'

const ApplicationFormHeaderComponent = ({ userRole, name, surname }) => {
  let history = useHistory();

  const [nameToShow, setNameShow] = useState("");

  useEffect(() => {
    if (userRole === "ROLE_ADMIN") {
      setNameShow("Administratorius")
    } else if (userRole === "ROLE_EDUCATION_SPECIALIST") {
      setNameShow("Švietimo specialistas")
    } else if (userRole === "ROLE_GUARDIAN") {

      setNameShow(name + " " + surname)
    }
  }, [userRole, name, surname]);

  const handleLogout = () => {
    axios
      .get(`${baseUrl}/logout`)
      .then(() => {
        setNameShow("");
        history.push("/");
      })
      .catch((err) => console.log(err))
  }

  return (
    <div className="templatemo-top-nav-container">
      <div className="row">
        <nav className="templatemo-top-nav col-lg-12 col-md-12">
          <ul className="text-uppercase">
            <li><button className="btn" onClick={handleLogout}>ATSIJUNGTI <BsBoxArrowRight size={20} /></button></li>
            <li>{window.location.pathname === `/kindergarten${urls.guardian.primaryDataBase}` ? <p className="lead">
            </p> : <p className="lead mb-0">
              <strong>{nameToShow}</strong>
            </p>}</li>
          </ul>
        </nav>
      </div>
    </div>
  );
};

ApplicationFormHeaderComponent.propTypes = {
  userRole: Proptypes.string.isRequired,
  name: Proptypes.string.isRequired,
  surname: Proptypes.string.isRequired
}

export default ApplicationFormHeaderComponent;
