import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenCreationFormComponent from './KindergartenCreationFormComponent';
import baseUrl from '../../AppConfig';
import "../../styles/forms.css";
import Footer from '../Footer/Footer';
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';

class KindergartenCreationFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            title: "",
            companyCode: "",
            address: "",
            district: "",
            postalCode: "",
            phoneNo: "",
            email: "",
            website: "",
            message: "",
            messageStyle: "",
            titleValidation: "",
            companyCodeValidation: "",
            addressValidation: "",
            districtValidation: "",
            postalCodeValidation: "",
            phoneNoValidation: "",
            emailValidation: "",
            websiteValidation: ""
        }
    }

    componentDidMount = () => {
        Axios
            .get(`${baseUrl}/api/districts`)
            .then((res) => {
                this.setState({ districts: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });

        if (name === "title") {
            if (value.trim().length < 8 || value.trim().length > 35) {
                this.setState({ titleValidation: "is-invalid" })
            } else {
                this.setState({ titleValidation: "" })
            }
        }

        if (name === "companyCode") {
            if (value.trim().length === 7 || value.trim().length === 9) {
                this.setState({ companyCodeValidation: "" })
            } else {
                this.setState({ companyCodeValidation: "is-invalid" })
            }
        }

        if (name === "address") {
            if (value.trim().length >= 8 && value.trim().length <= 50) {
                this.setState({ addressValidation: "" })
            } else {
                this.setState({ addressValidation: "is-invalid" })
            }
        }

        if (name === "district") {
            if (value !== "Pasirinkti...") {
                this.setState({ districtValidation: "" })
            } else {
                this.setState({ districtValidation: "is-invalid" })
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
            const re = /^\S+@\S+$/;
            if (re.test(value) || value.trim().length === 0 || value === null) {
                this.setState({ emailValidation: "" })
            } else {
                this.setState({ emailValidation: "is-invalid" })
            }
        }

        if (name === "website") {
            const re = /^((https?):\/\/)?([w|W]{3}\.)+[a-zA-Z0-9\-.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/;
            if (re.test(value) || value.trim().length === 0 || value === null) {
                this.setState({ websiteValidation: "" })
            } else {
                this.setState({ websiteValidation: "is-invalid" })
            }
        }

        this.setState({ message: "" })
        this.setState({ messageStyle: "" })
    }

    validateBlank = () => {
        if (this.state.title.trim().length === 0) {
            this.setState({ titleValidation: "is-invalid" })
        }
        if (this.state.companyCode.trim().length === 0) {
            this.setState({ companyCodeValidation: "is-invalid" })
        }
        if (this.state.address.trim().length === 0) {
            this.setState({ addressValidation: "is-invalid" })
        }
        if (this.state.district.trim().length === 0) {
            this.setState({ districtValidation: "is-invalid" })
        }
        if (this.state.postalCode.trim().length === 0) {
            this.setState({ postalCodeValidation: "is-invalid" })
        }
        if (this.state.phoneNo.trim().length === 0) {
            this.setState({ phoneNoValidation: "is-invalid" })
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.validateBlank();

        if ((this.state.titleValidation === "" && this.state.title.trim().length !== 0)
            && (this.state.companyCodeValidation === "" && this.state.companyCode.trim().length !== 0)
            && (this.state.addressValidation === "" && this.state.address.trim().length !== 0)
            && (this.state.districtValidation === "" && this.state.district.trim().length !== 0)
            && (this.state.postalCodeValidation === "" && this.state.postalCode.trim().length !== 0)
            && (this.state.phoneNoValidation === "" && this.state.phoneNo.trim().length !== 0)
            && (this.state.emailValidation === "")
            && (this.state.websiteValidation === "")) {
            Axios
                .post(`${baseUrl}/api/kindergartens`, {
                    "address": e.target.address.value,
                    "city": "VILNIUS",
                    "companyCode": e.target.companyCode.value,
                    "district": {
                        id: e.target.district.value.split(",")[1],
                        title: e.target.district.value.split(",")[0]
                    },
                    "email": e.target.email.value,
                    "phoneNumber": "+370" + e.target.phoneNo.value,
                    "postalCode": e.target.postalCode.value,
                    "title": e.target.title.value,
                    "website": e.target.website.value
                })
                .then(() => {
                    this.setState({ message: "Darželis sėkmingai sukurtas" })
                    this.setState({ messageStyle: "alert alert-success" })
                    e.target.reset();
                })
                .catch((err) => {
                    console.log(err);
                    if (err.response.status && err.response.status === 409) {
                        this.setState({ message: err.response.data })
                        this.setState({ messageStyle: "alert alert-danger" })
                    }

                });
        } else {
            this.setState({ message: "Darželio sukurti nepavyko. Pasitikrinkite duomenis." })
            this.setState({ messageStyle: "alert alert-danger" })
        }
    }

    render() {
        console.log(window.location.pathname);
        return (
            <div>
                <div className="footerBottom">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="container py-4">
                        <div className="row">
                            <ESNavigationComponent />
                            <div className="col-8">
                                <h1 className="mb-5 text-center">Darželių sąrašas</h1>
                                <KindergartenCreationFormComponent
                                    districts={this.state.districts}
                                    handleChange={this.handleChange}
                                    handleSubmit={this.handleSubmit}
                                    stopCreatingKindergarten={this.props.stopCreatingKindergarten}
                                    otherProps={this.state}
                                    message={this.state.message}
                                    messageStyle={this.state.messageStyle}
                                    titleValidation={this.state.titleValidation}
                                    companyCodeValidation={this.state.companyCodeValidation}
                                    addressValidation={this.state.addressValidation}
                                    districtValidation={this.state.districtValidation}
                                    postalCodeValidation={this.state.postalCodeValidation}
                                    phoneNoValidation={this.state.phoneNoValidation}
                                    emailValidation={this.state.emailValidation}
                                    websiteValidation={this.state.websiteValidation}
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

export default KindergartenCreationFormContainer;