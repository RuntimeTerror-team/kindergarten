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
            password: "",
            usernameValidation: "",
            passwordValidation: "",
            areCredentialsIncorrect: false
        }
    }

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({ [name]: value });

        if (this.state.usernameValidation !== "" && name === "username") {
            this.setState({ usernameValidation: "" });
        }

        if (this.state.passwordValidation !== "" && name === "password") {
            this.setState({ passwordValidation: "" });
        }

        if (this.state.areCredentialsIncorrect) {
            this.setState({ areCredentialsIncorrect: false });
        }
    }

    resetState = () => {
        this.setState({ username: "" });
        this.setState({ password: "" });
        this.setState({ usernameValidation: "" });
        this.setState({ passwordValidation: "" });
        this.setState({ incorrectCredentials: false });
    }

    handleSubmit = (e) => {
        e.preventDefault();


        let roleFromBack = "";
        let usernameFromUser = e.target.username.value;
        let passwordFromUser = e.target.password.value;


        this.doValidation(usernameFromUser, passwordFromUser);

        if (usernameFromUser.trim().length !== 0 && passwordFromUser.trim().length !== 0) {

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

                       // this.resetState();
                    } else {
                        this.setState({ areCredentialsIncorrect: true });
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
               this.resetState();
        }
    }

    doValidation = (username, password) => {
        if (username.trim().length === 0) {
            this.setState({ usernameValidation: "is-invalid" });
        }

        if (password.trim().length === 0) {
            this.setState({ passwordValidation: "is-invalid" });
        }
    }

    render() {
        return (
            <div id="loginPage" className="justify-content-center align-items-center">
                <h1 className="text-center text-info pt-4">Vaikų darželių informacinė sistema</h1>
                <LoginComponent
                    username={this.state.username}
                    password={this.state.password}
                    usernameValidation={this.state.usernameValidation}
                    passwordValidation={this.state.passwordValidation}
                    areCredentialsIncorrect={this.state.areCredentialsIncorrect}
                    onSubmit={this.handleSubmit}
                    onUsernameChange={this.handleChange}
                    onPasswordChange={this.handleChange}
                />
            </div>
        )
    }

}

LoginContainer.contextType = ServicesContext;

export default withRouter(LoginContainer);