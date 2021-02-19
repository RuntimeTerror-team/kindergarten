import axios from 'axios';
import React, { Component } from 'react';
import KindergartenInfoFormComponent from './KindergartenInfoFormComponent';
import baseUrl from '../../AppConfig';
import Footer from '../Footer/Footer';
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';

class KindergartenInfoFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            kindergarten: null
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}`)
            .then((res) => {
                this.setState({ kindergarten: res.data })
            })
            .catch((err) => console.log(err));
    }

    render() {
        if (this.state.kindergarten !== null) {
            return (
                <div>
                    <div className="footerBottom">
                        <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                        <div className="container py-4">
                            <div className="row">
                                <ESNavigationComponent />
                                <div className="col-8">
                                    <h1 className="mb-5 text-center">Darželio kontaktinė informacija</h1>
                                    <KindergartenInfoFormComponent
                                        districts={this.state.districts}
                                        kindergarten={this.state.kindergarten}
                                        resetWantsInfo={this.props.resetWantsInfo}
                                        otherProps={this.state}
                                    />
                                </div>
                            </div>
                        </div>
                        <Footer />
                    </div>
                </div>

            )
        } else {
            return (
                <div>
                    <div className="footerBottom">
                        <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                        <div className="container py-4">
                            <div className="row">
                                <ESNavigationComponent />
                                <div className="col-8">
                                    <h1 className="mb-5 text-center">Darželio kontaktinė informacija</h1>
                                    <div>Duomenys kraunasi...</div>
                                </div>
                            </div>
                        </div>
                        <Footer />
                    </div>
                </div>

            )
        }

    }
}

export default KindergartenInfoFormContainer;