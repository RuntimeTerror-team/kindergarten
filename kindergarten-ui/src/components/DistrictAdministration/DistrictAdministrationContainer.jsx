import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import DistrictAdministrationComponent from './DistrictAdministrationComponent';

class DistrictAdministrationContainer extends Component {
    constructor() {
        super();
        this.state = {
            districts: [],
            districtName: "",
            updatingId: "",
            updatingTitle: "",
            titleValidation: "",
            titleValidationInUpdate: ""
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

        this.validateLength(districtName);

        if (this.state.titleValidation === "") {
            axios
                .post(`${baseUrl}/api/district`, {
                    id: 0,
                    title: districtName
                })
                .then(() => {
                    e.target.districtName.value = "";
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
        if (title.length >= 5 && title.length <= 20) {
            this.setState({ titleValidation: "" });
        } else {
            this.setState({ titleValidation: "is-invalid" });
        }
    }

    validateLengthInUpdate = (title) => {
        if (title.length >= 5 && title.length <= 20) {
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
        }
    }

    onCreatingDistrictNameChange = (e) => {
        this.validateLength(e.target.value);
        this.setState({ districtName: e.target.value });
    }

    onDistrictNameChange = (e) => {
        this.validateLengthInUpdate(e.target.value);
        this.setState({ updatingTitle: e.target.value });
    }

    updateDistrict = (e) => {
        e.preventDefault();

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
    }

    render() {
        return (
            <DistrictAdministrationComponent
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
            />
        );
    }
}

export default DistrictAdministrationContainer;