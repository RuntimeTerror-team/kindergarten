import Axios from 'axios';
import React, { Component } from 'react';
import ServicesContext from '../../context/ServicesContext';
import baseUrl from '../../AppConfig';

import '../../styles/pages.css';
import GuardianPageComponent from './GuardianPageComponent';

class GuardianPageContainer extends Component {
    constructor() {
        super();
        this.state = {
            choice: "greeting",
            currentUser: "",
            currentUserFirstname: "",
            currentUserLastname: ""
        }
    }

    componentDidMount = () => {
        // let usernameFromBack = "";

        // Axios
        // .get(`${baseUrl}/loggedUsername`)
        // .then((res) => {
        //     usernameFromBack = res.data;
        //     this.context.userService.setCurrentUser(usernameFromBack);
        //     this.context.userService.updateCurrentUser();
        // })
        // .then(()=> {
            let currentUser = this.context.userService.getCurrentUser();
            console.log(currentUser);
            Axios
            .get(`${baseUrl}/api/users/${currentUser}`)
            .then(res => {
                this.setState({ currentUserFirstname: res.data.firstName });
                this.setState({ currentUserLastname: res.data.lastName });
            })
            .catch(err => console.log(err));
        // })
        // .then(() => {
        //     console.log(this.context.userService.getCurrentUser());
        //     console.log("guardian page");
        //     console.log(this.state.currentUserFirstname);
        // })
        // .catch(err => console.log(err));
    }

    handleUserChoice = (e) => {
        this.setState({ choice: e.target.name });
    }

    handleLogout = () => {
        this.context.userService.setCurrentUser("");
        this.context.userService.setUserRole("");
        this.context.userService.updateCurrentUser();
        this.context.userService.updateUserRole();

        this.props.history.push("/");
    }

    render() {
        if (this.context.userService.getUserRole() === "ROLE_GUARDIAN") {
            return (
                <div>
                    <GuardianPageComponent
                        handleUserChoice={this.handleUserChoice}
                        handleLogout={this.handleLogout}
                        choice={this.state.choice}
                        currentUserFirstname={this.state.currentUserFirstname}
                        currentUserLastname={this.state.currentUserLastname}
                    />
                </div>
            )
        } else {
            return (
                <h1>Access denied</h1>
            )
        }
    }
}

GuardianPageContainer.contextType = ServicesContext;

export default GuardianPageContainer;