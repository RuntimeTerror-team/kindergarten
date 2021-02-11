import axios from 'axios';
import React, { Component } from 'react';
import KindergartenAdministrationComponent from './KindergartenAdministrationComponent';
import baseUrl from '../../AppConfig';
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

class KindergartenAdministrationContainer extends Component {
    constructor(props) {

        super(props);
        this.state = {
            kindergartens: [],
            isCreatingKindergarten: false,
            wantsInfo: false,
            kindergartenInfoId: "",
            wantsGroups: false
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/kindergartens`)
            .then((res) => {
                this.setState({ kindergartens: res.data })
            })
            .catch((err) => console.log(err));
    }

    startCreatingKindergarten = () => {
        this.setState({ isCreatingKindergarten: true })
    }

    stopCreatingKindergarten = () => {
        this.setState({ isCreatingKindergarten: false });
    }

    handleUpdateKindergartenList = () => {
        this.stopCreatingKindergarten();
        axios
            .get(`${baseUrl}/api/kindergartens`)
            .then((res) => {
                this.setState({ kindergartens: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleWantsInfo = (e) => {
        this.setState({ kindergartenInfoId: e.target.id });
        this.setState({ wantsInfo: true })
    }

    resetWantsInfo = () => {
        this.setState({ kindergartenInfoId: "" });
        this.setState({ wantsInfo: false })
    }

    handleWantsGroups = (e) => {
        this.setState({ kindergartenInfoId: e.target.id });
        this.setState({ wantsGroups: true })
    }

    resetWantsGroups = () => {
        this.setState({ kindergartenInfoId: "" });
        this.setState({ wantsGroups: false })
    }

    render() {
        return (

            <div>
                <div className="footerBottom">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="container py-4">
                        <div className="row">
                            <ESNavigationComponent />
                            <div className="col-8">
                                <h1 className="mb-5 text-center">Darželių sąrašas</h1>
                                <KindergartenAdministrationComponent
                                    kindergartens={this.state.kindergartens}
                                    isCreatingKindergarten={this.state.isCreatingKindergarten}
                                    startCreatingKindergarten={this.startCreatingKindergarten}
                                    stopCreatingKindergarten={this.stopCreatingKindergarten}
                                    handleUpdateKindergartenList={this.handleUpdateKindergartenList}
                                    handleWantsInfo={this.handleWantsInfo}
                                    wantsInfo={this.state.wantsInfo}
                                    kindergartenInfoId={this.state.kindergartenInfoId}
                                    handleWantsGroups={this.handleWantsGroups}
                                    wantsGroups={this.state.wantsGroups}
                                    resetWantsInfo={this.resetWantsInfo}
                                    resetWantsGroups={this.resetWantsGroups}
                                />
                            </div>
                        </div>
                    </div>
                    <Footer />
                </div>
            </div>

        )
    }

}

export default KindergartenAdministrationContainer;