import React, { Component } from "react";
import axios from "axios";
import baseUrl from "../../AppConfig";
import AdminNavigationComponent from "../Navigation/AdminNavigationComponent";
import HeaderComponent from "../Header/HeaderComponent";
import Footer from "../Footer/Footer";
import LoggingComponent from "./LoggingComponent";
import positions from "../../constants/positions";

class LoggingContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      logs: [],
    };
  }

  componentDidMount = () => {
    axios
      .get(`${baseUrl}/api/logging`)
      .then((res) => {
        this.setState({ logs: res.data });
      })
      .catch((err) => console.log(err));
  };

  render() {
    return (
      <div className="footerBottom">
        <HeaderComponent userRole="ROLE_ADMIN" />
        <div className={`${positions.bodyContainer}`}>
          <div className="row">
            <AdminNavigationComponent />
            <div className={`${positions.userPagePosition}`}>
              <h1 className="mb-5 text-center">Įvykių žurnalas</h1>
              {<LoggingComponent logs={this.state.logs} />}
            </div>
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default LoggingContainer;
