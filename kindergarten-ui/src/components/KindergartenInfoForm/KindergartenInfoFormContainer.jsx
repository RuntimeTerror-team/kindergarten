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
            kindergarten: {
                title: "",
                companyCode: "",
                address: "",
                district: "",
                postalCode: "",
                phoneNumber: "",
                email: "",
                website: "",
                id: ""
            },
            errors: {
                title: "",
                address: "",
                district: "",
                postalCode: "",
                phoneNumber: "",
                email: "",
                website: ""
            },
            message: "",
            messageStyle: "",
            isDisabled: true
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}`)
            .then((res) => {
                let kindergarten = {
                    title: res.data.title,
                    companyCode: res.data.companyCode,
                    address: res.data.address,
                    district: res.data.district,
                    postalCode: res.data.postalCode,
                    phoneNumber: res.data.phoneNumber.substr(res.data.phoneNumber.length - 8),
                    email: res.data.email,
                    website: res.data.website,
                    id: res.data.id
                }
                this.setState({ kindergarten: kindergarten })
            })
            .catch((err) => console.log(err));

        axios
            .get(`${baseUrl}/api/districts`)
            .then((res) => {
                this.setState({ districts: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleChange = ({ target: input }) => {
        const errors = { ...this.state.errors };
        const errorMessage = this.validateProperty(input);
        if (errorMessage) errors[input.name] = errorMessage;
        else delete errors[input.name];

        const kindergarten = { ...this.state.kindergarten };
        kindergarten[input.name] = input.value;
        this.setState({ kindergarten, errors });

        this.setState({ message: "", messageStyle: "" });
    }

    validateProperty = ({ name, value }) => {
        if (name === "title") {
            if (value.trim().length < 8 || value.trim().length > 35)
                return "is-invalid";
        }

        if (name === "companyCode") {
            if (value.trim().length !== 7 && value.trim().length !== 9)
                return "is-invalid";
        }

        if (name === "address") {
            if (value.trim().length < 8 || value.trim().length > 50)
                return "is-invalid";
        }

        if (name === "district") {
            if (value === "Pasirinkti..." || value === "")
                return "is-invalid";
        }

        if (name === "postalCode") {
            if (value.trim().length !== 5)
                return "is-invalid";
        }

        if (name === "phoneNumber") {
            if (value.trim().length !== 8)
                return "is-invalid";
        }

        if (name === "email") {
            const re = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/g;
            if (!re.test(value) && value.trim() !== "")
                return "is-invalid";
        }

        if (name === "website") {
            const re = /^((https?):\/\/)?([w|W]{3}\.)+[a-zA-Z0-9\-.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/;
            if (!re.test(value) && value.trim() !== "")
                return "is-invalid";
        }
    }

    validate = () => {
        const errors = {};
        const reEmail = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/g;
        const reWebsite = /^((https?):\/\/)?([w|W]{3}\.)+[a-zA-Z0-9\-.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/;

        const { kindergarten } = this.state;
        if (kindergarten.title.trim() === ''
            || kindergarten.title.trim().length < 8
            || kindergarten.title.trim().length > 35)
            errors.title = "is-invalid"

        if (kindergarten.district === '' || kindergarten.district === 'Pasirinkti...')
            errors.district = "is-invalid"

        if (kindergarten.address.trim() === ''
            || kindergarten.address.trim().length < 8
            || kindergarten.address.trim().length > 50)
            errors.address = "is-invalid"

        if (kindergarten.postalCode.trim() === '' || kindergarten.postalCode.trim().length !== 5)
            errors.postalCode = "is-invalid"

        if (kindergarten.phoneNumber.trim() === '' || kindergarten.phoneNumber.trim().length !== 8)
            errors.phoneNumber = "is-invalid"

        if (!reEmail.test(kindergarten.email.trim()) && kindergarten.email.trim() !== "")
            errors.email = "is-invalid";

        if (!reWebsite.test(kindergarten.website.trim()) && kindergarten.website.trim() !== "")
            errors.website = "is-invalid";

        return Object.keys(errors).length === 0 ? null : errors;
    }

    toggleDisabled = () => {

        if (this.state.isDisabled === true) {
            this.setState({
                message: "",
                messageStyle: ""
            })
        }
        this.setState({ isDisabled: false });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const { kindergarten } = this.state;

        const errors = this.validate();
        this.setState({ errors: errors || {} });

        if (errors) {
            this.setState({ message: "Darželio informacijos atnaujinti nepavyko. Pasitikrinkite duomenis." })
            this.setState({ messageStyle: "alert alert-danger" })
            return;
        }

        axios
            .put(`${baseUrl}/api/kindergartens/${kindergarten.id}`, {
                "address": kindergarten.address,
                "city": "VILNIUS",
                "companyCode": kindergarten.companyCode,
                "district": {
                    id: typeof kindergarten.district === 'string'
                        ? kindergarten.district.split("+")[1]
                        : kindergarten.district.id,
                    title: typeof kindergarten.district === 'string'
                        ? kindergarten.district.split("+")[0]
                        : kindergarten.district.title
                },
                "email": kindergarten.email,
                "phoneNumber": "+370" + kindergarten.phoneNumber,
                "postalCode": kindergarten.postalCode,
                "title": kindergarten.title,
                "website": kindergarten.website
            })
            .then(() => {
                this.setState({ isDisabled: true });
                this.setState({ message: "Darželio informacija sėkmingai atnaujinta" })
                this.setState({ messageStyle: "alert alert-success" })
                axios
                    .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}`)
                    .then((res) => {
                        let kindergarten = {
                            title: res.data.title,
                            companyCode: res.data.companyCode,
                            address: res.data.address,
                            district: res.data.district,
                            postalCode: res.data.postalCode,
                            phoneNumber: res.data.phoneNumber.substr(res.data.phoneNumber.length - 8),
                            email: res.data.email,
                            website: res.data.website,
                            id: res.data.id
                        }
                        this.setState({ kindergarten: kindergarten })
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                if (err.response.status && err.response.status === 409) {
                    this.setState({ message: err.response.data })
                    this.setState({ messageStyle: "alert alert-danger" })
                } else {
                    console.log(err);
                }
            });
    }

    render() {
        return (
            <div className="templatemo-flex-row">
                <ESNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Darželio kontaktinė informacija</strong></h1>
                        {this.state.kindergarten !== null
                            && <KindergartenInfoFormComponent
                                districts={this.state.districts}
                                kindergarten={this.state.kindergarten}
                                errors={this.state.errors}
                                handleChange={this.handleChange}
                                handleSubmit={this.handleSubmit}
                                message={this.state.message}
                                messageStyle={this.state.messageStyle}
                                isDisabled={this.state.isDisabled}
                                toggleDisabled={this.toggleDisabled}
                            />}
                        {this.state.kindergarten === null
                            && <div>Duomenys kraunasi...</div>}
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default KindergartenInfoFormContainer;