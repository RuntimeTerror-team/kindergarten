import Axios from "axios";
import React, { Component } from "react";
import baseUrl from "../../AppConfig";
import ESNavigationComponent from "../Navigation/ESNavigationComponent";
import HeaderComponent from "../Header/HeaderComponent";
import ESApprovedApplicationListComponent from "./ESApprovedApplicationListComponent";
import Footer from "../Footer/Footer";

class ESApprovedApplicationListContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      applications: [],
      currentPage: 1,
      applicationsPerPage: 2,
    };
  }

  componentDidMount() {
    this.updateApplicationList(this.state.currentPage);
  }

  updateApplicationList(currentPage) {
    currentPage -= 1;
    Axios.get(baseUrl + "/api/applications/sorted/?page=" + currentPage + "&size=" + this.state.applicationsPerPage)
      .then((res) => {
        this.setState({
          applications: res.data.content,
          totalPages: res.data.totalPages,
          totalElements: res.data.totalElements,
          currentPage: res.data.number + 1,
        });
        this.translateStatus();
      })
      .catch((err) => {
        console.log(err);
      });
  }

  translateStatus() {
    this.state.applications.forEach((application) => {
      if (application.status === "SUBMITTED") {
        application.status = "Pateiktas";
        this.forceUpdate();
      } else if (application.status === "UNCOMFIRMED") {
        application.status = "Nepatvirtintas";
        this.forceUpdate();
      } else if (application.status === "REJECTED") {
        application.status = "Atmestas";
        this.forceUpdate();
      } else if (application.status === "APPROVED") {
        console.log("Approved");
        application.status = "Patvirtintas";
        this.forceUpdate();
      } else if (application.status === "WAITING") {
        application.status = "Eilėje";
        this.forceUpdate();
      }
    });
  }

  recalculateApplications = async () => {
    await Axios.post(`${baseUrl}/api/applications/recalculation`).catch((err) => console.log(err));
    this.updateApplicationList();
  };

  firstPage = () => {
    let firstPage = 1;
    if (this.state.currentPage > firstPage) {
      this.updateApplicationList(firstPage);
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      this.updateApplicationList(this.state.currentPage - prevPage);
    }
  };

  lastPage = () => {
    let condition = Math.ceil(this.state.totalElements / this.state.applicationsPerPage);
    if (this.state.currentPage < condition) {
      this.updateApplicationList(condition);
    }
  };

  nextPage = () => {
    if (this.state.currentPage < Math.ceil(this.state.totalElements / this.state.applicationsPerPage)) {
      this.updateApplicationList(this.state.currentPage + 1);
    }
  };

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    this.updateApplicationList(targetPage);
    this.setState({
      [event.target.name]: targetPage,
    });
  };

  render() {
    const { applications, currentPage, totalPages } = this.state;

    return (
      <div className="footerBottom">
        <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
        <div className="container py-4">
          <div className="row">
            <ESNavigationComponent />
            <div className="col-8">
              <h1 className="mb-5 text-center">Prašymai</h1>
              <ESApprovedApplicationListComponent
                applications={applications}
                recalculation={this.recalculateApplications}
                currentPage={currentPage}
                totalPages={totalPages}
                firstPage={this.firstPage}
                prevPage={this.prevPage}
                lastPage={this.lastPage}
                nextPage={this.nextPage}
              />
            </div>
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default ESApprovedApplicationListContainer;
