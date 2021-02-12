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
            kindergartenInfoId: ""
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

    handleUpdateKindergartenList = () => {
        axios
            .get(`${baseUrl}/api/kindergartens`)
            .then((res) => {
                this.setState({ kindergartens: res.data })
            })
            .catch((err) => console.log(err));
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
                                    handleUpdateKindergartenList={this.handleUpdateKindergartenList}
                                    kindergartenInfoId={this.state.kindergartenInfoId}
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