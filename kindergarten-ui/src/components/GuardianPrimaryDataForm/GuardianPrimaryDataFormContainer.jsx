import React, { Component } from 'react';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

import '../../styles/pages.css';
import GuardianPrimaryDataFormComponent from './GuardianPrimaryDataComponent';
import GuardianPrimaryInfoComponent from './GuardianPrimaryInfoComponent';

class GuardianPrimaryDataFormContainer extends Component {
    constructor() {
        super();
        this.state = {
            firstname: "",
            lastname: "",
            personalCode: "",
            phoneNo: "",
            address: "",
            city: "",
            postalCode: "",
            email: "",
            firstnameValidation: "",
            lastnameValidation: "",
            personalCodeValidation: "",
            phoneNoValidation: "",
            addressValidation: "",
            cityValidation: "",
            postalCodeValidation: "",
            emailValidation: "",
            handleSaveGuardianInfo: "",
            handleChange: "",
            message: "",
            messageStyle: ""
        }
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSaveGuardianInfo = (e) => {
        console.log(e);
    }

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_GUARDIAN" />
                <div className="container py-4">
                    <div className="row">
                        <GuardianPrimaryInfoComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Pirminių duomenų anketa</h1>
                            <GuardianPrimaryDataFormComponent
                                firstname={this.state.firstname}
                                lastname={this.state.lastname}
                                personalCode={this.state.personalCode}
                                phoneNo={this.state.phoneNo}
                                address={this.state.address}
                                city={this.state.city}
                                postalCode={this.state.postalCode}
                                email={this.state.email}
                                firstnameValidation={this.state.firstnameValidation}
                                lastnameValidation={this.state.lastnameValidation}
                                personalCodeValidation={this.state.personalCodeValidation}
                                phoneNoValidation={this.state.phoneNoValidation}
                                addressValidation={this.state.addressValidation}
                                cityValidation={this.state.cityValidation}
                                postalCodeValidation={this.state.postalCodeValidation}
                                emailValidation={this.state.emailValidation}
                                handleSaveGuardianInfo={this.handleSaveGuardianInfo}
                                handleChange={this.handleChange}
                                message={this.state.message}
                                messageStyle={this.state.messageStyle}
                            />
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default GuardianPrimaryDataFormContainer;