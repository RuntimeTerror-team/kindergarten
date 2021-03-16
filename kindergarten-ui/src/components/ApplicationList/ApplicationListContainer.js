import React, { Component } from 'react';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import ApplicationListComponent from './ApplicationListComponent';
import axios from 'axios';
import baseUrl from '../../AppConfig';

class GuardianPageContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            applications: [],
            distributedApplications: [],
            queues: [],
            queueStatus: ""
        }
    }

    componentDidMount() {

        axios.get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data });
                this.setState({ queueStatus: this.state.queues[0].status });
            })
            .catch((err) => console.log(err));

        axios
            .get(`${baseUrl}/api/loggedUsername`)
            .then((res) => {
                this.setState({ username: res.data })
                axios.get(baseUrl + "/api/applications/info/" + this.state.username)
                    .then(res => {
                        this.setState({ applications: res.data });
                        this.translateStatus();
                    })
                    .catch(err => { console.log(err) })
                axios
                    .get(baseUrl + "/api/distributedApplications/info/" + this.state.username)
                    .then(res => {
                        this.setState({ distributedApplications: res.data });
                        this.translateFinalStatus();
                    })
                    .catch(err => { console.log(err) })

            })

            .catch(err => console.log)
    }

    cancelApplication = (e) => {
                axios.put(baseUrl + "/api/applications/" + e.target.id + "/REJECTED")
                .then(() =>{
                    axios.get(baseUrl + "/api/applications/info/" + this.state.username)
                            .then(res => {
                                this.setState({ applications: res.data });
                                this.translateStatus();
                            })
                            .catch(err => { console.log(err) })
                        })
                .catch((e) => console.log(e));
                this.translateStatus();

    }

    translateStatus() {

        this.state.applications.forEach(application => {

            switch (application.applicationStatus) {
                case "SUBMITTED":
                    application.applicationStatus = 'Pateiktas'
                    break;
                case "REJECTED":
                    application.applicationStatus = 'Atmestas'
                    break;
                case "APPROVED":
                    application.applicationStatus = 'Patvirtintas'
                    break;
                case "WAITING":
                    application.applicationStatus = 'Eilėje'
                    break;

                default:
                    break;
            }

            this.forceUpdate()
        })
    }

    translateFinalStatus() {

        this.state.distributedApplications.forEach(application => {

            if (application.applicationStatus === "SUBMITTED") {
                application.applicationStatus = 'Pateiktas'
                this.forceUpdate()
            }

            else if (application.applicationStatus === "REJECTED") {
                application.applicationStatus = 'Atmestas'
                this.forceUpdate()
            }

            else if (application.applicationStatus === "APPROVED") {
                application.applicationStatus = 'Patvirtintas'
                this.forceUpdate()
            }

            else if (application.applicationStatus === "WAITING") {
                application.applicationStatus = 'Eilėje'
                this.forceUpdate()
            }
        })
    }
    render() {
        return (
            <div className="templatemo-flex-row">
                <GuardianNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_GUARDIAN" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Prašymai</strong></h1>
                        <ApplicationListComponent
                            applications={this.state.applications}
                            distributedApplications={this.state.distributedApplications}
                            queueStatus={this.state.queueStatus}
                            cancelApplication={this.cancelApplication}
                            />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default GuardianPageContainer;
