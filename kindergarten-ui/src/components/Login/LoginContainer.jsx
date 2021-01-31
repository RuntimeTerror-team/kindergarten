import Axios from 'axios';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import LoginComponent from './LoginComponent';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";

Axios.defaults.withCredentials = true;

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


            let userData = new URLSearchParams();
            userData.append('username', this.state.username);
            userData.append('password', this.state.password);

            Axios
                .post(`${baseUrl}/login`,
                    userData,
                    { headers: { 'Content-type': 'application/x-www-form-urlencoded' } })
                .then((res) => {
                    console.log(res.data);
                    console.log("user " + res.data.username + " logged in")
                    this.context.userService.setCurrentUser(res.data.username);
                    this.context.userService.updateCurrentUser();
                    this.resetState();
                })
                .then(()=>{
                    Axios
                    .get(`${baseUrl}/loggedRole`)
                    .then((res) => {
                        console.log(res.data);
                        roleFromBack = res.data;
                        this.context.userService.setUserRole(roleFromBack);
                        this.context.userService.updateUserRole();
                    })
                    .then(() => {
                        if (this.context.userService.getUserRole() === "ROLE_ADMIN") {
                            this.props.history.push("/admin");
                        } else if (this.context.userService.getUserRole() === "ROLE_EDUCATION_SPECIALIST") {
                            this.props.history.push("/education-specialist");
                        } else if (this.context.userService.getUserRole() === "ROLE_GUARDIAN") {
                            this.props.history.push("/guardian");
                        }
                    })
                    .catch(err => console.log(err));
                })
                .catch((e) => { 
                    this.setState({ areCredentialsIncorrect: true });
                    console.log(e);
                 });

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
            <div id="loginPage" className="justify-content-center align-items-center" >
                <h1 className="text-center text-info pt-4">Darželių informacinė sistema</h1>
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