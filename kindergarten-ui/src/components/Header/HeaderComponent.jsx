import React, { useEffect, useState } from "react";
import Proptypes from "prop-types";
import logo from "../../image/logo.png";
import "../../styles/logo.css";
import { IoMdLogOut } from "react-icons/io";
import axios from "axios";
import baseUrl from "../../AppConfig";
import { useHistory } from "react-router";
import urls from '../../constants/urls'

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
        setNameShow("");
        history.push("/");
      })
      .catch((err) => console.log(err))
  }

  return (
    <div className="container-fluid p-3">
      <div className="row">
        <div className="offset-1 col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3">
          <img className="logo" src={logo} alt="Logo" />
        </div>
        <div className="offset-5 col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3">
          {window.location.pathname === `/kindergarten${urls.guardian.primaryDataBase}` ? <p className="lead">
          </p> : <p className="lead">
              <strong>{nameToShow}</strong>
            </p>}
          <button className="btn btn-yellow" onClick={handleLogout}>
            Atsijungti <IoMdLogOut size={20} />
          </button>
        </div>
      </div>
    </div>
  );
};

HeaderComponent.propTypes = {
  userRole: Proptypes.string.isRequired
}

export default HeaderComponent;
