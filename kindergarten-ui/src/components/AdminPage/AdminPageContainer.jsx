import React, { Component } from 'react';
import ServicesContext from '../../context/ServicesContext';

import '../../styles/pages.css';
import AdminPageComponent from './AdminPageComponent';

class AdminPageContainer extends Component {
    constructor() {
        super();
        this.state = {
            choice: "greeting",
            currentUserFirstame: "",
            currentUserLastname: ""
        }
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
        if (this.context.userService.getUserRole() === "ROLE_ADMIN") {
            return (
                <div className="pagesBackground">
                <AdminPageComponent
                    handleUserChoice={this.handleUserChoice}
                    handleLogout={this.handleLogout}
                    choice={this.state.choice}
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

AdminPageContainer.contextType = ServicesContext;

export default AdminPageContainer;