import React, { useEffect, useState } from "react";
import Proptypes from "prop-types";
import "../../styles/logo.css";
import { IoMdExit } from "react-icons/io";
import axios from "axios";
import baseUrl from "../../AppConfig";
import { useHistory } from "react-router";
import urls from '../../constants/urls'
import { logout } from "../../utils/utils";

const HeaderComponent = ({ userRole }) => {
  let history = useHistory();

  const [nameToShow, setNameShow] = useState("");

  useEffect(() => {
    if (userRole === "ROLE_ADMIN") {
      setNameShow("Administratorius")
    } else if (userRole === "ROLE_EDUCATION_SPECIALIST") {
      setNameShow("Švietimo specialistas")
    } else if (userRole === "ROLE_GUARDIAN") {
      axios
        .get(`${baseUrl}/loggedUsername`)
        .then((res) => {
          axios.get(`${baseUrl}/api/users/${res.data}/details`)
            .then((res) => {
              setNameShow(`${res.data.personDetails.firstName} ${res.data.personDetails.lastName}`)
            })
            .catch(err => console.log(err))
        })
        .catch(err => console.log(err))
    }
  }, [userRole]);

  const handleLogout = () => {
    axios
      .get(`${baseUrl}/logout`)
      .then(() => {
        logout();
        setNameShow("");
        history.push("/");
      })
      .catch((err) => console.log(err))
  }

  return (
    <div className="templatemo-top-nav-container">
      <nav class="templatemo-top-nav col-lg-12 col-md-12">
        <ul class="text-uppercase">
          <li><button className="btn" onClick={handleLogout}>ATSIJUNGTI <IoMdExit size={20} /></button></li>
          <li>{window.location.pathname === `/kindergarten${urls.guardian.primaryDataBase}` ? <p className="lead">
          </p> : <p className="lead">
            <strong>{nameToShow}</strong>
          </p>}</li>
        </ul>
      </nav>
    </div>
  );
};

HeaderComponent.propTypes = {
  userRole: Proptypes.string.isRequired
}

export default HeaderComponent;
