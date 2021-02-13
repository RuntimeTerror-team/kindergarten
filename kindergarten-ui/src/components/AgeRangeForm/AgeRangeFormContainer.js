import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import axios from 'axios';
import baseUrl from '../../AppConfig';
import HeaderComponent from "../Header/HeaderComponent";
import ESNavigationComponent from "../Navigation/ESNavigationComponent";
import Footer from "../Footer/Footer";
import AgeRangeFormComponent from "./AgeRangeFormComponent";

import "../../styles/groupsForm.css";

class AgeRangeFormContainer extends Component {
    constructor() {
        super();
        this.state = {
            groups: [],
            fromAge: "",
            toAge: "",
            fromAgeFieldValidation: "",
            toAgeFieldValidation: "",
            invalidInterval: false,
            requestMessage: "",
            messageStyle: ""
        };
    }

    componentDidMount() {

        axios
            .get(`${baseUrl}/api/ageRanges`)
            .then((res) => {
                this.setState({ groups: res.data })
            })
            .catch((err) => console.log(err));
    }



    handleChangeFromAge = (e) => {
        e.preventDefault();
        this.setState({ fromAgeFieldValidation: "" })
        this.setState({ requestMessage: "" })
        this.setState({ messageStyle: "" })
        this.setState({ invalidInterval: false })
        this.setState({ fromAge: e.target.value });
    };

    handleChangeToAge = (e) => {
        e.preventDefault();
        this.setState({ toAgeFieldValidation: "" })
        this.setState({ requestMessage: "" })
        this.setState({ messageStyle: "" })
        this.setState({ invalidInterval: false })
        this.setState({ toAge: e.target.value });
    };

    handleDelete = (e) => {


        e.preventDefault();
        this.setState({ requestMessage: "" });
        this.setState({ messageStyle: "" });
        let interval = e.target.value.split("-");

        console.log(e.target.value);
        console.log(interval);

        axios.delete(baseUrl + "/api/ageRanges/" + interval[0] + "/" + interval[1])
            .then((res) => {

                axios
                    .get(`${baseUrl}/api/ageRanges`)
                    .then((res) => {
                        this.setState({ groups: res.data })
                    })
                    .catch((err) => console.log(err));
            }

            )

            .catch((err) => console.log(err));
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.setState({ fromAgeFieldValidation: "" });
        this.setState({ toAgeFieldValidation: "" });
        this.setState({ invalidInterval: false });
        this.setState({ requestMessage: "" })
        this.setState({ messageStyle: "" })

        console.log(this.state.fromAge + " " + this.state.toAge);

        this.validate(this.state.fromAge, this.state.toAge);

        if (this.validInterval(this.state.fromAge, this.state.toAge)) {

            let ageRange = {

                minAge: this.state.fromAge,
                maxAge: this.state.toAge
            }

            axios
                .post(baseUrl + "/api/saveInterval", ageRange)
                .then(res => {

                    this.setState({ requestMessage: res.data.message })

                    if (res.data.status) {

                        this.setState({ messageStyle: "alert alert-success mt-4" })
                    } else {

                        this.setState({ messageStyle: "alert alert-danger mt-4" })
                    }

                    axios
                        .get(`${baseUrl}/api/ageRanges`)
                        .then((res) => {
                            this.setState({ groups: res.data })
                        })
                        .catch((err) => console.log(err));
                }


                )
                .catch(err => console.log(err));

        }
        this.setState({ fromAge: "" });
        this.setState({ toAge: "" });
    }

    validate = (fromAge, toAge) => {
        if (fromAge === "") {
            this.setState({ fromAgeFieldValidation: "is-invalid" });
        }

        if (toAge === "") {
            this.setState({ toAgeFieldValidation: "is-invalid" });
        }
    }

    validInterval = (fromAge, toAge) => {
        if (fromAge >= toAge && fromAge !== "" && toAge !== "") {
            this.setState({ invalidInterval: true });
            return false;
        }

        if (fromAge < toAge && fromAge !== "" && toAge !== "") {
            return true;
        } else {
            return false;
        }
    }

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                <div className="container py-4">
                    <div className="row">
                        <ESNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Amžiaus grupės sukūrimas</h1>
                            <div className="groupsRegistration">
                                <AgeRangeFormComponent
                                    groups={this.state.groups}
                                    fromAge={this.state.fromAge}
                                    toAge={this.state.toAge}
                                    fromAgeFieldValidation={this.state.fromAgeFieldValidation}
                                    toAgeFieldValidation={this.state.toAgeFieldValidation}
                                    invalidInterval={this.state.invalidInterval}
                                    requestMessage={this.state.requestMessage}
                                    messageStyle={this.state.messageStyle}
                                    onSubmit={this.handleSubmit}
                                    onDelete={this.handleDelete}
                                    onFromAgeChange={this.handleChangeFromAge}
                                    onToAgeChange={this.handleChangeToAge}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        );
    }
}

export default withRouter(AgeRangeFormContainer);
