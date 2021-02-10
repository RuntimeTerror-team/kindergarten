import React, { useEffect, useState } from "react";
import logo from "../../image/logo.png";
import "../../styles/logo.css";
import { IoMdExit } from "react-icons/io";
import axios from "axios";
import baseUrl from "../../AppConfig";
import { useHistory } from "react-router";

const HeaderComponent = () => {
  let history = useHistory();

  const [nameToShow, setNameShow] = useState("");
  const [userRole, setUserRole] = useState("");

  useEffect(() => {
    axios
      .get(`${baseUrl}/loggedRole`)
      .then((res) => {
        setUserRole(res.data);
      })
      .then(() => {
        console.log(userRole);
        if (userRole === "ROLE_ADMIN") {
          setNameShow("Administratorius")
        } else if (userRole === "ROLE_EDUCATION_SPECIALIST") {
          setNameShow("Švietimo specialistas")
        } else if (userRole === "ROLE_GUARDIAN") {
          axios
            .get(`${baseUrl}/loggedUsername`)
            .then((res) => {
              axios.get(`${baseUrl}/api/users/${res.data}`)
                .then((res) => {
                  setNameShow(`${res.data.firstName} ${res.data.lastName}`)
                })
                .catch(err => console.log(err))
            })
            .catch(err => console.log(err))
        }
      })
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
          <p className="lead">
            <strong>{nameToShow}</strong>
          </p>
          <button className="btn btn-yellow" onClick={handleLogout}>
            Atsijungti <IoMdExit size={20} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default HeaderComponent;
