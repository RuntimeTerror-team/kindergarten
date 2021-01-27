// import Axios from 'axios';
import React, { Component } from 'react';
// import baseUrl from "../../AppConfig";
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

        let firstname = e.target.firstname.value;
        let lastname = e.target.lastname.value;
        let role = e.target.role.value;

        console.log(firstname);
        console.log(lastname);
        console.log(role);

        // Axios
        // .get(`${baseUrl}/api/users/${usernameFromUser}`)
        // .then(res => {
        //     let passwordFromBack = res.data.password;
        //     roleFromBack = res.data.role;

        //     if (passwordFromUser === passwordFromBack) {
        //         this.context.userService.setCurrentUser(res.data.username);
        //         this.context.userService.setUserRole(roleFromBack);
        //         this.context.userService.updateCurrentUser();
        //         this.context.userService.updateUserRole();
        //     }
        // })
        // .then(() => {
        //     if (this.context.userService.getUserRole() === "ADMIN") {
        //         this.props.history.push("/admin");
        //     }
        // })
        // .catch(err => console.log(err));

        this.setState({ username: "" })
        this.setState({ password: "" })
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