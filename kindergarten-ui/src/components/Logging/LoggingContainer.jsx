import React, { Component } from "react";
import axios from "axios";
import baseUrl from "../../AppConfig";
import AdminNavigationComponent from "../Navigation/AdminNavigationComponent";
import HeaderComponent from "../Header/HeaderComponent";
import Footer from "../Footer/Footer";
import LoggingComponent from "./LoggingComponent";

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
        <div className="templatemo-flex-row">
            <AdminNavigationComponent />
            <div className="templatemo-content light-gray-bg col px-0">
                <HeaderComponent userRole="ROLE_ADMIN" />
                <div className="templatemo-content-container">
                    <h1 className="mb-5 text-center page-name"><strong>Įvykių žurnalas</strong></h1>
                    <LoggingComponent logs={this.state.logs} />
                    <Footer />
                </div>
            </div>
        </div>
    )
  }
  
  }

export default LoggingContainer;