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

      queues: [],
      pdf: {},
      queueStatus: "",
      permission: false,
      noPDF: false,
      statusRejected: false,
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

    Axios.get(`${baseUrl}/api/queues`)
      .then((res) => {
        this.setState({ queues: res.data });
        this.setState({ queueStatus: this.state.queues[0].status });
      })
      .catch((err) => console.log(err));

    Axios.get(baseUrl + "/api/users/ES/permission")
      .then((res) => {
        this.setState({ permission: res.data });
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

  handleStatusChange = (e, currentPage) => {
    currentPage -= 1;
    Axios.put(baseUrl + "/api/applications/" + e.target.value +  "/REJECTED")
      .then(
        Axios.get(baseUrl + "/api/applications/sorted/?page=" + currentPage + "&size=" + this.state.applicationsPerPage)
          .then((res) => {
            this.setState({ applications: res.data.content });
            this.translateStatus();
            this.updateApplicationList(this.state.currentPage);
            this.setState({statusRejected: true})
          })
          .catch((err) => {
            console.log(err);
          })
      )
      .catch((e) => console.log(e));
  };

  handleOpenPDF = (e) => {

    Axios.get(baseUrl + "/api/health-forms/singleForm/" + e.target.value)
    .then(res => {
      this.setState({pdf: res.data})

      if(this.state.pdf === ""){
        this.setState({noPDF: true})
      } else{
        this.setState({noPDF: false})
        window.open(this.state.pdf.url)
      }

    }).catch(err => console.log(err))

  }

  closeAlert = (e) => {

    this.setState({noPDF: false})
    this.setState({statusRejected: false})

  }

  render() {
    const { applications, currentPage, totalPages } = this.state;
    return (
      <div className="templatemo-flex-row">
        <ESNavigationComponent />
        <div className="templatemo-content light-gray-bg col px-0">
          <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
          <div className="templatemo-content-container">
            <h1 className="mb-5 text-center page-name"><strong>Prašymai</strong></h1>
            <ESApprovedApplicationListComponent
              applications={applications}
              recalculation={this.recalculateApplications}
              currentPage={currentPage}
              totalPages={totalPages}
              firstPage={this.firstPage}
              prevPage={this.prevPage}
              lastPage={this.lastPage}
              nextPage={this.nextPage}
              queueStatus={this.state.queueStatus}
              permission={this.state.permission}
              statusRejected={this.state.statusRejected}
              noPDF={this.state.noPDF}
              onStatusChange={this.handleStatusChange}
              onOpenPDF={this.handleOpenPDF}
              closeAlert={this.closeAlert}
            />
            <Footer />
          </div>
        </div>
      </div>
    );
  }
}

export default ESApprovedApplicationListContainer;
