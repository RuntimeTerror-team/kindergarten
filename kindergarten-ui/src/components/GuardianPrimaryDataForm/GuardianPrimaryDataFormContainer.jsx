import React, { Component } from 'react';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

import '../../styles/pages.css';
import GuardianPrimaryDataFormComponent from './GuardianPrimaryDataComponent';
import GuardianPrimaryInfoComponent from './GuardianPrimaryInfoComponent';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import urls from '../../constants/urls';

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
            handleSubmit: "",
            handleChange: "",
            message: "",
            messageStyle: "",
            username: ""
        }
    }

    timer = null;

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/loggedUsername`)
            .then((res) => this.setState({ username: res.data }))
            .catch((err) => console.log(err))
    }

    componentWillUnmount = () => {
        clearTimeout(this.timer);
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });

        if (name === "firstname") {
            if (value.trim().length < 2 || value.trim().length > 30) {
                this.setState({ firstnameValidation: "is-invalid" })
            } else {
                this.setState({ firstnameValidation: "" })
            }
        }

        if (name === "lastname") {
            if (value.trim().length < 2 || value.trim().length > 30) {
                this.setState({ lastnameValidation: "is-invalid" })
            } else {
                this.setState({ lastnameValidation: "" })
            }
        }

        if (name === "personalCode") {
            if (value.trim().length === 11) {
                this.setState({ personalCodeValidation: "" })
            } else {
                this.setState({ personalCodeValidation: "is-invalid" })
            }
        }

        if (name === "address") {
            if (value.trim().length >= 8 && value.trim().length <= 50) {
                this.setState({ addressValidation: "" })
            } else {
                this.setState({ addressValidation: "is-invalid" })
            }
        }

        if (name === "city") {
            if (value === "Pasirinkti...") {
                this.setState({ cityValidation: "is-invalid" })
            } else {
                this.setState({ cityValidation: "" })
            }
        }

        if (name === "postalCode") {
            if (value.trim().length === 5) {
                this.setState({ postalCodeValidation: "" })
            } else {
                this.setState({ postalCodeValidation: "is-invalid" })
            }
        }

        if (name === "phoneNo") {
            if (value.trim().length === 8) {
                this.setState({ phoneNoValidation: "" })
            } else {
                this.setState({ phoneNoValidation: "is-invalid" })
            }
        }

        if (name === "email") {
            const re = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/g;
            if (re.test(value) || value.trim().length === 0 || value === null) {
                this.setState({ emailValidation: "" })
            } else {
                this.setState({ emailValidation: "is-invalid" })
            }
        }

        this.setState({ message: "" })
        this.setState({ messageStyle: "" })
    }

    validateBlank = (e) => {
        if (this.state.firstname.trim().length === 0) {
            this.setState({ firstnameValidation: "is-invalid" })
        }
        if (this.state.lastname.trim().length === 0) {
            this.setState({ lastnameValidation: "is-invalid" })
        }
        if (this.state.personalCode.trim().length === 0) {
            this.setState({ personalCodeValidation: "is-invalid" })
        }
        if (this.state.address.trim().length === 0) {
            this.setState({ addressValidation: "is-invalid" })
        }
        if (this.state.city.trim().length === 0) {
            this.setState({ cityValidation: "is-invalid" })
        }
        if (this.state.postalCode.trim().length === 0) {
            this.setState({ postalCodeValidation: "is-invalid" })
        }
        if (this.state.phoneNo.trim().length === 0) {
            this.setState({ phoneNoValidation: "is-invalid" })
        }
        if (this.state.email.trim().length === 0) {
            this.setState({ emailValidation: "is-invalid" })
        }

        this.savePerson(e);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.validateBlank(e);
    }

    savePerson = (e) => {
        if ((this.state.firstnameValidation === "" && this.state.firstname.trim().length !== 0)
            && (this.state.lastnameValidation === "" && this.state.lastname.trim().length !== 0)
            && (this.state.personalCodeValidation === "" && this.state.personalCode.trim().length !== 0)
            && (this.state.addressValidation === "" && this.state.address.trim().length !== 0)
            && (this.state.cityValidation === "" && this.state.city.trim().length !== 0)
            && (this.state.postalCodeValidation === "" && this.state.postalCode.trim().length !== 0)
            && (this.state.phoneNoValidation === "" && this.state.phoneNo.trim().length !== 0)
            && (this.state.emailValidation === "")) {
            axios
                .post(`${baseUrl}/api/persons/username`, {
                    "address": e.target.address.value,
                    "cityEnum": e.target.city.value,
                    "email": e.target.email.value,
                    "firstName": e.target.firstname.value,
                    "lastName": e.target.lastname.value,
                    "personalCode": e.target.personalCode.value,
                    "phoneNumber": "+370" + e.target.phoneNo.value,
                    "postalCode": e.target.postalCode.value,
                    "username": this.state.username
                })
                .then(() => {
                    this.setState({ message: "Jūsų duomenys sėkmingai išsaugoti" })
                    this.setState({ messageStyle: "alert alert-success" })
                    e.target.reset();
                    this.timer = setTimeout(() => {
                        this.props.history.push(urls.guardian.applicationBase)
                    }, 1000);
                })
                .catch((err) => {
                    console.log(err);
                    this.setState({ message: "Įvyko klaida. Nepavyko išsaugoti duomenų." })
                    this.setState({ messageStyle: "alert alert-danger" })
                });
        } else {
            this.setState({ message: "Duomenų išsaugoti nepavyko. Pasitikrinkite." })
            this.setState({ messageStyle: "alert alert-danger" })
        }
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
                                handleSubmit={this.handleSubmit}
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