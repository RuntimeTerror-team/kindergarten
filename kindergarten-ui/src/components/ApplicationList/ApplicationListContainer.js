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
        }
    }

    componentDidMount() {

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
            })

            .catch(err => console.log)


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
            // if (application.applicationStatus === "SUBMITTED") {
            //     application.applicationStatus = 'Pateiktas'
            // }

            // else if (application.applicationStatus === "REJECTED") {
            //     application.applicationStatus = 'Atmestas'
            // }

            // else if (application.applicationStatus === "APPROVED") {
            //     application.applicationStatus = 'Patvirtintas'
            // }

            // else if (application.applicationStatus === "WAITING") {
            //     application.applicationStatus = 'Eilėje'
            // }

            this.forceUpdate()
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
                            applications={this.state.applications} />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default GuardianPageContainer;