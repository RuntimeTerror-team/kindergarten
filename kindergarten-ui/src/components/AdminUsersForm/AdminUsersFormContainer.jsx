import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";
import AdminUsersFormComponent from './AdminUsersFormComponent';


class AdminUsersFormContainer extends Component {
    constructor() {

        super();
        this.state = {

            firstname: "",
            lastname: "",
            role: ""
        }
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit = (e) => {
        e.preventDefault();

        Axios
            .post(`${baseUrl}/api/users/admin`,
                {
                    firstName: e.target.firstname.value,
                    lastName: e.target.lastname.value,
                    role: e.target.role.value
                })
            .then(res => {
                console.log("username" + res.data);
            })
        .catch(err => console.log(err));

        this.setState({ firstname: "" })
        this.setState({ lastname: "" })
        this.setState({ role: "" })
    }


    render() {

        return (

            <AdminUsersFormComponent
                handleSubmit={this.handleSubmit}
                handleChange={this.handleChange}
                {...this.state}
            />

        )

    }

}

AdminUsersFormContainer.contextType = ServicesContext;

export default AdminUsersFormContainer;