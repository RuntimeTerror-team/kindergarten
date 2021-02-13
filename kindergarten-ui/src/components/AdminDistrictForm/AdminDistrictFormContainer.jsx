import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import AdminDistrictFormComponent from './AdminDistrictFormComponent';
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

class AdminDistrictFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            districtName: "",
            updatingId: "",
            updatingTitle: "",
            titleValidation: "",
            titleValidationInUpdate: "",
            requestMessage: "",
            messageStyle: "",
            updatingMessageStyle: "",
            updatingMessage: ""
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/districts`)
            .then((res) => {
                this.setState({ districts: res.data })
            })
            .catch((err) => console.log(err));
    }

    addDistrict = (e) => {
        e.preventDefault();

        let districtName = e.target.districtName.value;
        this.setState({ requestMessage: "" });
        this.setState({ messageStyle: "" })
        this.setState({ titleValidation: "" })
        this.setState({ districtName: "" })

        if (this.validateLength(districtName)) {
            axios
                .post(`${baseUrl}/api/district`, {
                    id: 0,
                    title: districtName
                })
                .then((res) => {
                    e.target.districtName.value = "";

                    this.setState({ requestMessage: res.data.message });
                    if (!res.data.status) {

                        this.setState({ messageStyle: "alert alert-danger mt-4" })
                    } else if (res.data.status) {

                        this.setState({ messageStyle: "alert alert-success mt-4" })
                    }
                    axios
                        .get(`${baseUrl}/api/districts`)
                        .then((res) => {
                            this.setState({ districts: res.data })
                        })
                        .catch((err) => console.log(err));
                })
                .catch((err) => console.log(err));


        }
    }

    validateLength = (title) => {
        if (title.trim().length >= 5 && title.trim().length <= 20) {
            this.setState({ titleValidation: "" });
            return true;

        } else {

            this.setState({ titleValidation: "is-invalid" });
            return false;
        }
    }

    validateLengthInUpdate = (title) => {
        if (title.trim().length >= 5 && title.trim().length <= 20) {
            this.setState({ titleValidationInUpdate: "" });
        } else {
            this.setState({ titleValidationInUpdate: "is-invalid" });
        }
    }

    startUpdate = (e) => {
        if (e.target.value !== undefined) {
            this.setState({ updatingId: e.target.id });
            this.setState({ updatingTitle: e.target.value });
            this.validateLengthInUpdate(e.target.value);
            this.setState({ requestMessage: "" })
            this.setState({ messageStyle: "" })
        }
    }

    onCreatingDistrictNameChange = (e) => {
        this.validateLength(e.target.value);
        this.setState({ districtName: e.target.value });
        this.setState({ requestMessage: "" })
        this.setState({ messageStyle: "" })
        this.setState({ updatingId: "" });
        this.setState({ updatingTitle: "" });
    }

    onDistrictNameChange = (e) => {
        this.validateLengthInUpdate(e.target.value);
        this.setState({ updatingTitle: e.target.value });
        this.setState({ updatingMessage: "" })
        this.setState({ updatingMessageStyle: "" })
        this.setState({})
    }

    updateDistrict = (e) => {
        e.preventDefault();

        if (this.state.districts.filter(district => district.title.toLowerCase() === this.state.updatingTitle.toLowerCase()).trim().length === 0
            || (this.state.districts.filter(district => district.title.toLowerCase() === this.state.updatingTitle.toLowerCase()).trim().length === 1
                && +this.state.districts.filter(district => district.title.toLowerCase() === this.state.updatingTitle.toLowerCase())[0].id === +this.state.updatingId)) {
            if (this.state.titleValidationInUpdate === "") {
                axios
                    .put(`${baseUrl}/api/district/${this.state.updatingId}`, {
                        id: 0,
                        title: this.state.updatingTitle
                    })
                    .then(() =>
                        axios
                            .get(`${baseUrl}/api/districts`)
                            .then((res) => {
                                this.setState({ districts: res.data });
                            })
                            .catch((err) => console.log(err))
                    )
                    .then(() => {
                        this.setState({ updatingId: "" });
                        this.setState({ updatingTitle: "" });
                    })
                    .catch((err) => console.log(err));
            }
        } else {
            this.setState({ updatingMessage: "Toks rajonas jau yra įrašytas" })
            this.setState({ updatingMessageStyle: "alert alert-danger mt-4" })
        }
    }

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_ADMIN" />
                <div className="container py-4">
                    <div className="row">
                        <AdminNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Rajonų administravimas</h1>
                            <AdminDistrictFormComponent
                                districts={this.state.districts}
                                addDistrict={this.addDistrict}
                                updateDistrict={this.updateDistrict}
                                startUpdate={this.startUpdate}
                                updatingId={this.state.updatingId}
                                onDistrictNameChange={this.onDistrictNameChange}
                                updatingTitle={this.updatingTitle}
                                titleValidation={this.state.titleValidation}
                                onCreatingDistrictNameChange={this.onCreatingDistrictNameChange}
                                titleValidationInUpdate={this.state.titleValidationInUpdate}
                                requestMessage={this.state.requestMessage}
                                messageStyle={this.state.messageStyle}
                                updatingMessage={this.state.updatingMessage}
                                updatingMessageStyle={this.state.updatingMessageStyle}
                            />
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default AdminDistrictFormContainer;