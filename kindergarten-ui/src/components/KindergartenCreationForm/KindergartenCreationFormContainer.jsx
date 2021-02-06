import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenCreationFormComponent from './KindergartenCreationFormComponent';
import baseUrl from '../../AppConfig';

class KindergartenCreationFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            kindergartens: [],
            title: "",
            companyCode: "",
            address: "",
            district: "",
            postalCode: "",
            phoneNo: "",
            email: "",
            website: "",
            failMessage: "",
            failMessageStyle: "",
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

    componentDidUpdate = () => {
        console.log("district: " + this.state.district);
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });

        console.log(value)

        if (name === "title") {
            if (value.length < 8 || value.length > 35) {
                this.setState({ titleValidation: "is-invalid" })
            } else {
                this.setState({ titleValidation: "" })
            }
        }

        if (name === "companyCode") {
            if (value.length === 7 || value.length === 9) {
                this.setState({ companyCodeValidation: "" })
            } else {
                this.setState({ companyCodeValidation: "is-invalid" })
            }
        }

        if (name === "address") {
            if (value.length >= 8 && value.length <= 50) {
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
            if (value.length === 5) {
                this.setState({ postalCodeValidation: "" })
            } else {
                this.setState({ postalCodeValidation: "is-invalid" })
            }
        }

        if (name === "phoneNo") {
            if (value.length === 8) {
                this.setState({ phoneNoValidation: "" })
            } else {
                this.setState({ phoneNoValidation: "is-invalid" })
            }
        }

        if (name === "email") {
            const re = /^\S+@\S+$/;
            if (re.test(value)) {
                this.setState({ emailValidation: "" })
            } else {
                this.setState({ emailValidation: "is-invalid" })
            }
        }

        if (name === "email") {
            const re = /^\S+@\S+$/;
            if (re.test(value) || value.length === 0 || value === null) {
                this.setState({ emailValidation: "" })
            } else {
                this.setState({ emailValidation: "is-invalid" })
            }
        }

        if (name === "website" || value.length === 0 || value === null) {
            const re = /^((https?):\/\/)?([w|W]{3}\.)+[a-zA-Z0-9\-.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/;
            if (re.test(value)) {
                this.setState({ websiteValidation: "" })
            } else {
                this.setState({ websiteValidation: "is-invalid" })
            }
        }

        this.setState({ failMessage: "" })
        this.setState({ failMessageStyle: "" })
    }

    validateBlank = () => {
        if (this.state.title.length === 0) {
            this.setState({ titleValidation: "is-invalid" })
        }
        if (this.state.companyCode.length === 0) {
            this.setState({ companyCodeValidation: "is-invalid" })
        }
        if (this.state.address.length === 0) {
            this.setState({ addressValidation: "is-invalid" })
        }
        if (this.state.district.length === 0) {
            this.setState({ districtValidation: "is-invalid" })
        }
        if (this.state.postalCode.length === 0) {
            this.setState({ postalCodeValidation: "is-invalid" })
        }
        if (this.state.phoneNo.length === 0) {
            this.setState({ phoneNoValidation: "is-invalid" })
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.validateBlank();

        Axios
            .get(`${baseUrl}/api/kindergartens`)
            .then((res) => {
                this.setState({ kindergartens: res.data })
            })
            .then(() => {
                if (this.state.kindergartens.filter(kg => +kg.companyCode === +this.state.companyCode).length === 0) {
                    if ((this.state.titleValidation === "" && this.state.title.length !== 0)
                        && (this.state.companyCodeValidation === "" && this.state.companyCode.length !== 0)
                        && (this.state.addressValidation === "" && this.state.address.length !== 0)
                        && (this.state.districtValidation === "" && this.state.district.length !== 0)
                        && (this.state.postalCodeValidation === "" && this.state.postalCode.length !== 0)
                        && (this.state.phoneNoValidation === "" && this.state.phoneNo.length !== 0)
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
                                this.props.handleUpdateKindergartenList();
                                this.props.stopCreatingKindergarten();
                            })
                            .catch((err) => {
                                console.log(err);
                                this.setState({ failMessage: "Darželio sukurti nepavyko. Pasitikrinkite duomenis." })
                                this.setState({ failMessageStyle: "alert alert-danger" })
                            });
                    } else {
                        this.setState({ failMessage: "Darželio sukurti nepavyko. Pasitikrinkite duomenis." })
                        this.setState({ failMessageStyle: "alert alert-danger" })
                    }
                } else {
                    this.setState({ failMessage: "Darželio sukurti nepavyko. Toks įmonės kodas jau egzistuoja." })
                    this.setState({ failMessageStyle: "alert alert-danger" })
                }
            })
            .catch((err) => console.log(err));
    }

    render() {
        return (
            <KindergartenCreationFormComponent
                districts={this.state.districts}
                handleChange={this.handleChange}
                handleSubmit={this.handleSubmit}
                stopCreatingKindergarten={this.props.stopCreatingKindergarten}
                otherProps={this.state}
                failMessage={this.state.failMessage}
                failMessageStyle={this.state.failMessageStyle}
                titleValidation={this.state.titleValidation}
                companyCodeValidation={this.state.companyCodeValidation}
                addressValidation={this.state.addressValidation}
                districtValidation={this.state.districtValidation}
                postalCodeValidation={this.state.postalCodeValidation}
                phoneNoValidation={this.state.phoneNoValidation}
                emailValidation={this.state.emailValidation}
                websiteValidation={this.state.websiteValidation}
            />
        )
    }
}

export default KindergartenCreationFormContainer;