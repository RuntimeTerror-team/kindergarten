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
            firstnameLength: "",
            lastnameLength: "",
            isCreated: false,
            createdUsername: ""
        }
    }

    handleChange = (e) => {
        const re = /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{30,}$/;
        const { name, value } = e.target;

        if (value !== '' && !re.test(value)) {
            this.setState({ [name]: value });
        } else if (value.length === 0) {
            this.setState({ [name]: "" });
        }


        if (this.state.firstnameLength !== "" && name === "firstname") {
            this.setState({ firstnameLength: "" });
        }

        if (this.state.lastnameLength !== "" && name === "lastname") {
            this.setState({ lastnameLength: "" });
        }

        if (this.state.isCreated) {
            this.setState({ isCreated: false });
            this.setState({ createdUsername: "" });
        }
    }

    validate = (fname, lname) => {
        if (fname.trim().length < 2 || fname.trim().length > 30) {
            this.setState({ firstnameLength: "is-invalid" });
        }

        if (lname.trim().length < 2 || lname.trim().length > 30) {
            this.setState({ lastnameLength: "is-invalid" });
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

        if (typedFirstname.length >= 2 
            && typedFirstname.length <=30
            && typedLastname.length >= 2
            && typedLastname.length <=30) {
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
            </div>
        )

    }

}

AdminUserFormContainer.contextType = ServicesContext;

export default AdminUserFormContainer;