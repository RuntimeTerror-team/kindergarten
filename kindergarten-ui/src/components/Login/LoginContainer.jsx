import Axios from 'axios';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import LoginComponent from './LoginComponent';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";


class LoginContainer extends Component {
    constructor() {

        super();
        this.state = {

            username: "",
            password: ""
        }
    }

    handleChangeUsername = (e) => {
        e.preventDefault();
        this.setState({ username: e.target.value })
    }

    handleChangePassword = (e) => {
        e.preventDefault();
        this.setState({ password: e.target.value })
    }

    handleSubmit = (e) => {
        e.preventDefault();

        let roleFromBack = "";
        let usernameFromUser = e.target.username.value;
        let passwordFromUser = e.target.password.value;

        Axios
            .get(`${baseUrl}/api/users/${usernameFromUser}`)
            .then(res => {
                let passwordFromBack = res.data.password;
                roleFromBack = res.data.role;

                if (passwordFromUser === passwordFromBack) {
                    this.context.userService.setCurrentUser(res.data.username);
                    this.context.userService.setUserRole(roleFromBack);
                    this.context.userService.updateCurrentUser();
                    this.context.userService.updateUserRole();
                }
            })
            .then(() => {
                if (this.context.userService.getUserRole() === "ADMIN") {
                    this.props.history.push("/admin");
                } else if (this.context.userService.getUserRole() === "EDUCATION_SPECIALIST") {
                    this.props.history.push("/education-specialist");
                } else if (this.context.userService.getUserRole() === "GUARDIAN") {
                    this.props.history.push("/guardian");
                }
            })
            .catch(err => console.log(err));

        this.setState({ username: "" })
        this.setState({ password: "" })
    }


    render() {
        return (
            <LoginComponent
                username={this.state.username}
                password={this.state.password}
                onSubmit={this.handleSubmit}
                onUsernameChange={this.handleChangeUsername}
                onPasswordChange={this.handleChangePassword}
            />
        )
    }

}

LoginContainer.contextType = ServicesContext;

export default withRouter(LoginContainer);