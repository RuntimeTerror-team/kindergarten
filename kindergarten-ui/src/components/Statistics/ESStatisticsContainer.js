import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig'
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import StatisticsComponent from './StatisticsComponent'
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

class StatisticsContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            popularKindergartens: {},
            applicationsStatistics: {}
        }
    }

    componentDidMount = () => {
        
        Axios
            .get(`${baseUrl}/api/kindergartens/mostPopular`)
            .then((res) => {
                this.setState({ popularKindergartens: res.data });
            })
            .catch((err) => console.log(err));

            Axios
            .get(`${baseUrl}/api/applications/statistics`)
            .then((res) => {
                this.setState({ applicationsStatistics: res.data });
            })
            .catch((err) => console.log(err));
    }

    render() {
        return (
            <div className="templatemo-flex-row">
                <ESNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Statistika</strong></h1>
                        <StatisticsComponent
                            firstPlace={this.state.popularKindergartens.firstPlace}
                            secondPlace={this.state.popularKindergartens.secondPlace}
                            thirdPlace={this.state.popularKindergartens.thirdPlace}
                            fourthPlace={this.state.popularKindergartens.fourthPlace}
                            fifthPlace={this.state.popularKindergartens.fifthPlace}
                            fromEndFirstPlace={this.state.popularKindergartens.fromEndFirstPlace}
                            fromEndSecondPlace={this.state.popularKindergartens.fromEndSecondPlace}
                            fromEndThirdPlace={this.state.popularKindergartens.fromEndThirdPlace}
                            fromEndFourthPlace={this.state.popularKindergartens.fromEndFourthPlace}
                            fromEndFifthPlace={this.state.popularKindergartens.fromEndFifthPlace}
                            firstPlaceAddress={this.state.popularKindergartens.firstPlaceAddress}
                            secondPlaceAddress={this.state.popularKindergartens.secondPlaceAddress}
                            thirdPlaceAddress={this.state.popularKindergartens.thirdPlaceAddress}
                            fourthPlaceAddress={this.state.popularKindergartens.fourthPlaceAddress}
                            fifthPlaceAddress={this.state.popularKindergartens.fifthPlaceAddress}
                            fromEndFirstPlaceAddress={this.state.popularKindergartens.fromEndFirstPlaceAddress}
                            fromEndSecondPlaceAddress={this.state.popularKindergartens.fromEndSecondPlaceAddress}
                            fromEndThirdPlaceAddress={this.state.popularKindergartens.fromEndThirdPlaceAddress}
                            fromEndFourthPlaceAddress={this.state.popularKindergartens.fromEndFourthPlaceAddress}
                            fromEndFifthPlaceAddress={this.state.popularKindergartens.fromEndFifthPlaceAddress}
                            nrOfApplications={this.state.applicationsStatistics.nrOfApplications}
                            nrOfKindergartenSpots={this.state.applicationsStatistics.nrOfKindergartenSpots}
                            nrOfWaitingApplications={this.state.applicationsStatistics.nrOfWaitingApplications}
                            nrOfApprovedApplications={this.state.applicationsStatistics.nrOfApprovedApplications}
                        />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default StatisticsContainer;