import React, { Component } from 'react';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

import '../../styles/pages.css';
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
            .get(`${baseUrl}/loggedUsername`)
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
                    .then(res =>{
                        this.setState({distributedApplications: res.data});
                        this.translateFinalStatus();
                    })
                    .catch(err => {console.log(err)})    
                
            })

            .catch(err => console.log)

        
            


    }

    translateStatus() {

        this.state.applications.forEach(application => {

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
                            />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default GuardianPageContainer;