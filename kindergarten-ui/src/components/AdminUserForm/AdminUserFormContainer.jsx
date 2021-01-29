import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";
import AdminUserFormComponent from './AdminUserFormComponent';


class AdminUserFormContainer extends Component {
    constructor() {

        super();
        this.state = {

            firstname: "",
            lastname: "",
            role: "",
            firstnameFieldValidation: "",
            lastnameFieldValidation: "",
            isCreated: false,
            createdUsername: ""
        }
    }

    handleChange = (e) => {
        const re = /^[a-zA-Z\b]+$/;
        const { name, value } = e.target;

        if (value !== '' && re.test(value)) {
            this.setState({ [name]: value });
        } else if (value.length === 0) {
            this.setState({ [name]: "" });
        }


        if (this.state.firstnameFieldValidation !== "" && name === "firstname") {
            this.setState({ firstnameFieldValidation: "" });
        }

        if (this.state.lastnameFieldValidation !== "" && name === "lastname") {
            this.setState({ lastnameFieldValidation: "" });
        }

        if (this.state.isCreated) {
            this.setState({ isCreated: false });
            this.setState({ createdUsername: "" });
        }
    }

    validate = (fname, lname) => {
        if (fname.trim().length === 0) {
            this.setState({ firstnameFieldValidation: "is-invalid" });
        }

        if (lname.trim().length === 0) {
            this.setState({ lastnameFieldValidation: "is-invalid" });
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        if (this.state.isCreated) {
            this.setState({ isCreated: false });
            this.setState({ createdUsername: "" });
        }

        let typedFirstname = e.target.firstname.value;
        let typedLastname = e.target.lastname.value;

        this.validate(typedFirstname, typedLastname);

        if (typedFirstname.trim().length !== 0 && typedLastname.trim().length !== 0) {
            Axios
                .post(`${baseUrl}/api/users/admin`,
                    {
                        firstName: typedFirstname,
                        lastName: typedLastname,
                        role: e.target.role.value
                    })
                .then(res => {
                    this.setState({ isCreated: true });
                    this.setState({ createdUsername: res.data })
                })
                .catch(err => console.log(err));

            this.setState({ firstname: "" })
            this.setState({ lastname: "" })
            this.setState({ role: "" })
        }
    }


    render() {

        return (
            <div>
                <AdminUserFormComponent
                    handleSubmit={this.handleSubmit}
                    handleChange={this.handleChange}
                    {...this.state}
                />
                {this.state.isCreated && this.state.createdUsername.length<=30 && <div className="alert alert-success w-100 mt-4" role="alert">{`Naudotojas sukurtas. Prisijungimo vardas ir slaptažodis: ${this.state.createdUsername}`}</div>}
                {this.state.isCreated && this.state.createdUsername.length>30 && <div className="alert alert-success w-100 mt-4" role="alert">{this.state.createdUsername}</div>}
            </div>
        )

    }

}

AdminUserFormContainer.contextType = ServicesContext;

export default AdminUserFormContainer;